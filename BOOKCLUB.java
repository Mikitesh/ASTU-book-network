package bookclub;
import java.sql.*;
public class BOOKCLUB {
		    private static final String URL = "jdbc:mysql://localhost:3306/bookclub";
		    private static final String USER = "root";
		    private static final String PASSWORD = "getahun";

		    public static void main(String[] args) {
		        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
		            System.out.println("Database connection established.");

		            // Create tables
		            createUsersTable(connection);
		            createBooksTable(connection);
		            createReviewsTable(connection);
		            createDiscussionsTable(connection);
		            createCommentsTable(connection);
		            createBookClubsTable(connection);
		            createClubMembershipsTable(connection);

		            // Insert data examples
		            insertUser(connection, "john_doe", "john@example.com", "hashed_password", "John Doe");
		            insertBook(connection, "Effective Java", "Joshua Bloch", "1234567890123", "Programming", Date.valueOf("2018-01-01"), "Addison-Wesley", "A programming guide.");
		            insertReview(connection, 1, 1, 5, "Great book!");
		            insertDiscussion(connection, 1, "Discussion on Effective Java", "Let's discuss the main concepts.");
		            insertComment(connection, 1, 1, "I agree, it's a great read!");
		            insertBookClub(connection, "Java Enthusiasts", 1, "A club for Java lovers.");
		            insertClubMembership(connection, 1, 1);

		            // Read data examples
		            readUsers(connection);
		            readBooks(connection);
		            readReviews(connection);
		            readDiscussions(connection);
		            readComments(connection);
		            readBookClubs(connection);
		            readClubMemberships(connection);

		            // Update examples
		            updateUser(connection, "john_doe_updated", 1);
		            updateBook(connection, "Effective Java 2nd Edition", 1);

		            // Delete examples
		            deleteUser(connection, 1);
		            deleteBook(connection, 1);

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    // Create Tables
		    private static void createUsersTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
		                + "user_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "username VARCHAR(255) NOT NULL UNIQUE, "
		                + "email VARCHAR(255) NOT NULL UNIQUE, "
		                + "password VARCHAR(255) NOT NULL, "
		                + "full_name VARCHAR(255), "
		                + "profile_picture VARCHAR(255), "
		                + "join_date DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "bio TEXT)";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createBooksTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS Books ("
		                + "book_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "title VARCHAR(255) NOT NULL, "
		                + "author VARCHAR(255), "
		                + "isbn VARCHAR(20), "
		                + "genre VARCHAR(100), "
		                + "publication_date DATE, "
		                + "publisher VARCHAR(255), "
		                + "summary TEXT)";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createReviewsTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS Reviews ("
		                + "review_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "user_id INT, "
		                + "book_id INT, "
		                + "rating INT CHECK (rating >= 1 AND rating <= 5), "
		                + "review_text TEXT, "
		                + "review_date DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "FOREIGN KEY (user_id) REFERENCES Users(user_id), "
		                + "FOREIGN KEY (book_id) REFERENCES Books(book_id))";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createDiscussionsTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS Discussions ("
		                + "discussion_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "book_id INT, "
		                + "title VARCHAR(255) NOT NULL, "
		                + "description TEXT, "
		                + "created_by INT, "
		                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "FOREIGN KEY (book_id) REFERENCES Books(book_id), "
		                + "FOREIGN KEY (created_by) REFERENCES Users(user_id))";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createCommentsTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS Comments ("
		                + "comment_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "discussion_id INT, "
		                + "user_id INT, "
		                + "comment_text TEXT, "
		                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "FOREIGN KEY (discussion_id) REFERENCES Discussions(discussion_id), "
		                + "FOREIGN KEY (user_id) REFERENCES Users(user_id))";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createBookClubsTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS BookClubs ("
		                + "club_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "club_name VARCHAR(255) NOT NULL, "
		                + "created_by INT, "
		                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "description TEXT, "
		                + "FOREIGN KEY (created_by) REFERENCES Users(user_id))";
		        executeUpdate(connection, createTableSQL);
		    }

		    private static void createClubMembershipsTable(Connection connection) {
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS ClubMemberships ("
		                + "membership_id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "club_id INT, "
		                + "user_id INT, "
		                + "join_date DATETIME DEFAULT CURRENT_TIMESTAMP, "
		                + "FOREIGN KEY (club_id) REFERENCES BookClubs(club_id), "
		                + "FOREIGN KEY (user_id) REFERENCES Users(user_id))";
		        executeUpdate(connection, createTableSQL);
		    }

		    // Insert Data Methods
		    private static void insertUser(Connection connection, String username, String email, String password, String fullName) {
		        String insertSQL = "INSERT INTO Users (username, email, password, full_name) VALUES (?, ?, ?, ?)";
		        executeInsert(connection, insertSQL, username, email, password, fullName);
		    }

		    private static void insertBook(Connection connection, String title, String author, String isbn, String genre, Date publicationDate, String publisher, String summary) {
		        String insertSQL = "INSERT INTO Books (title, author, isbn, genre, publication_date, publisher, summary) VALUES (?, ?, ?, ?, ?, ?, ?)";
		        executeInsert(connection, insertSQL, title, author, isbn, genre, publicationDate, publisher, summary);
		    }

		    private static void insertReview(Connection connection, int userId, int bookId, int rating, String reviewText) {
		        String insertSQL = "INSERT INTO Reviews (user_id, book_id, rating, review_text) VALUES (?, ?, ?, ?)";
		        executeInsert(connection, insertSQL, userId, bookId, rating, reviewText);
		    }

		    private static void insertDiscussion(Connection connection, int bookId, String title, String description) {
		        String insertSQL = "INSERT INTO Discussions (book_id, title, description, created_by) VALUES (?, ?, ?, ?)";
		        executeInsert(connection, insertSQL, bookId, title, description, 1); // Replace 1 with actual user ID
		    }

		    private static void insertComment(Connection connection, int discussionId, int userId, String commentText) {
		        String insertSQL = "INSERT INTO Comments (discussion_id, user_id, comment_text) VALUES (?, ?, ?)";
		        executeInsert(connection, insertSQL, discussionId, userId, commentText);
		    }

		    private static void insertBookClub(Connection connection, String clubName, int createdBy, String description) {
		        String insertSQL = "INSERT INTO BookClubs (club_name, created_by, description) VALUES (?, ?, ?)";
		        executeInsert(connection, insertSQL, clubName, createdBy, description);
		    }

		    private static void insertClubMembership(Connection connection, int clubId, int userId) {
		        String insertSQL = "INSERT INTO ClubMemberships (club_id, user_id) VALUES (?, ?)";
		        executeInsert(connection, insertSQL, clubId, userId);
		    }

		    // Read Data Methods
		    private static void readUsers(Connection connection) {
		        String selectSQL = "SELECT * FROM Users";
		        executeRead(connection, selectSQL);
		    }

		    private static void readBooks(Connection connection) {
		        String selectSQL = "SELECT * FROM Books";
		        executeRead(connection, selectSQL);
		    }

		    private static void readReviews(Connection connection) {
		        String selectSQL = "SELECT * FROM Reviews";
		        executeRead(connection, selectSQL);
		    }

		    private static void readDiscussions(Connection connection) {
		        String selectSQL = "SELECT * FROM Discussions";
		        executeRead(connection, selectSQL);
		    }

		    private static void readComments(Connection connection) {
		        String selectSQL = "SELECT * FROM Comments";
		        executeRead(connection, selectSQL);
		    }

		    private static void readBookClubs(Connection connection) {
		        String selectSQL = "SELECT * FROM BookClubs";
		        executeRead(connection, selectSQL);
		    }

		    private static void readClubMemberships(Connection connection) {
		        String selectSQL = "SELECT * FROM ClubMemberships";
		        executeRead(connection, selectSQL);
		    }

		    // Update Data Methods
		    private static void updateUser(Connection connection, String newUsername, int userId) {
		        String updateSQL = "UPDATE Users SET username = ? WHERE user_id = ?";
		        executeUpdate(connection, updateSQL, newUsername, userId);
		    }

		    private static void updateBook(Connection connection, String newTitle, int bookId) {
		        String updateSQL = "UPDATE Books SET title = ? WHERE book_id = ?";
		        executeUpdate(connection, updateSQL, newTitle, bookId);
		    }

		    // Delete Data Methods
		    private static void deleteUser(Connection connection, int userId) {
		        String deleteSQL = "DELETE FROM Users WHERE user_id = ?";
		        executeUpdate(connection, deleteSQL, userId);
		    }

		    private static void deleteBook(Connection connection, int bookId) {
		        String deleteSQL = "DELETE FROM Books WHERE book_id = ?";
		        executeUpdate(connection, deleteSQL, bookId);
		    }

		    // Utility Methods
		    private static void executeUpdate(Connection connection, String sql, Object... params) {
		        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		            for (int i = 0; i < params.length; i++) {
		                pstmt.setObject(i + 1, params[i]);
		            }
		            pstmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void executeInsert(Connection connection, String sql, Object... params) {
		        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		            for (int i = 0; i < params.length; i++) {
		                pstmt.setObject(i + 1, params[i]);
		            }
		            pstmt.executeUpdate();
		            System.out.println("Data inserted.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void executeRead(Connection connection, String sql) {
		        try (PreparedStatement pstmt = connection.prepareStatement(sql);
		             ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                // Print all columns (customize as needed)
		                System.out.println("Row: " + rs.getString(1) + ", " + rs.getString(2)); // Adjust indices based on columns
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

