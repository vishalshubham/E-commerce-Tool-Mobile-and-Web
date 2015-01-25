/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbconnection;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAL
 */
public class DBConnectionManager {


    private static int freePos[] = {0,0,0,0,0,0,0,0,0};
    private static int currentPos = 0;
    private static int size = 9;
    private static DBConnection[] dbCons = new DBConnection[size];

    static
    {
        for(int i = 0 ; i < size ; i++)
        {
            try {
                dbCons[i] = new DBConnection();
            } catch (Exception ex) {
                Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static DBConnection getDBConnection()
    {
        if(freePos[currentPos] == 0)
        {
            DBConnection conn = dbCons[currentPos];
            freePos[currentPos]  = 1;
            currentPos = (currentPos + 1) % size;
            return conn;
        }

        for(int i = (currentPos + 1) % size, cnt =0 ; cnt < size ; cnt++ , i = (i+1)%size )
        {
            if(freePos[i] == 0)
            {
                DBConnection conn = dbCons[currentPos];
                freePos[currentPos]  = 1;
                currentPos = (currentPos + 1) % size;
                return conn;
            }
        }
        return null;
    }


    public static void releaseDBConnection(DBConnection dBConnection)
    {
        for(int i = 0 ;i < size ; i++)
        {
            if(dbCons[i] == dBConnection)
            {
                freePos[i] = 0;
                System.out.println("Connection released to the pool");
            }
        }
    }
}
