
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
public class NameUpdateHandler extends HttpServlet {

    
   
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
        
            System.out.println("----" + request.getParameter("name"));
            Map<String, String[]> params = request.getParameterMap();

            Map<String, String> requestData  = new HashMap<String, String>();
            Iterator<String> iterator =  params.keySet().iterator();

            while(iterator.hasNext())
            {
                String key  = iterator.next();
                String[] vals = params.get(key);

                requestData.put(key, vals[0]);
            }
        if(validateName(requestData))
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
                response.sendRedirect("/MBA/User/ChangeInfoNameResult.jsp?status="+status.getStatusCode());
    //            response.getOutputStream().write("valid Your name has been changed!!!".getBytes());
            }
            else
            {
                response.setContentType("text/html;charset=UTF-8");
                System.out.println("2----------------------------------");
                PrintWriter out = response.getWriter();
                response.reset();
                response.resetBuffer();
                response.sendRedirect("/MBA/User/ChangeInfoNameResult.jsp?status="+status.getStatusCode());
    //            response.getOutputStream().write("invalid".getBytes());
            }
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
                System.out.println("2++++++++++++++++++++++++++++++++++");
                PrintWriter out = response.getWriter();
                response.reset();
                response.resetBuffer();
                response.sendRedirect("/MBA/User/ChangeInfoNameResult.jsp?status=3");
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

    
    private boolean validateName(Map parameterMap)
    {
        String name = (String)parameterMap.get("name");
        char[] arrName = name.toCharArray();
        if(arrName.length > 2)
        {
            for(int i = 0 ; i < arrName.length ; i++)
            {
                if(arrName[i] == '0' ||  arrName[i] == '1' || arrName[i] == '2' || arrName[i] == '3' || arrName[i] == '4' || arrName[i] == '5' || arrName[i] == '6' || arrName[i] == '7' || arrName[i] == '8' || arrName[i] == '9'||arrName[i] == '"'||arrName[i] == ';'||arrName[i] == '?'||arrName[i] == '/'||arrName[i] == '.'||arrName[i] == ','||arrName[i] == '>'||arrName[i] == '<')
                    return false;
            }
            return true;
        }
        return false;
    }

}
