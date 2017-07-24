package com.howard.demo.equal;
/**
 * 书
 * @author Howard
 * 2017年2月24日
 */
public class Book {
	
	static int i = 1;
	
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 页数
	 */
	private int  pageNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31 + i++;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pageNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pageNum != other.pageNum)
			return false;
		return true;
	}
	
}
