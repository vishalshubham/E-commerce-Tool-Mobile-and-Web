

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
public class FormEditItem implements CommandListener
{
    private String productname;
    private TextField teditquantity;
    private Command ok13;
    private Command back13;
    private Form formcheckyourcart;

    public Form makeFormEditItem(String size)
    {
        Form fedititem=new Form("Edit Item :");
        char arr[]=size.toCharArray();
        String qty="";

        int indexdash = size.indexOf('-');
        for(int i = 0 ; i < indexdash ; i++ )
        {
            qty=qty+arr[i];
        }
        System.out.println("Quantity previous is :" + qty);
        productname="";
        for(int i = indexdash + 1 ; i < arr.length ; i++ )
        {
            productname=productname+arr[i];
        }
        System.out.println("Product is :" + productname);
//
//        int total = 0;
        StringItem itemname=new StringItem("Change this Product :",productname);
        //StringItem totalstr = new StringItem("Your Total is : ",Integer.toString(total));
        teditquantity =new TextField("Change Quantity :",qty, 3, TextField.ANY);
        ok13=new Command("Change",Command.OK,1);
        back13=new Command("Back",Command.BACK,1);
        fedititem.append(itemname);
        fedititem.append(teditquantity);
        fedititem.addCommand(ok13);
        fedititem.addCommand(back13);
        fedititem.setCommandListener(this);
        return fedititem;
    }

    static Form getFormEditItem(String size)
    {
        FormEditItem fe = new FormEditItem();
        return fe.makeFormEditItem(size);
    }

    public void commandAction(Command c, Displayable d)
    {
        if(c==ok13)
            {
                String newitemname = teditquantity.getString()+"-"+productname;
                System.out.println(newitemname);
                System.out.println(FormCheckYourCart.edititem);
                newitemname.trim();
                FormCheckYourCart.edititem.trim();
                FormBuyItem.bill = StringUtilities.replaceData(FormBuyItem.bill, FormCheckYourCart.edititem, newitemname);
                System.out.println("Your bill is now ::"+FormBuyItem.bill);
                formcheckyourcart=FormCheckYourCart.getFormCheckYourCart();
                MobileMidlet1.switchDisplay(formcheckyourcart);
            }
            else if(c==back13)
            {
                formcheckyourcart = FormCheckYourCart.getFormCheckYourCart();
                MobileMidlet1.switchDisplay(formcheckyourcart);
            }
    }

}
