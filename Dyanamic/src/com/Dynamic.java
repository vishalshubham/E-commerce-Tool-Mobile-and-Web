
package com;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author VISHAL
 */

public class Dynamic extends MIDlet implements CommandListener
{
    int wayflag = 1;

    private String itemname;
    private String productname="";
    private String edititem="";

    private String bill="";
    private char cbill[];

    private String home="View Item/Place Order,Check Your Cart,Change Information,Discount/Sales,Help,Exit,";
    private char chome[]=home.toCharArray();

    private String staticitem1="Lux,Cinthol,Margo,;";
    private char cstaticitem1[]=staticitem1.toCharArray();

    private String staticitem2="Tide,Wheel,Arial,;";
    private char cstaticitem2[]=staticitem2.toCharArray();

    private String staticitem3="Levi,Wrangler,Pepe,;";
    private char cstaticitem3[]=staticitem3.toCharArray();

    private String staticmenu="Soaps,detergent,Clothes,;";
    private char cstaticmenu[]=staticmenu.toCharArray();


    private String item="";
    private char citem[]=item.toCharArray();

    private String menu="";
    private char cmenu[]=menu.toCharArray();

    private StringItem choose;


    private Display display;
    private Form formsplash;
    private Form formlogin;
    private List formhttpsms;

        private List formhome;

            private List formmenu;
                private Form formbuyitem;

            private Form formcheckyourcart;
                private Form formedititem;
                
            private List formchange;
                private Form formchangecontact;
                private Form formchangepassword;
                private Form formchangeaddress;
                private Form formchangename;


    

    private Command ok7;// splash screen

    private Command ok;
    private Command back;

    private Command ok1;// change name
    private Command back1;// change name

    private Command ok2;// change address
    private Command back2;// change address

    private Command ok3;// change contact
    private Command back3;// change contact

    private Command ok4;// change password
    private Command back4;// change password

    private Command ok5;// menu
    private Command back5;// menu

    private Command ok6;// Item List
    private Command back6;// Item List

    private Command ok8;// Bill List : Edit Item
    private Command ok9;// Bill List : Delete Item
    private Command ok10;// Bill List : Send Cart
    private Command back8;// Bill List

    private Command ok11;// Form Buy Item

    private Command ok12;//Form Menu

    private Command ok13;//Edit Bill Item
    private Command back13;//Edit Bill Item
    


    private Image img;
    private ImageItem imageitem;
    private ChoiceGroup c;
    private TextField tlog;
    private TextField tpass;
    private TextField tname;
    private TextField taddress1;
    private TextField taddress2;
    private TextField tcity;
    private TextField tstate;
    private TextField tpincode;
    private TextField tmobile;
    private TextField temail;
    private TextField toldpass;
    private TextField tnewpass;
    private TextField tconfirmnewpass;
    private TextField tquantity;
    private TextField teditquantity;
    private ChoiceGroup itemlist;
    private ChoiceGroup billlist;

    private String strusername="";

    
    public Dynamic()
    {
        display= Display.getDisplay(this);
        formsplash=getFormSplash();                     // Form Splash Getter
        formlogin=getFormLogin();                       // Form Login Getter
        formhttpsms=getFormHttpSms();                   // Form Http Sms Getter
        formhome=getFormHome();                         // Form Home Getter
        //formmenu=getFormMenu();                         // Form Item Menu Getter
        formchange=getFormChange();                     // Form Change Getter
        formchangename=getFormChangeName();             // Form Change Name Getter
        formchangeaddress=getFormChangeAddress();       // Form Change Address Getter
        formchangecontact=getFormChangeContact();       // Form Change Contact Getter
        formchangepassword=getFormChangePassword();     // Form Change Password Getter
    }

    private List getFormHttpSms()
    {
        List fhttpsms = new List("Way Of Communication : ", List.IMPLICIT);
        fhttpsms.append("HTTP", null);
        fhttpsms.append("SMS",null);
        fhttpsms.setCommandListener(this);
        return fhttpsms;
    }

