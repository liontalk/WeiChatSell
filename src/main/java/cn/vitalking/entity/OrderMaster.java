package cn.vitalking.entity;

import cn.vitalking.enums.OrderStatusEnum;
import cn.vitalking.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 订单主表
 * @date 2018-08-21 21:35
 **/
@Data
@Entity
@DynamicUpdate
public class OrderMaster {


    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家微信id
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal buyerAmount;


    /**
     * 购买地址
     */
    private String buyerAddress;

    /**
     *订单状态,默认0 新下单
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     *支付状态,默认0 未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

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


    /**
     * 忽略数据库中对应的字段
     */
    //@Transient
    //private List<OrderDetail> list;

}
