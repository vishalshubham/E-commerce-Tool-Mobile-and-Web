/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.data;

import com.mba.user.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class AccessAuthenticationGetter extends TransactionHelper
{
    @Override
    public void serviceRequest(Map<String, String> requestData, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
         System.out.println("inside the Access Authentication getter");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if (dBConnection != null)
        {
            try
            {
                AccessAuthenticate aa = new AccessAuthenticate();
                String userId = requestData.get("username");
                ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{userId});

                if (rs.next())
                {
                    System.out.println("inserting---");
                    aa.setIsCustomer("yes");
                    System.out.println("inserted---");
                }
                else
                {
                    aa.setIsCustomer("no");
                }
                String designation = "Manager";
                ResultSet rs1 = dBConnection.runQuery("Fetch_Employee_Details", new String[]{userId});

                if (rs1.next())
                {
                    System.out.println("inserting");
                    aa.setIsManager("yes");
                    System.out.println("inserted");
                }
                else
                {
                    aa.setIsManager("no");
                }

                transactionObjects.put("AccessAuthenticateObject", aa);
                ts.setStatusCode(TransactionStatusCode.SUCCESS);
                return;
            }
            catch (SQLException ex)
            {
                ts.setStatusCode(TransactionStatusCode.DATABASE_EXCEPTION);
                ts.setTransactionStatusMessage("Server error");
                return;
            }
            finally
            {
                DBConnectionManager.releaseDBConnection(dBConnection);
            }
        }
    }

}
