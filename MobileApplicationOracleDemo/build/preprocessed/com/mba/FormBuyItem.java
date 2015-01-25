

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
public class FormBuyItem implements CommandListener
{
    private TextField tquantity;
    private ChoiceGroup itemlist;
    private String category;
    private StringItem choose;
    static String bill="";
    private char cbill[];

    private String item="";
    private char citem[]=item.toCharArray();
    private Command ok6;
    private Command ok11;
    private Command back6;
    private List formmenu;
    private Form formcheckyourcart;

    public Form makeFormBuyItem(String category)
    {
        Form fbuyitem=new Form("CATEGORY");
        tquantity=new TextField("QUANTITY :",null,3,TextField.ANY);
        itemlist=new ChoiceGroup("List of "+category,ChoiceGroup.POPUP);
        choose=new StringItem(null, null);
        item=returnItem(category);
        citem=item.toCharArray();
        returnList(itemlist,citem);
        fbuyitem.append(itemlist);
        fbuyitem.append(choose);
        fbuyitem.append(tquantity);
        ok6=new Command("Add To Cart",Command.OK,1);
        ok11=new Command("View your Cart",Command.OK,1);
        back6=new Command("Back",Command.BACK,1);
        fbuyitem.addCommand(ok6);
        fbuyitem.addCommand(ok11);
        fbuyitem.addCommand(back6);
        fbuyitem.setCommandListener(this);

        return fbuyitem;
    }

    static Form getFormBuyItem(String category)
    {
        FormBuyItem fb = new FormBuyItem();
        return fb.makeFormBuyItem(category);
    }

    public void returnList(ChoiceGroup c,char citem[])
    {
        String name="";
        int i;
        for(i=0;i<citem.length;i++)
        {

                    if(citem[i]!=',')
                    {
                        name=name+citem[i];
                    }
                    else
                    {
                        c.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    private String returnItem(String category)
    {
        try
        {
            DataInputStream dis;
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/ItemList?requestsourceid=1&category="+category);
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[200];
            int cnt = dis.read(data);
            String response = new String(data,0,cnt);
            System.out.println("Came to Item category"+category);
            System.out.println(response);
            if(response != null)
            {
                return response;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
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
        if(c==ok6)
            {
                if(!(tquantity.getString().equals("")))
                {
                    bill=bill+tquantity.getString()+"-"+itemlist.getString(itemlist.getSelectedIndex())+",";
                    choose.setLabel("Added To Your Cart : "+tquantity.getString()+"--"+itemlist.getString(itemlist.getSelectedIndex()));
                    System.out.println("Your bill : "+bill);
                }
                else
                {
                    Alert alert= new Alert("QUANTITY CANNOT BE BLANK::","Please Enter any Quantity for Purchase",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                }
            }
            else if(c==ok11)
            {
                showCart();
            }
            else if(c==back6)
            {
                formmenu = FormMenu.getFormMenu();
                MobileMidlet1.switchDisplay(formmenu);
            }
    }

}
