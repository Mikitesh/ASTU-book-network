import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class bookstore {
    private static final String URL = "jdbc:mysql://localhost:3306/ASTU_Book_Store";
    private static final String USER = "root";   
    private static final String PASSWORD = "12345678"; 

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");

            // Create a SQL query
            String sql = "SELECT * FROM Books";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int bookId = resultSet.getInt("BookID");
                String title = resultSet.getString("Title");
                String author = resultSet.getString("Author");
                
                // int stock = resultSet.getInt("Stock");

                System.out.printf("Book ID: %d, Title: %s, Author: %s, Price: %.2f, Stock: %d%n",
                        bookId, title, author, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
