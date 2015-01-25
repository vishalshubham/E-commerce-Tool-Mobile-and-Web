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
public class FormHomeDeliveryAnalysisTimePeriod implements CommandListener
{
    private Form formdaily,formmonthly,formyearly;
    private List ftimeperiod;
    private Command back;
    private List formhome;

    public List makeFormHomeDeliveryAnalysisTimePeriod()
    {
        ftimeperiod=new List("Choose the Time Period",List.IMPLICIT);
        ftimeperiod.append("DAILY", null);
        ftimeperiod.append("MONTHLY", null);
        ftimeperiod.append("YEARLY", null);
        ftimeperiod.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        ftimeperiod.addCommand(back);
        return ftimeperiod;
    }

    static List getFormHomeDeliveryAnalysisTimePeriod()
    {
        FormHomeDeliveryAnalysisTimePeriod fc = new FormHomeDeliveryAnalysisTimePeriod();
        return fc.makeFormHomeDeliveryAnalysisTimePeriod();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(ftimeperiod.getString(ftimeperiod.getSelectedIndex()).equals("DAILY"))
                {
                    formdaily = FormHomeDeliveryAnalysis.getFormDaily();
                    MobileMidlet.switchDisplay(formdaily);
                    System.out.println("Choosed : DAILY category sales analysis");
                }
                else if(ftimeperiod.getString(ftimeperiod.getSelectedIndex()).equals("MONTHLY"))
                {
                    formmonthly = FormHomeDeliveryAnalysis.getFormMonthly();
                    MobileMidlet.switchDisplay(formmonthly);
                    System.out.println("Choosed : MONTHLY category sales analysis");
                }
                else if(ftimeperiod.getString(ftimeperiod.getSelectedIndex()).equals("YEARLY"))
                {
                    formyearly = FormHomeDeliveryAnalysis.getFormYearly();
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
