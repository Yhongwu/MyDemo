package com.howard.demo.serializable.test01;

import java.io.Serializable;

/**
 * Created by Howard Yao on 2018/8/5.
 */
public class Student extends People implements Serializable{

    private static final long serialVersionUID = 8226555763774581618L;

    private static double high = 168L;

    private String nickname;

    @Override
    public String toString() {
        return "Student{" +
                "nickname='" + nickname + '\'' +
                '}'+  super.toString();
    }

    public static double getHigh() {
        return high;
    }

    public static void setHigh(double high) {
        Student.high = high;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
