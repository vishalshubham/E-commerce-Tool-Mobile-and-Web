/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.http.Item;

import com.mba.html.user.transaction.CartItemObject;
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
import java.util.ArrayList;
/**
 *
 * @author VISHAL
 */
public class ItemListGetter extends HttpServlet {
   
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
                    String category = request.getParameter("category");

                    response.reset();
                    response.resetBuffer();
                    System.out.print("Getting item List");

                    ResultSet rs = dBConnection.runQuery("Fetch_Item_List",new String[]{category});
                    String item = "";
                    System.out.println("Servlet ");
                    try
                    {
                        int i=0;
                        while(rs.next())
                        {
                            System.out.print(i+"request");
                            item=item+rs.getString("itemid")+"."+rs.getString("item_name")+"=@"+rs.getString("price")+",";
                            System.out.println(item);
                        }
                        item=item+";";
                        response.getOutputStream().write(item.getBytes());

                    } catch (SQLException ex) {
                        System.out.print("Insideeeeeeeeeeeee exception");
                        Logger.getLogger(ItemListGetter.class.getName()).log(Level.SEVERE, null, ex);
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



    public static ArrayList<CartItemObject> getItemList(String category)
    {
        System.out.println("Category is : "+category);
        System.out.print("inside the Item List Getter servlet???");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
                        
        if(dBConnection != null)
        {
            System.out.println("in if dbconnection ");
            try
            {
                System.out.print("Getting item List-------------");
                ResultSet rs = dBConnection.runQuery("Fetch_Item_List",new String[]{category});
                ArrayList<CartItemObject> item = new ArrayList<CartItemObject>();
                System.out.println("0000000000000");
                try
                {
                    System.out.println("iiin the try");
                    int i=0;
                    while(rs.next())
                    {
                        CartItemObject io = new CartItemObject();
                        System.out.println("in the while");
                        System.out.print("request");
                        String itemId = rs.getString("itemid");
                        String itemName = rs.getString("item_name");
                        String itemPrice = rs.getString("price");
                        String itemCategory = rs.getString("category");
                        System.out.println("item id is :"+itemId);
                        System.out.println("item name is :"+itemName);
                        System.out.println("item price is :"+itemPrice);
                        System.out.println("item category is :"+itemCategory);

                        io.setItemId(itemId);
                        io.setItemName(itemName);
                        io.setItemPrice(itemPrice);
                        io.setCategory(itemCategory);
//                        item=item+rs.getString("itemid")+"."+rs.getString("item_name")+"=@"+rs.getString("price")+",";
                        item.add(io);
                        System.out.println("1");

                    }
                    return item;
                }
                catch (SQLException ex)
                {
                        System.out.print("IIIIInside exception");
                        Logger.getLogger(ItemListGetter.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                }

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
