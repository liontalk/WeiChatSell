package cn.vitalking.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 表单的验证
 * @date 2018-08-27 22:05
 **/
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message="姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message="手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message="地址必填")
    private String address;


    /**
     * 买家的openid
     */
    @NotEmpty(message="openId必填")
    private String openId;


    /**
     * 购物车
     */
    @NotEmpty(message="购物车必填")
    private String items;


}
