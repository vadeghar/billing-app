package com.mytest.billapp.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ProductSizeEnum {
	
	J_28_32(ProductTypeEnum.JEANS, 1, "28-30-32"),
	J_34_36(ProductTypeEnum.JEANS, 2, "34-36"),
	J_38(ProductTypeEnum.JEANS, 3, "38"),
	
	TSHIRT_S(ProductTypeEnum.TSHIRT,4,"S"),
	TSHIRT_M(ProductTypeEnum.TSHIRT,5,"M"),
	TSHIRT_L(ProductTypeEnum.TSHIRT,6,"L"),
	TSHIRT_X(ProductTypeEnum.TSHIRT,7,"X"),
	TSHIRT_XL(ProductTypeEnum.TSHIRT,8,"XL"),
	TSHIRT_XXL(ProductTypeEnum.TSHIRT,9,"XXL"),
	
	SHIRT_S(ProductTypeEnum.SHIRT ,10,"S"),
	SHIRT_M(ProductTypeEnum.SHIRT,11,"M"),
	SHIRT_L(ProductTypeEnum.SHIRT,12,"L"),
	SHIRT_X(ProductTypeEnum.SHIRT,13,"X"),
	SHIRT_XL(ProductTypeEnum.SHIRT,14,"XL"),
	SHIRT_XXL(ProductTypeEnum.SHIRT,15,"XXL"),
	
	CSHIRT_S(ProductTypeEnum.CASUAL_SHIRT ,16,"S"),
	CSHIRT_M(ProductTypeEnum.CASUAL_SHIRT,17,"M"),
	CSHIRT_L(ProductTypeEnum.CASUAL_SHIRT,18,"L"),
	CSHIRT_X(ProductTypeEnum.CASUAL_SHIRT,19,"X"),
	CSHIRT_XL(ProductTypeEnum.CASUAL_SHIRT,20,"XL"),
	CSHIRT_XXL(ProductTypeEnum.CASUAL_SHIRT,21,"XXL"),
	
	CP_28_32(ProductTypeEnum.CASUAL_PANT, 22, "28-30-32"),
	CP_34_36(ProductTypeEnum.CASUAL_PANT, 23, "34-36"),
	CP_38(ProductTypeEnum.CASUAL_PANT, 24, "38"),
	;

	private ProductSizeEnum(ProductTypeEnum productTypeEnum, Integer id, String size) {
		this.productTypeEnum = productTypeEnum;
		this.id = id;
		this.size = size;
	}
	private ProductTypeEnum productTypeEnum;
	private Integer id;
	private String size;
	
	public ProductTypeEnum getProductTypeEnum() {
		return productTypeEnum;
	}
	public Integer getId() {
		return id;
	}
	public String getSize() {
		return size;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static ProductSizeEnum getById(Long id) {
	    for(ProductSizeEnum e : values()) {
	        if(e.getId().equals(id)) return e;
	    }
	    return null;
	}
	
	public static Map<Integer, String> getSizesByProductType(ProductTypeEnum productTypeEnum) {
		Map<Integer, String> sizes = new LinkedHashMap<Integer, String>();
		 for(ProductSizeEnum sizeEnum : values()) {
			 if(sizeEnum.getProductTypeEnum() == productTypeEnum) {
				 sizes.put(sizeEnum.getId(), sizeEnum.getSize());
			 }
		 }
		return sizes;
	}
	
}
