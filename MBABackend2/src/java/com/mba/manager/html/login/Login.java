/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.login;

import com.mba.manager.data.PasswordRetainer;
import com.mba.manager.login.LoginChecker;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VISHAL
 */
public class Login extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        System.out.println("We came in manager Login html");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
        String reqsrc = request.getParameter("requestsourceid");
        System.out.println("manager Login checker is called!!!");

            if(dBConnection != null & request.getParameter("username").length() > 7 & request.getParameter("password").length()>7)
            {
                System.out.println("000000000000");
                try {
                        String name = request.getParameter("username");
                        String password = request.getParameter("password");
                        PasswordRetainer.setPassword(password);
                        System.out.println("passsssssssss"+PasswordRetainer.getPassword());
                        System.out.print("GOT DATA FROM UI " + name + " _______ " + password);
                        ResultSet rs = dBConnection.runQuery("Fetch_Employee_Details", new String[]{name});
                        try
                        {
                            if(rs.next())
                            {
                                if(password.equals(rs.getString("password")))
                                {
                                    request.getSession().setAttribute("USERNAME", name);
                                    response.sendRedirect("/MBA2/Manager/Services.jsp");
                                }
                                else
                                {
                                    response.sendRedirect("/MBA2/Manager/Login.jsp");
                                    request.getSession().setAttribute("LOGINMSG", "USERID and PASSWORD DOES NOT MATCH");
                                }
                            }
                            else
                            {
                                response.sendRedirect("/MBA2/Manager/Login.jsp");
                                request.getSession().setAttribute("LOGINMSG", "USER DOES NOT EXIST");
                            }
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }
                }
                finally
                {
                    DBConnectionManager.releaseDBConnection(dBConnection);
                }
            }

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
