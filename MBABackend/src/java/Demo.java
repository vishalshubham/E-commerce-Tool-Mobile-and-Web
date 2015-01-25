
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author VISHAL
 */
public class Demo
{
     public static void main(String[] args) throws InterruptedException {

        PropertyConfigurator.configure("conf/log4j.properties");
        Logger logger = Logger.getLogger("db.log");
        int cnt = 1;
        while(true)
        {
            logger.debug("THIS IS FIRST LINE " + cnt++);
//            logger.error("THIS IS ERROR LINE " + cnt++);
            Thread.sleep(1000);

        }

    }
}
