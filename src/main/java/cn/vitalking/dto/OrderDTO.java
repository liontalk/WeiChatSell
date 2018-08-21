package cn.vitalking.dto;

import cn.vitalking.entity.OrderDetail;
import cn.vitalking.enums.OrderStatusEnum;
import cn.vitalking.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description
 * @date 2018-08-21 22:47
 **/
@Data
public class OrderDTO {


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
    private Integer orderStatus;

    /**
     *支付状态,默认0 未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 忽略数据库中对应的字段
     */
    private List<OrderDetail> list;

}
