
package com.mba.user.main;

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
public class MainHandlerServlet extends HttpServlet
{
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

        System.out.println("1---------------------------");
        TransactionStatus status = RequestHandler.processUserRequest(requestData);
        

        if(status.getStatusCode() == 0)
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("++++++++++++++++++++++++++++++++++");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.getOutputStream().write("valid".getBytes());
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("----------------------------------");
            PrintWriter out = response.getWriter();
            response.reset();
            response.resetBuffer();
            response.getOutputStream().write("invalid".getBytes());
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




//                    case 1: if(Integer.parseInt(request.getParameter("requestsourceid")) == 1)
//                            {
//                                String username1 = request.getParameter("username");
//                                String password1 = request.getParameter("password");
//
//                                System.out.println("My username : "+username1);
//                                System.out.println("My password : "+password1);
//
//
//                                response.reset();
//                                response.resetBuffer();
//
//                                LoginCheck lc = new LoginCheck(username1, password1);
//                                String result1 = lc.getString().toString();
//                                System.out.println("Our result is : "+result1);
////                                response.getOutputStream().write(result1.getBytes());
//                                if(Integer.parseInt(request.getParameter("requestsourceid")) == 1)
//                                {
//                                    response.getOutputStream().write(result1.getBytes());
//                                }
//                                else if(Integer.parseInt(request.getParameter("requestsourceid")) == 2)
//                                {
//                                    if(result1.equals("valid"))
//                                    {
//                                        StringBuffer sb = new StringBuffer();
//                                        sb.append("<html>");
//                                        sb.append("<head>");
//                                        sb.append("Welcome To Best Mart <br> Best Quality : Best Price : Best Service<br>");
//                                        sb.append("</head>");
//                                        sb.append("<title>Welcome To Best Mart: Best Quality : Best Price : Best Service</title>");
//                                        sb.append("<a href=\"menupage.html\" > View Item/Place Order </a><br><br>");
//                                        sb.append("<a href=\"cartpage.html\" > Check Your Cart </a><br><br>");
//                                        sb.append("<a href=\"changeinfo.html\" > Change Information </a><br><br>");
//                                        sb.append("<a href=\"discount.html\" > Discount / Sales </a><br><br>");
//                                        sb.append("<a href=\"help.html\" > Help </a><br><br>");
//
//                                        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
//
//                                        sb.append("<body>");
//                                        sb.append("</body>");
//                                        sb.append("</html>");
//
//                                        response.getOutputStream().write(sb.toString().getBytes());
//                                    }
//                                    else
//                                    {
//                                        response.getOutputStream().write(result1.toString().getBytes());
//                                    }
//                                }
//                                break;
//                            }
//                            else if(Integer.parseInt(request.getParameter("requestsourceid")) == 2)
//                            {
//                                System.out.println("Hell man we are inside the html");
//                                String username1 = request.getParameter("username");
//                                String password1 = request.getParameter("password");
//                                StringBuffer sb = new StringBuffer();
//                                sb.append("<html>");
//                                sb.append("<head>");
//                                sb.append("<title>Welcome To Best Mart <br> Best Quality : Best Price : Best Service</title>");
//                                sb.append("<a href=\"p.html\" > CLICK THIS </a>");
//                                sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
//                                sb.append("</head>");
//                                sb.append("<body>");
//                                sb.append("</body>");
//                                sb.append("</html>");
//
//                                response.getOutputStream().write(sb.toString().getBytes());
//
//
//                            }