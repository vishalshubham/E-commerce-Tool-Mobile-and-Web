
package com.mba.user.http.Transaction;

import java.util.Date;
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
    static String dd;
    static String getDate()
    {
        dd = s.format(c.getTime());
        System.out.println(dd);
        return dd;
    }
}
