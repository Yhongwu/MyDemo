package com.howard.stringutil;

import java.math.BigDecimal;
/**
 * 生成随机数的方法
 * @author Howard
 * 2017年3月15日
 */
public class RamdomUtil {
	/**
	 * 根据指定范围和位数生成随机小数
	 * @param Max 最大
	 * @param Min 最小
	 * @param count 保留小数的位数
	 * @return
	 */
	public static String getRandomFloat(float Max,float Min,int count) {
		BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);  
//        System.out.println(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入  
//                .toString());  
        return db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留2位小数并四舍五入  
                .toString();
	}
	
	
	public static void main(String[] args) {
		String randomFloat = getRandomFloat(100, 0.0F,2);
		System.out.println(randomFloat);
	}
}
