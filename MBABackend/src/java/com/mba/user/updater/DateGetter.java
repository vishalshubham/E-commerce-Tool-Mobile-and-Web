
package com.mba.user.updater;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author VISHAL
 */
public class DateGetter
{
    static Calendar c = Calendar.getInstance();
    static SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
    static String dd;
    static String tt;
    static String getDate()
    {
        dd = s.format(c.getTime());
        System.out.println(dd);
        return dd;
    }
    static String getTime()
    {
        tt = t.format(c.getTime());
        System.out.println(tt);
        return tt;
    }


}
