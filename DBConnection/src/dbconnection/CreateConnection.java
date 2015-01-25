
package dbconnection;

import java.sql.*;

/**
 * @author VISHAL
 */
public class CreateConnection
{
    public static Connection getDbConnection()
    {
	String dsn="emp";
	try
	{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("\nLoaded");
            return (Connection) DriverManager.getConnection("jdbc:odbc:"+dsn,"","");
//            return (Connection) DriverManager.getConnection("jdbc:odbc:"+dsn,"system","baby");
//            return (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","system","baby");
	}
	catch(Exception e)
	{
            e.printStackTrace();
    	}
        return null;
    }

    public static void main(String args[])
    {
//        CreateConnection con=neCreateConnection();
        System.out.print(CreateConnection.getDbConnection());

    }
}
