/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.transaction;

import com.mba.html.user.transaction.CartDelete;
import com.mba.user.main.DateGetter;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
public class OrderListGetter extends HttpServlet {
   
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

        DBConnection dBConnection = DBConnectionManager.getDBConnection();
        String reqsrc = request.getParameter("requestsourceid");

        
        if(Integer.parseInt(reqsrc) == 1)
        {
            if(dBConnection != null)
            {
                try
                {
                    String username = request.getParameter("username");

                    response.reset();
                    response.resetBuffer();
                    System.out.print("Getting Order List");

                    DateGetter date = new DateGetter();
                    String date_time = date.getDate();
                    System.out.println("This is my date : "+date_time);
                    date_time = processDate(date_time);

                    ResultSet rs = dBConnection.runQuery("Fetch_Order_List",new String[]{username,"yes"});
                    String order = "";

                    try
                    {
                        Double deadline_date = Double.parseDouble(date_time);
                        System.out.println("deadline_date : "+deadline_date);
                        while(rs.next())
                        {
                            Double order_date = Double.parseDouble(rs.getString("tdate"));
                            System.out.println("order_date : "+order_date);

                            if(order_date > deadline_date)
                            {
                                order=order+Double.toString(order_date)+",";
                                System.out.println(order);
                            }
                        }
                        order=order+";";
                        System.out.println("this is my order : "+order);
                        response.getOutputStream().write(order.getBytes());
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("inside catch of order list getter");
                        Logger.getLogger(OrderListGetter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                finally
                {
                    out.close();
                    DBConnectionManager.releaseDBConnection(dBConnection);
                }
            }
        }
        else if(Integer.parseInt(reqsrc) == 2)
        {
            if(dBConnection != null)
            {
                try
                {
                    String username = request.getParameter("username");
                    response.reset();
                    response.resetBuffer();
                    System.out.print("Getting Order List in 2");

                    DateGetter date = new DateGetter();
                    String date_time = date.getDate();
                    System.out.println("This is my date : "+date_time);
                    date_time = processDate(date_time);

                    ResultSet rs = dBConnection.runQuery("Fetch_Order_List",new String[]{username,"yes"});
                    String order = "";

                    try
                    {
                        Double deadline_date = Double.parseDouble(date_time);
                        System.out.println("deadline_date : "+deadline_date);
                        while(rs.next())
                        {
                            Double order_date = Double.parseDouble(rs.getString("tdate"));
                            System.out.println("order_date : "+order_date);

                            if(order_date > deadline_date)
                            {
                                CartDelete cart = new CartDelete();
                                cart.setUsername(username);
                                String string_order_date = processDateHtml(Double.toString(order_date));
                                cart.setOrderTime(string_order_date);
//                                order=order+Double.toString(order_date)+",";
//                                System.out.println(order);

                                Object obj =  request.getSession().getAttribute("ORDEREDCART");
                                if(obj == null)
                                {
                                    Map<String, CartDelete> orderCart = new HashMap<String, CartDelete>() {};
                                    orderCart.put(string_order_date, cart);
                                    request.getSession().setAttribute("ORDEREDCART", orderCart );
                                }
                                else
                                {
                                    Map<String, CartDelete> orderCart = (Map<String, CartDelete>) obj;
                                    orderCart.put(string_order_date, cart);
                                    request.getSession().setAttribute("ORDEREDCART", orderCart );
                                }

                                
                            }
                            
                        }
                        response.sendRedirect("/MBA/User/CancelOrderList.jsp");
//                        System.out.println("this is my order : "+order);
//                        response.getOutputStream().write(order.getBytes());
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("inside catch of order list getter");
                        Logger.getLogger(OrderListGetter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                finally
                {
                    out.close();
                    DBConnectionManager.releaseDBConnection(dBConnection);
                }
            }
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

    private String processDateHtml(String html_date)
    {
        System.out.println("this is my html date in orderlist getter : "+html_date);

        char[] char_date = html_date.toCharArray();
        
        String year = "" + char_date[0] + char_date[2] + char_date[3] + char_date[4];
        String month = "" + char_date[5] + char_date[6];
        String date = "" +  char_date[7] + char_date[8];

        String hour = "" +  char_date[9] + char_date[10];
        String minute = "" +  char_date[11] + char_date[12];
        String second = "" +  char_date[13] + char_date[14];

        String final_date = hour + ":" + minute + ":" + second + "on" + date + "/" + month + "/" + year;

        return final_date;
    }

















    private String processDate(String date_time)
    {

        char[] arrdate = date_time.toCharArray();
        String date ="";
        System.out.println("Time now : "+date_time);
        date = date + arrdate[8] + arrdate[9];
        switch(Integer.parseInt(date))
        {
            case 0: date = "12";
                    break;
            case 1: date = "13";
                    break;
            case 2: date = "14";
                    break;
            case 3: date = "15";
                    break;
            case 4: date = "16";
                    break;
            case 5: date = "17";
                    break;
            case 6: date = "18";
                    break;
            case 7: date = "19";
                    break;
            case 8: date = "20";
                    break;
            case 9: date = "21";
                    break;
            case 10:date = "22";
                    break;
            case 11:date = "23";
                    break;
            case 12:date = "00";
                    break;
            case 13:date = "01";
                    break;
            case 14:date = "02";
                    break;
            case 15:date = "03";
                    break;
            case 16:date = "04";
                    break;
            case 17:date = "05";
                    break;
            case 18:date = "06";
                    break;
            case 19:date = "07";
                    break;
            case 20:date = "08";
                    break;
            case 21:date = "09";
                    break;
            case 22:date = "10";
                    break;
            case 23:date = "11";
                    break;
            }
        date_time="";
        date_time = date_time + arrdate[0] + arrdate[1] + arrdate[2] + arrdate[3] + arrdate[4] + arrdate[5] + arrdate[6] + arrdate[7];
        date_time = date_time + date;
        date_time = date_time +arrdate[10] + arrdate[11] + arrdate[12] + arrdate[13];
        System.out.println(arrdate[0]+" : 0");
        System.out.println(arrdate[1]+" : 1");
        System.out.println(arrdate[2]+" : 2");
        System.out.println(arrdate[3]+" : 3");
        System.out.println(arrdate[4]+" : 4");
        System.out.println(arrdate[5]+" : 5");
        System.out.println(arrdate[6]+" : 6");
        System.out.println(arrdate[7]+" : 7");

        System.out.println("Time deadline : "+date_time);
        return date_time;
    }

}
