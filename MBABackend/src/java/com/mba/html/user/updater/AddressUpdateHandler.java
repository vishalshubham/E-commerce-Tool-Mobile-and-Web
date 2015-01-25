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
public class AddressUpdateHandler extends HttpServlet {
   
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
if(validateAddress(requestData))
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
            response.sendRedirect("/MBA/User/ChangeInfoAddressResult.jsp?status="+status.getStatusCode());
//            response.getOutputStream().write("valid Your address has been changed!!!".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("2----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.sendRedirect("/MBA/User/ChangeInfoAddressResult.jsp?status="+status.getStatusCode());
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
            response.sendRedirect("/MBA/User/ChangeInfoAddressResult.jsp?status=3");
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

    private boolean validateAddress(Map parameterMap)
    {
        String address=(String)parameterMap.get("address");
        String city=(String)parameterMap.get("city");
        String state=(String)parameterMap.get("state");
        String pinCode=(String)parameterMap.get("pincode");
        char[] arrAddress = address.toCharArray();
        char[] arrCity = city.toCharArray();
        char[] arrState = state.toCharArray();
        char[] arrPincode = pinCode.toCharArray();
        
        if(arrAddress.length > 8 & arrCity.length > 3 & arrState.length > 3 & arrPincode.length >5)
        {
            for(int i = 0 ; i < arrCity.length ; i++)
            {
                if(arrCity[i] == '0' ||  arrCity[i] == '1' || arrCity[i] == '2' || arrCity[i] == '3' || arrCity[i] == '4' || arrCity[i] == '5' || arrCity[i] == '6' || arrCity[i] == '7' || arrCity[i] == '8' || arrCity[i] == '9'||arrCity[i] == '"'||arrCity[i] == ';'||arrCity[i] == '?'||arrCity[i] == '/'||arrCity[i] == '.'||arrCity[i] == ','||arrCity[i] == '>'||arrCity[i] == '<')
                    return false;
            }
            for(int i = 0 ; i < arrState.length ; i++)
            {
                if(arrState[i] == '0' ||  arrState[i] == '1' || arrState[i] == '2' || arrState[i] == '3' || arrState[i] == '4' || arrState[i] == '5' || arrState[i] == '6' || arrState[i] == '7' || arrState[i] == '8' || arrState[i] == '9'||arrState[i] == '"'||arrState[i] == ';'||arrState[i] == '?'||arrState[i] == '/'||arrState[i] == '.'||arrState[i] == ','||arrState[i] == '>'||arrState[i] == '<')
                    return false;
            }
            for(int i = 0 ; i < arrPincode.length ; i++)
            {
                if(arrPincode[i] == '0' || arrPincode[i] == '1' || arrPincode[i] == '2' || arrPincode[i] == '3' || arrPincode[i] == '4' || arrPincode[i] == '5' || arrPincode[i] == '6' || arrPincode[i] == '7' || arrPincode[i] == '8' || arrPincode[i] == '9')
                    continue;
                else
                    return false;
            }
            return true;
        }
        return false;

    }

}
