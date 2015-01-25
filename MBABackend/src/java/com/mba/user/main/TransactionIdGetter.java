
package com.mba.user.main;

import com.mba.user.transaction.CartSend;
import java.sql.*;
import dbconnection.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class TransactionIdGetter
{
    static String ttid;
    static AtomicInteger tid = null;

    static
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                ResultSet rs = dBConnection.runQuery("Fetch_Last_Transaction_Details_User");
                try
                {
                    while(rs.next())
                    {
                        tid = new AtomicInteger(rs.getInt(1));
                        System.out.println(tid + "This is my tid----------");
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Reached Exxxxxxxxxxxxxception in tttttid");
                    ex.printStackTrace();
                    Logger.getLogger(CartSend.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            finally
            {
                DBConnectionManager.releaseDBConnection(dBConnection);
            }
        }
    }


    public static int getTransactionId()
    {
        System.out.println("This is transaction id : "+tid);
        return tid.incrementAndGet();
    }
}
