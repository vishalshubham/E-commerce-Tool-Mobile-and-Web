///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.mba.user.xtra;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javaapplication5.ServiceUtils;
//import javaapplication5.TransactionStatus;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author VISHAL
// */
//public class MainServlet extends HttpServlet {
//
//
////    private ServiceUtils serviceUtils = new ServiceUtils();
////
////    /**
////     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
////    throws ServletException, IOException {
//////        TransactionStatus ts = new TransactionStatus();
////
////        long tid = 1;//TransactionIdGetter.getNewTransId();
//////        ts.setTransactionId(tid);
////
////
////
////        int reqType = Integer.parseInt(request.getParameter("ReqType"));
////        Map reqMap = request.getParameterMap();
////
////
////        if(!AccessChecker.checkAccess(reqMap, ts))
////        {
//////            sendResponse(ts, request, response);
////        }
////
////
////
////
////
////
////
////        switch(reqType)
////        {
////            case 10 : ts = serviceUtils.getSales(request.getParameter("Msg"), reqType);
////                        break;
////
////            case 21:
////            default: ts = new TransactionStatus();
////
////
////        }
////
////
////
////        sendResponse(ts, request, response);
////    }
////
////
////
////
////
////
////    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
////    /**
////     * Handles the HTTP <code>GET</code> method.
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////    throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Handles the HTTP <code>POST</code> method.
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////    throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Returns a short description of the servlet.
////     * @return a String containing servlet description
////     */
////    @Override
////    public String getServletInfo() {
////        return "Short description";
////    }// </editor-fold>
////
////    private void sendResponse(TransactionStatus ts, HttpServletRequest request, HttpServletResponse response) {
////
////
////        int requestSrc = Integer.parseInt(request.getParameter("RS"));
////
////        if(requestSrc == 1)
////        {
////        }
////        else if(requestSrc == 2)
////        {
////            try {
////                response.getOutputStream().write(("TransId = " + ts.getTransactionId() + " \n Result = " + ts.getMessage() + " \n ResultCode = " + ts.getStatusCode()).getBytes());
////            } catch (IOException ex) {
////                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
////            }
////
////        }
////    }
////
////}
