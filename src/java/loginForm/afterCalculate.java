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
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author Hp
 */
public class afterCalculate extends HttpServlet {

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
            out.println("<title>Servlet afterCalculate</title>");
            out.println("<link rel='stylesheet' href=\"register.css\"> ");
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
             double billToPay = 0;
           
           
          
            try {

                String URL = "jdbc:mysql://localhost:3306/REGISTRATION";
                String Uname = "root";
                String Password = "password";
                String query = "select * from DATACAL where name='"+name+"' and MONTH='"+month+"' and YEAR='"+year+"';";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(URL, Uname, Password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
              
                
                if(rs.next()==false){
                 
                  int units = Integer.parseInt(request.getParameter("uconsumed"));
                 
                   if (units < 100) {
                    billToPay = units * 1.20;
                } else if (units < 300) {
                    billToPay = 100 * 1.20 + (units - 100) * 2;
                } else if (units > 300) {
                    billToPay = 100 * 1.20 + 200 * 2 + (units - 300) * 3;
                }
                   
                    try {
                   
                    String query1 = "insert into DATACAL values(?,?,?,?,?)";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(URL, Uname, Password);
                    PreparedStatement stmt = conn.prepareStatement(query1);
                    stmt.setString(1, year);
                    stmt.setString(2, month);
                    stmt.setInt(3, units);
                    stmt.setDouble(4, billToPay);
                    stmt.setString(5, name);

                    int count = stmt.executeUpdate(); //this function returns the count of updated rows
                    System.out.println(count + " rows are affected");
                    conn.close();
                    stmt.close();
                } catch (Exception e) {

                    out.println(e);
                }
                out.println(" <div class=\"topnav\">");
                out.println(" </div>");
                out.println(" <h1>Your Total bill is Rs." + billToPay + "</h1>");
                out.println("<a href='GenerateBill'>Generate_Bill</a>");
                out.println("<div class=\"footer\">");
                out.println(" <p>Footer</p>");
                out.println("  </div>");
               
                 
                  
                }else{
               out.println(" <div class=\"topnav\">");
                out.println(" </div>");
                out.println(" <h1>You Have Written Already existing Year and Month</h1>");
                out.println("<a href='Calculate'>Calculate_Again</a>");
                out.println("   <div class=\"footer\">");
                out.println(" <p>Footer</p>");
                out.println("  </div>");
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
