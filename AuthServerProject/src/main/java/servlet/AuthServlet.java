package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication logic (replace with your own logic)
        if ("user".equals(username) && "password".equals(password)) {
            // After successful auth, send a request to the BidServer (on port 8085)
            URL bidServerUrl = new URL("http://localhost:8085/BidServerProject/bid");
            HttpURLConnection connection = (HttpURLConnection) bidServerUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Example JSON payload (replace with real data as needed)
            String bidPayload = "{\"username\":\"" + username + "\", \"bid\":\"100\"}";
            try (OutputStream os = connection.getOutputStream()) {
                os.write(bidPayload.getBytes());
                os.flush();
            }

            // Handle response from BidServer
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                response.getWriter().write("Bid request sent successfully.");
            } else {
                response.getWriter().write("Failed to send bid request.");
            }
        } else {
            response.getWriter().write("Invalid credentials.");
        }
    }
}
