package com.howard.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解类
 * 但是必须要加上元注解
 * @Retention(RetentionPolicy.RUNTIME)元注解
 * @Target设定该注解的使用的目标 可以是方法或类等，也可以多个
 * @author Howard
 * 2017年2月19日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface myAnnotation {

	//属性 @myAnnotation(xxx="yyy")
	//如果各个属性都没默认值 那么使用该注解必须注明所有属性的值
	//如果有默认值的 可以不注明 注明则覆盖默认值
	//如果属性是value的，并且只有这个属性或者其它属性有默认值不另赋值，那么vlue这个属性在注明的
	//时候可以简单写为@myAnnotation("red")
	String color() default "red";
	String value();
	
	int[] attr() default {1,2,3};
	//属性也是一个注解 mataAnnotation也是一个自定义注解
	mataAnnotation mataAnnotation() default @mataAnnotation("aaa");
}
