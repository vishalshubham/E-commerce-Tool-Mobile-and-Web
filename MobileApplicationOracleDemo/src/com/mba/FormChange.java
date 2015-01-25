
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
public class FormChange implements CommandListener
{
    private Form formchangename,formchangeaddress,formchangecontact,formchangepassword;
    private List fchange;
    private Command back;
    private List formhome;

    public List makeFormChange()
    {
        fchange=new List("What You want to CHANGE",List.IMPLICIT);
        fchange.append("CHANGE NAME", null);
        fchange.append("CHANGE ADDRESS", null);
        fchange.append("CHANGE CONTACT", null);
        fchange.append("CHANGE PASSWORD", null);
        fchange.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        fchange.addCommand(back);
        return fchange;
    }

    static List getFormChange()
    {
        FormChange fc = new FormChange();
        return fc.makeFormChange();
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fchange.getString(fchange.getSelectedIndex()).equals("CHANGE NAME"))
                {
                    formchangename = FormChangeAny.getFormChangename();
                    MobileMidlet1.switchDisplay(formchangename);
                    System.out.println("Choosed : change name");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("CHANGE ADDRESS"))
                {
                    formchangeaddress = FormChangeAny.getFormChangeAddress();
                    MobileMidlet1.switchDisplay(formchangeaddress);
                    System.out.println("Choosed : change address");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("CHANGE CONTACT"))
                {
                    formchangecontact = FormChangeAny.getFormChangeContact();
                    MobileMidlet1.switchDisplay(formchangecontact);
                    System.out.println("Choosed : change contact");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("CHANGE PASSWORD"))
                {
                    formchangepassword = FormChangeAny.getFormChangePassword();
                    MobileMidlet1.switchDisplay(formchangepassword);
                    System.out.println("Choosed : change password");
                }
            }
        else if(c==back)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet1.switchDisplay(formhome);
            }

    }

}
