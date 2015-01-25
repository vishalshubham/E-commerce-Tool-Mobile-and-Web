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
public class LoginAuthenticationGetter extends TransactionHelper
{
    
    @Override
    public void serviceRequest(Map<String, String> requestData, TransactionStatus ts, Map<String, Object> transactionObjects)
    {
        System.out.println("inside the login authentication getter");
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if (dBConnection != null)
        {
            try
            {
                System.out.println(PasswordRetainer.getPassword() + " : This is myyyyyyyyyyy Password");
                String userId = requestData.get("username");
                String password = PasswordRetainer.getPassword();

                Object obj =  transactionObjects.get("UserDetailsObject");
                    if(obj != null)
                    {
                        UserDetails ud = (UserDetails) obj;
                        LoginAuthenticate la = new LoginAuthenticate();

                        if (password.equals(ud.getPassword()))
                        {

                            la.setPassword(password);
                            la.setUsername(userId);
                            ts.setStatusCode(TransactionStatusCode.SUCCESS);
                            System.out.println("inserting");
                        }
                        else
                        {
                             ts.setStatusCode(TransactionStatusCode.INVALID_AUTHENTICATION);
                             System.out.println("inserted");
                        }

                        transactionObjects.put("LoginAuthenticateObject", la);
                        return;
                    }
            }
            catch (Exception ex)
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
