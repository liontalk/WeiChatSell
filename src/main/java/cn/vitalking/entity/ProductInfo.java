package cn.vitalking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.BindingResult;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品实体信息
 * @date 2018-08-19 21:39
 **/
@Data
@Entity
public class ProductInfo {

    /**
     * 商品id
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 商品状态 0 下架  1上架
     */
    private Integer productStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
