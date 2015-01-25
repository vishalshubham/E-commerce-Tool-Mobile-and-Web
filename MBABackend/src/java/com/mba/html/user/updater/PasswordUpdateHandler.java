/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.updater;

//import com.mba.html.user.main.RequestHandler;
import com.mba.user.data.PasswordRetainer;
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
public class PasswordUpdateHandler extends HttpServlet {
   
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
if(validatePassword(requestData))
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
            response.sendRedirect("/MBA/User/ChangeInfoPasswordResult.jsp?status="+status.getStatusCode());
//            response.getOutputStream().write("valid Your password has been changed!!!".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/ChangeInfoPasswordResult.jsp?status="+status.getStatusCode());
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
            response.sendRedirect("/MBA/User/ChangeInfoPasswordResult.jsp?status=3");
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

    private boolean validatePassword(Map parameterMap)
    {
        String oldpassword = (String)parameterMap.get("oldpassword");
        String password = (String)parameterMap.get("password");
        String newpassword = (String)parameterMap.get("newpassword");
        System.out.println("ggggggggggggggggggg");
        if(oldpassword.length()>7 & password.length()>7 & newpassword.length()>7)
        {
            if(oldpassword.equals(PasswordRetainer.getPassword()) & password.length() > 7)
            {
                if(password.equals(newpassword))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

}
