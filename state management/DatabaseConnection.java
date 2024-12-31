import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    // Constructor to establish the connection
    public DatabaseConnection() {
        try {
            // Replace with your DB credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/activity", "root", "paperartisthani1");
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Method to record user activity
    public void recordActivity(int user_id, String activityType, int post_id, int comment_id, int like_id) {
        String query = "INSERT INTO activity (user_id, activity_type, timestamp, post_id, comment_id, like_id) VALUES (?, ?, NOW(), ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, activityType);
            stmt.setInt(3, post_id);
            stmt.setInt(4, comment_id);
            stmt.setInt(5, like_id);
            stmt.executeUpdate();
            System.out.println("Activity recorded successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to record activity.");
            e.printStackTrace();
        }
    }

    // Main class for testing
    public static void main(String[] args) {
        // Create an instance of the database connection
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Test the recordActivity method
        dbConnection.recordActivity(1, "Post", 101, 0, 0);
        dbConnection.recordActivity(2, "Comment", 102, 201, 0);
        dbConnection.recordActivity(3, "Like", 103, 0, 301);
    }
}


