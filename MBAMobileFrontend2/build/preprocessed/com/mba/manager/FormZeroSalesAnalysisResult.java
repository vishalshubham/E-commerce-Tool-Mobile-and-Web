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
public class FormZeroSalesAnalysisResult implements CommandListener
{


    private List fzerosalesanalysisdailyresult;
    private List fzerosalesanalysistilldateresult;

    private StringItem object1;
    private StringItem object2;
    private StringItem object3;
    private StringItem object4;
    private StringItem object5;
    private StringItem object6;
    private StringItem object7;
    private StringItem object8;
    private StringItem object9;
    private StringItem object10;
    private StringItem object11;
    private StringItem object12;
    private StringItem object13;
    private StringItem object14;
    private StringItem object15;

    private StringItem[] objects;
    private Command back1,back2,back3;

    static String typeOfRequest;

    private Form fzerosalesanalysis;


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


    static List getFormZeroSalesAnalysisDailyResult(String result)
    {
        FormZeroSalesAnalysisResult fc = new FormZeroSalesAnalysisResult();
        return fc.makeFormZeroSalesAnalysisDailyResult(result);
    }

    public List makeFormZeroSalesAnalysisDailyResult(String result)
    {
        fzerosalesanalysisdailyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fzerosalesanalysisdailyresult.append("Items that were not sold :",null);
        returnResultMenu(fzerosalesanalysisdailyresult, result.toCharArray());
        fzerosalesanalysisdailyresult.setCommandListener(this);
        back1=new Command("Back",Command.BACK,1);
        fzerosalesanalysisdailyresult.addCommand(back1);
        return fzerosalesanalysisdailyresult;
    }


    static List getFormZeroSalesAnalysisTillDateResult(String result)
    {
        FormZeroSalesAnalysisResult fc = new FormZeroSalesAnalysisResult();
        return fc.makeFormZeroSalesAnalysisTillDateResult(result);
    }
    public List makeFormZeroSalesAnalysisTillDateResult(String result)
    {
        fzerosalesanalysistilldateresult=new List("This is Your Till Date Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fzerosalesanalysistilldateresult.append("Items that were not sold :",null);
        returnResultMenu(fzerosalesanalysistilldateresult, result.toCharArray());
        fzerosalesanalysistilldateresult.setCommandListener(this);
        back2=new Command("Back",Command.BACK,1);
        fzerosalesanalysistilldateresult.addCommand(back2);
        return fzerosalesanalysistilldateresult;
    }

    public void commandAction(Command c, Displayable d)
    {
        if(d==fzerosalesanalysisdailyresult)
        {
            if(c==back1)
            {
                fzerosalesanalysis = FormZeroSalesAnalysisTimePeriod.getFormDaily();
                MobileMidlet.switchDisplay(fzerosalesanalysis);
            }
        }
        else if(d==fzerosalesanalysistilldateresult)
        {
            if(c==back2)
            {
                fzerosalesanalysis = FormZeroSalesAnalysisTimePeriod.getFormTillDate();
                MobileMidlet.switchDisplay(fzerosalesanalysis);
            }
        }
    }

}
