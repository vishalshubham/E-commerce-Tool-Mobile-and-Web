
package dbconnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author VISHAL
 */
public class DBConnection
{
    private Connection conn;
    private Map<String, PreparedStatement> queryMap=new HashMap<String, PreparedStatement>();

    public DBConnection() throws Exception
    {
        conn = CreateConnection.getDbConnection();
        System.out.println("got the connection");
        if(conn == null)
        {
            throw new Exception();
        }

        Properties p=new Properties();
        try
        {
            p.load(new FileInputStream("E:/PROJECT/CODE/DBConnection/conf/query.properties"));
            Iterator<Object> keys=p.keySet().iterator();
            while(keys.hasNext())
            {
                String key=(String)keys.next();
                String value=p.getProperty(key);
//                System.out.println(key+"=========="+value);
                value=value.replaceAll("-", "=");
                
                PreparedStatement ps = conn.prepareStatement(value);
                queryMap.put(key, ps);
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public ResultSet runQuery(String str)
    {
        try
        {
            System.out.println(str + "this is our query");
            PreparedStatement stmt= queryMap.get(str);
            System.out.println(stmt.toString() + "this is our value");
            return stmt.executeQuery();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean runInsertQuery(String str,String[] parameters)
    {
        try
        {
            System.out.print(queryMap);
            System.out.print("KEY RECEIVED " + str);
            PreparedStatement stmt= queryMap.get(str);
            int i = 1;
            for(String param : parameters)
                {
                    stmt.setString(i++, param);
                }
            stmt.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean runUpdateQuery(String str,String[] parameters)
    {
        try
        {
            System.out.print(queryMap);
            System.out.print("KEY RECEIVED " + str);
            PreparedStatement stmt= queryMap.get(str);
            int i = 1;
            for(String param : parameters)
                {
                    stmt.setString(i++, param);
                }
            stmt.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet runQuery(String str, String[] parameters)
    {
        try
        {
            System.out.print(queryMap);
             System.out.print("KEY RECEIVED " + str);
            PreparedStatement stmt= queryMap.get(str);
            System.out.println(stmt);
            int i = 1;
            for(String param : parameters)
            {
                System.out.println("Parameter : "+param);
                stmt.setString(i++, param);
            }
            System.out.println("After : "+stmt);
            return stmt.executeQuery();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    

//    public String getQuery(String key)
//    {
//
//        System.out.println("Key received::"+key);
//        return queryMap.get(key);
//    }
//
//    public static void main(String[] args) throws SQLException
//    {
//        DBCon d=new DBCon();
//        System.out.println(d.getQuery("SELECT_ALLUSER_DETAILS"));
//        System.out.println(d.getQuery("SELECT_USER_DETAILS"));
//        System.out.println(d.getQuery("SELECT_USER_DETAILSjkk"));
//
//        d.runQuery(d.getQuery("SELECT_ALLUSER_DETAILS"));
//
//    }
}
