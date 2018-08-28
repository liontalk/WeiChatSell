package cn.vitalking.converter;

import cn.vitalking.dto.CartDTO;
import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.OrderDetail;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.form.OrderForm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setOrderId(orderForm.getOpenId());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        List<OrderDetail> list = new ArrayList<>();
        try {
            JSONArray array = JSONArray.parseArray(orderForm.getItems());
            System.out.println(array.get(0));
            if (array != null && array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    OrderDetail orderDetail = new OrderDetail();
                    Map map= JSON.parseObject(array.get(i).toString());
                    Integer productQuantity = (Integer) map.get("productQuantity");
                    String productId = (String) map.get("productId");
                    orderDetail.setProductId(productId);
                    orderDetail.setProductQuantity(productQuantity);
                    list.add(orderDetail);
                }
            }
        } catch (Exception e) {
            log.error("【对象转换错误】string={}", orderForm.getItems());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        orderDTO.setList(list);
        log.info("获得实体 orderDTO={}",orderDTO);
        return orderDTO;
    }

/*
    public static void main(String[] args) {
        OrderForm orderForm = new OrderForm();
        orderForm.setName("zhangsan");
        orderForm.setPhone("18868822111");
        orderForm.setAddress("慕课网总部");
        orderForm.setOpenId("ew3euwhd7sjw9diwkq");
        orderForm.setItems(" [{\n" +
                "            \"productId\": \"1423113435324\",\n" +
                "            \"productQuantity\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": \"1423113435325\",\n" +
                "            \"productQuantity\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": \"1423113435326\",\n" +
                "            \"productQuantity\": 2\n" +
                "        }]");

        System.out.println("=---==========================>" + convert(orderForm));
    }*/
}
