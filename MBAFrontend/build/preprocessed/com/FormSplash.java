
package com;

import java.io.IOException;
import javax.microedition.lcdui.*;

/**
 *
 * @author VISHAL
 */
public class FormSplash implements CommandListener
{
    private Form formsplash;
    private Command ok7;
    private Image img;

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
        Form fsplash=new Form("Best Mart : Best For You");
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

    public static Form getFormSplash()
    {
        FormSplash fs = new FormSplash();
        return fs.getFormSplash();
    }

    public void commandAction(Command c, Displayable d)
    {

    }
}
