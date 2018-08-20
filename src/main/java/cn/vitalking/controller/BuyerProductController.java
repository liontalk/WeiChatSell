package cn.vitalking.controller;

import cn.vitalking.VO.ProductInfoVO;
import cn.vitalking.VO.ProductVO;
import cn.vitalking.VO.ResultVO;
import cn.vitalking.entity.ProductCategory;
import cn.vitalking.entity.ProductInfo;
import cn.vitalking.service.ProductCategoryService;
import cn.vitalking.service.ProductInfoService;
import cn.vitalking.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.PortableRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 买家商品controller
 * @date 2018-08-20 21:04
 **/
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {


    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    ProductCategoryService productCategoryService;


    @RequestMapping(value = "/list")
    public ResultVO list() {

        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        List<Integer> integerList = new ArrayList<>();
        if (productInfoList != null && productInfoList.size() > 0) {
            for (ProductInfo productInfo : productInfoList) {
                integerList.add(productInfo.getCategoryType());
            }
        }
        List<ProductVO> productVOList = new ArrayList<>();

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(integerList);
        if (productCategoryList != null && productCategoryList.size() > 0) {
            for (ProductCategory category : productCategoryList) {
                ProductVO productVO = new ProductVO();
                productVO.setCategoryName(category.getCategoryName());
                productVO.setCategoryType(category.getCategoryType());
                List<ProductInfoVO> productInfoVOList = new ArrayList<>();
               if (productInfoList != null && productInfoList.size() > 0) {
                    for (ProductInfo productInfo : productInfoList) {
                        if (category.getCategoryType().equals(productInfo.getCategoryType())) {
                            ProductInfoVO productInfoVO = new ProductInfoVO();
                            /*productInfoVO.setProductName(productInfo.getProductName());
                            productInfoVO.setProductId(productInfo.getProductId());
                            productInfoVO.setProductDescription(productInfo.getProductDescription());
                            productInfoVO.setProductIcon(productInfo.getProductIcon());
                            productInfoVO.setProductPrice(productInfo.getProductPrice());*/
                            BeanUtils.copyProperties(productInfo,productInfoVO);
                            productInfoVOList.add(productInfoVO);
                        }
                    }
                   productVO.setFoods(productInfoVOList);
                   productVOList.add(productVO);
                }

            }
        }
        return ResultVOUtil.success(productVOList);
    }


}
