package cn.vitalking.repository;

import cn.vitalking.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 类目实体
 * @date 2018-08-19 20:16
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


    /**
     * 查询目录
     * @param list
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
