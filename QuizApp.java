package quizApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            // Fetch questions from the database
            String fetchQuestions = "SELECT * FROM quiz";
            PreparedStatement ps = conn.prepareStatement(fetchQuestions);
            ResultSet rs = ps.executeQuery();

            int score = 0;

            while (rs.next()) {
                System.out.println(rs.getString("question"));
                System.out.println("A. " + rs.getString("option_a"));
                System.out.println("B. " + rs.getString("option_b"));
                System.out.println("C. " + rs.getString("option_c"));
                System.out.println("D. " + rs.getString("option_d"));

                System.out.print("Your answer: ");
                String answer = scanner.nextLine().toUpperCase();

                if (answer.equals(rs.getString("correct_option"))) {
                    score++;
                }
            }

            // Display the score
            System.out.println("Your score: " + score);

            // Save the score
            System.out.print("Enter your name: ");
            String userName = scanner.nextLine();
            String saveScore = "INSERT INTO scores (user_name, score) VALUES (?, ?)";
            PreparedStatement savePs = conn.prepareStatement(saveScore);
            savePs.setString(1, userName);
            savePs.setInt(2, score);
            savePs.executeUpdate();

            System.out.println("Score saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

