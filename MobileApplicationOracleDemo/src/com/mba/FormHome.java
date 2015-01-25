
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
public class FormHome implements CommandListener
{

    private List fhome;
    private List formmenu;
    private List formchange;
    private String home="View Item/Place Order,Check Your Cart,Change Information,Cancel Order,Help,Exit,";
    private char chome[]=home.toCharArray();
    private Form formcheckyourcart;
    private Form formcancelorder;
    private List formhelp;

    public List makeFormHome()
    {
        fhome=new List("Enter Your Choice:",List.IMPLICIT);
        returnMenu(fhome,chome);
        fhome.setCommandListener(this);
        return fhome;
    }

    static List getFormHome()
    {
        FormHome fh = new FormHome();
        return fh.makeFormHome();
    }
    public void returnMenu(List cg,char menu[])
    {
        String name="";
        int i;
        for(i=0;i<menu.length;i++)
        {

                    if(menu[i]!=',')
                    {
                        name=name+menu[i];
                    }
                    else
                    {
                        cg.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    private void showCart()
    {
        if(!(FormBuyItem.bill.equals("")))
        {
            System.out.println(FormBuyItem.bill);
            formcheckyourcart=FormCheckYourCart.getFormCheckYourCart();
            MobileMidlet1.switchDisplay(formcheckyourcart);
            System.out.println("Choosed : Check Your Cart");
        }
        else
        {
            Alert alert= new Alert("SORRY ::","No Purchases yet! Please purchase Something",null,null);
            alert.setTimeout(Alert.FOREVER);
            alert.setType(AlertType.ERROR);
            MobileMidlet1.switchDisplay(alert);
        }
    }


    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fhome.getString(fhome.getSelectedIndex()).equals("View Item/Place Order"))
                {
                    formmenu=FormMenu.getFormMenu();                         // Form Item Menu Getter
                    MobileMidlet1.switchDisplay(formmenu);
                    System.out.println("Choosed : View Item");
                }

                else if(fhome.getString(fhome.getSelectedIndex()).equals("Check Your Cart"))
                {
                    showCart();
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Change Information"))
                {
                    formchange = FormChange.getFormChange();
                    MobileMidlet1.switchDisplay(formchange);
                    System.out.println("Choosed : Change Information");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Cancel Order"))
                {
                    showOrder();
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Help"))
                {
                    formhelp=FormHelp.getFormHelp();                         // Form Item Menu Getter
                    MobileMidlet1.switchDisplay(formhelp);
                    System.out.println("Choosed : Help");
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Exit"))
                {
                    System.out.println("Choosed : Exit");
//                    destroyApp(true);
//                    notifyDestroyed();
//                    switchDisplay(null);
                }
            }
    }

    private void showOrder()
    {
         if(FormCancelOrder.returnOrderList().equals(";"))
            {
                Alert alert= new Alert("SORRY","You donot have recent any order to cancel.",null,null);
                alert.setTimeout(Alert.FOREVER);
                alert.setType(AlertType.INFO);
                System.out.println("in :");
                MobileMidlet1.switchDisplay(alert);
//                MobileMidlet1.switchDisplay(formhome);
            }
         else
         {
                    formcancelorder=FormCancelOrder.getFormCancelOrder();                         // Form Item Menu Getter
                    MobileMidlet1.switchDisplay(formcancelorder);
                    System.out.println("Choosed : Discount/Sales");
         }
    }
}
