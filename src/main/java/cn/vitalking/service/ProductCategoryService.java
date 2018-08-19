package cn.vitalking.service;

import cn.vitalking.entity.ProductCategory;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description ProductCategory Service层
 * @date 2018-08-19 21:18
 **/
public interface ProductCategoryService {


    /**
     * 根据categoryId查找目录
     * @param categoryId
     * @return
     */
    ProductCategory findById(Integer categoryId);


    /**
     * 查找所有的目录
     * @return
     */
    List<ProductCategory> findAll();


    /**
     * 根据类型查找所有的产品目录
     * @param list
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);


    /**
     * 新增或更新目录
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
