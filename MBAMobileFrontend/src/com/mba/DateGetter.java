
package com.mba;

import java.util.Calendar;
import java.util.Date;
import javax.microedition.lcdui.DateField;
//import java.text.SimpleDateFormat;

/**
 *
 * @author VISHAL
 */
public class DateGetter
{
    private Calendar c = Calendar.getInstance();
    private String dd;
    private String tt = "";
    private String mm = "";
    private Date date;
    private DateField currentdate;
    private char[] arr;

    public String getDate()
    {
        date = new Date();
        currentdate = new DateField("", DateField.DATE_TIME);
        currentdate.setDate(date);
        System.out.println(date.toString() + "this is my date of sending request : "+date.toString().length());
        arr = date.toString().toCharArray();
        for(int i = 4 ; i < 7 ; i++ )
        {
            mm = mm + arr[i];
        }
        
        String u = "";
        u = u + arr[30]+ arr[31]+ arr[32]+ arr[33] + getMonth(mm) + arr[8] + arr[9] + arr[11]+ arr[12] + arr[14]+ arr[15]+ arr[17]+ arr[18];
        System.out.println(u + "this is date sent from mobile");
        return u;
    }

    public String getMonth(String mm)
    {
        String month = "";
        if(mm.equals("Jan"))
        {
            month = month + "01";
        }
        else if(mm.equals("Feb"))
        {
            month = month + "02";
        }
        else if(mm.equals("Mar"))
        {
            month = month + "03";
        }
        else if(mm.equals("Apr"))
        {
            month = month + "04";
        }
        else if(mm.equals("May"))
        {
            month = month + "05";
        }
        else if(mm.equals("Jun"))
        {
            month = month + "06";
        }
        else if(mm.equals("Jul"))
        {
            month = month + "07";
        }
        else if(mm.equals("Aug"))
        {
            month = month + "08";
        }
        else if(mm.equals("Sep"))
        {
            month = month + "09";
        }
        else if(mm.equals("Oct"))
        {
            month = month + "10";
        }
        else if(mm.equals("Nov"))
        {
            month = month + "11";
        }
        else if(mm.equals("Dec"))
        {
            month = month + "12";
        }

        return month;
    }
}
