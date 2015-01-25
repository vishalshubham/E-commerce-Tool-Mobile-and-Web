/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

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
import javax.microedition.lcdui.TextField;
/**
 *
 * @author VISHAL
 */
public class FormGeneralQuery implements CommandListener
{
    private Form fgeneralquery;   
    private TextField tquery;
    private Command ok1;
    private Command back1;
    private List formhelp;

   
    static Form getFormGeneralQuery()
    {
        FormGeneralQuery fc = new FormGeneralQuery();
        return fc.makeFormGeneralQuery();
    }

    private Form makeFormGeneralQuery()
    {
        fgeneralquery=new Form("Write Your Query :");
        tquery=new TextField("Query:",null,500,TextField.ANY);
        fgeneralquery.append(tquery);
        ok1=new Command("Send",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fgeneralquery.addCommand(ok1);
        fgeneralquery.addCommand(back1);
        fgeneralquery.setCommandListener(this);
        return fgeneralquery;
    }


     public void commandAction(Command c, Displayable d)
    {
        if(c==back1)
        {
            tquery.setString("");
            formhelp = FormHelp.getFormHelp();
            MobileMidlet.switchDisplay(formhelp);
        }
        else if(c==ok1)
        {
            String msg = tquery.getString();
            msg = processMsg(msg);
            if(sendGeneralMail(msg))
                {
                    Alert alert= new Alert("THANKS","Your request has been processed.",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet.switchDisplay(alert);
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




    private boolean sendGeneralMail(String msg)
    {
        try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.print("Sending mail: " + msg);
            if(msg.indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                String newarr = StringUtilities.replaceData(msg," ","%20");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                while(msg.indexOf(" ")!=-1)
                {
                    newarr=StringUtilities.replaceData(msg," ","%20");
                }
                System.out.println(spacedate + " : date is thissssssssssss ");
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=8&requestsourceid=1&query="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=8&requestsourceid=1&query="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                System.out.println(spacedate + " : date is this ");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                System.out.println(spacedate + " : date is this ");
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=8&requestsourceid=1&query="+ msg+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=8&requestsourceid=1&query="+ msg+ "&username="+FormLogin.getUserName()+ "&date="+spacedate);
                dis = new DataInputStream(con.openInputStream());
            }

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

            if(response != null && response.equals("valid"))
            {
                return true;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    private String processMsg(String msg)
    {
        System.out.println("this is my message : "+msg);
        while(msg.indexOf(" ")!=-1)
        {
            msg = StringUtilities.replaceData(msg," ","%20");
            System.out.println("My message : "+msg);
        }
        return msg;
    }
}
