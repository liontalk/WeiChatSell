package cn.vitalking.repository;

import cn.vitalking.entity.OrderDetail;
import cn.vitalking.entity.OrderMaster;
import cn.vitalking.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description
 * @date 2018-08-19 20:16
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单查询订单详情
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
