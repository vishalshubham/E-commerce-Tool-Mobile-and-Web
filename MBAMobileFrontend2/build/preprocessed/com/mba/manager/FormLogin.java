
package com.mba.manager;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

/**
 *
 * @author VISHAL
 */
public class FormLogin implements CommandListener
{
    private TextField tlog;
    private TextField tpass;

    private Command ok;
    private Command back;

    static String strusername;

    private Form flogin;
    private List formhttpsms;
    private List formhome;

    public Form makeFormLogin()
    {
        flogin=new Form("Enter Your Manager Account:");
        tlog=new TextField("Username :",null,30,TextField.ANY);
        tpass=new TextField("Password :",null,15,TextField.ANY|TextField.PASSWORD);
        ok=new Command("Login",Command.OK,1);
        back=new Command("Exit",Command.EXIT,2);
        flogin.append(tlog);
        flogin.append(tpass);
        flogin.addCommand(ok);
        flogin.addCommand(back);
        flogin.setCommandListener(this);
        return flogin;
    }


    static Form getFormLogin()
    {
        FormLogin fs = new FormLogin();
        return fs.makeFormLogin();
    }

     private boolean validLogin()
    {
        try
        {
            DataInputStream dis;
            System.out.print("SENDING : " + tlog.getString() + " ----- " + tpass.getString());
            strusername=tlog.getString();   // try to keep it inside the if block below
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8090/MBA2/Login?requestsourceid=1&serviceid=1&username="+ tlog.getString()+ "&password="+tpass.getString());
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);
            response = "valid";
            if(response != null && response.equals("valid"))
            {
                System.out.println(response + " : Response");
                return true;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    static String getUserName()
    {
        return strusername;
    }

    public void commandAction(Command c, Displayable d)
    {
        if(d==flogin)
        {
            if(c==ok)
            {
                if(FormHttpSms.wayflag == 1&& tlog.getString().length()>7 && tpass.getString().length()>7)
                {
                    if(validLogin())
                    {
                        formhome = FormHome.getFormHome();
                        MobileMidlet.switchDisplay(formhome);
                    }
                    else
                    {
                        tpass.setString("");
                        Alert alert= new Alert("ERROR","Wrong username or Password",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
                }
                else if(FormHttpSms.wayflag == 2)
                {
                    if(tlog.getString().equals("vishal") && tpass.getString().equals("1"))
                    {
                    //    formhome = FormHome.getFormHome();
                        MobileMidlet.switchDisplay(formhome);
                    }
                    else
                    {
                        tpass.setString("");
                        Alert alert= new Alert("ERROR","Wrong username or Password",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet.switchDisplay(alert);
                    }
                }
            }
            else if(c==back)
            {
                formhttpsms = FormHttpSms.getFormHttpSms();
                MobileMidlet.switchDisplay(formhttpsms);
            }
        }
    }

}
