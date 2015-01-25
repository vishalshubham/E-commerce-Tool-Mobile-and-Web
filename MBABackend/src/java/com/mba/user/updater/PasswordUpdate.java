
package com.mba.user.updater;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import java.sql.*;
import dbconnection.*;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class PasswordUpdate
{

    public static TransactionStatus updatePassword(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in password updater ");

                String cid="";
                String oldpassword = "";
                String username = requestDataMap.get("username");
                String newpassword = requestDataMap.get("password");
                ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{username});

                                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        cid = ud.getCid();
                        oldpassword = ud.getPassword();
                        System.out.println("Your cid is -----------: "+cid);
                    }

                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(6);

                String dd = ts.getTransaction_date_time();

                boolean rs1 = dBConnection.runUpdateQuery("Update_User_Password", new String[]{newpassword,username});

                System.out.println("0000000000000000000000000000000000000000000");

                if(rs1==true)
                {
                    //sb.append("valid");
                    ts.setStatusCode(0);
                    System.out.println("Done with the Change Password");
                    ts.setTransactionStatusMessage("valid");
                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Password_Change",new String[]{tid,oldpassword,newpassword});

                }
                else
                {
                   // sb.append("invalid");
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
