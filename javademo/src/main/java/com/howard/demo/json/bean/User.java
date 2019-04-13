package com.howard.demo.json.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * ordinal: 序列化顺序
 * name：指定json的命名
 * serialize：不被序列化
 * format：格式化
 * Created by Howard Yao on 2019/3/23.
 */
public class User {
    @JSONField(name = "u_age", ordinal = 6)
    private int age;
    @JSONField(name = "u_username", serialize = false, ordinal = 5)
    private String userName;
    @JSONField(name = "u_nickname", ordinal = 4)
    private String nickName;
    /**
     * 不提供getter方法
     */
    @JSONField(name = "u_realname", ordinal = 3)
    private String realName;
    /**
     * getter setter方法名不一致
     */
    @JSONField(ordinal = 2)
    private String ip;

    @JSONField(name = "u_time", ordinal = 1, format = "yyyyMMdd")
    private Date lastLoginTime;

    public User(int age, String userName, String nickName, String realName, String ip, Date lastLoginTime) {
        this.age = age;
        this.userName = userName;
        this.nickName = nickName;
        this.realName = realName;
        this.ip = ip;
        this.lastLoginTime = lastLoginTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", ip='" + ip + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
