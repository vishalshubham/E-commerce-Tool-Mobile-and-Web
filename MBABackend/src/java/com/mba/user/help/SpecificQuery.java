/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.help;

import com.mba.user.data.UserDetails;
import com.mba.user.help.mail.SendMail;
import com.mba.user.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class SpecificQuery
{
    public static TransactionStatus sendSpecificQuery(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in Specific Query ");

                String cid="";
                String username = requestDataMap.get("username");
                String query = requestDataMap.get("query");
                String mail = requestDataMap.get("mail");
                String password = requestDataMap.get("password");
                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        cid = ud.getCid();
                        System.out.println("Your cid is -----------: "+cid);
                    }

                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(9);

                String dd = ts.getTransaction_date_time();


                System.out.println("before valid::::");


                if(sendSpecificQuery(mail,password,query))
                {
                    System.out.println("Message sending");
                    ts.setStatusCode(0);
                    System.out.println("Message Sent");
                    ts.setTransactionStatusMessage("valid");

                }
                else
                {
                    System.out.println("Message not sent");
                    ts.setStatusCode(1);
                    ts.setTransactionStatusMessage("invalid");
                }
            }
            finally
            {
                DBConnectionManager.releaseDBConnection(dBConnection);
                return ts;
            }
         }
         return ts;
    }

    private static boolean sendSpecificQuery(String mail, String password, String query)
    {
        SendMail s = new SendMail(mail,password,query);
        return true;
    }
}
