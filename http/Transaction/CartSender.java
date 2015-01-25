/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.http.Transaction;

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
import java.sql.*;
/**
 *
 * @author VISHAL
 */
public class CartSender extends HttpServlet {
   
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
        if(Integer.parseInt(reqsrc) == 1)
            {
            if(dBConnection != null)
            {
                try
                {
                    String name = request.getParameter("username");
                    String bill = request.getParameter("bill");
                    String cid="";
                    System.out.println("GOT BILL FROM UI " + name + " _______ " + bill);
                    response.reset();
                    response.resetBuffer();
                    ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{name});

                    try
                    {
                        if(rs.next())
                        {
                            cid = rs.getString("cid");
                            System.out.println("Your cid is -----------: "+cid);
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Reached Exception in cid");
                        Logger.getLogger(CartSender.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String tid="";
                    ResultSet rs1 = dBConnection.runQuery("Fetch_AllTransaction_Details");
                    try
                    {
                        int max = 0;
                        while(rs1.next())
                        {
                          tid = rs1.getString(1);
//                            if(Integer.parseInt(tid) > max)
//                            {
//                                max = Integer.parseInt(tid);
//                                System.out.println(max);
//                            }
                         //   tid = Integer.toString(rs.getInt("Tid"));
                            System.out.println(tid + "This is my tid----------");
                        }

                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Reached Exxxxxxxxxxxxxception in tttttid");
                        ex.printStackTrace();
                        Logger.getLogger(CartSender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int inttid=Integer.parseInt(tid);
                    tid=Integer.toString(inttid+1);


                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Details",new String[]{tid,cid,DateGetter.getDate(),name});

                    System.out.println("This is your Transaction : "+tid+"=="+cid+"=="+name);
                    System.out.println(res);

                    if(res==true)
                    {
                        response.getOutputStream().write("valid".getBytes());
                    }
                    else
                    {
                        response.getOutputStream().write("invalid".getBytes());
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