    private Form getFormEditItem(String size)
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
     
    public Form getFormCheckYourCart()
    {
        Form fcheckyourcart=new Form("You Have Purchased:");
        int total = 0;
        int itemtotal = 0;
        int indexattherate = 0;
        int indexdash = 0;

        billlist=new ChoiceGroup("Your Cart :", ChoiceGroup.EXCLUSIVE);
        cbill=bill.toCharArray();
        returnList(billlist, cbill);


        System.out.println(billlist.size());
        for(int i = 0 ; i < billlist.size() ; i++)
        {
            String units = "";
            String rate = "";
            char[] arrequal = billlist.getString(i).toCharArray();
            String strequal = billlist.getString(i);
            indexattherate = strequal.indexOf('@');
            indexdash = strequal.indexOf('-');
            for(int j = 0 ; j < indexdash ; j++)
            {
                units = units + arrequal[j];
            }
            for(int j = indexattherate + 1 ; j < billlist.getString(i).length() ; j++)
            {
                rate = rate + arrequal[j];
            }
            System.out.println(i + billlist.getString(i));

            itemtotal = Integer.parseInt(units) * Integer.parseInt(rate);
            total = total + itemtotal;
        }

        StringItem strtotal = new StringItem("Your Total is : ",Integer.toString(total));
        fcheckyourcart.append(new String("    Qty Item      Price"));
        fcheckyourcart.append(billlist);
        fcheckyourcart.append(strtotal);
        System.out.println("YOUR LIST IS : ");
        
        ok8=new Command("Edit Item",Command.OK,1);
        ok9=new Command("Delete Item",Command.OK,2);
        ok10=new Command("Send Cart",Command.OK,3);
        back8=new Command("Back",Command.BACK,1);
        fcheckyourcart.addCommand(ok8);
        fcheckyourcart.addCommand(ok9);
        fcheckyourcart.addCommand(ok10);
        fcheckyourcart.addCommand(back8);
        fcheckyourcart.setCommandListener(this);
        return fcheckyourcart;
    }
    
    public Form getFormBuyItem(String category)
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

    public Form getFormChangePassword()
    {
        Form fchangepassword=new Form("Change Your Password:");
        toldpass= new TextField("Old Password:",null,20,TextField.ANY|TextField.PASSWORD);
        tnewpass= new TextField("New Password:",null,20,TextField.ANY|TextField.PASSWORD);
        tconfirmnewpass= new TextField("Confirm New Password:",null,20,TextField.ANY|TextField.PASSWORD);
        fchangepassword.append(toldpass);
        fchangepassword.append(tnewpass);
        fchangepassword.append(tconfirmnewpass);
        ok4=new Command("Change",Command.OK,1);
        back4=new Command("Back",Command.BACK,1);
        fchangepassword.addCommand(ok4);
        fchangepassword.addCommand(back4);
        fchangepassword.setCommandListener(this);

        return fchangepassword;
    }

    public Form getFormChangeContact()
    {
        Form fchangecontact=new Form("Change Your Contact:");
        tmobile=new TextField("New Mobile Number:",null,40,TextField.ANY);
        temail=new TextField("New E-mail Address:",null,40,TextField.ANY);
        fchangecontact.append(tmobile);
        fchangecontact.append(temail);
        ok3=new Command("Change",Command.OK,1);
        back3=new Command("Back",Command.BACK,1);
        fchangecontact.addCommand(ok3);
        fchangecontact.addCommand(back3);
        fchangecontact.setCommandListener(this);

        return fchangecontact;
    }

    public Form getFormChangeAddress()
    {
        Form fchangeaddress=new Form("Change Your Address:");
        taddress1=new TextField("ADDRESS LINE 1:",null,40,TextField.ANY);
        taddress2=new TextField("ADDRESS LINE 2:",null,40,TextField.ANY);
        tcity=new TextField("CITY:",null,40,TextField.ANY);
        tstate=new TextField("STATE:",null,40,TextField.ANY);
        tpincode=new TextField("PIN CODE:",null,40,TextField.ANY);

        ok2=new Command("Change",Command.OK,1);
        back2=new Command("Back",Command.BACK,1);
        fchangeaddress.addCommand(ok2);
        fchangeaddress.addCommand(back2);
        fchangeaddress.setCommandListener(this);

        fchangeaddress.append(taddress1);
        fchangeaddress.append(taddress2);
        fchangeaddress.append(tcity);
        fchangeaddress.append(tstate);
        fchangeaddress.append(tpincode);

        return fchangeaddress;
    }

