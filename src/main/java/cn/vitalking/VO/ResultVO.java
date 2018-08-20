package cn.vitalking.VO;

import lombok.Data;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 请求之后返回的对象数据
 * @date 2018-08-20 21:08
 **/
@Data
public class ResultVO {


    /**
     * 错误码
     */
    private int code;


    /**
     * 提示信息
     */
    private String message;


    /**
     * 返回具体内容
     */
    private Object  data;


}
