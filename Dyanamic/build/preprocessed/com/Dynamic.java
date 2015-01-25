
package com;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author VISHAL
 */

public class Dynamic extends MIDlet implements CommandListener
{
    private String src="Soaps,Shampoos,Detergent,Biscuits,Toothpaste";
    private char carr[]=src.toCharArray();
    private Display display;
    private ChoiceGroup c;
    private Form f;
    private TextField t;
    public Dynamic()
    {
        display= Display.getDisplay(this);
        f=new Form("Your Choice");
        t=new TextField("Quantity::",null,3,0);
        c=new ChoiceGroup("Select Your menu",Choice.POPUP);
        
        c.append("Soaps", null);
        c.append("Shampoo",null);
        ret();
        f.append(c);
     //   f.append(t);
        display.setCurrent(f);
    }
    public void ret()
    {
        String name="";
        int i;
        for(i=0;i<carr.length;i++)
        {

                    if(carr[i]!=',')
                    {
                        name=name+carr[i];
                    }
                    else
                    {
                        c.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    public void startApp()
    {
    }

    public void pauseApp()
    {
    }

    public void destroyApp(boolean unconditional)
    {
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