    public Form getFormChangeName()
    {
        Form fchangename=new Form("Change Your Name:");
        tname=new TextField("New Name:",null,25,TextField.ANY);
        fchangename.append(tname);
        ok1=new Command("Change",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fchangename.addCommand(ok1);
        fchangename.addCommand(back1);
        fchangename.setCommandListener(this);
        return fchangename;
    }

    public List getFormChange()
    {
        List fchange=new List("What You want to CHANGE",List.IMPLICIT);
        fchange.append("CHANGE NAME", null);
        fchange.append("CHANGE ADDRESS", null);
        fchange.append("CHANGE CONTACT", null);
        fchange.append("CHANGE PASSWORD", null);
        fchange.setCommandListener(this);
        back=new Command("Back",Command.BACK,1);
        fchange.addCommand(back);
        return fchange;
    }

    public Form getFormSplash()
    {
        ok7=new Command("Enter",Command.OK,1);
        try
        {
            img = Image.createImage("/Best.jpg");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        Form fsplash=new Form("Best Mart : Best For You");
        StringItem s1=new StringItem("BEST SERVICE",null);
        StringItem s2=new StringItem("BEST QUALITY",null);
        StringItem s3=new StringItem("BEST PRICE",null);
        fsplash.append(img);
        fsplash.append(s1);
        fsplash.append(s2);
        fsplash.append(s3);
        fsplash.addCommand(ok7);
        fsplash.setCommandListener(this);
        return fsplash;
    }

    public Form getFormLogin()
    {
        Form flogin=new Form("Enter Your Account:");
        tlog=new TextField("Username :",null,15,TextField.ANY);
        tpass=new TextField("Password :",null,15,TextField.ANY|TextField.PASSWORD);
        ok=new Command("Login",Command.OK,1);
        back=new Command("Exit",Command.EXIT,2);
        flogin.append(tlog);
        flogin.append(tpass);
        flogin.addCommand(ok);
        flogin.addCommand(back);
        flogin.setCommandListener(this);
        return flogin;
    }

    public List getFormHome()
    {
        List fhome=new List("Enter Your Choice:",List.IMPLICIT);
        returnMenu(fhome,chome);
        fhome.setCommandListener(this);
        return fhome;
    }

    public List getFormMenu()
    {
        List fmenu=new List("Your Menu:",List.IMPLICIT);
        back5=new Command("Back",Command.BACK,1);
        ok12=new Command("View Your Cart",Command.OK,1);
        System.out.println("your way flag is : "+wayflag);
        if(wayflag == 1)
        {
            menu=returnItemCategory();
        }
        else if(wayflag == 2)
        {
            menu = staticmenu;
        }
        cmenu=menu.toCharArray();
        returnMenu(fmenu,cmenu);
        fmenu.addCommand(back5);
        fmenu.addCommand(ok12);
        fmenu.setCommandListener(this);
        return fmenu;
    }

    public void switchDisplay(Displayable d)
    {
        display.setCurrent(d);
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

    public void startApp()
    {
        display.setCurrent(formsplash);
    }

    public void pauseApp()
    {
    }

    public void destroyApp(boolean unconditional)
    {
        destroyApp(false);
        notifyDestroyed();
    }

    public void commandAction(Command c, Displayable d)
    {
        if(d==formsplash)
        {
            if(c==ok7)
            {
                switchDisplay(formlogin);
            }
        }
        else if(d==formlogin)
        {
            if(c==ok)
            {
                if(validLogin())
                {
                    switchDisplay(formhttpsms);
                }
                else
                {
                    tpass.setString("");
                    Alert alert= new Alert("ERROR","Wrong username or Password",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    switchDisplay(alert);
                }
            }
            else if(c==back)
            {
                destroyApp(false);
                notifyDestroyed();
            }
        }
        else if(d==formhttpsms)
        {
            if(c==List.SELECT_COMMAND)
            {
                if(formhttpsms.getString(formhttpsms.getSelectedIndex()).equals("HTTP"))
                {
                    wayflag = 1;
                    System.out.println("Flag is : "+wayflag);
                    switchDisplay(formhome);
                }
                else if(formhttpsms.getString(formhttpsms.getSelectedIndex()).equals("SMS"))
                {
                    wayflag = 2;
                    System.out.println("Flag is : "+wayflag);
                    switchDisplay(formhome);
//                    Alert alert= new Alert("NOT COMPLETED","This part is still remaining",null,null);
//                    alert.setTimeout(Alert.FOREVER);
//                    alert.setType(AlertType.ERROR);
//                    switchDisplay(alert);
                }
            }
        }
        else if(d==formchangename)
        {
            if(c==back1)
            {
                tname.setString("");
                switchDisplay(formchange);
            }
            else if(c==ok1)
            {
                if(changeName())
                {
                    Alert alert= new Alert("NAME CHANGED::","Your Account Name Has Been Changed To::"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    switchDisplay(alert);
                }
            }
        }
        else if(d==formchangeaddress)
        {
            if(c==back2)
            {
                taddress1.setString("");
                taddress2.setString("");
                tcity.setString("");
                tstate.setString("");
                tpincode.setString("");
                switchDisplay(formchange);
            }
            else if(c==ok2)
            {
                if(changeAddress())
                {
                    Alert alert= new Alert("ADDRESS CHANGED::","Your Account Address Has Been Changed To::"+tcity.getString()+"::"+tstate.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    switchDisplay(alert);
                }
            }
        }
        else if(d==formchangecontact)
        {
            if(c==back3)
            {
                tmobile.setString("");
                temail.setString("");
                switchDisplay(formchange);
            }
            else if(c==ok3)
            {
                if(changeContact())
                {
                    Alert alert= new Alert("CONTACT CHANGED::","Your Account Contact Has Been Changed To::"+tmobile.getString()+"::"+temail.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    switchDisplay(alert);
                }
            }
        }
        else if(d==formchangepassword)
        {
            if(c==back4)
            {
                toldpass.setString("");
                tnewpass.setString("");
                tconfirmnewpass.setString("");
                switchDisplay(formchange);
            }
            else if(c==ok4)
            {
                if(!(tnewpass.getString().equals("")) && tnewpass.getString().equals(tconfirmnewpass.getString()) && validLoginChange())
                {
                    if(changePassword())
                    {
                        Alert alert= new Alert("PASSWORD CHANGED::","Your Account Password Has Been Changed To::"+tnewpass.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.INFO);
                        switchDisplay(alert);
                    }
                    else
                    {
                        Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        switchDisplay(alert);
                    }
                }
                else
                {
                        Alert alert= new Alert("SORRY PASSWORD NOT CHANGED::","Your Account Password is wrong or two different Passwords are Entered or Blank Passwords Are not allowed",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        switchDisplay(alert);

                }
            }
        }
        else if(d==formcheckyourcart)
        {
            if(c==ok8)//Edit Item
            {
                edititem=billlist.getString(billlist.getSelectedIndex());
                formedititem=getFormEditItem(edititem);
                switchDisplay(formedititem);

//
//                formedititem=getFormEditItem(deleteitem);

            }
            else if(c==ok9)//Delete Item
            {
//                int count=billlist.size();
//                    String deleteitem="";
//                for(int i=0 ; i < billlist.size() ; i++)
//                {
//                    if(i!=billlist.getSelectedIndex())
//                    {
//                        deleteitem=deleteitem + billlist.getString(i)+",";
//                        System.out.println("Our previous bill was : "+bill);
//                        System.out.println("This item we have deleted : "+deleteitem);
//                    }
//                }
                System.out.println("This is our list : "+bill);
                String deleteitem=billlist.getString(billlist.getSelectedIndex());
                bill=StringUtilities.replaceData(bill,deleteitem+",","");
                System.out.println("This is our list after deletion : "+bill);
//              bill=deleteitem;
                formcheckyourcart=getFormCheckYourCart();
                switchDisplay(formcheckyourcart);
//                cbill=bill.toCharArray();
//                returnList(billlist, cbill);
//
                //switchDisplay(getFormCheckYourCart());
            }
            else if(c==ok10)//Send Cart
            {
                if(sendCart())
                {
                        Alert alert= new Alert("THANKS YOU","Your cart has been registered and Delivery will be done till tommorow.",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.INFO);
                        switchDisplay(alert);
                }
                else
                {
                        Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        switchDisplay(alert);
                }
            }
            else if(c==back8)
            {
                switchDisplay(formhome);
            }
        }
        else if(d==formbuyitem)
        {
            if(c==ok6)
            {
                if(!(tquantity.getString().equals("")))
                {
                    bill=bill+tquantity.getString()+"-"+itemlist.getString(itemlist.getSelectedIndex())+",";
                    choose.setLabel("Added To Your Cart : "+tquantity.getString()+"--"+itemlist.getString(itemlist.getSelectedIndex()));
                }
                else
                {
                    Alert alert= new Alert("QUANTITY CANNOT BE BLANK::","Please Enter any Quantity for Purchase",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    switchDisplay(alert);
                }
            }
            else if(c==ok11)
            {
                showCart();
            }
            else if(c==back6)
            {
                switchDisplay(formmenu);
            }
        }
        else if(d==formmenu)
        {
            if(c==List.SELECT_COMMAND)
            {
                formbuyitem=getFormBuyItem(formmenu.getString(formmenu.getSelectedIndex()));
                switchDisplay(formbuyitem);
                System.out.println("Selected Item:" + formmenu.getString(formmenu.getSelectedIndex()));
            }
            else if(c==ok12)
            {
                showCart();
            }
            else if(c==back5)
            {
                switchDisplay(formhome);
            }
        }
        else if(d==formedititem)
        {
            if(c==ok13)
            {
                String newitemname = teditquantity.getString()+"-"+productname;
                System.out.println(newitemname);
                System.out.println(edititem);
                newitemname.trim();
                edititem.trim();
                bill = StringUtilities.replaceData(bill, edititem, newitemname);
                System.out.println("Your bill is now ::"+bill);
//                Alert alert= new Alert("DONE :: ","Your item Quantity has been changed!!!",null,null);
//                alert.setTimeout(Alert.FOREVER);
//                alert.setType(AlertType.INFO);
//                switchDisplay(alert);
                formcheckyourcart=getFormCheckYourCart();
                switchDisplay(formcheckyourcart);
            }
            else if(c==back13)
            {
                switchDisplay(formcheckyourcart);
            }
        }
        else if(d==formchange)
        {
            if(c==List.SELECT_COMMAND)
            {
                if(formchange.getString(formchange.getSelectedIndex()).equals("CHANGE NAME"))
                {
                    switchDisplay(formchangename);
                    System.out.println("Choosed : change name");
                }
                else if(formchange.getString(formchange.getSelectedIndex()).equals("CHANGE ADDRESS"))
                {
                    switchDisplay(formchangeaddress);
                    System.out.println("Choosed : change address");
                }
                else if(formchange.getString(formchange.getSelectedIndex()).equals("CHANGE CONTACT"))
                {
                    switchDisplay(formchangecontact);
                    System.out.println("Choosed : change contact");
                }
                else if(formchange.getString(formchange.getSelectedIndex()).equals("CHANGE PASSWORD"))
                {
                    switchDisplay(formchangepassword);
                    System.out.println("Choosed : change password");
                }
            }
            else if(c==back)
            {
                switchDisplay(formhome);
            }
        }
        else if(d==formhome)
        {
            if(c==List.SELECT_COMMAND)
            {
                if(formhome.getString(formhome.getSelectedIndex()).equals("View Item/Place Order"))
                {
                    formmenu=getFormMenu();                         // Form Item Menu Getter
                    switchDisplay(formmenu);
                    System.out.println("Choosed : View Item");
                }

                else if(formhome.getString(formhome.getSelectedIndex()).equals("Check Your Cart"))
                {
                    showCart();
                }
                else if(formhome.getString(formhome.getSelectedIndex()).equals("Change Information"))
                {
                    switchDisplay(formchange);
                    System.out.println("Choosed : Change Information");
                }
                else if(formhome.getString(formhome.getSelectedIndex()).equals("Discount/Sales"))
                {
                    switchDisplay(formmenu);
                    System.out.println("Choosed : Discount/Sales");
                }
                else if(formhome.getString(formhome.getSelectedIndex()).equals("Help"))
                {
                    switchDisplay(formmenu);
                    System.out.println("Choosed : Help");
                }
                else if(formhome.getString(formhome.getSelectedIndex()).equals("Exit"))
                {
                    System.out.println("Choosed : Exit");
                    destroyApp(true);
                    notifyDestroyed();
                    switchDisplay(null);
                }
            }
        }
    }

    private void showCart()
    {
        if(!(bill.equals("")))
        {
            System.out.println(bill);
            formcheckyourcart=getFormCheckYourCart();
            switchDisplay(formcheckyourcart);
            System.out.println("Choosed : Check Your Cart");
        }
        else
        {
            Alert alert= new Alert("SORRY ::","No Purchases yet! Please purchase Something",null,null);
            alert.setTimeout(Alert.FOREVER);
            alert.setType(AlertType.ERROR);
            switchDisplay(alert);
        }
    }

    private boolean changePassword()
    {
        try
        {
            DataInputStream dis;
            System.out.print("Changing Password to: " + tnewpass.getString());
            if(tnewpass.getString().indexOf(" ")!=-1)
            {
                String password=StringUtilities.replaceData(tnewpass.getString()," ","%20");
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Password?password="+ password+ "&username="+strusername);
                 dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Password?password="+ tnewpass.getString()+ "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

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

    private boolean changeContact()
    {
        try
        {
            DataInputStream dis;
            String contact= tmobile.getString();
            String email= temail.getString();
            System.out.print("Changing Contact to: " + contact + email);

            contact = StringUtilities.replaceData(contact, " ", "%20");

            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Contact?contact=" + contact + "&email=" + email + "&username=" + strusername);
            dis = new DataInputStream(con.openInputStream());
            byte data[] = new byte[100];
            int cnt = dis.read(data);
            System.out.print("Changing Contact response received : " + new String(data));

            String response = new String(data, 0, cnt);

            if (response != null && response.equals("valid")) {
                return true;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean changeAddress()
    {
        try
        {
            DataInputStream dis;
            String address= taddress1.getString()+","+taddress2.getString();
            String city= tcity.getString();
            String state= tstate.getString();
            String pincode= tpincode.getString();


            System.out.print("Changing Address to: " + address + city + state + pincode);
            
            if(address.indexOf(" ")!=-1 || city.indexOf(" ")!=-1 || state.indexOf(" ")!=-1)
            {
                address=StringUtilities.replaceData(address," ","%20");
                city=StringUtilities.replaceData(city," ","%20");
                state=StringUtilities.replaceData(state," ","%20");

                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Address?address="+ address+ "&city="+city+"&state="+state+"&pincode="+pincode+ "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Address?address="+ address+"&city="+city+"&state="+state+"&pincode="+pincode+  "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

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

    private boolean changeName()
    {
        try
        {
            DataInputStream dis;
            System.out.print("Changing Name to: " + tname.getString());
            if(tname.getString().indexOf(" ")!=-1)
            {
                String newarr=StringUtilities.replaceData(tname.getString()," ","%20");
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Name?name="+ newarr+ "&username="+strusername);
                 dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Name?name="+ tname.getString()+ "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }
           
            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);
            
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

        private boolean sendCart()
    {
        try
        {
            DataInputStream dis;
            System.out.print("Sending Cart: " + bill);
            if(bill.indexOf(" ")!=-1)
            {
                System.out.println("inside the if");
                String newarr=StringUtilities.replaceData(bill," ","%20");
                System.out.println("http://localhost:8084/MBA/Cart?bill="+ bill+ "&username="+strusername);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Cart?bill="+ newarr+ "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                System.out.println("inside the else if");
                System.out.println("http://localhost:8084/MBA/Cart?bill="+ bill+ "&username="+strusername);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Cart?bill="+ bill+ "&username="+strusername);
                dis = new DataInputStream(con.openInputStream());
            }

            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);

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

    private String returnItemCategory()
    {
        try
        {
            DataInputStream dis;
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/ItemCategory");
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[200];
            int cnt = dis.read(data);
            String response = new String(data,0,cnt);
            System.out.println("Came to returnItem category");
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

    private String returnItem(String category)
    {
        try
        {
            DataInputStream dis;
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/ItemList?category="+category);
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
    
    private boolean validLogin()
    {
        try
        {
            DataInputStream dis;
            System.out.print("SENDING : " + tlog.getString() + " ----- " + tpass.getString());
            strusername=tlog.getString();   // try to keep it inside the if block below
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Login?username="+ tlog.getString()+ "&password="+tpass.getString());
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);
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

    private boolean validLoginChange()
    {
        try
        {
            DataInputStream dis;
            System.out.print("SENDING : " + tlog.getString() + " ----- " + toldpass.getString());
            strusername=tlog.getString();   // try to keep it inside the if block below
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Login?username="+ tlog.getString()+ "&password="+toldpass.getString());
            dis = new DataInputStream(con.openInputStream());
            byte data [] = new byte[100];
            int cnt = dis.read(data);
            String response = new String(data, 0, cnt);
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
  
}



//        for(int i=0;i<arr.length;i++)
//        {
//                    if(arr[i]!='-' || arr[i]!=',')
//                    {
//                        name=name+arr[i];
//                    }
//                    else
//                    {
//                        param[count++]=name;
//                        System.out.println(param[count]+"=="+name);
//                        name="";
//                    }
//        }
        


                




//                if(formmenu.getString(formmenu.getSelectedIndex()).equals("Soaps"))
//                {
//                    itemname=formmenu.getString(formmenu.getSelectedIndex());
//                    switchDisplay(formbuyitem);
//                    System.out.println("Choosed : "+formmenu.getString(formmenu.getSelectedIndex()));
//                }
//                else if(formmenu.getString(formmenu.getSelectedIndex()).equals("Shampoos"))
//                {
//                    itemname=formmenu.getString(formmenu.getSelectedIndex());
//                    switchDisplay(formbuyitem);
//                    System.out.println("Choosed : "+formmenu.getString(formmenu.getSelectedIndex()));
//                }
//                else if(formmenu.getString(formmenu.getSelectedIndex()).equals("Detergents"))
//                {
//                    itemname=formmenu.getString(formmenu.getSelectedIndex());
//                    switchDisplay(formbuyitem);
//                    System.out.println("Choosed : "+formmenu.getString(formmenu.getSelectedIndex()));
//                }
//                else if(formmenu.getString(formmenu.getSelectedIndex()).equals("Biscuits"))
//                {
//                    itemname=formmenu.getString(formmenu.getSelectedIndex());
//                    switchDisplay(formbuyitem);
//                    System.out.println("Choosed : "+formmenu.getString(formmenu.getSelectedIndex()));
//                }
//                else if(formmenu.getString(formmenu.getSelectedIndex()).equals("Toothpastes"))
//                {
//                    itemname=formmenu.getString(formmenu.getSelectedIndex());
//                    switchDisplay(formbuyitem);
//                    System.out.println("Choosed : "+formmenu.getString(formmenu.getSelectedIndex()));
//                }