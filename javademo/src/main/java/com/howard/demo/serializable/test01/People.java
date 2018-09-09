package com.howard.demo.serializable.test01;

import java.io.Serializable;

/**
 * Created by Howard Yao on 2018/8/5.
 */
public class People implements Serializable{

    private static final long serialVersionUID = -7102828067482132548L;

    transient private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
