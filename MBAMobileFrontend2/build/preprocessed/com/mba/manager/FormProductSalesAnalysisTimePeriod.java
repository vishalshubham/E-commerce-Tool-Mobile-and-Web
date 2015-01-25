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
public class FormProductSalesAnalysisTimePeriod implements CommandListener
{
    private Form formdaily,formmonthly,formyearly,formbetweendates;
    private List fchange;
    private Command back;
    private List formhome;

    public List makeFormGroupSalesAnalysisTimePeriod()
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

    static List getFormProductSalesAnalysisTimePeriod()
    {
        FormProductSalesAnalysisTimePeriod fc = new FormProductSalesAnalysisTimePeriod();
        return fc.makeFormGroupSalesAnalysisTimePeriod();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fchange.getString(fchange.getSelectedIndex()).equals("DAILY"))
                {
                    formdaily = FormProductSalesAnalysis.getFormDaily();
                    MobileMidlet.switchDisplay(formdaily);
                    System.out.println("Choosed : change name");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("MONTHLY"))
                {
                    formmonthly = FormProductSalesAnalysis.getFormMonthly();
                    MobileMidlet.switchDisplay(formmonthly);
                    System.out.println("Choosed : change address");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("YEARLY"))
                {
                    formyearly = FormProductSalesAnalysis.getFormYearly();
                    MobileMidlet.switchDisplay(formyearly);
                    System.out.println("Choosed : change contact");
                }
            }
        else if(c==back)
            {
                formhome = FormProductSalesAnalysisItemList.getFormMenu();
                MobileMidlet.switchDisplay(formhome);
            }

    }

}
