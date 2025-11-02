//package com.example;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class Main {
//    public static final String URL="jdbc:mysql://localhost:3306/demo_batch?useSSL=false&serverTimezone=UTC";
//    public static final String DBUSER="root";
//    public static final String DBPASSWORD="12345";
//    public static void main(String[] args) {
//        final int TOTAL =100;
//        final int BATCH_SIZE=25;
//        String query="INSERT INTO bulk_users (username, email) VALUES (?, ?)";
//        long start = System.nanoTime();
//        try(Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
//            PreparedStatement ps = conn.prepareStatement(query);)
//        {
//            conn.setAutoCommit(false);
//            int count=0;
//            for(int i=0;i<TOTAL;i++){
//                ps.setString(1,"user_"+i); //set username
//                ps.setString(2,"user_"+i+"@google.com"); //  set email
//                ps.addBatch();
//                count++;
//                // to maintain the count of records inserted keep on incrementing until we reach 25
//                if(count%BATCH_SIZE==0){
//                    ps.executeBatch(); // send the cunk we have created tp db
//                    ps.clearBatch();// clear the client side buffer to prepare for next batch
//                    conn.commit();
//                }
//            }
//            if(count%BATCH_SIZE!=0){
//                ps.executeBatch();
//
//            }
//            conn.commit();
//            long elapsed_time = (System.nanoTime() - start) / 1_000_000;
//            System.out.println(" insert "+TOTAL+" rows in "+elapsed_time + "ms using jdbc batch processing of batch size" + BATCH_SIZE+").");
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
//package com.example;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class Main {
//    public static final String URL = "jdbc:mysql://localhost:3306/demo_batch?useSSL=false&serverTimezone=UTC";
//    public static final String DBUSER = "root";
//    public static final String DBPASSWORD = "12345";
//
//    public static void main(String[] args) {
//        final int TOTAL = 200; // total number of rows to insert
//
//        String query = "INSERT INTO bulk_users (username, email) VALUES (?, ?)";
//        long start = System.nanoTime();
//
//        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
//             PreparedStatement ps = conn.prepareStatement(query)) {
//
//            // Auto-commit is true by default (every insert commits immediately)
//            for (int i = 100; i < TOTAL; i++) {
//                ps.setString(1, "user_" + i); // set username
//                ps.setString(2, "user_" + i + "@google.com"); // set email
//                ps.executeUpdate(); // insert one record at a time
//            }
//
//            long elapsed_time = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Inserted " + TOTAL + " rows in " + elapsed_time +
//                    " ms (without using batch processing).");
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}
//
//package com.learn.jdbc;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class Main{
////rewriteBatchedStatements=true → important! It tells MySQL to combine all
//// 25 queries and send them to the server at once, making it much faster.
//    public static final String URL = "jdbc:mysql://localhost:3306/demo_batch?useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
//    public static final String DBUSER = "root";
//    public static final String DBPASSWORD = "12345";
//
//    public static void main(String[] args) {
//        final int TOTAL = 100;
//        final int BATCH_SIZE = 25;
//
//        String query = "INSERT INTO bulk_users (username, email) VALUES (?, ?)";
//        long start = System.nanoTime();
//
//        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
//             PreparedStatement ps = conn.prepareStatement(query)) {
//
//            conn.setAutoCommit(false);
//            int count = 0;
//
//            for (int i = 0; i < TOTAL; i++) {
//                ps.setString(1, "user_" + i);
//                ps.setString(2, "user_" + i + "@google.com");
//                ps.addBatch();
//                count++;
//
//                if (count % BATCH_SIZE == 0) {
//                    ps.executeBatch();
//                    ps.clearBatch();
//                    conn.commit();
//                }
//            }
//
//            if (count % BATCH_SIZE != 0) {
//                ps.executeBatch();
//            }
//
//            conn.commit();
//
//            long elapsed_time = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Inserted " + TOTAL + " rows in " + elapsed_time +
//                    " ms using JDBC batch processing (rewriteBatchedStatements=true).");
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}

package com.learn.jdbc;

import com.learn.jdbc.model.Student;
import com.learn.jdbc.service.StudentService;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentService service = new StudentService();

        service.addStudent(new Student(1, "John", 20, "A"));
        service.addStudent(new Student(2, "Nandhini", 22, "B"));
        service.addStudent(new Student(3, "Roshan", 21, "A+"));
        service.addStudent(new Student(4, "Devangam", 23, "B+"));
        service.addStudent(new Student(5, "Abhay", 19, "A"));
        service.addStudent(new Student(1, "Nandhini", 20, "A"));
        service.addStudent(new Student(2, "pushpa", 22, "B"));
        service.addStudent(new Student(3, "abhsheik", 21, "A+"));
        service.addStudent(new Student(4, "Narasima", 23, "B+"));
        service.addStudent(new Student(5, "Abhay", 19, "A"));

        Student updatedStudent = new Student(3, "Roshan", 21, "A++");
        service.updateStudent(updatedStudent);

        service.deleteStudent(2);

        List<Student> students = service.getAllStudents();
        students.forEach(System.out::println);

        System.out.println("\n All operations completed successfully!");
    }
}