package com.example.jetpack.navigation;

import java.io.Serializable;

/**
 * Date: 2019-11-21
 * Author: 锅得铁
 * #
 */
public class User implements Serializable {
    public String name;
    public int age;
    public String sex;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
