package com.chatApp;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//@WebServlet("/startchat")
public class Chat extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String id = request.getParameter("id");
          String username = request.getParameter("username");
          String password = request.getParameter("password");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "gade123");
          
            
            
            if (conn == null) {
                throw new Exception("Failed to connect to the database.");
            }
            String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("id", id);
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("password", password);
        response.sendRedirect("startchat.jsp");
    }
    
}
