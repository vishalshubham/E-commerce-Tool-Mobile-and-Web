
package com.mba.manager.main;

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
                ResultSet rs = dBConnection.runQuery("Fetch_Last_Transaction_Details_Manager");
                try
                {
                    while(rs.next())
                    {
                        tid = new AtomicInteger(rs.getInt(1));
                        System.out.println(tid + "This is my tid in manager----------");
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Reached Exxxxxxxxxxxxxception in tttttid");
                    ex.printStackTrace();
                    Logger.getLogger(TransactionIdGetter.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("This is transaction id for manager : "+tid);
        return tid.incrementAndGet();
    }
}
