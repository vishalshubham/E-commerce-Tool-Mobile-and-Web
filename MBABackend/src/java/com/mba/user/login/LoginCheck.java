
package com.mba.user.login;

import com.mba.user.data.PasswordRetainer;
import java.sql.*;
import dbconnection.*;

/**
 *
 * @author VISHAL
 */
public class LoginCheck
{
    private StringBuffer sb = new StringBuffer();

    public LoginCheck(String username,String password)
    {
        DBConnection dBConnection = DBConnectionManager.getDBConnection();

        if(dBConnection != null)
        {
            try
            {
                ResultSet rs = dBConnection.runQuery("Fetch_User_Details", new String[]{username});
                try {
                        if (rs.next())
                        {
                            //LoginDetails ld = new LoginDetails();
                            //ld.setUserid(res.get,,,;
                            //ld.setgro etc
//                            request.getSession().setAttribute("XYZ", ld);

                            if (password.equals(rs.getString("password")))
                            {
                                PasswordRetainer.setPassword(rs.getString("password"));
                                System.out.println("passssssssssss : "+PasswordRetainer.getPassword());
                                sb.append("valid");
                            }
                            else
                            {
                                sb.append("invalid");
                            }
                        }
                        else
                        {
                            sb.append("invalid");
                        }
                    } 
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                        sb.append("invalid");
                    }
            }
            finally
            {
                DBConnectionManager.releaseDBConnection(dBConnection);
            }
        }
    }

    public StringBuffer getString()
    {
        return sb;
    }

}
