/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author VISHAL
 */
public class FormRevenueGeneratedResult implements CommandListener
{
     private List frevenuegenerateddailyresult;
    private List frevenuegeneratedmonthlyresult;
    private List frevenuegeneratedyearlyresult;

    private StringItem object1;

    private StringItem[] objects;
    private Command back1,back2,back3;

    static String typeOfRequest;
    private Form frevenuegenerated;


    public void returnResultMenu(List cg,char menu[])
    {
        String name="";
        int i;
        for(i=0;i<menu.length;i++)
        {

                    if(menu[i]!=',')
                    {
                        name=name+menu[i];
                    }
                    else
                    {
                        cg.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    static List getFormRevenueGeneratedDailyResult(String result)
    {
        FormRevenueGeneratedResult fc = new FormRevenueGeneratedResult();
        return fc.makeFormRevenueGeneratedDailyResult(result);
    }
    public List makeFormRevenueGeneratedDailyResult(String result)
    {
        frevenuegenerateddailyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        frevenuegenerateddailyresult.append("Total Revenue Generated (in Rs.):",null);
        returnResultMenu(frevenuegenerateddailyresult, result.toCharArray());
        frevenuegenerateddailyresult.setCommandListener(this);
        back1=new Command("Back",Command.BACK,1);
        frevenuegenerateddailyresult.addCommand(back1);
        return frevenuegenerateddailyresult;
    }


    static List getFormRevenueGeneratedMonthlyResult(String result)
    {
        FormRevenueGeneratedResult fc = new FormRevenueGeneratedResult();
        return fc.makeFormRevenueGeneratedMonthlyResult(result);
    }
    public List makeFormRevenueGeneratedMonthlyResult(String result)
    {
        frevenuegeneratedmonthlyresult=new List("This is Your Monthly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        frevenuegeneratedmonthlyresult.append("Total Revenue Generated (in Rs.):",null);
        returnResultMenu(frevenuegeneratedmonthlyresult, result.toCharArray());
        frevenuegeneratedmonthlyresult.setCommandListener(this);
        back2=new Command("Back",Command.BACK,1);
        frevenuegeneratedmonthlyresult.addCommand(back2);
        return frevenuegeneratedmonthlyresult;
    }


    static List getFormRevenueGeneratedYearlyResult(String result)
    {
        FormRevenueGeneratedResult fc = new FormRevenueGeneratedResult();
        return fc.makeFormRevenueGeneratedYearlyResult(result);
    }
    public List makeFormRevenueGeneratedYearlyResult(String result)
    {
        frevenuegeneratedyearlyresult=new List("This is Your Yearly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        frevenuegeneratedyearlyresult.append("Total Revenue Generated (in Rs.):",null);
        returnResultMenu(frevenuegeneratedyearlyresult, result.toCharArray());
        frevenuegeneratedyearlyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        frevenuegeneratedyearlyresult.addCommand(back3);
        return frevenuegeneratedyearlyresult;
    }


    public void commandAction(Command c, Displayable d)
    {
        if(d==frevenuegenerateddailyresult)
        {
            if(c==back1)
            {
                frevenuegenerated = FormRevenueGenerated.getFormDaily();
                MobileMidlet.switchDisplay(frevenuegenerated);
            }
        }
        else if(d==frevenuegeneratedmonthlyresult)
        {
            if(c==back2)
            {
                frevenuegenerated = FormRevenueGenerated.getFormMonthly();
                MobileMidlet.switchDisplay(frevenuegenerated);
            }
        }
        else if(d==frevenuegeneratedyearlyresult)
        {
            if(c==back3)
            {
                frevenuegenerated = FormRevenueGenerated.getFormYearly();
                MobileMidlet.switchDisplay(frevenuegenerated);
            }
        }
    }
}
