/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormHttpSms implements CommandListener
{
    private List fhttpsms;
    private Form formlogin;
    static int wayflag = 1;

    public List makeFormHttpSms()
    {
        fhttpsms = new List("Way Of Communication : ", List.IMPLICIT);
        fhttpsms.append("HTTP", null);
        fhttpsms.append("SMS",null);
        fhttpsms.setCommandListener(this);
        return fhttpsms;
    }

    static int getWayFlag()
    {
        return wayflag;
    }

    static List getFormHttpSms()
    {
        FormHttpSms fh = new FormHttpSms();
        return fh.makeFormHttpSms();
    }
    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fhttpsms.getString(fhttpsms.getSelectedIndex()).equals("HTTP"))
                {
                    wayflag = 1;
                    System.out.println("Flag is : "+wayflag);
                    formlogin=FormLogin.getFormLogin();
                    MobileMidlet.switchDisplay(formlogin);
                }
                else if(fhttpsms.getString(fhttpsms.getSelectedIndex()).equals("SMS"))
                {
                    wayflag = 2;
                    System.out.println("Flag is : "+wayflag);
                    formlogin=FormLogin.getFormLogin();
                    MobileMidlet.switchDisplay(formlogin);
//                    Alert alert= new Alert("NOT COMPLETED","This part is still remaining",null,null);
//                    alert.setTimeout(Alert.FOREVER);
//                    alert.setType(AlertType.ERROR);
//                    switchDisplay(alert);
                }
            }
    }

}
