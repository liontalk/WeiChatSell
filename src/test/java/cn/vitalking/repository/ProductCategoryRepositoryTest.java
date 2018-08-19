package cn.vitalking.repository;

import cn.vitalking.LoggerTest;
import cn.vitalking.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);


    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Test
    public void findByIdTest() {
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        logger.info("productCategory:{}", productCategory);
    }


    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("计算机科学");
        productCategory.setCategoryType(5);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }


    @Test
    public void updateTest() {
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("物理科学");
        productCategoryRepository.save(productCategory);
    }


    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 4, 5,3);
        List<ProductCategory> categoryList = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, categoryList.size());
    }
}