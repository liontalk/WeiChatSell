package cn.vitalking.enums;

import lombok.Getter;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品状态枚举
 * @date 2018-08-20 20:40
 **/
@Getter
public enum OrderStatusEnum {

    /**
     * 新订单
     */
    NEW(0,"新订单"),

    /**
     * 完成订单
     */
    FINISHED(0,"完成订单")

    /**
     * 取消订单
     */
    ,CANCEL(0,"取消订单");

    private int code;

    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
