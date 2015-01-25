
package com.mba.user.updater;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import java.sql.*;
import dbconnection.*;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class ContactUpdate
{

    public static TransactionStatus updateContact(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in comtact updater ");

                String cid="";
                String oldcontact = "";
                String oldemail = "";
                String username = requestDataMap.get("username");
                String newcontact = requestDataMap.get("contact");
                String newemail = requestDataMap.get("email");
                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        cid = ud.getCid();
                        oldcontact = ud.getContact();
                        oldemail = ud.getEmail();
                        System.out.println("Your cid is -----------: "+cid);
                    }


                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(5);

                String dd = ts.getTransaction_date_time();


                boolean rs1 = dBConnection.runUpdateQuery("Update_User_Contact", new String[]{newcontact,newemail,username});

                if(rs1==true)
                {
                   // sb.append("valid");   
                    ts.setStatusCode(0);
                    System.out.println("Done with the Change Contact");
                    ts.setTransactionStatusMessage("valid");
                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Contact_Change",new String[]{tid,oldcontact,oldemail,newcontact,newemail});
                }
                else
                {
                   // sb.append("invalid");
                    ts.setStatusCode(1);
                    ts.setTransactionStatusMessage("valid");
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
