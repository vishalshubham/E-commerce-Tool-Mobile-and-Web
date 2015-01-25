/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.services.expirydateanalysis;

import com.mba.manager.data.UserDetails;
import com.mba.manager.main.DateGetter;
import com.mba.manager.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class ExpiryDateAnalysis
{
    public static String expiryDateAnalysisResult = "";
    public static TransactionStatus getDailyReport(Map<String, String> requestDataMap, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
expiryDateAnalysisResult = "";
        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in expiry Date Analysis Daily Servlet");

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
                ts.setRequestType(36);

                String dd = ts.getTransaction_date_time();


                System.out.println(requestDataMap.get("category")+": category");

                ResultSet rs  = dBConnection.runQuery("Fetch_Expiry_Date_Analysis", new String[]{requestDataMap.get("category")});
                DateGetter d = new DateGetter();
                int firstDate = Integer.parseInt(d.getTodayDate());
                int firstMonth = Integer.parseInt(d.getTodayMonth());
                int firstYear = Integer.parseInt(d.getTodayYear());

                while(rs.next())
                {
                    char[] expiryDate = rs.getString("date").toCharArray();

                    int secondYear = getYear(expiryDate);
                    int secondMonth = getMonth(expiryDate);
                    int secondDate = getDate(expiryDate);

                    DateGetter ddd = new DateGetter();
                    int noOfDays = ddd.daysBetweenGetter(firstYear, firstMonth, firstDate, secondYear, secondMonth, secondDate);


                    expiryDateAnalysisResult = expiryDateAnalysisResult + rs.getString("itemid") + "-" + rs.getString("item_name") + "-" + noOfDays + ",";

                }

                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk" + expiryDateAnalysisResult);
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

    private static int getDate(char[] expiryDate)
    {
        String sdate = "" + expiryDate[6] + expiryDate[7];
        System.out.println("date : "+sdate);
        return Integer.parseInt(sdate);
    }

    private static int getYear(char[] expiryDate)
    {
        String sdate = "" + expiryDate[0] + expiryDate[1] + expiryDate[2] + expiryDate[3] ;
        System.out.println("date : "+sdate);
        return Integer.parseInt(sdate);
    }

    private static int getMonth(char[] expiryDate)
    {
        String sdate = "" + expiryDate[4] + expiryDate[5];
        System.out.println("date : "+sdate);
        return Integer.parseInt(sdate);
    }
}
