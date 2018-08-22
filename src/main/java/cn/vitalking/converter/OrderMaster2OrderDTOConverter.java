package cn.vitalking.converter;

import cn.vitalking.dto.OrderDTO;
import cn.vitalking.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 对象转换器
 * @date 2018-08-22 22:02
 **/
public class OrderMaster2OrderDTOConverter {


    public static OrderDTO conver(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }


    public static List<OrderDTO> conver(List<OrderMaster> orderMasters) {

        List<OrderDTO> list = orderMasters.stream().map(e ->
                conver(e)
        ).collect(Collectors.toList());
        return list;
    }

}
