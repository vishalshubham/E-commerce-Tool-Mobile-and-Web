
package com.mba.user.updater;

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
public class AddressUpdate
{

    public static TransactionStatus updateAddress(Map<String, String> requestDataMap, TransactionStatus ts,Map<String, Object> transactionObjects)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                System.out.println(" I am in address updater ");

                String cid="";
                String username = requestDataMap.get("username");
                String newaddress = requestDataMap.get("address");
                String newcity = requestDataMap.get("city");
                String newstate = requestDataMap.get("state");
                String newpincode = requestDataMap.get("pincode");

                String oldaddress = "";
                String oldcity = "";
                String oldstate = "";
                String oldpincode = "";

               Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        cid = ud.getCid();
                        oldaddress = ud.getCaddr();
                        oldcity = ud.getCity();
                        oldstate = ud.getState();
                        oldpincode = ud.getPincode();
                        System.out.println("Your cid is -----------: "+cid);
                    }

                        
                String tid=Integer.toString(ts.getTid());
                ts.setRequestType(4);

                String dd = ts.getTransaction_date_time();

                boolean rs1 = dBConnection.runUpdateQuery("Update_User_Address", new String[]{newaddress,newcity,newstate,newpincode,username});



                if(rs1==true)
                {
                  //  sb.append("valid");
                    ts.setStatusCode(0);
                    System.out.println("Done with the change address");
                    ts.setTransactionStatusMessage("valid");
                    boolean res=dBConnection.runInsertQuery("Insert_Transaction_Address_Change",new String[]{tid,oldaddress,oldcity,oldstate,oldpincode,newaddress,newcity,newstate,newpincode});

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
