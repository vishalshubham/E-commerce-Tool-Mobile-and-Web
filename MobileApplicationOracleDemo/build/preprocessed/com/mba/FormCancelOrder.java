
package com.mba;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

/**
 *
 * @author VISHAL
 */
public class FormCancelOrder implements CommandListener
{
    private List formhome;
    private ChoiceGroup order;
    private String orderlist;
    private Command cancel;
    private Command back10;
    private char[] corder;

    private Form makeFormCancelOrder()
    {
        Form fcancelorder = new Form("Your Orders : ");
        order=new ChoiceGroup("Your Cart :", ChoiceGroup.EXCLUSIVE);
        cancel = new Command("Cancel Order", Command.OK, 1);
        back10 = new Command("Back", Command.BACK, 1);
        if(FormHttpSms.getWayFlag() == 1)
        {
            orderlist = FormCancelOrder.returnOrderList();
           
        }
        else if(FormHttpSms.getWayFlag() == 2)
        {
            Alert alert= new Alert("THANKS YOU","You cannot cancel order using SMS mode. Please Move to HTTP mode",null,null);
            alert.setTimeout(Alert.FOREVER);
            alert.setType(AlertType.INFO);
            MobileMidlet1.switchDisplay(alert);
        }
        corder = orderlist.toCharArray();
        returnOrder(order, corder);
        fcancelorder.append(order);
        fcancelorder.addCommand(cancel);
        fcancelorder.addCommand(back10);
        fcancelorder.setCommandListener(this);
        return fcancelorder;
    }

    public void returnOrder(ChoiceGroup cg,char menu[])
    {
        String retOrder="";
        int i;
        for(i=0;i<menu.length;i++)
        {

            if(menu[i]!=',')
            {
                retOrder=retOrder+menu[i];
            }
            else
            {
                retOrder = processOrder(retOrder);
                cg.append("Order at : "+retOrder, null);
                System.out.println(retOrder);
                retOrder="";
            }
        }
    }


    static Form getFormCancelOrder()
    {
        FormCancelOrder fc = new FormCancelOrder();
        return fc.makeFormCancelOrder();
    }

    static String returnOrderList()
    {
        try
        {
            DataInputStream dis;
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/OrderList?requestsourceid=1&username="+FormLogin.getUserName());
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[200];
            int cnt = dis.read(data);
            String response = new String(data,0,cnt);
            System.out.println("Came to Order List");
            System.out.println("hell"+response+"HELL");
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


    public void commandAction(Command c, Displayable d)
    {
        if(c==cancel)
        {
            try
            {
                String cancelThisOrder = processOrderReverse(order.getString(order.getSelectedIndex()));
                if (cancelOrder(cancelThisOrder)) {
                    Alert alert = new Alert("THANK YOU", "Your cart has been canceled", null, null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                } else {
                    Alert alert = new Alert("SORRY::", "Some ERROR has been occured on the server", null, null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet1.switchDisplay(alert);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(c==back10)
        {
            formhome = FormHome.getFormHome();
            MobileMidlet1.switchDisplay(formhome);
        }
    }

    
    private String processOrder(String order)
    {
        char[] arrOrder = order.toCharArray();
        String newOrder = "";
        newOrder = newOrder + arrOrder[0] + arrOrder[2] + arrOrder[3] + arrOrder[4] + arrOrder[5] + arrOrder[6] + arrOrder[7] + arrOrder[8] + arrOrder[9] + arrOrder[10] + arrOrder[11] + arrOrder[12] + arrOrder[13] + arrOrder[14];
        System.out.println("This is our New Order : "+newOrder);
        char[] cnewOrder = newOrder.toCharArray();
        System.out.println("8 : " + cnewOrder[8] + " :9: "+cnewOrder[9]);
        String d = "" + cnewOrder[8] + cnewOrder[9];
        System.out.println("d : "+d);
        newOrder = "";
        newOrder = d + ":" + cnewOrder[10] + cnewOrder[11] + ":" + cnewOrder[12] + cnewOrder[13] + " on " + cnewOrder[6] + cnewOrder[7] + "/" + cnewOrder[4] + cnewOrder[5] + "/" + cnewOrder[0] + cnewOrder[1] +cnewOrder[2] + cnewOrder[3];
        return newOrder;
    }

    private boolean cancelOrder(String string) throws IOException
    {
        DataInputStream dis;
        DateGetter d = new DateGetter();
        String spacedate = d.getDate();
        try
        {
            System.out.println("inside the else if");
            System.out.println(FormLogin.getUserName()+" this is the username");

            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=7&requestsourceid=1&cancelorder="+ string + "&username="+FormLogin.getUserName()+ "&date="+spacedate);
            dis = new DataInputStream(con.openInputStream());

            byte data [] = new byte[1000];

            int cnt = dis.read(data);

            String response = new String(data, 0, cnt);
            System.out.println("Data recd length :: " + cnt + " "+ response.length()+ " response"+ response);

            if(response != null && response.equals("valid"))
            {
                return true;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    private String processOrderReverse(String string)
    {
        System.out.println("Our string : "+string);
        char[] cstring = string.toCharArray();
        String d = "";
        string = d + cstring[29] + cstring[30] + cstring[31] + cstring[32] + cstring[26] + cstring[27] + cstring[23] + cstring[24] + cstring[11] + cstring[12] + cstring[14] + cstring[15] + cstring[17] + cstring[18];
        System.out.println("Our later string : "+string);
        return string;
    }

}
