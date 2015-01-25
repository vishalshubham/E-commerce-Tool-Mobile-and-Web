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
public class FormHomeDeliveryAnalysisResult implements CommandListener
{
    
    
    private List fhomedeliveryanalysisdailyresult;
    private List fhomedeliveryanalysismonthlyresult;
    private List fhomedeliveryanalysisyearlyresult;
    
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
    private Form fhomedeliveryanalysis;

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


    static List getFormHomeDeliveryAnalysisDailyResult(String result)
    {
        FormHomeDeliveryAnalysisResult fc = new FormHomeDeliveryAnalysisResult();
        return fc.makeFormHomeDeliveryAnalysisDailyResult(result);
    }
    public List makeFormHomeDeliveryAnalysisDailyResult(String result)
    {
        fhomedeliveryanalysisdailyresult=new List("This is Your Daily Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fhomedeliveryanalysisdailyresult.append("Home Delivery Information :",null);
        returnResultMenu(fhomedeliveryanalysisdailyresult, result.toCharArray());
        fhomedeliveryanalysisdailyresult.setCommandListener(this);
        back1=new Command("Back",Command.BACK,1);
        fhomedeliveryanalysisdailyresult.addCommand(back1);
        return fhomedeliveryanalysisdailyresult;
    }

    
    static List getFormHomeDeliveryAnalysisMonthlyResult(String result)
    {
        FormHomeDeliveryAnalysisResult fc = new FormHomeDeliveryAnalysisResult();
        return fc.makeFormHomeDeliveryAnalysisMonthlyResult(result);
    }
    public List makeFormHomeDeliveryAnalysisMonthlyResult(String result)
    {
        fhomedeliveryanalysismonthlyresult=new List("This is Your Monthly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fhomedeliveryanalysismonthlyresult.append("Home Delivery Information :",null);
        returnResultMenu(fhomedeliveryanalysismonthlyresult, result.toCharArray());
        fhomedeliveryanalysismonthlyresult.setCommandListener(this);
        back2=new Command("Back",Command.BACK,1);
        fhomedeliveryanalysismonthlyresult.addCommand(back2);
        return fhomedeliveryanalysismonthlyresult;
    }


    static List getFormHomeDeliveryAnalysisYearlyResult(String result)
    {
        FormHomeDeliveryAnalysisResult fc = new FormHomeDeliveryAnalysisResult();
        return fc.makeFormHomeDeliveryAnalysisYearlyResult(result);
    }
    public List makeFormHomeDeliveryAnalysisYearlyResult(String result)
    {
        fhomedeliveryanalysisyearlyresult=new List("This is Your Yearly Result",List.IMPLICIT);
        object1 = new StringItem(result, result);
        fhomedeliveryanalysisyearlyresult.append("Home Delivery Information :",null);
        returnResultMenu(fhomedeliveryanalysisyearlyresult, result.toCharArray());
        fhomedeliveryanalysisyearlyresult.setCommandListener(this);
        back3=new Command("Back",Command.BACK,1);
        fhomedeliveryanalysisyearlyresult.addCommand(back3);
        return fhomedeliveryanalysisyearlyresult;
    }

    
    public void commandAction(Command c, Displayable d)
    {
        if(d==fhomedeliveryanalysisdailyresult)
        {
            if(c==back1)
            {
                fhomedeliveryanalysis = FormHomeDeliveryAnalysis.getFormDaily();
                MobileMidlet.switchDisplay(fhomedeliveryanalysis);
            }
        }
        else if(d==fhomedeliveryanalysismonthlyresult)
        {
            if(c==back2)
            {
                fhomedeliveryanalysis = FormHomeDeliveryAnalysis.getFormMonthly();
                MobileMidlet.switchDisplay(fhomedeliveryanalysis);
            }
        }
        else if(d==fhomedeliveryanalysisyearlyresult)
        {
            if(c==back3)
            {
                fhomedeliveryanalysis = FormHomeDeliveryAnalysis.getFormYearly();
                MobileMidlet.switchDisplay(fhomedeliveryanalysis);
            }
        }
    }
}
