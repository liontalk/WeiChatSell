package cn.vitalking.service;


import cn.vitalking.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description ProductCategory Service层
 * @date 2018-08-19 21:18
 **/
public interface ProductInfoService {


    /**
     * 查询商品信息
     * @param productID
     * @return
     */
    ProductInfo findOne(String productID);


    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();


    /**
     * 新增产品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);


    /**
     * 查询所有的商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

}
