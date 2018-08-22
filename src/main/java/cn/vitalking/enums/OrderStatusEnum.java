package cn.vitalking.enums;

import io.swagger.models.auth.In;
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
    FINISHED(1,"完成订单")

    /**
     * 取消订单
     */
    ,CANCEL(2,"取消订单");

    private Integer code;

    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
