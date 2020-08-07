package com.example.assignment.Model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name,mssv,age;
    private int id;

    public Student() {
    }

    public Student(int id, String name, String mssv, String age) {
        this.id = id;
        this.name = name;
        this.mssv = mssv;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
