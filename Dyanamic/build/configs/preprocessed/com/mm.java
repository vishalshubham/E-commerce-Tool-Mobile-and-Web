/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.*;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author VISHAL
 */
public class mm extends MIDlet implements CommandListener
{
    private Display display;
    private TextField tlog,tpass;
    private Form flogin;
    private Command ok,back;


    public mm()
    {
        display=Display.getDisplay(this);
        flogin=new Form("Enter Your Account:");
        tlog=new TextField("Username :",null,15,TextField.ANY);
        tpass=new TextField("Password :",null,15,TextField.ANY|TextField.PASSWORD);
        ok=new Command("Login",Command.OK,1);
        back=new Command("Exit",Command.EXIT,2);
        flogin.append(tlog);
        flogin.append(tpass);
        flogin.addCommand(ok);
        flogin.addCommand(back);
        flogin.setCommandListener(this);
    }
    public Form retform()
    {
        return flogin;
    }
    public void switchDisplay(Displayable d)
    {
        display.setCurrent(d);
    }
    public void commandAction(Command c, Displayable d) 
    {

        if(d==flogin)
        {
            if(c==ok)
            {
//                if(tlog.getString().equals("vish") && tpass.getString().equals("vish"))
                    if(validLogin())
                {
                    System.out.print("guuuuddddddddddddddd");
                }
                else
                {
                    tpass.setString("");
                    Alert alert= new Alert("ERROR","Wrong username or Password",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    switchDisplay(alert);
                }
            }
            else if(c==back)
            {
                try {
                    destroyApp(false);
                } catch (MIDletStateChangeException ex) {
                    ex.printStackTrace();
                }
                notifyDestroyed();
            }
        }
    }
     private boolean validLogin()
    {
        try
        {
            DataInputStream dis;
            System.out.print("SENDING : " + tlog.getString() + " ----- " + tpass.getString());
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Login?loginName="+ tlog.getString()+ "&pass="+tpass.getString());
            dis = new DataInputStream(con.openInputStream());
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

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void pauseApp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void startApp() throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
