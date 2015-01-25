/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.zerosalesanalysis;

import com.mba.manager.main.RequestHandler;
import com.mba.manager.main.TransactionStatus;
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
public class ZeroSalesAnalysisDaily extends HttpServlet {
   
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
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");

        String dailydate = "" + year + month + day + "000000";
        System.out.println("Dailydate : " + dailydate);


        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> requestData  = new HashMap<String, String>();
        Iterator<String> iterator =  params.keySet().iterator();

        while(iterator.hasNext())
        {
            String key  = iterator.next();
            String[] vals = params.get(key);
            requestData.put(key, vals[0]);
        }

        requestData.put("dailydate", dailydate);

        System.out.println("2-------------++++++++++++++++=");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);

        String finalResult = RequestHandler.finalResult;

        System.out.println(finalResult + " : result");
        Map<String, ZeroSalesAnalysisObject> zsaObject = new HashMap<String, ZeroSalesAnalysisObject>();


        char[] arrResult = finalResult.toCharArray();
        int flag1 = 0;
        int flag2 = 0;
        String itemid = "";
        String item_name = "";
        String category = "";



        for(int i = 0 ; i < arrResult.length ; i++)
        {
            System.out.println("0");
            if(arrResult[i]!='-' && flag1==0 && flag2==0 )
            {
                System.out.println("1");
                itemid = itemid + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==0 && flag2==0)
            {
                System.out.println("2");
                flag1 = 1;
            }
            else if(arrResult[i]!='-' && flag1==1 && flag2==0)
            {
                System.out.println("3");
                item_name = item_name + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==1 && flag2==0)
            {
                System.out.println("4");
                flag2 = 1;
            }
            else if(arrResult[i]!=',' && flag1==1 && flag2==1)
            {
                System.out.println("5");
                category = category + arrResult[i];
            }
            else if(arrResult[i]==',' && flag1==1 && flag2==1)
            {
                System.out.println("6");
                ZeroSalesAnalysisObject c = new ZeroSalesAnalysisObject();
                c.setItemid(itemid);
                c.setItem_name(item_name);
                c.setCategory(category);
                c.setStatus("0");


                System.out.println("----" + itemid + item_name + category);
                flag1 = 0;
                flag2 = 0;
                zsaObject.put(itemid, c);
                itemid = "";
                item_name = "";
                category = "";
            }
        }

        request.getSession().setAttribute("ZSA_DAILY_OBJECT", zsaObject);

        if(status.getStatusCode() == 0)
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2++++++++++++++++++++++++++++++++++");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
        }
        response.sendRedirect("/MBA2/Manager/ZeroSalesAnalysisDailyResult.jsp");


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
