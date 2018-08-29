package cn.vitalking.controller;

import cn.vitalking.VO.ResultVO;
import cn.vitalking.converter.OrderForm2OrderDTOConverter;
import cn.vitalking.dto.OrderDTO;
import cn.vitalking.enums.OrderStatusEnum;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
import cn.vitalking.form.OrderForm;
import cn.vitalking.service.BuyerService;
import cn.vitalking.service.OrderService;
import cn.vitalking.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
@Api("用户订单管理")
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建订单", notes = "用户创建订单")
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
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "订单列表", notes = "获取订单列表")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单列表】 openId 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> pageList = orderService.findList(openId, pageRequest);
        return ResultVOUtil.success(pageList.getContent());
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "订单详情", notes = "订单详情")
    public ResultVO<List<OrderDTO>> detail(@RequestParam("openId") String openId,
                                           @RequestParam(value = "orderId") String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单详情】 openId 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单详情】 orderId 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(orderId, openId);
        return ResultVOUtil.success(orderDTO);
    }


    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "取消订单", notes = "取消订单")
    public ResultVO<List<OrderDTO>> cancel(@RequestParam("openId") String openId,
                                           @RequestParam(value = "orderId") String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单详情】 openId 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单详情】 orderId 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancleOrder(orderId, openId);
        return ResultVOUtil.success();
    }

}
