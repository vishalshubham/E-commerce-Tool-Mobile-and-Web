/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author VISHAL
 */
public class FormZeroSalesAnalysisTimePeriod implements CommandListener
{
    private Command ok1,back1;
    private Command ok2,back2;

    private List formmenu;

    private List fresult;


    private Form fdaily,ftilldate,fyearly;
    private ChoiceGroup date1,month1,year1;

    private TextField threshold;

    public Form makeFormDaily()
    {
        fdaily=new Form("Choose the Date : ");
        date1 = new ChoiceGroup("Date", ChoiceGroup.POPUP);
        month1 = new ChoiceGroup("Month", ChoiceGroup.POPUP);
        year1 = new ChoiceGroup("Year", ChoiceGroup.POPUP);

        getDateFilled(date1);
        getMonthFilled(month1);
        getYearFilled(year1);

        fdaily.append(year1);
        fdaily.append(month1);
        fdaily.append(date1);

        ok1=new Command("Calculate",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fdaily.addCommand(ok1);
        fdaily.addCommand(back1);
        fdaily.setCommandListener(this);
        return fdaily;
    }
    static Form getFormDaily()
    {
        FormZeroSalesAnalysisTimePeriod fc = new FormZeroSalesAnalysisTimePeriod();
        return fc.makeFormDaily();
    }

    public Form makeFormTillDate()
    {
        ftilldate=new Form("Zero Sales Analysis : ");
        threshold=new TextField("Enter Threshold Quantity :",null,7,TextField.NUMERIC);
        
        ftilldate.append(threshold);

        ok2=new Command("Calculate",Command.OK,1);
        back2=new Command("Back",Command.BACK,1);
        ftilldate.addCommand(ok2);
        ftilldate.addCommand(back2);
        ftilldate.setCommandListener(this);
        return ftilldate;
    }
    static Form getFormTillDate()
    {
        FormZeroSalesAnalysisTimePeriod fc = new FormZeroSalesAnalysisTimePeriod();
        return fc.makeFormTillDate();
    }


     public void commandAction(Command c, Displayable d)
    {
        if(d==fdaily)
        {
            if(c==back1)
            {
                formmenu = FormZeroSalesAnalysisMenu.getFormZeroSalesAnalysisMenu();
                MobileMidlet.switchDisplay(formmenu);
            }
            else if(c==ok1)
            {
                String dailyDate = year1.getString(year1.getSelectedIndex()) + month1.getString(month1.getSelectedIndex()) + date1.getString(date1.getSelectedIndex())+"000000";
                String result1 = sendCategorySalesAnalysisDaily(dailyDate);
                if(result1 != null)
                {
                    fresult = FormZeroSalesAnalysisResult.getFormZeroSalesAnalysisDailyResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                    FormZeroSalesAnalysisResult.typeOfRequest = "1";
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
            }
        }
        else if(d==ftilldate)
        {
            if(c==back2)
            {
                formmenu = FormZeroSalesAnalysisMenu.getFormZeroSalesAnalysisMenu();
                MobileMidlet.switchDisplay(formmenu);
            }
            else if(c==ok2)
            {
                if(threshold.getString().length()>0)
                {


                String thresholdValue = threshold.getString();
                String result1 = sendZeroSalesAnalysisTillDate(thresholdValue);
                if(result1 != null)
                {
                    fresult = FormZeroSalesAnalysisResult.getFormZeroSalesAnalysisTillDateResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                    FormZeroSalesAnalysisResult.typeOfRequest = "2";
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
                }
                else
                {
                     Alert alert= new Alert("SORRY::","Please Enter correct number",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
            }
        }
    }


    public void getDateFilled(ChoiceGroup date1)
    {
        date1.append("01", null);
        date1.append("02", null);
        date1.append("03", null);
        date1.append("04", null);
        date1.append("05", null);
        date1.append("06", null);
        date1.append("07", null);
        date1.append("08", null);
        date1.append("09", null);
        date1.append("10", null);
        date1.append("11", null);
        date1.append("12", null);
        date1.append("13", null);
        date1.append("14", null);
        date1.append("15", null);
        date1.append("16", null);
        date1.append("17", null);
        date1.append("18", null);
        date1.append("19", null);
        date1.append("20", null);
        date1.append("21", null);
        date1.append("22", null);
        date1.append("23", null);
        date1.append("24", null);
        date1.append("25", null);
        date1.append("26", null);
        date1.append("27", null);
        date1.append("28", null);
        date1.append("29", null);
        date1.append("30", null);
        date1.append("31", null);
    }
    public void getMonthFilled(ChoiceGroup date1)
    {
        date1.append("01", null);
        date1.append("02", null);
        date1.append("03", null);
        date1.append("04", null);
        date1.append("05", null);
        date1.append("06", null);
        date1.append("07", null);
        date1.append("08", null);
        date1.append("09", null);
        date1.append("10", null);
        date1.append("11", null);
        date1.append("12", null);
    }
    public void getYearFilled(ChoiceGroup date1)
    {
        date1.append("2005", null);
        date1.append("2006", null);
        date1.append("2007", null);
        date1.append("2008", null);
        date1.append("2009", null);
        date1.append("2010", null);
        date1.append("2011", null);
    }

    private String sendZeroSalesAnalysisTillDate(String thresholdValue)
    {
          try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.println(spacedate + " : date is this ");
            while(spacedate.indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
            }
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=31&requestsourceid=1&threshold=" + thresholdValue + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
            dis = new DataInputStream(con.openInputStream());

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

            if(response != null/* && response.equals("valid")*/)
            {
                System.out.println(response + "this is our response");
                return response;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    private String sendCategorySalesAnalysisDaily(String dailyDate)
    {
        try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.println(spacedate + " : date is this ");
            while(spacedate.indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
            }
            while(dailyDate.indexOf(" ")!=-1)
            {
                dailyDate = StringUtilities.replaceData(dailyDate, " ", "%20");
            }
            System.out.println(spacedate + " : date is this-- "+"--"+dailyDate);
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=32&requestsourceid=1" + "&dailydate="+ dailyDate + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
            dis = new DataInputStream(con.openInputStream());

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

            if(response != null/* && response.equals("valid")*/)
            {
                return response;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
