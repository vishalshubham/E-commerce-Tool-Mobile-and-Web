
package com.mba.manager.main;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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


    public DateGetter()
    {
        
    }

    public int daysBetweenGetter(int firstYear, int firstMonth, int firstDay, int secondYear, int secondMonth, int secondDay)
    {
        System.out.println("first date : "+ firstDay);
        System.out.println("first nomth : "+ firstMonth);
        System.out.println("first yaer : "+ firstYear);
        System.out.println(" date : "+ secondDay);
        System.out.println(" month : "+ secondMonth);
        System.out.println(" year : "+ secondYear);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal1.set(firstYear, firstMonth, firstDay);
        cal2.set(secondYear, secondMonth, secondDay);

        int days =  daysBetween(cal1.getTime(), cal2.getTime());
        System.out.println("days remaining :: " + days);
        return days;
    }

    public int daysBetween(Date d1, Date d2)
    {
     return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

















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

    public String getTodayYear()
    {
        dd = s.format(c.getTime());
        System.out.println(dd + "Date getter : ");
        String ddd = formatTodayYear(dd);
        System.out.println("ddd is : "+ddd);
        return ddd;
    }
    private String formatTodayYear(String date)
    {
        char[] adate = date.toCharArray();
        String formatDate = "";
        formatDate = formatDate + adate[0] + adate[1] + adate[2] + adate[3];
        return formatDate;
    }
    public String getTodayMonth()
    {
        dd = s.format(c.getTime());
        System.out.println(dd + "Date getter : ");
        String ddd = formatTodayMonth(dd);
        System.out.println("ddd is : "+ddd);
        return ddd;
    }
    private String formatTodayMonth(String date)
    {
        char[] adate = date.toCharArray();
        String formatDate = "";
        formatDate = formatDate + adate[5] + adate[6];
        return formatDate;
    }
    public String getTodayDate()
    {
        dd = s.format(c.getTime());
        System.out.println(dd + "Date getter : ");
        String ddd = formatTodayDate(dd);
        System.out.println("ddd is : "+ddd);
        return ddd;
    }
    private String formatTodayDate(String date)
    {
        char[] adate = date.toCharArray();
        String formatDate = "";
        formatDate = formatDate + adate[8] + adate[9];
        return formatDate;
    }

    public static void main(String args[])
    {
        DateGetter d = new DateGetter();
        System.out.println("date is :" + d.daysBetweenGetter(2011, 05, 01, 2011, 07, 01));
    }


}
