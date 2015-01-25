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
class FormCategorySalesAnalysisTimePeriod implements CommandListener
{

    private Form formdaily,formmonthly,formyearly,formbetweendates;
    private List fchange;
    private Command back;
    private List formhome;

    public List makeFormCategorySalesAnalysisTimePeriod()
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

    static List getFormCategorySalesAnalysisTimePeriod()
    {
        FormCategorySalesAnalysisTimePeriod fc = new FormCategorySalesAnalysisTimePeriod();
        return fc.makeFormCategorySalesAnalysisTimePeriod();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fchange.getString(fchange.getSelectedIndex()).equals("DAILY"))
                {
                    formdaily = FormCategorySalesAnalysis.getFormDaily();
                    MobileMidlet.switchDisplay(formdaily);
                    System.out.println("Choosed : DAILY category sales analysis");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("MONTHLY"))
                {
                    formmonthly = FormCategorySalesAnalysis.getFormMonthly();
                    MobileMidlet.switchDisplay(formmonthly);
                    System.out.println("Choosed : MONTHLY category sales analysis");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("YEARLY"))
                {
                    formyearly = FormCategorySalesAnalysis.getFormYearly();
                    MobileMidlet.switchDisplay(formyearly);
                    System.out.println("Choosed : YEARLY category sales analysis");
                }
            }
        else if(c==back)
            {
                formhome = FormCategorySalesAnalysisMenu.getFormMenu();
                MobileMidlet.switchDisplay(formhome);
            }
    }  
//    SELECT SUM(ITEM_QUANTITY) FROM EXAMPLE1 WHERE ITEM_NO IN (SELECT ITEM_NO FROM EXAMPLE WHERE ITEM_GROUP='soap');

}
