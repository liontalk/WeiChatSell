package cn.vitalking.enums;

import lombok.Getter;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品状态枚举
 * @date 2018-08-20 20:40
 **/
@Getter
public enum ResultEnum {
    /**
     * 商品不存在
     */
    PRODUCT_NOT_EXIST(100, "商品不存在"),


    /**
     * 商品不充足
     */
    PRODUCT_NOT_ENOUGH(101, "商品不充足"),


    /**
     * 订单详情不存在
     */
    ORDER_DETAIL_NOT_EXIST(102, "订单详情不存在"),


    /**
     * 订单状态不正确
     */
    ORDER_STATUS_ERROR(104, "订单状态不正确"),


    /**
     * 订单更新异常
     */
    ORDER_UPDATE_ERROR(105, "订单更新异常"),

    /**
     * 订单支付状态不正确
     */
    ORDER_PAY_STATUS_ERROR(106, "订单支付状态不正确"),



    /**
     * 参数不正确
     */
    PARAM_ERROR(107, "参数不正确"),

    /**
     * 购物车不能为空
     */
    CART_EMPTY(108, "购物车不能为空"),


    /**
     * 订单不存在
     */
    ORDER_NOT_EXIST(103, "订单不存在");

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
