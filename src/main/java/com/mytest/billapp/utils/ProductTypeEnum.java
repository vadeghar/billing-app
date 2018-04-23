package com.mytest.billapp.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ProductTypeEnum {
	
	JEANS(1,"JEANS","Jeans"),
	TSHIRT(2,"T-SHIRT","T Shirt"),
	SHIRT(2,"SHIRT","Shirt"),
	CASUAL_SHIRT(4,"CASUAL SHIRT","Casual Shirt"),
	CASUAL_PANT(5,"CASUAL PANT","Casual Pant"),
	UNKNOWN(0,"0","0");
	

	private ProductTypeEnum(Integer id, String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer id;
	public String code;
	public String description;
	
	public Integer getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public static ProductTypeEnum getById(Long id) {
	    for(ProductTypeEnum e : values()) {
	        if(e.getId().equals(id)) return e;
	    }
	    return UNKNOWN;
	}
	
	public static Map<Integer, String> getAllProductTypes() {
		Map<Integer, String> productTypes = new LinkedHashMap<Integer, String>();
		 for(ProductTypeEnum productTypeEnum : values()) {
			 productTypes.put(productTypeEnum.getId(), productTypeEnum.getCode());
		 }
		return productTypes;
	}

}
