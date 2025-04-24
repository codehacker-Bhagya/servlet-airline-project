package com.bhagya.practice.servlet.Controller;

import com.bhagya.practice.servlet.Service.PassengerService;
import com.bhagya.practice.servlet.model.Passenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class PassengerController extends HttpServlet {

    PassengerService passengerService = new PassengerService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=======inside the doGet() method");

        try {

            List<Passenger> passengerList = passengerService.displayPassenger();

            System.out.println("---------set the attribute-------");
            System.out.println("--------redirecting servlet request to dispatcher-----");
            request.setAttribute("passengerList", passengerList);

            // forward method
            request.getRequestDispatcher("DisplayPassenger.jsp").forward(request, response);

//             redirect method
//            request.getSession().setAttribute("personList",personList);
//            response.sendRedirect("DisplayPerson.jsp");

        } catch (SQLException | ServletException e) {

            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fullName = request.getParameter("fullName");
        String MoNo = request.getParameter("MoNo");
        String UIDNo = request.getParameter("UIDNo");


        Passenger passenger = new Passenger(fullName,MoNo,UIDNo);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (passengerService.insertPassenger(passenger)) {
                out.println("<h2>passenger inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert passenger.</h2>");
            }
        } catch (SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("========inside the service() method=====");

        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else if (request.getMethod().equals("DELETE")) {
            this.destroy(request,response);
        } else {
            this.doGet(request, response);
        }
    }

    private void destroy(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("========inside the destroy() method=====");

        String idStr = request.getParameter("UIDno");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "personId is required.");
            return;
    }

        long UIDNo = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = passengerService.deletePassenger(UIDNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>person deleted successfully</h1>");
        } else {
            out.println("<h1>person not found</h1>");
        }
        out.println("</body></html>");
    }

}
