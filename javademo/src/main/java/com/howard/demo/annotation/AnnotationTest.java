package com.howard.demo.annotation;
/**
 * 测试java注解
 * @author Howard
 * 2017年2月19日
 */
@myAnnotation(value="abc",attr={3,4,5},mataAnnotation=@mataAnnotation("bbb"))
public class AnnotationTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		//该方法已经过时 在方法上加注解@SuppressWarnings("deprecation")可以取消黄色提醒
		System.runFinalizersOnExit(false);
		//过时
		method1();
		//检测是该类否有注解 
		if(AnnotationTest.class.isAnnotationPresent(myAnnotation.class)){
			myAnnotation annotation = AnnotationTest.class.getAnnotation(myAnnotation.class);
			//该注解类必须有元注解才可以
			//@com.Howard.test04.myAnnotation()
			System.out.println(annotation);
			//获取注解的属性值
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.attr().length);
			System.out.println(annotation.mataAnnotation().value());
		}
	}
	@Deprecated
	public static void method1(){
		//java为了可以向下兼容使用以前版本的jdk用户，并不会删除过时的方法，仅提醒而已
		System.out.println("已经过时的方法，加注解就变成过时的方法");
	}
	
//	@Override用于重写方法
	
}
