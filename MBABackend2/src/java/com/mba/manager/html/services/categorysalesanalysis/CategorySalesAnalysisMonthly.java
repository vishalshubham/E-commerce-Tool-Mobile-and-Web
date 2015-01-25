/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.categorysalesanalysis;

import com.mba.charts.PieChart;
import com.mba.manager.main.RequestHandler;
import com.mba.manager.main.TransactionStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VISHAL
 */
public class CategorySalesAnalysisMonthly extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Throwable
    {
        String month = request.getParameter("month");
        String year = request.getParameter("year");

        String dailydate = "" + year + month + "00000000";
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
        Map<String, CategorySalesAnalysisObject> csaObject = new HashMap<String, CategorySalesAnalysisObject>();

        int recordNumber = countCharacter(finalResult, ',');

        char[] arrResult = finalResult.toCharArray();
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        String itemid = "";
        String item_name = "";
        String category = "";
        String quantity = "";

        double[] data1 = new double[13];
        String[] labels1 = new String[13];
        int countRecords = 0;

        for(int i = 0 ; i < arrResult.length ; i++)
        {
            if(arrResult[i]!='-' && flag1==0 && flag2==0 && flag3==0)
            {
                itemid = itemid + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==0 && flag2==0 && flag3==0)
            {
                flag1 = 1;
            }
            else if(arrResult[i]!='-' && flag1==1 && flag2==0 && flag3==0)
            {
                item_name = item_name + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==1 && flag2==0 && flag3==0)
            {
                flag2 = 1;
            }
            else if(arrResult[i]!='-' && flag1==1 && flag2==1 && flag3==0)
            {
                category = category + arrResult[i];
            }
            else if(arrResult[i]=='-' && flag1==1 && flag2==1 && flag3==0)
            {
                flag3 = 1;
            }
            else if(arrResult[i]!=',' && flag1==1 && flag2==1 && flag3==1)
            {
                quantity = quantity + arrResult[i];
            }
            else if(arrResult[i]==',' && flag1==1 && flag2==1 && flag3==1)
            {
                CategorySalesAnalysisObject c = new CategorySalesAnalysisObject();
                c.setItemid(itemid);
                c.setItem_name(item_name);
                c.setCategory(category);
                c.setQuantity(quantity);
                System.out.println("----" + itemid + item_name + category + quantity);
                        data1[countRecords] = Double.parseDouble(quantity);
                labels1[countRecords] = item_name;
                countRecords++;
        

                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                csaObject.put(itemid, c);
                itemid = "";
                item_name = "";
                category = "";
                quantity = "";
            }
        }

        PieChart p = new PieChart(data1, labels1, "D:/Apache Software Foundation/Apache Tomcat 6.0.20/webapps/images/CategorySalesAnalysisMonthly/myimg");
        p.run();
         data1 = null;
        labels1 = null;
        

        request.getSession().setAttribute("CSA_MONTHLY_OBJECT", csaObject);

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
        response.sendRedirect("/MBA2/Manager/CategorySalesAnalysisMonthlyResult.jsp");


    }

    public int countCharacter(String text, char c)
    {
        int count = 0;
        char[] array = text.toCharArray();

        for(int i = 0 ; i < array.length ; i++)
        {
            if(array[i]==c)
                count++;
        }
        return count;
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
        } catch (Throwable ex) {
            Logger.getLogger(CategorySalesAnalysisMonthly.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Throwable ex) {
            Logger.getLogger(CategorySalesAnalysisMonthly.class.getName()).log(Level.SEVERE, null, ex);
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
