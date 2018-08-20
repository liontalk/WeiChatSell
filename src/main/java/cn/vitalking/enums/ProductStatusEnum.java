package cn.vitalking.enums;

import lombok.Getter;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品状态枚举
 * @date 2018-08-20 20:40
 **/
@Getter
public enum ProductStatusEnum {

    UP(1,"上架"),
    DOWN(0,"下架");


    /**
     * 代码信息
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;


    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
