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
public class FormCategorySalesAnalysisResult implements CommandListener
{

    private List fcategorysalesanalysisdailyresult;
    private List fcategorysalesanalysismonthlyresult;
    private List fcategorysalesanalysisyearlyresult;
    
    private StringItem object1;

    
    
    private Command back1,back2,back3;

    static String typeOfRequest;
    private Form fcategorysalesanalysis;

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


    static List getFormCategorySalesAnalysisDailyResult(String result)
    {
        FormCategorySalesAnalysisResult fc = new FormCategorySalesAnalysisResult();
        return fc.makeFormCategorySalesAnalysisDailyResult(result);
    }
    public List makeFormCategorySalesAnalysisDailyResult(String result)
    {
        fcategorysalesanalysisdailyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem("List of Items:", null);
        fcategorysalesanalysisdailyresult.append("List Of Items : ", null);
        returnResultMenu(fcategorysalesanalysisdailyresult, result.toCharArray());
        fcategorysalesanalysisdailyresult.setCommandListener(this);
        back1=new Command("Back",Command.BACK,1);
        fcategorysalesanalysisdailyresult.addCommand(back1);
        return fcategorysalesanalysisdailyresult;
    }

    
    static List getFormCategorySalesAnalysisMonthlyResult(String result)
    {
        FormCategorySalesAnalysisResult fc = new FormCategorySalesAnalysisResult();
        return fc.makeFormCategorySalesAnalysisMonthlyResult(result);
    }
    public List makeFormCategorySalesAnalysisMonthlyResult(String result)
    {
        fcategorysalesanalysismonthlyresult=new List("This is Your Monthly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fcategorysalesanalysismonthlyresult.append("List Of Items : ", null);
        returnResultMenu(fcategorysalesanalysismonthlyresult, result.toCharArray());
        fcategorysalesanalysismonthlyresult.setCommandListener(this);
        back2=new Command("Back",Command.BACK,1);
        fcategorysalesanalysismonthlyresult.addCommand(back2);
        return fcategorysalesanalysismonthlyresult;
    }


    static List getFormCategorySalesAnalysisYearlyResult(String result)
    {
        FormCategorySalesAnalysisResult fc = new FormCategorySalesAnalysisResult();
        return fc.makeFormCategorySalesAnalysisYearlyResult(result);
    }
    public List makeFormCategorySalesAnalysisYearlyResult(String result)
    {
        fcategorysalesanalysisyearlyresult=new List("This is Your Yearly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fcategorysalesanalysisyearlyresult.append("List Of Items : ", null);
        returnResultMenu(fcategorysalesanalysisyearlyresult, result.toCharArray());
        fcategorysalesanalysisyearlyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        fcategorysalesanalysisyearlyresult.addCommand(back3);
        return fcategorysalesanalysisyearlyresult;
    }

    
    public void commandAction(Command c, Displayable d)
    {
        if(d==fcategorysalesanalysisdailyresult)
        {
            if(c==back1)
            {
                fcategorysalesanalysis = FormCategorySalesAnalysis.getFormDaily();
                MobileMidlet.switchDisplay(fcategorysalesanalysis);
            }
        }
        else if(d==fcategorysalesanalysismonthlyresult)
        {
            if(c==back2)
            {
                fcategorysalesanalysis = FormCategorySalesAnalysis.getFormMonthly();
                MobileMidlet.switchDisplay(fcategorysalesanalysis);
            }
        }
        else if(d==fcategorysalesanalysisyearlyresult)
        {
            if(c==back3)
            {
                fcategorysalesanalysis = FormCategorySalesAnalysis.getFormYearly();
                MobileMidlet.switchDisplay(fcategorysalesanalysis);
            }
        }
    }
}
