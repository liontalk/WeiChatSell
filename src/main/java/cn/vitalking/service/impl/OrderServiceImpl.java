package cn.vitalking.service.impl;

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
import org.omg.CORBA.Object;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 订单services实现
 * @date 2018-08-21 22:53
 **/
@Service
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
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO cancel(String orderId) {
        return null;
    }

    @Override
    public OrderDTO finish(String orderId) {
        return null;
    }

    @Override
    public OrderDTO paid(String orderId) {
        return null;
    }
}
