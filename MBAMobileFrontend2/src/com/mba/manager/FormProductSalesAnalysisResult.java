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
public class FormProductSalesAnalysisResult implements CommandListener
{
    static String typeOfRequest;

    private List fproductsalesanalysisdailyresult;
    private StringItem object1;
    private Command back1,back2,back3;
    private List fproductsalesanalysismonthlyresult;
    private List fproductsalesanalysisyearlyresult;
    private Form fproductsalesanalysis;

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



    static List getFormProductSalesAnalysisDailyResult(String result)
    {
        FormProductSalesAnalysisResult fc = new FormProductSalesAnalysisResult();
        return fc.makeFormProductSalesAnalysisDailyResult(result);
    }

    public List makeFormProductSalesAnalysisDailyResult(String result)
    {
        fproductsalesanalysisdailyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fproductsalesanalysisdailyresult.append("Sales Information :",null);
        returnResultMenu(fproductsalesanalysisdailyresult, result.toCharArray());
        fproductsalesanalysisdailyresult.setCommandListener(this);
        back1=new Command("Back",Command.BACK,1);
        fproductsalesanalysisdailyresult.addCommand(back1);
        return fproductsalesanalysisdailyresult;
    }




    static List getFormProductSalesAnalysisMonthlyResult(String result)
    {
        FormProductSalesAnalysisResult fc = new FormProductSalesAnalysisResult();
        return fc.makeFormProductSalesAnalysisMonthlyResult(result);
    }
    public List makeFormProductSalesAnalysisMonthlyResult(String result)
    {
        fproductsalesanalysismonthlyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fproductsalesanalysismonthlyresult.append("Sales Information :",null);
        returnResultMenu(fproductsalesanalysismonthlyresult, result.toCharArray());
        fproductsalesanalysismonthlyresult.setCommandListener(this);
        back2=new Command("Back",Command.BACK,1);
        fproductsalesanalysismonthlyresult.addCommand(back2);
        return fproductsalesanalysismonthlyresult;
    }




    static List getFormProductSalesAnalysisYearlyResult(String result)
    {
        FormProductSalesAnalysisResult fc = new FormProductSalesAnalysisResult();
        return fc.makeFormProductSalesAnalysisYearlyResult(result);
    }
    public List makeFormProductSalesAnalysisYearlyResult(String result)
    {
        fproductsalesanalysisyearlyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fproductsalesanalysisyearlyresult.append("Sales Information :",null);
        returnResultMenu(fproductsalesanalysisyearlyresult, result.toCharArray());
        fproductsalesanalysisyearlyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        fproductsalesanalysisyearlyresult.addCommand(back3);
        return fproductsalesanalysisyearlyresult;
    }

    public void commandAction(Command c, Displayable d)
    {
        if(d==fproductsalesanalysisdailyresult)
        {
            if(c==back1)
            {
                fproductsalesanalysis = FormProductSalesAnalysis.getFormDaily();
                MobileMidlet.switchDisplay(fproductsalesanalysis);
            }
        }
        else if(d==fproductsalesanalysismonthlyresult)
        {
            if(c==back2)
            {
                fproductsalesanalysis = FormProductSalesAnalysis.getFormMonthly();
                MobileMidlet.switchDisplay(fproductsalesanalysis);
            }
        }
        else if(d==fproductsalesanalysisyearlyresult)
        {
            if(c==back3)
            {
                fproductsalesanalysis = FormProductSalesAnalysis.getFormYearly();
                MobileMidlet.switchDisplay(fproductsalesanalysis);
            }
        }
    }

}
