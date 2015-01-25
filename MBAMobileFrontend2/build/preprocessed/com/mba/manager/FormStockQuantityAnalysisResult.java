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
public class FormStockQuantityAnalysisResult implements CommandListener
{
    private List fstockquantityanalysisdailyresult;
    private StringItem object1;
    private Command back3;
    private List fstockquantityanalysis;

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

    static List getFormStockQuantityAnalysisDailyResult(String result)
    {
        FormStockQuantityAnalysisResult fc = new FormStockQuantityAnalysisResult();
        return fc.makeFormStockQuantityAnalysisDailyResult(result);
    }
    public List makeFormStockQuantityAnalysisDailyResult(String result)
    {
        fstockquantityanalysisdailyresult=new List("This is Your daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fstockquantityanalysisdailyresult.append("List of Items with stock :",null);
        returnResultMenu(fstockquantityanalysisdailyresult, result.toCharArray());
        fstockquantityanalysisdailyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        fstockquantityanalysisdailyresult.addCommand(back3);
        return fstockquantityanalysisdailyresult;
    }


    public void commandAction(Command c, Displayable d)
    {
        if(d==fstockquantityanalysisdailyresult)
        {
            if(c==back3)
            {
                fstockquantityanalysis = FormStockQuantityAnalysisMenu.getFormMenu();
                MobileMidlet.switchDisplay(fstockquantityanalysis);
            }
        }
    }

}
