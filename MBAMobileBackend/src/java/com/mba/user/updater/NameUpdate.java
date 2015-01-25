
package com.mba.user.updater;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import dbconnection.*;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class NameUpdate
{
    public static TransactionStatus updateName(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
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
               
                boolean rs1 = dBConnection.runUpdateQuery("Update_User_Name", new String[]{newname,username});

                System.out.println("before valid::::");
                System.out.println("result : "+rs1 );


                if(rs1)
                {
                    System.out.println("belllllllllllllllllllllllllllllllllll");
                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Name_Change",new String[]{tid,oldname,newname});
                   // sb.append("valid");
                    ts.setStatusCode(0);
                    System.out.println("Done with the Change Name");
                    ts.setTransactionStatusMessage("valid");
                    
                }
                else
                {
                    System.out.println("helllllllllllllllllllllllllllllllll");
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
