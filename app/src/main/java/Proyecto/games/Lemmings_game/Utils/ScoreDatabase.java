package Proyecto.games.Lemmings_game.Utils;

import java.sql.*;


public class ScoreDatabase {

    private Connection conn;

    public ScoreDatabase(String dbFileName) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "player TEXT NOT NULL," +
                     "score INTEGER NOT NULL)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    public void addScore(String player, int score) {
        String sql = "INSERT INTO scores (player, score) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalScore(String player) {
        String sql = "SELECT SUM(score) FROM scores WHERE player = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1); // Devuelve el total o 0
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
