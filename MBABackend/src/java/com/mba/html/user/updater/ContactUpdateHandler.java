/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.updater;

//import com.mba.html.user.main.RequestHandler;
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
public class ContactUpdateHandler extends HttpServlet {
   
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
if(validateContact(requestData))
       {
        System.out.println("2-------------++++++++++++++++=");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);

         if(status.getStatusCode() == 0)
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2++++++++++++++++++++++++++++++++++");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/ChangeInfoContactResult.jsp?status="+status.getStatusCode());
//            response.getOutputStream().write("valid Your contact has been changed!!!".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/ChangeInfoContactResult.jsp?status="+status.getStatusCode());
//            response.getOutputStream().write("invalid".getBytes());
        }
        }
       else
       {
           response.setContentType("text/html;charset=UTF-8");
            System.out.println("2----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/ChangeInfoContactResult.jsp?status=3");
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

    private boolean validateContact(Map parameterMap)
    {
        String contact=(String)parameterMap.get("contact");
        String email=(String)parameterMap.get("email");
        char[] arrContact = contact.toCharArray();
        char[] arrEmail = email.toCharArray();

        if(arrEmail.length > 8  & arrContact.length >9 & email.indexOf("@")>0)
        {
            for(int i = 0 ; i < arrEmail.length ; i++)
            {
                if(arrEmail[i] == '-' ||  arrEmail[i] == '=' || arrEmail[i] == '*' || arrEmail[i] == '&' || arrEmail[i] == '^' || arrEmail[i] == '%' || arrEmail[i] == '$' || arrEmail[i] == '#' || arrEmail[i] == '!' || arrEmail[i] == '~'||arrEmail[i] == '"'||arrEmail[i] == ';'||arrEmail[i] == '?'||arrEmail[i] == '/'||arrEmail[i] == '|' || arrEmail[i] == ','||arrEmail[i] == '>'||arrEmail[i] == '<')
                    return false;
            }
            for(int i = 0 ; i < arrContact.length ; i++)
            {
                if(arrContact[i] == '0' || arrContact[i] == '1' || arrContact[i] == '2' || arrContact[i] == '3' || arrContact[i] == '4' || arrContact[i] == '5' || arrContact[i] == '6' || arrContact[i] == '7' || arrContact[i] == '8' || arrContact[i] == '9' || arrContact[i] == '+')
                    continue;
                else
                    return false;
            }
            return true;
        }
        return false;
    }

}
