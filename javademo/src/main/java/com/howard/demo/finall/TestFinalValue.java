package com.howard.demo.finall;
/**
 * 测试final
 * 不能变的是引用变量 引用变量所指的内容还是可以变的
 * @author Howard
 * 2017年3月8日
 */
public class TestFinalValue {
	static final StringBuffer s = new StringBuffer("abc");
	{
		//即使s为final，但是其引用不能变，引用所指的对象内容还是可以变的
		s.append("cde");
	}
	//即使参数设置为final，还是阻止不了里面对象内容被改变
	public void testChange(final StringBuffer ss){
		ss.append("asd");
		
	}
}
