/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class CartItemAdd extends HttpServlet {
   
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
        String itemid = request.getParameter("itemid");
        String itemname = request.getParameter("itemname");
        String itemprice = request.getParameter("itemprice");
        String category = request.getParameter("category");
        String quantity = "1";

        System.out.println(itemid + "--" + itemname + "--" +itemprice + "--" + category);

        CartItemObject co = new CartItemObject();
        co.setItemId(itemid);
        co.setItemName(itemname);
        co.setItemPrice(itemprice);
        co.setCategory(category);
        co.setQuantity(quantity);

        String key = itemid;



        Object obj =  request.getSession().getAttribute("CART");
        if(obj == null)
        {
            Map<String, CartItemObject> cart = new HashMap<String, CartItemObject>() {};
            if(cart.containsKey(key))
            {
                System.out.println("item is already present !!!");
                CartItemObject cartedit = cart.get(key);
                cart.remove(key);
                String stringquantity = cartedit.getQuantity();
                cartedit.setQuantity(Integer.toString(Integer.parseInt(stringquantity)+1));
                cart.put(key, cartedit);
            }
            else
            {
                System.out.println("item is not present !!!");
                cart.put(key, co);
            }
            
            System.out.println("oooo");
            request.getSession().setAttribute("CART", cart );
            response.sendRedirect("/MBA/User/ItemList.jsp?category="+category);
        }
        else
        {
            Map<String, CartItemObject> cart = (Map<String, CartItemObject>) obj;
            if(cart.containsKey(key))
            {
                System.out.println("item is already present !!!");
                CartItemObject cartedit = cart.get(key);
                cart.remove(key);
                String stringquantity = cartedit.getQuantity();
                cartedit.setQuantity(Integer.toString(Integer.parseInt(stringquantity)+1));
                cart.put(key, cartedit);
            }
            else
            {
                System.out.println("item is not present !!!");
                cart.put(key, co);
            }

            System.out.println("jjj"+itemid + "--" + itemname + "--" + itemprice);
            System.out.println("jjjj"+category);
            request.getSession().setAttribute("CART", cart );
            response.sendRedirect("/MBA/User/ItemList.jsp?category="+category);
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
