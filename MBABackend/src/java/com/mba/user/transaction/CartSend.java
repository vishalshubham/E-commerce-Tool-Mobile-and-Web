
package com.mba.user.transaction;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import java.sql.*;
import dbconnection.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class CartSend
{
    private static int countComma(String get)
    {
        int i = 0;
        while(get.indexOf(",") != -1)
        {
            i++;
        }
        return i;
    }
    private TransactionStatus tts = null;


    public static TransactionStatus sendCart(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
    {

        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println("I am in cart send ::: ");

                String cid="";
                String username = requestDataMap.get("username");
                System.out.println("This is my username : "+username);
                System.out.println("This is my bill : "+requestDataMap.get("bill"));

//                ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{username});
//                try
//                {
//                    if(rs.next())
//                    {
//                        cid = rs.getString("cid");
//                        System.out.println("Your cid is -----------: "+cid);
//                    }
//                }
                Object obj =  transactionObjects.get("UserDetailsObject");
                if(obj != null)
                {
                    UserDetails ud = (UserDetails) obj;
                    cid = ud.getCid();
                    System.out.println("Your cid is -----------: "+cid);
                }

                
                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(2);
                String dd = ts.getTransaction_date_time();

                boolean res=dBConnection.runInsertQuery("Insert_Transaction_Buy_Item_Details",new String[]{tid,cid,dd,username});



                String bill = requestDataMap.get("bill");
                char[] arrc = bill.toCharArray();
                int count = 0;
                for(int i = 0 ; i < arrc.length ; i++)
                {
                    if(arrc[i] == ',')
                    {
                        count++;
                    }
                }

                while(count != 0)
                {
                    int size = bill.length();
                    char[] arr = bill.toCharArray();
                    String qty = "";
                    int indexOfDash = bill.indexOf("-");
                    for(int i = 0 ; i < indexOfDash ; i++)
                    {
                        qty = qty + arr[i];
                    }

                    String itemid = "";
                    int indexOfRate = bill.indexOf("@");
                    for(int i = indexOfDash + 1 ; i < indexOfRate ; i++)
                    {
                        itemid = itemid + arr[i];
                    }

                    String rate = "";
                    int indexOfComma = bill.indexOf(",");
                    for(int i = indexOfRate + 1 ; i < indexOfComma ; i++)
                    {
                        rate = rate + arr[i];
                    }

                    bill = "";
                    for(int i = indexOfComma + 1 ; i < size ; i++)
                    {
                        bill =bill + arr[i];
                    }
                    System.out.println("Our : "+qty+" "+itemid+" "+rate);
                    String discount = "0";
                    boolean rrs=dBConnection.runInsertQuery("Insert_Transaction_Item_Details",new String[]{tid,itemid,qty,rate,discount});
                    count--;
                }

                System.out.println("This is your Transaction : "+tid+"=="+cid+"=="+username);
                System.out.println(res);

                UserDetails t = (UserDetails) transactionObjects.get("UserDetailsObject");
                
                if(res==true)
                {
                    System.out.println("valid valid");
                    boolean rss = dBConnection.runUpdateQuery("Update_User_Last_Transaction_Time", new String[]{requestDataMap.get("date"),requestDataMap.get("username")});
                    if(rss)
                    {
                        t.setLast_transaction_date(requestDataMap.get("date"));
                        System.out.println("ddddddddddd");
                       // sb.append("valid");
                        ts.setStatusCode(0);
                        ts.setTransactionStatusMessage("valid");
                    }
                }
                else
                {
                    System.out.println("invalid invalid");
                    //sb.append("invalid");
                    ts.setStatusCode(1);
                    ts.setTransactionStatusMessage("Wrong Information");
                }
            }
            finally
            {
               // tts = ts;
                System.out.println(ts.getRequestType()+"-"+ts.getStatusCode()+"-"+ts.getTid()+"-"+ts.getTransactionStatusMessage()+"-"+ts.getTransaction_date_time());
                DBConnectionManager.releaseDBConnection(dBConnection);
                return ts;
            }
        }
        return ts;
    }

    private void processBillMore(String bill)
    {
        
    }
    public TransactionStatus getTransactionStatus()
    {
        System.out.println("Transaction Status Sent back;;;;;;;;;;;;;;");
        System.out.println(tts.getRequestType()+tts.getStatusCode()+tts.getTid()+tts.getTransactionStatusMessage()+tts.getTransaction_date_time());
        return tts;
    }

}