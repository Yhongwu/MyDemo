package com.howard.springmvc.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
/**
 * 使用注解的方式格式化数据
 * 2017年12月3日
 * @author hongwu
 */
public class FormatUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	//正常数字类型
	@NumberFormat(style=Style.NUMBER,pattern="#,###")
	private int total;
	
	//百分数类型
	@NumberFormat(style=Style.PERCENT)
	private double discount;
	
	//货币类型
	@NumberFormat(style=Style.CURRENCY)
	private double money;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "FormatUser [birthday=" + birthday + ", total=" + total + ", discount=" + discount + ", money=" + money
				+ "]";
	}
	
	
}
