package Proyecto.games.New_Lemmings_game.utils;

import java.sql.*;

public class ScoreDatabase {

    public static Connection connect() {
        Connection conn = null;
        try {
            // This creates the file if it doesn't exist
            String dbPath = "jdbc:sqlite:app\\src\\main\\java\\Proyecto\\games\\New_Lemmings_game\\utils\\Lemmings_Score.db";
            conn = DriverManager.getConnection(dbPath);
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return conn;
    }

    public static java.util.List<String[]> getRanking() {
        String sql = "SELECT player, score FROM scores ORDER BY score DESC LIMIT 10";
        java.util.List<String[]> ranking = new java.util.ArrayList<>();
        try (Connection conn = ScoreDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ranking.add(new String[]{rs.getString("player"), String.valueOf(rs.getInt("score"))});
            }
        } catch (Exception e) {
            System.err.println("Error getting ranking: " + e.getMessage());
        }
        return ranking;
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

    public static void clearScores() {
        String deleteSql = "DELETE FROM scores";
        String resetSql = "DELETE FROM sqlite_sequence WHERE name='scores'";

        try (Connection conn = ScoreDatabase.connect();
             Statement stmt = conn.createStatement()) {

            // Borrar los puntajes
            stmt.executeUpdate(deleteSql);

            // Resetear el contador de AUTOINCREMENT
            stmt.executeUpdate(resetSql);

            System.out.println("Todos los puntajes fueron eliminados y el ID fue reseteado.");
        } catch (Exception e) {
            System.err.println("Error al limpiar la base de datos: " + e.getMessage());
        }
    }


}
