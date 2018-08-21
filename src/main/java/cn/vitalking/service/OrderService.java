package cn.vitalking.service;

import cn.vitalking.dto.OrderDTO;
import org.springframework.data.domain.Page;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 订单service
 * @date 2018-08-21 22:49
 **/

public interface OrderService {


    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);


    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderDTO findOne(String orderId);


    /**
     * 查询订单列表
     * @param buyerOpenId
     * @param orderDTO
     * @return
     */
    Page<OrderDTO> findList(String buyerOpenId,OrderDTO orderDTO);


    /**
     * 取消订单
     * @param orderId
     * @return
     */
    OrderDTO cancel(String orderId);


    /**
     * 完成订单
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);


    /**
     * 支付订单
     * @param orderId
     * @return
     */
    OrderDTO paid(String orderId);

}
