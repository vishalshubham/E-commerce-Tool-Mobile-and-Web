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
public class FormSpecificQuery implements CommandListener
{
    private Form fspecificquery;
    private TextField tspecificquery;
    private Command ook2;
    private Command bback2;
    private TextField tmailid;
    private TextField tpassword;
    private List formhelp;

    private Form makeFormSpecificQuery()
    {
        fspecificquery=new Form("Write Your Query :");
        tmailid=new TextField("Email id :", null, 40, TextField.ANY);
        tpassword=new TextField("Password", null, 15, TextField.PASSWORD);
        tspecificquery=new TextField("Query:",null,25,TextField.ANY);
        fspecificquery.append(tmailid);
        fspecificquery.append(tpassword);
        fspecificquery.append(tspecificquery);
        ook2=new Command("Send",Command.OK,1);
        bback2=new Command("Back",Command.BACK,1);
        fspecificquery.addCommand(ook2);
        fspecificquery.addCommand(bback2);
        fspecificquery.setCommandListener(this);
        return fspecificquery;
    }
    static Form getFormSpecificQuery()
    {
        FormSpecificQuery fc = new FormSpecificQuery();
        return fc.makeFormSpecificQuery();
    }

   public void commandAction(Command c, Displayable d)
    {
        if(c==bback2)
        {
            System.out.println("in back");
            tspecificquery.setString("");
            formhelp = FormHelp.getFormHelp();
            MobileMidlet1.switchDisplay(formhelp);
        }
        else if(c==ook2)
        {
            System.out.println("in ok");
            if(sendSpecificMail())
                {
                    Alert alert= new Alert("THANKS","Your request has been processed.",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet1.switchDisplay(alert);
                }
        }
    }

    private boolean sendSpecificMail()
    {
         try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.print("Sending mail: " + tspecificquery.getString());
            if(tspecificquery.getString().indexOf(" ")!=-1 || tmailid.getString().indexOf(" ")!=-1 || tpassword.getString().indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                String newarr = StringUtilities.replaceData(tspecificquery.getString()," ","%20");
                String mail = StringUtilities.replaceData(tmailid.getString()," ","%20");
                String pass = StringUtilities.replaceData(tpassword.getString()," ","%20");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                while(tspecificquery.getString().indexOf(" ")!=-1)
                {
                    newarr=StringUtilities.replaceData(tspecificquery.getString()," ","%20");
                }
                while(tmailid.getString().indexOf(" ")!=-1)
                {
                    mail=StringUtilities.replaceData(tmailid.getString()," ","%20");
                }
                while(tpassword.getString().indexOf(" ")!=-1)
                {
                    pass=StringUtilities.replaceData(tpassword.getString()," ","%20");
                }



                System.out.println(spacedate + " : date is thissssssssssss ");
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=9&requestsourceid=1&query="+ newarr+ "&email="+ mail  +"&password="+ pass + "&username="+FormLogin.getUserName() + "&date="+spacedate);
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=9&requestsourceid=1&query="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
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
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=9&requestsourceid=1&query="+ tspecificquery.getString()+"&email="+tmailid.getString()+"&password="+tpassword.getString()+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=9&requestsourceid=1&query="+ tspecificquery.getString()+ "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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

}
