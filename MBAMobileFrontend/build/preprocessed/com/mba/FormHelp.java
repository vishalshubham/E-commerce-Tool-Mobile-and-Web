/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

/**
 *
 * @author VISHAL
 */
public class FormHelp implements CommandListener
{
    private List fhelp;
    private Command back;
    private List formhome;
    private Form formgeneral;
    private Form formspecific;

    public List makeFormHelp()
    {
        fhelp=new List("Send Your Query Type:",List.IMPLICIT);
        fhelp.append("General Query : Through call", null);
        fhelp.append("Specific Query : Through Email", null);
        fhelp.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        fhelp.addCommand(back);
        return fhelp;
    }

    static List getFormHelp()
    {
        FormHelp fc = new FormHelp();
        return fc.makeFormHelp();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fhelp.getString(fhelp.getSelectedIndex()).equals("General Query : Through call"))
                {
                    formgeneral = FormGeneralQuery.getFormGeneralQuery();
                    MobileMidlet.switchDisplay(formgeneral);
                    System.out.println("Choosed : general query");
                }
                else if(fhelp.getString(fhelp.getSelectedIndex()).equals("Specific Query : Through Email"))
                {
                    formspecific = FormSpecificQuery.getFormSpecificQuery();
                    MobileMidlet.switchDisplay(formspecific);
                    System.out.println("Choosed : specific query");
                }
            }
        else if(c==back)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }

    }

}
