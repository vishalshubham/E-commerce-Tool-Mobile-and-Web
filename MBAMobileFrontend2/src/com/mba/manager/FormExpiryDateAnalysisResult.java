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
public class FormExpiryDateAnalysisResult implements CommandListener
{
     private List fexpirydateanalysisdailyresult;
    private StringItem object1;
    private Command back3;
    private List fexpirydateanalysis;

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

    static List getFormExpiryDateAnalysisDailyResult(String result)
    {
        FormExpiryDateAnalysisResult fc = new FormExpiryDateAnalysisResult();
        return fc.makeFormExpiryDateAnalysisDailyResult(result);
    }
    public List makeFormExpiryDateAnalysisDailyResult(String result)
    {
        fexpirydateanalysisdailyresult=new List("This is Your Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fexpirydateanalysisdailyresult.append("List Of Items with days to Expire :",null);
        returnResultMenu(fexpirydateanalysisdailyresult, result.toCharArray());
        fexpirydateanalysisdailyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        fexpirydateanalysisdailyresult.addCommand(back3);
        return fexpirydateanalysisdailyresult;
    }


    public void commandAction(Command c, Displayable d)
    {
        if(d==fexpirydateanalysisdailyresult)
        {
            if(c==back3)
            {
                fexpirydateanalysis = FormExpiryDateAnalysisMenu.getFormMenu();
                MobileMidlet.switchDisplay(fexpirydateanalysis);
            }
        }
    }
}
