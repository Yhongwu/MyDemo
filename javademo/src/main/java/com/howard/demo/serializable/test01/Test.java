package com.howard.demo.serializable.test01;

import java.io.*;

/**
 * 序列化测试
 * 如果父类没有实现序列化，而子类实现列序列化。那么父类中的成员没办法做序列化操作
 * transient关键字表示指定属性不参与序列化
 * 序列化并不保存静态变量的状态
 *
 * 对同一个对象进行多次写入，打印出的第一次存储结果和第二次存储结果，只多了5个字节的引用关系。并不会导致文件累加
 * Created by Howard Yao on 2018/8/5.
 */
public class Test {
    public static void main(String[] args) {

        serializeObject(initObject());
        Student student = deSerializeObject();
        student.setHigh(160);
        System.out.println(student);
        System.out.println(student.getHigh());
    }
    public static Student initObject() {
        Student student = new Student();
        student.setNickname("howard");
        student.setName("zymx");
        student.setAge(23);
        student.setHigh(170);
        return student;
    }
    public static void serializeObject(Student student) {
        ObjectOutputStream outputStream = null;
        try {

            outputStream = new ObjectOutputStream(new FileOutputStream(new File("student")));
            outputStream.writeObject(student);
            outputStream.flush();
            System.out.println("文件大小： " + new File("student").length());
            // 测试多次序列化
            outputStream.writeObject(student);
            outputStream.flush();
            System.out.println("文件大小： " + new File("student").length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Student deSerializeObject() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(new File("student")));

            Student student = (Student) inputStream.readObject();

            return student;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
