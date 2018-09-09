package com.howard.demo.serializable.test02;

import java.io.Serializable;

/**
 * Created by Howard Yao on 2018/8/5.
 */
public class Student implements Serializable{
    private static final long serialVersionUID = 4496077939987967216L;
    private String name;
    private Integer age;
    private Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
