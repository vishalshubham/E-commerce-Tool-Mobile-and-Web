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
public class CartDeleteHtml extends HttpServlet {
   
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

        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> requestData  = new HashMap<String, String>();
        Iterator<String> iterator =  params.keySet().iterator();

        while(iterator.hasNext())
        {
            String key  = iterator.next();
            String[] vals = params.get(key);
            requestData.put(key, vals[0]);
        }

        String cancelOrder = requestData.get("cancelorder");

        cancelOrder = processDate(cancelOrder);

        requestData.remove("cancelorder");

        requestData.put("cancelorder", cancelOrder);

        System.out.println("1---------------------------");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);


        if(status.getStatusCode() == 0)
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("++++++++++++++++++++++++++++++++++");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/CartCancelResult.jsp?status="+status.getStatusCode());
            response.getOutputStream().write("Thanks for Your Visit ! Your car thas been cancelled".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.getOutputStream().write("Sorry Dear customer! Server is busy right now so please try after some time ".getBytes());
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

    private String processDate(String html_date)
    {
        System.out.println("this is my html date in cart delete html : "+html_date);

        char[] char_date = html_date.toCharArray();

        String year = "" + char_date[16] + char_date[17] + char_date[18] + char_date[19];
        String month = "" + char_date[13] + char_date[14];
        String date = "" +  char_date[10] + char_date[11];

        String hour = "" +  char_date[0] + char_date[1];
        String minute = "" +  char_date[3] + char_date[4];
        String second = "" +  char_date[6] + char_date[7];

        String final_date = year + month + date + hour + minute + second;

        System.out.println("this is my html processed date : "+final_date);

        return final_date;
    }

}
