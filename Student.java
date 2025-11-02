package com.learn.jdbc.model;
//model-layer represents data-the structure of db recors
//each class in this layer corresponds to a table in db

public class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student() {
    }//default constructor

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', age=%d, grade='%s'}",
                id, name, age, grade);
    }
}