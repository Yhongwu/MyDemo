package com.howard.demo.serializable.test02;

import java.io.Serializable;

/**
 * Created by Howard Yao on 2018/8/5.
 */
public class Teacher implements Serializable{
    private static final long serialVersionUID = 4037451061451586851L;
    private String tName;

    @Override
    public String toString() {
        return "Teacher{" +
                "tName='" + tName + '\'' +
                '}';
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
