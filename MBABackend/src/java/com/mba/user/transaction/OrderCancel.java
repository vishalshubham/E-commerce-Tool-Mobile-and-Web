/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.transaction;

import com.mba.user.data.UserDetails;
import com.mba.user.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class OrderCancel
{

    public static TransactionStatus cancelOrder(Map<String, String> requestDataMap, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in order canceller ");

                String cid="";
                String username = requestDataMap.get("username");
                String order = requestDataMap.get("cancelorder");

                Object obj =  transactionObjects.get("UserDetailsObject");
                if(obj != null)
                {
                    UserDetails ud = (UserDetails) obj;
                    cid = ud.getCid();
                    System.out.println("Your cid is -----------: "+cid);
                }

                String tid = Integer.toString(ts.getTid());
                ts.setRequestType(7);

                String dd = ts.getTransaction_date_time();

                boolean rs1 = dBConnection.runUpdateQuery("Cancel_Placed_Order", new String[]{"no",order,username});

                System.out.println("before valid::::");
                System.out.println("result : "+rs1 );

                ResultSet rs = dBConnection.runQuery("Fetch_Canceled_Tid", new String[]{order,username});
                String canceledTid = "";
                while(rs.next())
                {
                    canceledTid = rs.getString("tid");
                }
                if(rs1)
                {
                    System.out.println("belllllllllllllllllllllllllllllllllll");
                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Cancel_Order",new String[]{tid,canceledTid,dd});
                    ts.setStatusCode(0);
                    System.out.println("Done with the Cancel Order");
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