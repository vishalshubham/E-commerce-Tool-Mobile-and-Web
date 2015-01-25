
package com.mba.user.data;

import com.mba.user.main.TransactionStatus;
import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class ReplayAttackCheckGetter extends TransactionHelper
{

    @Override
    public void serviceRequest(Map<String, String> requestDataMap, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
        UserDetails t = (UserDetails) transactionObjects.get("UserDetailsObject");
                
        System.out.println("inside the userdetailsgetter");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();
        if (dBConnection != null)
        {
            try
            {
                System.out.println("This is the last time u came here : "+t.getLast_transaction_date());
                System.out.println("And This is the time u sent the request"+requestDataMap.get("date"));

                ReplayAttackCheck ra = new ReplayAttackCheck();

                if(isRequestAfterPreviousDate(t.getLast_transaction_date(), requestDataMap.get("date")))
                {
                    boolean rs = dBConnection.runUpdateQuery("Update_User_Last_Transaction_Time", new String[]{requestDataMap.get("date"),requestDataMap.get("username")});
                    if(rs)
                    {
                        t.setLast_transaction_date(requestDataMap.get("date"));
                        ra.setIsReplayAttack("yes");
                        ts.setStatusCode(TransactionStatusCode.SUCCESS);
                        System.out.println("inserting replay attack object");
                    }
                }
                else
                {
                     ra.setIsReplayAttack("no");
                     ts.setStatusCode(TransactionStatusCode.INVALID_AUTHENTICATION);
                     System.out.println("inserted replay attack not object");
                }
                transactionObjects.put("ReplayAttackCheckObject", ra);
                return;
            }
            catch(Exception ex)
            {
                ts.setStatusCode(TransactionStatusCode.DATABASE_EXCEPTION);
                ts.setTransactionStatusMessage("Server error");
                ex.printStackTrace();
            }

        }
    }


    public boolean isRequestAfterPreviousDate(String previousDate , String currentDate)
    {
        Double prevDate = Double.parseDouble(previousDate);
        Double curDate = Double.parseDouble(currentDate);
        if(curDate > prevDate)
        {
            return true;
        }
        return false;
    }

}
