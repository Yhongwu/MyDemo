package com.howard.demo.serializable.test02;

import java.io.*;

/**
 * 序列化实现深度拷贝
 * Created by Howard Yao on 2018/8/5.
 */
public class Test {
    public static void main(String[] args) {
        Student student = initObject();
        Student stu = deepClone(student);
        System.out.println(stu);
        System.out.println(stu.getTeacher() == student.getTeacher());
    }

    public static Student deepClone(Student student) {


        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(student);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Student stu = (Student) objectInputStream.readObject();
            return stu;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student initObject() {
        Student student = new Student();
        student.setAge(23);
        student.setName("zymx");
        student.setAge(23);

        Teacher teacher = new Teacher();
        teacher.settName("howard");

        student.setTeacher(teacher);
        return student;
    }
}
