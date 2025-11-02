package com.learn.jdbc.dao;

import com.learn.jdbc.model.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class StudentDAOImpI implements StudentDao {

    // Connection method
    private Connection getconnection() throws SQLException, IOException {
        Properties prop = new Properties();

        // Load DB properties
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Error loading properties file");
            e.printStackTrace();  // <-- print full exception
        }

        String DBURL = prop.getProperty("db.url");
        String DBUSER = prop.getProperty("db.user");
        String DBPASS = prop.getProperty("db.password");

        return getConnection(DBURL, DBUSER, DBPASS);
    }

    // Get all students
    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age")); // <-- if you changed age to int, use getInt()
                student.setGrade(rs.getString("grade"));
                students.add(student);
            }

        } catch (IOException | SQLException e) {
            System.out.println("getAllStudents encountered an error...");
            e.printStackTrace();  // <-- print the full error for debugging
        }

        return students;
    }

    // Add student
    @Override
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";

        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge()); // <-- if age is int, use ps.setInt(2, student.getAge())
            ps.setString(3, student.getGrade());
            ps.executeUpdate();

        } catch (IOException | SQLException e) {
            System.out.println("Add encountered an error...");
            e.printStackTrace();  // <-- print full exception
        }
    }

    // Update student
    @Override
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";

        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge()); // <-- if age is int, use ps.setInt(2, student.getAge())
            ps.setString(3, student.getGrade());
            ps.setInt(4, student.getId());
            ps.executeUpdate();

        } catch (IOException | SQLException e) {
            System.out.println("Update encountered an error...");
            e.printStackTrace();  // <-- print full exception
        }
    }

    // Delete student by ID
    @Override
    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id=?";

        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (IOException | SQLException e) {
            System.out.println("Delete encountered an error...");
            e.printStackTrace();  // <-- print full exception
        }
    }
}