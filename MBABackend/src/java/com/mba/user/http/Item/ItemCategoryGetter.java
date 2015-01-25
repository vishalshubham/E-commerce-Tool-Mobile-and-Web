package com.mba.user.http.Item;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mba.user.login.LoginChecker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbconnection.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class ItemCategoryGetter extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.print("inside the servlet???");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
        String reqsrc = request.getParameter("requestsourceid");
        if(Integer.parseInt(reqsrc) == 1)
            {
            if(dBConnection != null)
            {
                try
                {
                    response.reset();
                    response.resetBuffer();
                    System.out.print("Getting item categories");
                    ResultSet rs = dBConnection.runQuery("Fetch_Item_Menu");
                    String item = "";
                    System.out.println("Servlet ");
                    try
                    {
                        int i=0;
                        while(rs.next())
                        {
                            System.out.print(i+"request");
                            item=item+rs.getString(1)+",";
                            System.out.println(item);
                        }
                        item=item+";";
                        response.getOutputStream().write(item.getBytes());

                    } catch (SQLException ex) {
                        System.out.print("Inside exception");
                        Logger.getLogger(ItemCategoryGetter.class.getName()).log(Level.SEVERE, null, ex);
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



public static ArrayList<String> getCategoryList()
    {
        System.out.print("inside the servlet???");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
            if(dBConnection != null)
            {
                try
                {
                    System.out.print("Getting item categories");
                    ResultSet rs = dBConnection.runQuery("Fetch_Item_Menu");

                    ArrayList<String> category = new ArrayList<String>();


                    while(rs.next())
                    {
                        category.add(rs.getString(1));
                    }

                    return category;
                }
                catch (SQLException ex)
                {
                        System.out.print("Inside exception");
                        Logger.getLogger(ItemCategoryGetter.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                }
                finally
                {
                    DBConnectionManager.releaseDBConnection(dBConnection);
                }
            }
            return null;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ItemCategoryGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ItemCategoryGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
