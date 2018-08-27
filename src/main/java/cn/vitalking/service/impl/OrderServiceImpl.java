package cn.vitalking.service.impl;

import cn.vitalking.converter.OrderMaster2OrderDTOConverter;
import cn.vitalking.dto.CartDTO;
import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.OrderDetail;
import cn.vitalking.entity.OrderMaster;
import cn.vitalking.entity.ProductInfo;
import cn.vitalking.enums.OrderStatusEnum;
import cn.vitalking.enums.PayStatusEnum;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.repository.OrderDetailRepository;
import cn.vitalking.repository.OrderMasterRepository;
import cn.vitalking.service.OrderService;
import cn.vitalking.service.ProductInfoService;
import cn.vitalking.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 订单services实现
 * @date 2018-08-21 22:53
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 1 查询商品
        List<OrderDetail> list = orderDTO.getList();
        if (list != null && list.size() > 0) {
            for (OrderDetail orderDetail : list) {
                ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
                if (productInfo == null) {
                    throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                // 计算总价
                orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                //订单入库
                BeanUtils.copyProperties(productInfo, orderDetail);
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtil.getUniqueKey());
                orderDetailRepository.save(orderDetail);
            }
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        /**
         * 返回扣库存的list
         */
        List<CartDTO> cartDTOList = orderDTO.getList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productInfoService.reduceProductInfoAmount(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> list = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(list)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setList(list);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> list = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> pageList = OrderMaster2OrderDTOConverter.conver(list.getContent());
        Page<OrderDTO> orderDTOS = new PageImpl<OrderDTO>(pageList, pageable, list.getTotalElements());
        return orderDTOS;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();


        // 1 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("取消订单:订单状态异常。orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        // 2 更新订单状态
        OrderMaster orderResult = orderMasterRepository.save(orderMaster);
        if (orderResult == null) {
            log.error("取消订单:更新订单失败。orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }


        if (CollectionUtils.isEmpty(orderDTO.getList())) {
            log.error("取消订单:订单中不存在商品。orderDTO={}", orderDTO);
        }
        // 3.修改库存
        List<CartDTO> list = orderDTO.getList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.addProductInfoAmount(list);
        // 4 如果已经支付 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }

        return orderDTO;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        // 1 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单完成】 完成状态不正确,orderId={},orderStatus={}",orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        // 2 修改订单状态
        return orderDTO;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        // 判断新订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】 订单支付状态不正确,orderId={},orderStatus={}",orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        // 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【订单支付完成】 支付状态更新失败,orderId={},orderStatus={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }
}
