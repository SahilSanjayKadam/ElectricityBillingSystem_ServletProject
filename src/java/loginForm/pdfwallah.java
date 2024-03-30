/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package loginForm;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hp
 */
public class pdfwallah extends HttpServlet {

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
            throws ServletException, IOException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
           
            Cookie[] cookies = request.getCookies();
            String name = "";
            if(cookies==null){
                out.println("<div class='center'>");
                out.println("<div class='container' style='background-color: grey'>");
                out.println("<h1>You are a new User Go To Home Page</h1>");
                out.println("<a href='/index.html'>home</a>");
                out.println("</div></div>");
            }
            else{
                for(Cookie c : cookies){
                    String tname= c.getName();
                    if(tname.equals("user_name")){
                        name=c.getValue();
                    }
                }
            }
            String Month = "";
            if(cookies==null){
                out.println("<div class='center'>");
                out.println("<div class='container' style='background-color: grey'>");
                out.println("<h1>no month</h1>");
                out.println("<a href='/index.html'>home</a>");
                out.println("</div></div>");
            }
            else{
                for(Cookie c : cookies){
                    String tname= c.getName();
                    if(tname.equals("month")){
                        Month=c.getValue();
                    }
                }
            }
            String year = "";
            if(cookies==null){
                out.println("<div class='center'>");
                out.println("<div class='container' style='background-color: grey'>");
                out.println("<h1>no year</h1>");
                out.println("<a href='/index.html'>home</a>");
                out.println("</div></div>");
            }
            else{
                for(Cookie c : cookies){
                    String tname= c.getName();
                    if(tname.equals("year")){
                        year=c.getValue();
                    }
                }
            }
            String FileName ="D:\\billPDF\\"+name+""+Month+""+year+".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FileName));
            document.open();
           
           
           
            try{
                    String sqlurl = "jdbc:mysql://localhost:3306/REGISTRATION?zeroDateTimeBehavior=CONVERT_TO_NULL";
                    String sqlname = "root";
                    String sqlpassword="password";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(sqlurl, sqlname, sqlpassword);
                    String query =" select * from USER_DATA where USER_NAME='"+name+"';";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    rs.next();
                    Paragraph para = new Paragraph("Name : "+rs.getString(1)+"");
                    Paragraph para2 = new Paragraph("Meter No. : "+rs.getString(5)+"");
                    Paragraph para3 = new Paragraph("City : "+rs.getString(7)+"");
                    Paragraph para4 = new Paragraph("Phone No : "+rs.getString(6)+"");
                    Paragraph para5 = new Paragraph("Email : "+rs.getString(3)+"");
                    document.add(para);
                    document.add(para2);
                    document.add(para3);
                    document.add(para4);
                    document.add(para5);
                    rs.close();
                    st.close();
                    con.close();
                }
            catch(Exception e){
                out.println("<h1>"+e+"</h1>");
            }
            try{
                String sqlurl = "jdbc:mysql://localhost:3306/REGISTRATION?zeroDateTimeBehavior=CONVERT_TO_NULL";
                String sqlname = "root";
                String sqlpassword="password";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(sqlurl, sqlname, sqlpassword);
                String query1 =" select * from DATACAL where USER_NAME ='"+name+"' and  MONTH='"+Month+"' and  YEAR ='"+year+"';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query1);
                if(rs.next()){
                    Paragraph para = new Paragraph("Year : "+rs.getString(1)+"");
                    Paragraph para2 = new Paragraph("Month : "+rs.getString(2)+"");
                    Paragraph para3 = new Paragraph("Unit : "+rs.getString(3)+"");
                    Paragraph para4 = new Paragraph("Amount : "+rs.getString(4)+"");
                    document.add(para);
                    document.add(para2);
                    document.add(para3);
                    document.add(para4);
                st.close();
                con.close();
                }
            }  
            catch(Exception e){
//                out.println("<div class='center'>");
//                out.println("<div class='container' style='transform: translate(0%, 0%);background-color: grey'>");
//                out.println("<h1 style='text-align:center; color:white'> Something Went Wrong try again "+e+"</h1>");
//                out.println("</div></div>");
//                out.println("</body>");
//                out.println("</html>");
            }
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Print</title>");  
            out.println("<link rel='stylesheet' href='pdfcss.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"topnav\">");
            out.println("<a class=\"active\" href=\"index.html\">Logout</a>");
            out.println("</div>");
            out.println("<h1>PDF SAVED at D:\\billPDF\\</h1>");
            out.println("<div class=\"footer\">");
            out.println(" <p>Footer</p>");
            out.println("</div>");
       
            out.println("</body>");
            out.println("</html>");
            Paragraph para = new Paragraph("this is a test");
            document.add(para);
            document.close();
        } catch (Exception e) {
            
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfwallah.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfwallah.class.getName()).log(Level.SEVERE, null, ex);
        }
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
