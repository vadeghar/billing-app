package com.mytest.billapp.utils;

import java.text.DecimalFormat;

public class Utils {
	public static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#####.##");
	
	
	public static Double formatDecimals(Double d) {
		if(d == null) return Double.valueOf(DECIMAL_FORMAT.format(0.00));
		return Double.valueOf(DECIMAL_FORMAT.format(d.doubleValue()));
	}
	
}
