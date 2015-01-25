/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import java.io.IOException;
import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormSplash implements CommandListener
{
    private Command ok7;
    private Image img;
    private Form fsplash;
    private List formhttpsms;
   
    public Form makeFormSplash()
    {
        ok7=new Command("Enter",Command.OK,1);
        try
        {
            img = Image.createImage("/Best.jpg");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        fsplash=new Form("Best Mart : Best For You");
        StringItem s1=new StringItem("BEST SERVICE",null);
        StringItem s2=new StringItem("BEST QUALITY",null);
        StringItem s3=new StringItem("BEST PRICE",null);
        fsplash.append(img);
        fsplash.append(s1);
        fsplash.append(s2);
        fsplash.append(s3);
        fsplash.addCommand(ok7);
        fsplash.setCommandListener(this);
        return fsplash;
    }

    static Form getFormSplash()
    {
        FormSplash fs = new FormSplash();
        return fs.makeFormSplash();

    }
    public void commandAction(Command c, Displayable d)
    {
        if(d==fsplash)
        {
            if(c==ok7)
            {
                formhttpsms = FormHttpSms.getFormHttpSms();
                MobileMidlet.switchDisplay(formhttpsms);
            }
        }
    }
}
