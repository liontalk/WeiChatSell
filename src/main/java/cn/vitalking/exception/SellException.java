package cn.vitalking.exception;

import cn.vitalking.enums.ResultEnum;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 自定义异常
 * @date 2018-08-21 23:01
 **/
public class SellException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(ResultEnum resultEnum,String message) {
        super(message);
        this.code = resultEnum.getCode();
    }
}
