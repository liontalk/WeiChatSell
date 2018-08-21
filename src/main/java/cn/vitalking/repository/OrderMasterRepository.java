package cn.vitalking.repository;

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
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {


    /**
     * 按照买家的openid 查询所有订单
     * @param openId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String openId,Pageable pageable);


}
