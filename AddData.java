package quizApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddData {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(insertQuery);

            // Adding a question
            ps.setString(1, "What is the size of int in java?");
            ps.setString(2, "8 bits");
            ps.setString(3, "16 bits");
            ps.setString(4, "32 bits");
            ps.setString(5, "64 bits");
            ps.setString(6, "A");
            ps.executeUpdate();
            
            ps.setString(1, "Which component is used to compile, debug and execute the java programs?");
            ps.setString(2, "JDK");
            ps.setString(3, "JRE");
            ps.setString(4, "JIT");
            ps.setString(5, "JVM");
            ps.setString(6, "A");
            ps.executeUpdate();

            System.out.println("Data added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

