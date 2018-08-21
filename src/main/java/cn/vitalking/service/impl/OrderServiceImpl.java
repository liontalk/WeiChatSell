package cn.vitalking.service.impl;

import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.ProductInfo;
import cn.vitalking.repository.OrderMasterRepository;
import cn.vitalking.service.OrderService;
import cn.vitalking.service.ProductInfoService;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // 1 查询商品


        // 2 计算总价


        return null;
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
