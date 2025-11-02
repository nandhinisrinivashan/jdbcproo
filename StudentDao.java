package com.learn.jdbc.dao;

import com.learn.jdbc.model.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents() throws SQLException;
    void addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(int id) throws SQLException;
}