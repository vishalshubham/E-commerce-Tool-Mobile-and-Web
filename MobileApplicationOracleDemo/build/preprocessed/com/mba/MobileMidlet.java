/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.*;

/**
 * @author VISHAL
 */
public class MobileMidlet extends MIDlet {static Display display;
    private Form formsplash;
    private Form formlogin;

    private String strusername;
    private String extra = "vishal-90,stefi-95,";

    public MobileMidlet()
    {
        display= Display.getDisplay(this);
        formsplash=FormSplash.getFormSplash();                     // Form Splash Getter
    }


    public void startApp()
    {
        display.setCurrent(formsplash);
    }

    public void pauseApp()
    {

    }

    public void destroyApp(boolean unconditional)
    {

    }

    static void switchDisplay(Displayable d)
    {
        display.setCurrent(d);
    }


    public void commandAction(Command c, Displayable d)
    {

    }
}
