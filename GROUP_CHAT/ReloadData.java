package com.chatApp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReloadData extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "gade123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, message FROM messages ");

            StringBuilder messages = new StringBuilder();
            while (rs.next()) {
                messages.append("<p><strong>").append(rs.getString("username")).append(":</strong> ")
                        .append(rs.getString("message")).append("</p>");
            }

            response.getWriter().println(messages.toString());

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

