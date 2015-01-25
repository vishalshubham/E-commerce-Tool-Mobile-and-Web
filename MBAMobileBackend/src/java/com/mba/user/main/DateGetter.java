
package com.mba.user.main;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author VISHAL
 */
public class DateGetter
{
    private Calendar c = Calendar.getInstance();
    private SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
    private String dd;
    private String tt;
    private Date date;

    public String getDate()
    {
        dd = s.format(c.getTime());
        System.out.println(dd + "Date getter : ");
        String ddd = formatDate(dd);
        System.out.println("ddd is : "+ddd);
        return ddd;
    }

    private String formatDate(String date)
    {
        char[] adate = date.toCharArray();
        String formatDate = "";
        formatDate = formatDate + adate[0] + adate[1] + adate[2] + adate[3] + adate[5] + adate[6] + adate[8] + adate[9] + adate[11] + adate[12] + adate[14] + adate[15] + adate[17] + adate[18];
        return formatDate;
    }

    private String getTime()
    {
        tt = t.format(c.getTime());
        System.out.println(tt);
        return tt;
    }


}
