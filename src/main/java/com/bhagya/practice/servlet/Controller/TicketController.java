package com.bhagya.practice.servlet.Controller;

import com.bhagya.practice.servlet.Service.TicketService;
import com.bhagya.practice.servlet.model.Ticket;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class TicketController {

    TicketService ticketService = new TicketService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=======inside the doGet() method");

        try {

            List<Ticket> ticketList = ticketService.displayTicket();

            System.out.println("---------set the attribute-------");
            System.out.println("--------redirecting servlet request to dispatcher-----");
            request.setAttribute("flightList", ticketList);

            // forward method
            request.getRequestDispatcher("DisplayTicket.jsp").forward(request, response);

//             redirect method
//            request.getSession().setAttribute("personList",personList);
//            response.sendRedirect("DisplayPerson.jsp");

        } catch (SQLException | ServletException e) {

            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String destination = request.getParameter("destination");
        String seatPreference = request.getParameter("seatPreference");
        String ticketNo = request.getParameter("ticketNo");


        Ticket ticket = new Ticket(ticketNo,destination);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (ticketService.insertTicket(ticket)) {
                out.println("<h2>ticket inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert ticket.</h2>");
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

        String idStr = request.getParameter("ticketNo");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ticketNo is required.");
            return;
        }

        int ticketNo = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = ticketService.deleteTicket(ticketNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>ticket cancelled successfully</h1>");
        } else {
            out.println("<h1>ticket not found</h1>");
        }
        out.println("</body></html>");
    }


}
