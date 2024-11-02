package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/bid")
public class BidServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read bid details (for simplicity, skipping detailed parsing)
        String bidDetails = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        // Process the bid (placeholder logic)
        System.out.println("Received bid details: " + bidDetails);

        // Send response back
        response.getWriter().write("Bid processed successfully.");
    }
}
