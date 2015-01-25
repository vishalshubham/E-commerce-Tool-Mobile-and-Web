/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.transaction;

import com.mba.user.main.RequestHandler;
import com.mba.user.main.TransactionStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VISHAL
 */
public class CartSend extends HttpServlet {
   
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

        System.out.println(" We came in servlet hurray");

        
        String bill = "";

        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> requestData  = new HashMap<String, String>();
        Iterator<String> iterator =  params.keySet().iterator();

        while(iterator.hasNext())
        {
            String key  = iterator.next();
            String[] vals = params.get(key);
            requestData.put(key, vals[0]);
        }

        Map<String, CartItemObject> cart = (Map<String, CartItemObject>) request.getSession().getAttribute("CART");

        Iterator<String> cartset = cart.keySet().iterator();

        if(cart.size() == 0)
        {
            out.println("<html><body>Sorry, You have not registered anything till now!!!</body></html>");
        }
        else
        {
            while(cartset.hasNext())
                {
                    String key = cartset.next();
                    CartItemObject cartItem = cart.get(key);
                    String itemId = cartItem.getItemId();
                    String itemName = cartItem.getItemName();
                    String itemPrice = cartItem.getItemPrice();
                    String itemCategory= cartItem.getCategory();
                    String itemQuantity= cartItem.getQuantity();

                    bill = bill + itemQuantity + "-" + itemId + "@" + itemPrice + ",";
                }
        }

        requestData.put("bill", bill);

        if(validateCart(bill))
        {
        System.out.println("1---------------------------");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);


        if(status.getStatusCode() == 0)
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("++++++++++++++++++++++++++++++++++");
            PrintWriter out1 = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/CartSendResult.jsp?status="+ status.getStatusCode());
//            response.getOutputStream().write("<html><body>Congratulations, Your Cart has been registered anything!!!</body></html>".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("----------------------------------");
            PrintWriter out1 = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/CartSendResult.jsp?status="+ status.getStatusCode());
//            response.getOutputStream().write("<html><body>Sorry, There is some problem with the server!!!</body></html>".getBytes());
        }
        }
        else
        {
            response.sendRedirect("/MBA/User/ErrorEntry.jsp");
        }
//         <input type="text" id="bill" name="bill" value="<%=session.getAttribute("CART")%>"/>
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

    private boolean validateCart(String str)
    {
        char[] array = str.toCharArray();
        for(int i = 0 ; i < array.length ; i++)
            {
                if(array[i] == '0' || array[i] == '1' || array[i] == '2' || array[i] == '3' || array[i] == '4' || array[i] == '5' || array[i] == '6' || array[i] == '7' || array[i] == '8' || array[i] == '9' || array[i] == '-' || array[i] == '@' || array[i] == ',')
                    continue;
                else
                    return false;
            }
            return true;
    }

}
