package cn.vitalking.service.impl;

import cn.vitalking.dto.OrderDTO;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.service.BuyerService;
import cn.vitalking.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 买家service实现类
 * @date 2018-08-29 22:12
 **/
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {


    @Autowired
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOne(String orderId, String openId) {
        return checkOrderDTO( orderId, openId);
    }

    @Override
    public OrderDTO cancleOrder(String orderId, String openId) {

        OrderDTO orderDTO =  checkOrderDTO(orderId,openId);
        if(orderDTO==null){
            log.error("查询订单不存在!");
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderService.cancel(orderDTO);
        return orderDTO;
    }


    private OrderDTO checkOrderDTO(String orderId, String openId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO!=null){
            if(!openId.equals(orderDTO.getBuyerOpenid())){
                log.error("[订单主人错误] openID 和订单id 不一致");
                throw  new SellException(ResultEnum.ORDER_OWNER_EMPTY);
            }
        }else{
            return null;
        }
        return orderDTO;
    }
}
