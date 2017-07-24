package com.howard.demo.classloader;
/**
 * 类加载器
 * @author Howard
 * 2017年2月20日
 */
public class ClassLoaderTest {

	public static void main(String[] args) {
		
		//sun.misc.Launcher$AppClassLoader classpath目录下的类
		//如果将该文件打包放在jdk下的jre/lib/ext目录，运行结果就会变成sun.misc.Launcher$ExtClassLoader
		System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
		//System类是有BootStrap即根加载器加载的，主要加载rt.jar包里的类  所以为null，无法打印其Name
		System.out.println(System.class.getClassLoader());
		
		/*
		 * 下面的结果：
		 * sun.misc.Launcher$AppClassLoader
		 * sun.misc.Launcher$ExtClassLoader
		 * null
		 */
		//系统默认有三个主要加载器 并且有父类与子类的关系 这里迭代打印出来
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		//最后打印根加载器
		System.out.println(loader);
	}
}
