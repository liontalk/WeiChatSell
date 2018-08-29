package cn.vitalking.service;


import cn.vitalking.dto.OrderDTO;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 买家service
 * @date 2018-08-21 22:49
 **/
public interface BuyerService {

    /**
     * 查询订单详情
     * @param orderId
     * @param openId
     * @return
     */
    OrderDTO findOrderOne(String orderId,String openId);


    /**
     * 取消dingdn
     * @param orderId
     * @param openId
     * @return
     */
    OrderDTO cancleOrder(String orderId,String openId);

}
