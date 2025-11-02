package com.learn.jdbc.service;

import com.learn.jdbc.dao.StudentDao;
import com.learn.jdbc.dao.StudentDAOImpI;
import com.learn.jdbc.model.Student;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final StudentDao studentDao = new StudentDAOImpI();

    public void addStudent(Student student) {
        try {
            studentDao.addStudent(student);
            System.out.println(" Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        try {
            return studentDao.getAllStudents();
        } catch (SQLException e) {
            System.out.println("Error while fetching students: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void updateStudent(Student student) {
        try {
            studentDao.updateStudent(student);
            System.out.println(" Student updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            studentDao.deleteStudent(id);
            System.out.println(" Student deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error while deleting student: " + e.getMessage());
        }
    }
}