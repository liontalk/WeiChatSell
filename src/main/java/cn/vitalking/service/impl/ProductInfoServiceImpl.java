package cn.vitalking.service.impl;

import cn.vitalking.dto.CartDTO;
import cn.vitalking.entity.ProductInfo;
import cn.vitalking.enums.ProductStatusEnum;
import cn.vitalking.enums.ResultEnum;
import cn.vitalking.exception.SellException;
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

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void addProductInfoAmount(List<CartDTO> list) {
        for (CartDTO dto : list) {
            ProductInfo productInfo = productInfoRepository.getOne(dto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() + dto.getAmount();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void reduceProductInfoAmount(List<CartDTO> list) {
        for (CartDTO dto : list) {
            ProductInfo productInfo = productInfoRepository.getOne(dto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - dto.getAmount();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
