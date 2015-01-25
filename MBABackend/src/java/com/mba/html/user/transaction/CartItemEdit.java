/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VISHAL
 */
public class CartItemEdit extends HttpServlet {
   
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
        String itemId = request.getParameter("itemid");
        String itemName = request.getParameter("itemname");
        String itemPrice = request.getParameter("itemprice");
        String itemCategory = request.getParameter("itemcategory");
        String itemQuantity = request.getParameter("itemquantity");

        if(validate(itemQuantity))
        {
        CartItemObject co = new CartItemObject();
        co.setItemId(itemId);
        co.setItemName(itemName);
        co.setItemPrice(itemPrice);
        co.setCategory(itemCategory);
        co.setQuantity(itemQuantity);

        String key = itemId;

        Object obj =  request.getSession().getAttribute("CART");
        
        if(obj == null)
        {
            Map<String, CartItemObject> cart = new HashMap<String, CartItemObject>() {};
            if(cart.containsKey(key))
            {
                System.out.println("item is already present !!!");
                CartItemObject cartedit = cart.get(key);
                cart.remove(key);
                cartedit.setQuantity(itemQuantity);
                cart.put(key, cartedit);
            }
            else
            {
                System.out.println("item is not present !!!");
                cart.put(key, co);
            }

            System.out.println("oooo");
            request.getSession().setAttribute("CART", cart );
            response.sendRedirect("/MBA/User/ViewYourCart.jsp");
        }
        else
        {
            Map<String, CartItemObject> cart = (Map<String, CartItemObject>) obj;
            if(cart.containsKey(key))
            {
                System.out.println("item is already present !!!");
                CartItemObject cartedit = cart.get(key);
                cart.remove(key);
                cartedit.setQuantity(itemQuantity);
                cart.put(key, cartedit);
            }
            else
            {
                System.out.println("item is not present !!!");
                cart.put(key, co);
            }

            System.out.println("jjjj");
            request.getSession().setAttribute("CART", cart );
            response.sendRedirect("/MBA/User/ViewYourCart.jsp");
        }
        }
        else
        {
            response.sendRedirect("/MBA/User/ErrorEntry.jsp");
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

    private boolean validate(String itemQuantity)
    {
        char[] arrPincode = itemQuantity.toCharArray();
        for(int i = 0 ; i < arrPincode.length ; i++)
            {
                if(arrPincode[i] == '0' || arrPincode[i] == '1' || arrPincode[i] == '2' || arrPincode[i] == '3' || arrPincode[i] == '4' || arrPincode[i] == '5' || arrPincode[i] == '6' || arrPincode[i] == '7' || arrPincode[i] == '8' || arrPincode[i] == '9')
                    continue;
                else
                    return false;
            }
            return true;
    }

}
