package cn.vitalking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 类目实体
 * @date 2018-08-19 20:16
 **/
@Entity
@Data
@DynamicUpdate
public class ProductCategory {


    /**
     * 类目id
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;


    /**
     * 类目编号
     */
    private Integer categoryType;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
