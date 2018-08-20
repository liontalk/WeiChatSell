package cn.vitalking.service.impl;

import cn.vitalking.entity.ProductInfo;
import cn.vitalking.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {


    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productInfoService.findOne("123");
        Assert.assertNotEquals(null, productInfo);
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfoEntity = new ProductInfo();
        productInfoEntity.setProductId("234");
        productInfoEntity.setCategoryType(7);
        productInfoEntity.setProductName("鸡蛋番茄");
        productInfoEntity.setProductIcon("https://wwww.2334.com/test/12.png");
        productInfoEntity.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoEntity.setProductStock(10);
        productInfoEntity.setProductDescription("真的很好吃");
        productInfoEntity.setProductPrice(new BigDecimal(25.5));
        ProductInfo result = productInfoService.save(productInfoEntity);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> list = productInfoService.findAll(pageRequest);
        System.out.println(list.getTotalElements());
        Assert.assertNotEquals(0,list.getTotalElements());
    }


}