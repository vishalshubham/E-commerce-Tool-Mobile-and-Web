/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.expirydateanalysis;

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
public class ExpiryDateAnalysisDays extends HttpServlet {
   
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

        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> requestData  = new HashMap<String, String>();
        Iterator<String> iterator =  params.keySet().iterator();

        while(iterator.hasNext())
        {
            String key  = iterator.next();
            String[] vals = params.get(key);
            requestData.put(key, vals[0]);
        }

        System.out.println("2-------------++++++++++++++++=days");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);

        String finalResult = RequestHandler.finalResult;

        System.out.println(finalResult + " : result");
        Map<String, ExpiryDateAnalysisObject> edaObject = new HashMap<String, ExpiryDateAnalysisObject>();
          edaObject.clear(); 
        if(!edaObject.isEmpty())
        {
            System.out.println("Clear");
            edaObject.clear();            
        }

        char[] arrResult = finalResult.toCharArray();
        int flag1 = 0;
        int flag2 = 0;
        String itemid = "";
        String item_name = "";
        String noOfDays = "";

        for(int i = 0 ; i < arrResult.length ; i++)
        {
            if(arrResult[i]!='-' && flag1==0 && flag2==0)
            {
                itemid = itemid + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==0 && flag2==0)
            {
                flag1 = 1;
            }
            else if(arrResult[i]!='-' && flag1==1 && flag2==0)
            {
                item_name = item_name + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==1 && flag2==0)
            {
                flag2 = 1;
            }
            else if(arrResult[i]!=',' && flag1==1 && flag2==1)
            {
                noOfDays = noOfDays + arrResult[i];
            }
            else if(arrResult[i]==',' && flag1==1 && flag2==1)
            {
                ExpiryDateAnalysisObject c = new ExpiryDateAnalysisObject();
                c.setItemid(itemid);
                c.setItem_name(item_name);
                c.setNoOfDays(noOfDays);
                System.out.println("----" + itemid + item_name + noOfDays);
                flag1 = 0;
                flag2 = 0;
                edaObject.put(itemid, c);
                itemid = "";
                item_name = "";
                noOfDays = "";
            }
        }

        request.getSession().setAttribute("EDA_DAYS_OBJECT", edaObject);

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
        response.sendRedirect("/MBA2/Manager/ExpiryDateAnalysisDaysResult.jsp");




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
