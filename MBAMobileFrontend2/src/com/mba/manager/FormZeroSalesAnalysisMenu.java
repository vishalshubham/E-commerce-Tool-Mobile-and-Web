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
public class FormZeroSalesAnalysisMenu implements CommandListener
{
    private Form formdaily,formtilltoday;
    private List fzeromenu;
    private Command back;
    private List formhome;

    public List makeFormZeroSalesAnalysisMenu()
    {
        fzeromenu=new List("Choose the Time Interval : ",List.IMPLICIT);
        fzeromenu.append("On A Particular Date", null);
        fzeromenu.append("Till Today's Date", null);
        fzeromenu.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        fzeromenu.addCommand(back);
        return fzeromenu;
    }

    static List getFormZeroSalesAnalysisMenu()
    {
        FormZeroSalesAnalysisMenu fc = new FormZeroSalesAnalysisMenu();
        return fc.makeFormZeroSalesAnalysisMenu();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fzeromenu.getString(fzeromenu.getSelectedIndex()).equals("On A Particular Date"))
                {
                    formdaily = FormZeroSalesAnalysisTimePeriod.getFormDaily();
                    MobileMidlet.switchDisplay(formdaily);
                    System.out.println("Choosed : On a particular date zero sales analysis");
                }
                else if(fzeromenu.getString(fzeromenu.getSelectedIndex()).equals("Till Today's Date"))
                {
                    formtilltoday = FormZeroSalesAnalysisTimePeriod.getFormTillDate();
                    MobileMidlet.switchDisplay(formtilltoday);
                    System.out.println("Choosed : till today's date zero sales analysis");
                }
            }
        else if(c==back)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }
    }
}
