package cn.vitalking.controller;

import cn.vitalking.VO.ResultVO;
import cn.vitalking.converter.OrderForm2OrderDTOConverter;
import cn.vitalking.dto.OrderDTO;
import cn.vitalking.enums.OrderStatusEnum;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.form.OrderForm;
import cn.vitalking.service.OrderService;
import cn.vitalking.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 买家controller
 * @date 2018-08-27 22:02
 **/
@Controller
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    //创建订单

    @RequestMapping(value = "/create")
    @ResponseBody
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);

        }


        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }


    //订单列表

    //


}
