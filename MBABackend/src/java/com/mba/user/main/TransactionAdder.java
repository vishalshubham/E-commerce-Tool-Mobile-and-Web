

package com.mba.user.main;

import dbconnection.DBConnection;
import dbconnection.DBConnectionManager;
import java.util.Map;
/**
 *
 * @author VISHAL
 */
public class TransactionAdder
{

    private StringBuffer sb = new StringBuffer();

    public TransactionAdder(TransactionStatus ts, Map<String, String> requestDataMap)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                String tid = Integer.toString(ts.getTid());
                String requesttype = Integer.toString(ts.getRequestType());
                String statuscode = Integer.toString(ts.getStatusCode());
                String date = ts.getTransaction_date_time();


                boolean res=dBConnection.runInsertQuery("Insert_Transaction_Details_User",new String[]{tid,requesttype,statuscode,date,"valid"});

                System.out.println("This is your Main Transaction : "+tid+"=="+requesttype+"=="+statuscode+"=="+date);
                System.out.println(res);

                if(res==true)
                {
                    System.out.println("aaaaaaaaaa---------");
                    sb.append("valid");
                }
                else
                {
                    System.out.println("lllllllllll");
                    sb.append("invalid");
                }




                String requestSource = requestDataMap.get("requestsourceid");
                boolean ress=dBConnection.runInsertQuery("Insert_Transaction_Way_Of_Request",new String[]{tid,requestSource});

                System.out.println(ress);

                if(ress==true)
                {
                    System.out.println("aaaaaaaaaa---------Request source");
                    sb.append("valid");
                }
                else
                {
                    System.out.println("lllllllllll");
                    sb.append("invalid");
                }



            }
            finally
            {
                DBConnectionManager.releaseDBConnection(dBConnection);
            }
        }
    }
}
