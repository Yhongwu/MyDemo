package com.howard.optional;

import java.util.Optional;

import com.howard.util.Student;
import org.junit.Test;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

	/**
	 * Optional.of(T t) : 创建一个 Optional 实例
	 * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
	 */
	@Test
	public void test4(){
		Optional<Student> op = Optional.of(new Student(101, "张三", 18, 100));
		
		Optional<String> op2 = op.map(Student::getName);
		System.out.println(op2.get());
		
		Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(op3.get());
	}

	/**
	 * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
	 */
	@Test
	public void test3(){
		//ofNullable允许为空
		Optional<Student> op = Optional.ofNullable(new Student());
		
		if(op.isPresent()){
			System.out.println(op.get());
		}

		//如果为空 默认张三
		Student emp = op.orElse(new Student("张三"));
		System.out.println(emp);

		Student emp2 = op.orElseGet(() -> new Student());
		System.out.println(emp2);
	}
	


	@Test
	public void test1(){
		Optional<Student> op = Optional.of(new Student());
		Student student = op.get();
		System.out.println(student);
	}
	

}
