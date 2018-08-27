package cn.vitalking.converter;

import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.OrderDetail;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description orderForm 转化orderDTO
 * @date 2018-08-27 22:23
 **/
@Slf4j
public class OrderForm2OrderDTOConverter {

    /**
     * OrderForm 准换为OrderDTO
     *
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setOrderId(orderForm.getOpenId());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        List<OrderDetail> list = null;
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换错误】string={}", orderForm.getItems());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        orderDTO.setList(list);
        return orderDTO;
    }

}
