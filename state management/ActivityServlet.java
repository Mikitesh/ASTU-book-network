package bookclub;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ActivityServlet extends HttpServlet {

    private DatabaseConnection dbConnection;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the database connection
        dbConnection = new DatabaseConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String action = request.getParameter("action"); // e.g., "like", "comment", "post", "online"

        // Validate input parameters
        if (username == null || username.isEmpty() || action == null || action.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username and action are required.");
            return;
        }

        // Record the user's activity
        switch (action) {
            case "like":
                dbConnection.recordActivity(username, "liked a post");
                break;
            case "comment":
                dbConnection.recordActivity(username, "commented");
                break;
            case "post":
                dbConnection.recordActivity(username, "posted");
                break;
            case "online":
                dbConnection.recordActivity(username, "was online");
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                return;
        }

        response.getWriter().println("Activity recorded for " + username);
    }

    @Override
    public void destroy() {
        // Cleanup code here, e.g., close database connections
        if (dbConnection != null) {
            dbConnection.close(); // Assuming close() is a method in DatabaseConnection
        }
    }
}