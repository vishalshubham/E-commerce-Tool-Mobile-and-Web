
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

/**
 *
 * @author VISHAL
 */
public class FormProductSalesAnalysis implements CommandListener
{
    private Command ok1,back1;
    private Command ok2,back2;
    private Command ok3,back3;
    private Command ok4,back4;


    private Form fdaily,fmonthly,fyearly,fbetweendates;
    private List formtime;
    private ChoiceGroup date1,month1,year1;
    private ChoiceGroup date2,month2,year2;
    private ChoiceGroup date3,month3,year3;
    private ChoiceGroup date4,month4,year4;
    private ChoiceGroup date5,month5,year5;
    private List fresult;


     public Form makeFormDaily()
    {
        fdaily=new Form("Choose the Date : ");
        date1 = new ChoiceGroup("Date", ChoiceGroup.POPUP);
        month1 = new ChoiceGroup("Month", ChoiceGroup.POPUP);
        year1 = new ChoiceGroup("Year", ChoiceGroup.POPUP);

        getDateFilled(date1);
        getMonthFilled(month1);
        getYearFilled(year1);

        fdaily.append(date1);
        fdaily.append(month1);
        fdaily.append(year1);

        ok1=new Command("Calculate",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fdaily.addCommand(ok1);
        fdaily.addCommand(back1);
        fdaily.setCommandListener(this);
        return fdaily;
    }


    static Form getFormDaily()
    {
        FormProductSalesAnalysis fc = new FormProductSalesAnalysis();
        return fc.makeFormDaily();
    }

     public Form makeFormMonthly()
    {
        fmonthly=new Form("Choose the Month : ");
        month2 = new ChoiceGroup("Month", ChoiceGroup.POPUP);
        year2 = new ChoiceGroup("Year", ChoiceGroup.POPUP);

        getMonthFilled(month2);
        getYearFilled(year2);

        fmonthly.append(month2);
        fmonthly.append(year2);

        ok2=new Command("Calculate",Command.OK,1);
        back2=new Command("Back",Command.BACK,1);
        fmonthly.addCommand(ok2);
        fmonthly.addCommand(back2);
        fmonthly.setCommandListener(this);
        return fmonthly;
    }

    static Form getFormMonthly()
    {
        FormProductSalesAnalysis fc = new FormProductSalesAnalysis();
        return fc.makeFormMonthly();
    }

     public Form makeFormYearly()
    {
        fyearly=new Form("Choose the Year : ");
        year3 = new ChoiceGroup("Year", ChoiceGroup.POPUP);

        getYearFilled(year3);

        fyearly.append(year3);

        ok3=new Command("Calculate",Command.OK,1);
        back3=new Command("Back",Command.BACK,1);
        fyearly.addCommand(ok3);
        fyearly.addCommand(back3);
        fyearly.setCommandListener(this);
        return fyearly;
    }

    static Form getFormYearly()
    {
        FormProductSalesAnalysis fc = new FormProductSalesAnalysis();
        return fc.makeFormYearly();
    }

    
     public void commandAction(Command c, Displayable d)
    {
        if(d==fdaily)
        {
            if(c==back1)
            {
                formtime = FormProductSalesAnalysisTimePeriod.getFormProductSalesAnalysisTimePeriod();
                MobileMidlet.switchDisplay(formtime);
            }
            else if(c==ok1)
            {
                String dailyDate = year1.getString(year1.getSelectedIndex()) + month1.getString(month1.getSelectedIndex()) + date1.getString(date1.getSelectedIndex())+"000000";
                String category = FormProductSalesAnalysisMenu.item;
                String item = processItem(FormProductSalesAnalysisItemList.item);
                String result1 = sendProductSalesAnalysisDaily(item, category, dailyDate);
                if(result1 != null)
                {
                    fresult = FormProductSalesAnalysisResult.getFormProductSalesAnalysisDailyResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                    FormProductSalesAnalysisResult.typeOfRequest = "1";
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
        else if(d==fmonthly)
        {
            if(c==back2)
            {
                formtime = FormProductSalesAnalysisTimePeriod.getFormProductSalesAnalysisTimePeriod();
                MobileMidlet.switchDisplay(formtime);
            }
            else if(c==ok2)
            {
                String dailyDate = year2.getString(year2.getSelectedIndex()) + month2.getString(month2.getSelectedIndex())+"00000000";
                String category = FormProductSalesAnalysisMenu.item;
                String item = processItem(FormProductSalesAnalysisItemList.item);
                String result1 = sendProductSalesAnalysisMonthly(item, category, dailyDate);
                if(result1 != null)
                {
                    fresult = FormProductSalesAnalysisResult.getFormProductSalesAnalysisMonthlyResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                    FormProductSalesAnalysisResult.typeOfRequest = "1";
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
        else if(d==fyearly)
        {
            if(c==back3)
            {
                formtime = FormProductSalesAnalysisTimePeriod.getFormProductSalesAnalysisTimePeriod();
                MobileMidlet.switchDisplay(formtime);
            }
            else if(c==ok3)
            {
                String dailyDate = year3.getString(year3.getSelectedIndex()) + "0000000000";
                String category = FormProductSalesAnalysisMenu.item;
                String item = processItem(FormProductSalesAnalysisItemList.item);
                String result1 = sendProductSalesAnalysisYearly(item, category, dailyDate);
                if(result1 != null)
                {
                    fresult = FormProductSalesAnalysisResult.getFormProductSalesAnalysisYearlyResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                    FormProductSalesAnalysisResult.typeOfRequest = "1";
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

    private String sendProductSalesAnalysisDaily(String item, String category, String dailyDate)
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
            while(category.indexOf(" ")!=-1)
            {
                category = StringUtilities.replaceData(category, " ", "%20");
            }
            System.out.println(spacedate + " : date is this-- "+item+"--"+dailyDate+"--"+category);
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=24&requestsourceid=1&item="+ item + "&dailydate="+ dailyDate + "&category="+ category + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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

    private String sendProductSalesAnalysisMonthly(String item, String category, String dailyDate)
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
            while(category.indexOf(" ")!=-1)
            {
                category = StringUtilities.replaceData(category, " ", "%20");
            }
            System.out.println(spacedate + " : date is this-- "+item+"--"+dailyDate+"--"+category);
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=25&requestsourceid=1&item="+ item + "&dailydate="+ dailyDate + "&category="+ category + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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

    private String sendProductSalesAnalysisYearly(String item, String category, String dailyDate)
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
            while(category.indexOf(" ")!=-1)
            {
                category = StringUtilities.replaceData(category, " ", "%20");
            }
            System.out.println(spacedate + " : date is this-- "+item+"--"+dailyDate+"--"+category);
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=26&requestsourceid=1&item="+ item + "&dailydate="+ dailyDate + "&category="+ category + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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


    private String processItem(String item)
    {
        String name="";
        char[] menu = item.toCharArray();
        int i;
        for(i=0;i<menu.length;i++)
        {
                    if(menu[i]!='.')
                    {
                        name=name+menu[i];
                    }
                    else
                    {
                        System.out.println(name);
                        break;
                    }
        }
        return name;
    }
}
