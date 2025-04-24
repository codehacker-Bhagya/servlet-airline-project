package com.bhagya.practice.servlet.Controller;

import com.bhagya.practice.servlet.Service.BillingService;
import com.bhagya.practice.servlet.model.Billing;
import com.bhagya.practice.servlet.model.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class BillingController {

    BillingService billingService = new BillingService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=======inside the doGet() method");

        try {

            List<Billing> billingList = billingService.displaybilling();

            System.out.println("---------set the attribute-------");
            System.out.println("--------redirecting servlet request to dispatcher-----");
            request.setAttribute("billingList", billingList);

            // forward method
            request.getRequestDispatcher("DisplayBill.jsp").forward(request, response);

//             redirect method
//            request.getSession().setAttribute("personList",personList);
//            response.sendRedirect("DisplayPerson.jsp");

        } catch (ServletException e) {

            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String PaymentMethod = request.getParameter("PaymentMethod");
        int upiId = request.getParameter(upiId);



        Billing billing = new Billing(PaymentMethod, upiId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (billingService.insertBilling(billing)) {
                out.println("<h2>billing inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert billing.</h2>");
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

        String idStr = request.getParameter("upiId");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "upiId is required.");
            return;
        }

        int upiId = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = billingService.deleteBilling(upiId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>bill deleted successfully</h1>");
        } else {
            out.println("<h1>Bill not found</h1>");
        }
        out.println("</body></html>");
    }


}
