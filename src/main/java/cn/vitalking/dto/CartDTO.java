package cn.vitalking.dto;

import lombok.Data;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 库存DTO
 * @date 2018-08-22 20:33
 **/
@Data
public class CartDTO {


    /**
     * 产品id
     */
    private String productId;


    /**
     * 数量
     */
    private Integer amount;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
