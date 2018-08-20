package cn.vitalking.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 请求之后返回的对象数据
 * @date 2018-08-20 21:08
 **/
@Data
public class ProductVO {


    /**
     *商品名称
     */
    @JsonProperty("name")
    private String  categoryName;


    /**
     * 商品类型
     */
    @JsonProperty("type")
    private Integer categoryType;


    /**
     * 返回具体内容
     */
    private List<ProductInfoVO> foods;


}
