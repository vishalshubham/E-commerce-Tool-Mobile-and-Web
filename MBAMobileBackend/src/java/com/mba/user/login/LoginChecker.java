/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VISHAL
 */
import com.mba.user.data.PasswordRetainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbconnection.*;
import java.sql.ResultSet;
/**
 *
 * @author VISHAL
 */
public class LoginChecker extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
        String reqsrc = request.getParameter("requestsourceid");
        System.out.println("Login checker is called!!!");
        if(Integer.parseInt(reqsrc) == 1)
        {
           // System.out.println("000000000000");
            if(dBConnection != null)
            {
                try {
                        String name = request.getParameter("username");
                        String password = request.getParameter("password");
                        PasswordRetainer.setPassword(password);
                        System.out.println("passsssssssss"+PasswordRetainer.getPassword());
                        System.out.print("GOT DATA FROM UI " + name + " _______ " + password);
                        response.reset();
                        response.resetBuffer();
                        ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{name});
                        try
                        {
                            if(rs.next())
                            {
                                if(password.equals(rs.getString("password")))
                                {
                                    response.getOutputStream().write("valid".getBytes());
                                }
                                else
                                {
                                    response.getOutputStream().write("invalid".getBytes());
                                }
                            }
                            else
                            {
                                response.getOutputStream().write("invalid".getBytes());
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
                    out.close();
                    DBConnectionManager.releaseDBConnection(dBConnection);
                }
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
