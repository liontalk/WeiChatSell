package cn.vitalking.repository;

import cn.vitalking.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductInfoStatusTest() throws Exception {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }


    @Test
    public void saveTest() {
        ProductInfo productInfoEntity = new ProductInfo();
        productInfoEntity.setProductId("123");
        productInfoEntity.setCategoryType(7);
        productInfoEntity.setProductName("鱼香肉丝");
        productInfoEntity.setProductIcon("https://wwww.2334.com/test/12.png");
        productInfoEntity.setProductStatus(0);
        productInfoEntity.setProductStock(20);
        productInfoEntity.setProductDescription("真的很好吃");
        productInfoEntity.setProductPrice(new BigDecimal(3.5));
        ProductInfo result = productInfoRepository.save(productInfoEntity);
        Assert.assertNotEquals(null, result);
    }


}