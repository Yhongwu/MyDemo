package com.howard.demo.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import javax.lang.model.element.QualifiedNameable;
import java.util.Date;

/**
 * Created by Howard Yao on 2019/3/23.
 */
public class Student {

    private int age;
    @SerializedName("NAME")
    private String name;
    private Date birth;
    /**
     * 班级
     */
    private Integer clazz;

    public Student() {
    }

    public Student(int age, String name, Date birth, Integer clazz) {
        this.age = age;
        this.name = name;
        this.birth = birth;
        this.clazz = clazz;
    }

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", clazz=" + clazz +
                '}';
    }
}
