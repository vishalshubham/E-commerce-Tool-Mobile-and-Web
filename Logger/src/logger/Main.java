/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logger;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author VISHAL
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        PropertyConfigurator.configure("conf/log4j.properties");
        Logger logger = Logger.getLogger("service.log");
        int cnt = 1;
        while(true)
        {
            logger.debug("THIS IS FIRST LINE " + cnt++);
//            logger.error("THIS IS ERROR LINE " + cnt++);
            Thread.sleep(1000);

        }

    }

}
