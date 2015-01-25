
package com.mba.manager.data;

import com.mba.manager.main.TransactionStatus;
import com.mba.manager.data.UserDetails;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
//import com.mba.user.http.Login.LoginChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class UserDetailsGetter extends TransactionHelper
{
    

    @Override
    public void serviceRequest(Map<String, String> requestData, TransactionStatus ts, Map<String, Object> transactionObjects)
    {

        System.out.println("inside the userdetailsgetter");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if (dBConnection != null)
        {
            try
            {
                String userId = requestData.get("username");
                ResultSet rs = dBConnection.runQuery("Fetch_Employee_Details", new String[]{userId});

                if (rs.next())
                {
                    UserDetails ud = new UserDetails();
                    System.out.println("inserting00000000");
                    ud.setEid(rs.getString("eid"));
                    ud.setEname(rs.getString("ename"));
                    ud.setDob(rs.getString("dob"));
                    ud.setDesignation(rs.getString("designation"));
                    ud.setDept(rs.getString("Category/Dept"));
                    ud.setAddress(rs.getString("address"));
                    ud.setCity(rs.getString("city"));
                    ud.setPincode(rs.getString("pincode"));
                    ud.setContact(rs.getString("contact"));
                    ud.setStatus(rs.getString("status"));
                    ud.setUsername(rs.getString("username"));
                    ud.setPassword(rs.getString("password"));
                    ud.setLast_transaction_date(rs.getString("last_transaction_date"));
                    System.out.println("inserted");

                    transactionObjects.put("UserDetailsObject", ud);
                }
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
    public static String getValue()
    {
        return "BYE !!!!!!!!!!!!";
    }
}






