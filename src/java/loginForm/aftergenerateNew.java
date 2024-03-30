/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package loginForm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author Hp
 */
public class aftergenerateNew extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet aftergenerateNew</title>");
            out.println("<link rel='stylesheet' href='register.css'>");
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            boolean f = false;
            String name = "";
            if (cookies == null) {
                out.println(
                        "<h1>You are new user, go to home page and submit your institute's name");
                return;
            } else {
                for (Cookie c : cookies) {
                    String tname = c.getName();
                    if (tname.equals("user_name")) {
                        f = true;
                        name = c.getValue();
                    }
                }
            }

            String year = request.getParameter("year");

            String month = request.getParameter("month");
            
            Cookie c1 = new Cookie("month", month);
            response.addCookie(c1);
            Cookie c2 = new Cookie("year", year);
            response.addCookie(c2);

            try {

                String URL = "jdbc:mysql://localhost:3306/REGISTRATION";
                String Uname = "root";
                String Password = "password";

                String query = "select * from DATACAL where name='" + name + "' and month='" + month + "' and year='" + year + "'";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(URL, Uname, Password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                String userData = "";

                while (rs.next()) {

                    out.println("<div class=\"topnav\">");
                    out.println("</div>");

                    out.println("<div class='aftergenerate'>");

                    out.println("<h2>USER_NAME: " + rs.getString(5) + "</h2>");
                    out.println("<h2>YEAR: " + rs.getString(1) + "</h2>");
                    out.println("<h2>MONTH: " + rs.getString(2) + "</h2>");
                    out.println("<h2>UNITS_CONSUMED: " + rs.getString(3) + "</h2>");
                    out.println("<h2>TOTAL_BILL: " + rs.getString(4) + "</h2>");
                    out.println("<a href='pdfwallah'>Print</a>");
                    out.println("</div>");
                    out.println("<div class=\"footer\">");
                    out.println("<p>Footer</p>");
                    out.println("</div>");

                }
                st.close();
                con.close();

            } catch (Exception e) {
                out.println(e);
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
