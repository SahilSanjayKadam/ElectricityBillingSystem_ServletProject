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

/**
 *
 * @author Hp
 */
public class DBservlet extends HttpServlet {

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
            out.println("<title>Servlet DBservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try{
                String URL="jdbc:mysql://localhost:3306/REGISTRATION";
		String Uname="root";
		String Password="password";
                String name=request.getParameter("username");
                String uname=request.getParameter("username1");
                String email=request.getParameter("usermail");
                String pass=request.getParameter("userpass");
                String  meterNo=request.getParameter("usermeter");
                String mobileNo=request.getParameter("usermobile");
                String city=request.getParameter("usercity");
                String query="insert into USER_DATA values(?,?,?,?,?,?,?)";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(URL,Uname,Password);
                PreparedStatement st=con.prepareStatement(query);
                        st.setString(1, name);
                        st.setString(2, pass);
                        st.setString(3, email);
                        st.setString(4, uname);
                        st.setString(5, meterNo);
                        st.setString(6, mobileNo);
                        st.setString(7, city);
                        int count=st.executeUpdate(); //this function returns the count of updated rows
	                System.out.println(count+" rows are affected");
                        
                   RequestDispatcher rd=request.getRequestDispatcher("Succ_Login.html");
                   rd.forward(request, response);
            }catch(Exception e){
                   RequestDispatcher rd=request.getRequestDispatcher("register.html");
                   rd.forward(request, response);
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
