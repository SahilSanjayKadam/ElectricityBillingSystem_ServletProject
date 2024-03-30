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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author Hp
 */
public class AfterLogin extends HttpServlet {

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
            out.println("<title>Servlet AfterLogin</title>");   
             out.println("<link rel='stylesheet' href='afterLoginCSS.css'>");
            out.println("</head>");
            out.println("<body>");
           
            try{
                String URL="jdbc:mysql://localhost:3306/REGISTRATION";
		String Uname="root";
		String Password="password";
               
                String uname=request.getParameter("username1");
                
                String pass=request.getParameter("userpass");
                String query="select * from USER_DATA";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(URL,Uname,Password);
                 Statement st=con.createStatement(); 
                ResultSet rs=st.executeQuery(query);
                ArrayList<String> userdata= new ArrayList<>(); 
                 ArrayList<String> userpass= new ArrayList<>(); 
                while(rs.next()){
                    userdata.add(rs.getString(4));
                    userpass.add(rs.getString(2));
                }  
                int count=0;
               for(int i=0;i<userdata.size();i++){
                   if(uname.equals(userdata.get(i))  && pass.equals(userpass.get(i)) ){
                       Cookie c=new Cookie("user_name",uname);
                       response.addCookie(c);
                    
                       out.println("<div class=\"topnav\">");
                       out.println("</div>");
                       
                       out.println(" <h1>Welcome "+uname+"</h1>");
                       out.println("<div class='center'>");
                       out.println("<div class=\"topnav1\">");
                       out.println("<h3><a href='seeDetails'> See More Details</a></h3>");
                       out.println("<h3><a href='GenerateBill'>Generate Bill</a></h3>");
                       out.println("<h3><a href='Calculate'>Calculate Bill</a></h3>");
                       out.println("</div>");
                       out.println("</div>");
                      
                       
                       out.println(" <div class=\"footer\">");
                       out.println("<p>Footer</p>");
                       out.println("</div>");
                   break;
                   }else{
                      count++; 
                   }
               }
               if(count==userdata.size()){
                   RequestDispatcher rd=request.getRequestDispatcher("afterLoginHtml.html");
                   rd.forward(request, response);
               }
                   
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
