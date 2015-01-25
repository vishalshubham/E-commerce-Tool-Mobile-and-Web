
package com.mba.user.data;

import com.mba.user.main.TransactionStatus;
import com.mba.user.data.TransactionStatusCode;
import com.mba.user.data.UserDetails;
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
                System.out.println("My username : "+userId);
                ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{userId});

                if (rs.next())
                {
                    UserDetails ud = new UserDetails();
                    System.out.println("inserting");
                    ud.setCid(rs.getString("cid"));
                    ud.setCname(rs.getString("cname"));
                    ud.setDob(rs.getString("dob"));
                    ud.setGender(rs.getString("gender"));
                    ud.setCaddr(rs.getString("caddr"));
                    ud.setCity(rs.getString("city"));
                    ud.setState(rs.getString("state"));
                    ud.setPincode(rs.getString("pincode"));
                    ud.setContact(rs.getString("contact"));
                    ud.setEmail(rs.getString("email"));
                    ud.setAge(rs.getString("age"));
                    ud.setIncome(rs.getString("income"));
                    ud.setUsername(rs.getString("username"));
                    ud.setPassword(rs.getString("password"));
                    ud.setJoining_date(rs.getString("joining_date"));
                    ud.setLast_transaction_date(rs.getString("last_transaction_date"));
                    System.out.println("inserted");

                    transactionObjects.put("UserDetailsObject", ud);
                    System.out.print("TD OBJ IN UDG : " + transactionObjects);
                     ts.setStatusCode(TransactionStatusCode.SUCCESS);
                    return;
                }
                ts.setStatusCode(TransactionStatusCode.INVALID_PARAMETERS);
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






