
package com.mba;

import javax.microedition.lcdui.*;;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

/**
 * @author VISHAL
 */
public class MobileMidlet1 extends MIDlet implements CommandListener
{
    static Display display;
    private Form formsplash;
    private Form formlogin;

    private String strusername;
    private String extra = "vishal-90,stefi-95,";

    public MobileMidlet1()
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
