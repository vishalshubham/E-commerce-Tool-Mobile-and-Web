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
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

/**
 *
 * @author VISHAL
 */
public class FormExpiryDateAnalysisMenu implements CommandListener
{
     private Command ok5;// menu
    private Command back5;// menu
    private Command ok12;//Form Menu

    private List formhome;
    private List fmenu;

    private List formmenucategory;

    private String menu="";
    private char cmenu[]=menu.toCharArray();

    private String staticmenu="Soaps,detergent,Clothes,;";

    private String staticitem1="Lux,Cinthol,Margo,;";
    private String staticitem2="Tide,Wheel,Arial,;";
    private String staticitem3="Lux,Cinthol,Margo,;";

    static String item;
    private List fresult;

    public List makeFormMenu()
    {
        fmenu=new List("Your Menu:",List.IMPLICIT);
        back5=new Command("Back",Command.BACK,1);
        ok12=new Command("Select",Command.OK,1);
        System.out.println("your way flag is : "+FormHttpSms.getWayFlag());
        if(FormHttpSms.getWayFlag() == 1)
        {
            menu=returnItemCategory();
        }
        else if(FormHttpSms.getWayFlag() == 2)
        {
            menu = staticmenu;
        }
        cmenu=menu.toCharArray();
        returnMenu(fmenu,cmenu);
        fmenu.addCommand(back5);
        fmenu.addCommand(ok12);
        fmenu.setCommandListener(this);
        return fmenu;
    }

    static List getFormMenu()
    {
        FormExpiryDateAnalysisMenu fm = new FormExpiryDateAnalysisMenu();
        return fm.makeFormMenu();
    }

    private String returnItemCategory()
    {
        try
        {
            DataInputStream dis;
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/ItemCategory?requestsourceid=1");
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[200];
            int cnt = dis.read(data);
            String response = new String(data,0,cnt);
            System.out.println("Came to returnItem category");
            System.out.println("hell"+response+"HELL");
            if(response != null)
            {
                return response;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void returnMenu(List cg,char menu[])
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


    public void commandAction(Command c, Displayable d)
    {
            if(c==List.SELECT_COMMAND)
            {
                item = fmenu.getString(fmenu.getSelectedIndex());

                String result1 = sendExpiryDateAnalysis(item);
                if(result1 != null)
                {
                    fresult = FormExpiryDateAnalysisResult.getFormExpiryDateAnalysisDailyResult(result1);
                    MobileMidlet.switchDisplay(fresult);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet.switchDisplay(alert);
                }
                System.out.println("Selected Item:" + fmenu.getString(fmenu.getSelectedIndex()));
            }
            else if(c==ok12)
            {
            }
            else if(c==back5)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }
    }

    private String sendExpiryDateAnalysis(String category)
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
            System.out.println(spacedate + " : date is this-- "+category+"--");
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/MainHandler?serviceid=36&requestsourceid=1&category="+ category + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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
