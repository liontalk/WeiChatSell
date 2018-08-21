package cn.vitalking.enums;


import lombok.Getter;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品状态枚举
 * @date 2018-08-20 20:40
 **/
@Getter
public enum PayStatusEnum {


    /**
     * 等待支付
     */
    WAIT(0,"等待支付"),

    /**
     * 支付成功
     */
    SUCCESS(0,"支付成功");

    /**
     * 取消订单
     */


    private int code;

    private String message;

    PayStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
