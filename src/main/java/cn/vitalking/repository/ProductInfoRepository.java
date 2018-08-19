package cn.vitalking.repository;

import cn.vitalking.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询所有商品的状态
     *
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer type);
}
