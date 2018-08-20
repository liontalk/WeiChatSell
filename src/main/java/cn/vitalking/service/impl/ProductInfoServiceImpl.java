package cn.vitalking.service.impl;

import cn.vitalking.entity.ProductInfo;
import cn.vitalking.enums.ProductStatusEnum;
import cn.vitalking.repository.ProductInfoRepository;
import cn.vitalking.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 商品service实现
 * @date 2018-08-20 20:30
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productID) {
        return productInfoRepository.findById(productID).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }
}
