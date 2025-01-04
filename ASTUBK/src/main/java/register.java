package registeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.http.Part;

@WebServlet("/Register.html")
public class register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String username = request.getParameter("username");
          String email = request.getParameter("email");
          String password = request.getParameter("password");
          String fullName = request.getParameter("full_name");
          String bio = request.getParameter("bio");
          Part profilePicture = request.getPart("profile_picture");
          InputStream inputStream = profilePicture.getInputStream();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookclub", "root", "getahun");
            String sql = "INSERT INTO users (username, email, password, full_name, profile_picture, join_date, bio) VALUES (?, ?, ?, ?, ?, NOW(), ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, fullName);
            statement.setBlob(5, inputStream);
            statement.setString(6, bio);
            statement.executeUpdate();
            con.close();
            response.sendRedirect("success.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
