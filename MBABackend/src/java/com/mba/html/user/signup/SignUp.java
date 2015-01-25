/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.html.user.signup;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class SignUp
{
    public static TransactionStatus signUpUser(Map<String, String> requestDataMap, TransactionStatus ts, Map<String, Object> transactionObjects)
    {

        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in name updater ");

                String cid="";
                String oldname = "";
                String username = requestDataMap.get("username");
                String newname = requestDataMap.get("name");

                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        cid = ud.getCid();
                        oldname = ud.getCname();
                        System.out.println("Your cid is -----------: "+cid);
                    }

                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(3);

                String dd = ts.getTransaction_date_time();

                //Insert query
                boolean rs1 = dBConnection.runUpdateQuery("Update_User_Name", new String[]{newname,username});

                System.out.println("before valid::::");
                System.out.println("result : "+rs1 );


                if(rs1)
                {
                    System.out.println("belllllllllllllllllllllllllllllllllll");
                    ts.setStatusCode(0);
                    ts.setTransactionStatusMessage("valid");

                }
                else
                {
                    System.out.println("helllllllllllllllllllllllllllllllll");
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
}
