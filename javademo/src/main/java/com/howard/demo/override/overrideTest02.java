package com.howard.demo.override;
/**
 * 测试重写
 * 被重写的方法不能为private，否则其子类中只是新定义了一个方法，并没有重写。
 * 由下面method3可看出 D类的method3无法加上@Override注解 证明并不能被重写
 * 静态方法不能重写
 * 由下面method2可看出 D类的method2无法加上@Override注解 证明并不能被重写
 * 另 由运行结果也可以看出 静态方法并不能重写 并不能实现多态
 * @author Howard
 * 2017年2月9日
 */
public class overrideTest02 {
	public static void main(String[] args) {
		C test01 = new C();
		C test02 = new D();
		
		test01.method1();
		test02.method1();
		
		test01.method2();
		test02.method2();
		
	}
}
class C{
	public void method1(){
		System.out.println("C 类的方法method1");
	}
	public static void method2(){
		System.out.println("C 类的方法method2");
	}
	private void method3(){
		System.out.println("C 类的方法method3");
	}
}

class D extends C{
	//重写
	@Override
	public void method1(){
		System.out.println("D 类的方法method1");
	}
	//@Override
	public static void method2(){
		System.out.println("D 类的方法method2");
	}
	//@Override
	private void method3(){
		System.out.println("D 类的方法method3");
	}
}