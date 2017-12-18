package com.test.utils;

import java.text.DecimalFormat;

/**
* description: 
*
*
* @author today zhaojintian@ediankai.com
* @date 2016年12月23日
* @Company: ediankai
*/
public class NumberUtil {
	
	
	//获取 保留n位小数
	public static float getFloat(float number,int n){
		
		if( n > 0 && n < 9 ){
			String formatString = ".";
			for( int i=1 ; i <= n ; i++ ){
				formatString += "0";
			}
			
			DecimalFormat decimalFormat=new DecimalFormat(formatString);
			String floatString = decimalFormat.format(number); 
			if( floatString != null) number = Float.parseFloat( floatString );
		}
		
		return number;
	}
	
	
}
