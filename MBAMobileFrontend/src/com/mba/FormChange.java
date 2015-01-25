
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
        fchange.append("Change Name", null);
        fchange.append("Change Address", null);
        fchange.append("Change Contact", null);
        fchange.append("Change Password", null);
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
                if(fchange.getString(fchange.getSelectedIndex()).equals("Change Name"))
                {
                    formchangename = FormChangeAny.getFormChangename();
                    MobileMidlet.switchDisplay(formchangename);
                    System.out.println("Choosed : change name");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("Change Address"))
                {
                    formchangeaddress = FormChangeAny.getFormChangeAddress();
                    MobileMidlet.switchDisplay(formchangeaddress);
                    System.out.println("Choosed : change address");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("Change Contact"))
                {
                    formchangecontact = FormChangeAny.getFormChangeContact();
                    MobileMidlet.switchDisplay(formchangecontact);
                    System.out.println("Choosed : change contact");
                }
                else if(fchange.getString(fchange.getSelectedIndex()).equals("Change Password"))
                {
                    formchangepassword = FormChangeAny.getFormChangePassword();
                    MobileMidlet.switchDisplay(formchangepassword);
                    System.out.println("Choosed : change password");
                }
            }
        else if(c==back)
            {
                formhome = FormHome.getFormHome();
                MobileMidlet.switchDisplay(formhome);
            }

    }

}
