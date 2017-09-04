package com.howard.demo.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/**
 * 欢聚yy 2017校招a卷编程题目
 * 现有一列表： List<ConsumInfo> list ， ConsumInfo 属性如下：
 * private long uid;
 * private long ordered;
 * private double price;
 * private Date createTime
 * 请依次根据 ConsumInfo 中的 price 、 createTime 对 list 进行倒序排序
 * 说明： ConsumInfo 可自定义 
 * 
 * 思路：实现Comparable接口
 * 2017年9月3日
 * @author hongwu
 */
public class ConsumInfoSort { //Comparator  Comparable 
	static class ConsumInfo implements Comparable<ConsumInfo> {
		private long uid;
		private long ordered;
		private double price;
		private Date createTime;
		
		public ConsumInfo() {
			 
		}
		public ConsumInfo(double price , Date createTime) {
			this.price = price;
			this.createTime = createTime;
		} 
		public long getUid() {
			return uid;
		}
		public void setUid(long uid) {
			this.uid = uid;
		}
		public long getOrdered() {
			return ordered;
		}
		public void setOrdered(long ordered) {
			this.ordered = ordered;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		@Override
		public int compareTo(ConsumInfo o) {
			if (o == null) {
				return 1;
			}
			if (o.getPrice() < price) {
				return 1;
			}else if (o.getPrice() > price) {
				return -1;
			}else if (o.getCreateTime() == null || createTime == null) {
				return 0;
			}else {
				return createTime.compareTo(o.getCreateTime());
			}
		}
		
	}
	
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
		List<ConsumInfo> list = new ArrayList<>();
		//非静态内部类要先new出外部类才可以实例化
		//这里干脆直接设置ConsumInfo为静态内部类
		list.add(new ConsumInfo(2f, fm.parse("20170819")));
		list.add(new ConsumInfo(2f,fm.parse("20170817")));
		list.add(new ConsumInfo(1f,fm.parse("20170813")));
		list.add(new ConsumInfo(5f,fm.parse("20170811")));
		list.add(new ConsumInfo(10f,fm.parse("20160811"))); 
		Collections.sort(list);
		ConsumInfo tmp;
		int size = list.size();
		int largeIndex; 
//		for(int i = 0 ; i< size/2 ; i++){
//			 tmp = list.get(i);
//			 largeIndex = size - (i + 1);
//			 list.set(i, list.get(largeIndex));
//			 list.set(largeIndex, tmp);
//		} 
//		for(ConsumInfo o : list){
//			 System.out.println(o.getPrice()+ " : " +
//			 fm.format(o.getCreateTime()));
//		} 
		
		for (int i = list.size() - 1; i >= 0 ; i -- ) {
			 System.out.println(list.get(i).getPrice()+ " : " +
			 fm.format(list.get(i).getCreateTime()));
		}
	}
}
