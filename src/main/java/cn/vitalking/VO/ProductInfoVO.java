package cn.vitalking.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 返回前端的商品详细信息
 * @date 2018-08-20 21:18
 **/
@Data
public class ProductInfoVO {

    /**
     * 商品ID
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;


    /**
     * 商品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;


    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 商品小图
     */
    @JsonProperty("icon")
    private String productIcon;

}
