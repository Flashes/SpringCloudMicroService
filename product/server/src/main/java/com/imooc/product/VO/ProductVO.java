package com.imooc.product.VO;




import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductVO {
    /**
     * 类目名字
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 类目类别
     */
    @JsonProperty("type")
    private int categoryType;
    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }
    public List<ProductInfoVO> getProductInfoVOList(){
        return productInfoVOList;
    }
    public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList1){
        this.productInfoVOList=productInfoVOList1;
    }
}
