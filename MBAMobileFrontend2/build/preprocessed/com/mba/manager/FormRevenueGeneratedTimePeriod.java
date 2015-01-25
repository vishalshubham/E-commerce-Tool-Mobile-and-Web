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

/**
 *
 * @author VISHAL
 */
public class FormRevenueGeneratedTimePeriod implements CommandListener
{
    private Form formdaily,formmonthly,formyearly,formbetweendates;
    private List fchange;
    private Command back;
    private List formhome;

    public List makeFormRevenueGeneratedTimePeriod()
    {
        fchange=new List("Choose the Time Period",List.IMPLICIT);
        fchange.append("DAILY", null);
        fchange.append("MONTHLY", null);
        fchange.append("YEARLY", null);
        fchange.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        fchange.addCommand(back);
        return fchange;
    }

    static List getFormRevenueGeneratedTimePeriod()
    {
        FormRevenueGeneratedTimePeriod fc = new FormRevenueGeneratedTimePeriod();
        return fc.makeFormRevenueGeneratedTimePeriod();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fchange.getString(fchange.getSelectedIndex()).equals("DAILY"))
                {
                    formdaily = FormRevenueGenerated.getFormDaily();
                    MobileMidlet.switchDisplay(formdaily);
                    System.out.println("Choosed : DAILY category sales analysis");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("MONTHLY"))
                {
                    formmonthly = FormRevenueGenerated.getFormMonthly();
                    MobileMidlet.switchDisplay(formmonthly);
                    System.out.println("Choosed : MONTHLY category sales analysis");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("YEARLY"))
                {
                    formyearly = FormRevenueGenerated.getFormYearly();
                    MobileMidlet.switchDisplay(formyearly);
                    System.out.println("Choosed : YEARLY category sales analysis");
                }
            }
        else if(c==back)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }
    }  
}
