package cn.vitalking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 订单详情实体
 * @date 2018-08-21 21:51
 **/
@Data
@Entity
@DynamicUpdate
public class OrderDetail {


    /**
     * 详情id
     */
    @Id
    private String detailId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品图标
     */
    private String productIcon;


    /**
     * '创建时间'
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
