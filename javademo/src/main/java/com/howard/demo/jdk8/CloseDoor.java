package com.howard.demo.jdk8;

public class CloseDoor {
	public void doClose(Closeable c) {
		System.out.println(c);
		c.close();
	}
	
	public static void main(String[] args) {
		CloseDoor cd = new CloseDoor();
		cd.doClose(new Closeable() {
			@Override
			public void close() {
				System.out.println("使用匿名内部类实现");
				
			}
		});
		
		cd.doClose( () -> System.out.println("使用lambda表达式实现"));
	}
}
@FunctionalInterface
interface Closeable {
	void close();
}
