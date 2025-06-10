package Proyecto.games.Lemmings_game.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreDatabase {
    public static Connection connect() {
        Connection conn = null;
        try {
            // This creates the file if it doesn't exist
            String url = "jdbc:sqlite:app\\src\\main\\java\\Proyecto\\games\\Lemmings_game\\utils\\Lemmings_Score.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return conn;
    }
    
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " player TEXT NOT NULL," +
                    " score INTEGER NOT NULL" +
                    ");";

        try (Connection conn = ScoreDatabase.connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'scores' created or already exists.");
        } catch (Exception e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void saveScore(String player, int score) {
        String sql = "INSERT INTO scores(player, score) VALUES(?, ?)";

        try (Connection conn = ScoreDatabase.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Score saved.");
        } catch (Exception e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }
    
    public static void showRanking() {
        String sql = "SELECT player, score FROM scores ORDER BY score DESC LIMIT 10";

        try (Connection conn = ScoreDatabase.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("🏆 Ranking:");
            while (rs.next()) {
                System.out.println(rs.getString("player") + " - " + rs.getInt("score"));
            }

        } catch (Exception e) {
            System.err.println("Error showing ranking: " + e.getMessage());
        }
    }
}