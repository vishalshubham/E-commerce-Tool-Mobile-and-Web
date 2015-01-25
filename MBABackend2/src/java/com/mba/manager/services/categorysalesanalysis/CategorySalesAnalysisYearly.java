/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.services.categorysalesanalysis;

import com.mba.manager.data.UserDetails;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.sql.ResultSet;
import com.mba.manager.main.TransactionStatus;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class CategorySalesAnalysisYearly
{
    public static String categorySalesAnalysisYearlyResult = "";

    public static TransactionStatus getDailyReport(Map<String, String> requestDataMap, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
        categorySalesAnalysisYearlyResult = "";

        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println("I am in Category Sales Analysis Yearly Servlet");

                String eid="";
                String oldname = "";
                String username = requestDataMap.get("username");

                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        eid = ud.getEid();
                        oldname = ud.getEname();
                        System.out.println("Your cid is -----------: "+eid+" : "+oldname);
                    }

                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(23);

                String dd = ts.getTransaction_date_time();


                System.out.println(requestDataMap.get("category")+": category");
                System.out.println(requestDataMap.get("dailydate")+": yearly date");

                ResultSet resultset = dBConnection.runQuery("Fetch_Category_Sales_Analysis_Yearly", new String[]{requestDataMap.get("category"), requestDataMap.get("dailydate")});

                while(resultset.next())
                {
                    categorySalesAnalysisYearlyResult = categorySalesAnalysisYearlyResult + resultset.getString("itemid") + "-" + resultset.getString("item_name") + "-" + resultset.getString("category") + "-" + resultset.getString("quantity") + "," ;
                }

                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk" + categorySalesAnalysisYearlyResult);
                boolean res=dBConnection.runInsertQuery("Insert_Transaction_Manager_Service_Details",new String[]{tid,eid,dd,username});


                System.out.println("This is your Transaction : "+tid+"=="+eid+"=="+username);
                System.out.println(res);

                UserDetails t = (UserDetails) transactionObjects.get("UserDetailsObject");

                if(res==true)
                {
                    System.out.println("valid valid");
                    boolean rss = dBConnection.runUpdateQuery("Update_Manager_Last_Transaction_Time", new String[]{requestDataMap.get("date"),requestDataMap.get("username")});
                    if(rss)
                    {
                        t.setLast_transaction_date(requestDataMap.get("date"));
                        System.out.println("ddddddddddd");
                        ts.setStatusCode(0);
                        ts.setTransactionStatusMessage("valid");
                    }
                }
                else
                {
                    System.out.println("invalid invalid");
                    ts.setStatusCode(1);
                    ts.setTransactionStatusMessage("Wrong Information");
                }
            }
            finally
            {
                System.out.println(ts.getRequestType()+"-"+ts.getStatusCode()+"-"+ts.getTid()+"-"+ts.getTransactionStatusMessage()+"-"+ts.getTransaction_date_time());
                DBConnectionManager.releaseDBConnection(dBConnection);
                return ts;
            }
        }
        return ts;
    }

}
