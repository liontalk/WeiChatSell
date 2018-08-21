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
    PRODUCT_NOT_EXIST(100, "商品不存在");


    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
