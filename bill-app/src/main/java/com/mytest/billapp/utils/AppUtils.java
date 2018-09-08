package com.mytest.billapp.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class AppUtils {
	public static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#####.##");
	
	
	public static Double formatDecimals(Double d) {
		if(d == null) return Double.valueOf(DECIMAL_FORMAT.format(0.00));
		return Double.valueOf(DECIMAL_FORMAT.format(d.doubleValue()));
	}
	
	
	public static Double getDiscountedValue(Double totalValue, Double discountedValue, String discountType) {
		Double discount = new Double(0.0);
		if(StringUtils.isEmpty(discountType)) return discount;
		if(discountedValue == null) return discount;
		if(discountType.equals("%")) {
			discount = totalValue * (discountedValue / 100);
		}else {
			discount =  discountedValue;
		}
		return discount;
	}
	
	public static boolean isValidNonZeroLong(Object object) {
		if(object == null) return false;
		if(object instanceof Long) {
			if(((Long)object).longValue() > 0)
				return true;
		}
		return false;
	}
	
}
