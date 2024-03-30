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

/**
 *
 * @author Hp
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<link rel='stylesheet' href='RegisterCSS.css'>");
            out.println("<title>Servlet RegisterServlet</title>");    
           
            out.println("</head>");
            out.println("<body>");
            
            
             out.println("<div class='topnav'>"); 
             out.println(" </div> ");  
             
            out.println("<div class=\"container\">") ;
            out.println("<h2>Register</h2>");
            out.println("<form action='DBservlet' method='post'>");
            out.println("Name:<input type=\"text\" name=\"username\"/ required><br/><br/>");
            out.println("User_Name:<input type=\"text\" name=\"username1\"/ required><br/><br/>");
            out.println("Email:<input type=\"email\" name=\"usermail\"/ required>    <br/><br/>");
            out.println("Password:<input type=\"password\" id='pass1' name=\"userpass\"/ pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\" title=\"Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters\"  required><br/><br/>");
            out.println("Meter Number:<input type=\"text\" name=\"usermeter\"/ required><br/><br/>");
            out.println("Mobile Number:<input type=\"text\" name=\"usermobile\"/ required><br/><br/>");
            out.println("City:<input type=\"text\" name=\"usercity\"/><br/><br/ required>");
            out.println(" <button type=\"submit\" onclick=\"CheckPassword()\">Submit</button>");
            out.println("</div>");
            out.println(" <div class=\"footer\">");
            out.println("<p>Footer</p>");
            out.println(" </div>");
            
          

           out.println();
         
           
            
            
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
