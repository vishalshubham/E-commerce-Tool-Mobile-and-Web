
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
public class FormChangeAny implements CommandListener
{
    private Form fchangename,fchangeaddress,fchangecontact,fchangepassword;
    private TextField tname;
    private Command ok1,back1;
    private TextField taddress1;
    private TextField taddress2;
    private TextField tcity;
    private TextField tstate;
    private TextField tpincode;
    private Command ok2;
    private Command back2;
    private TextField tmobile;
    private TextField temail;
    private Command ok3;
    private Command back3;
    private TextField toldpass;
    private TextField tnewpass;
    private TextField tconfirmnewpass;
    private Command ok4;
    private Command back4;
    private List formchange;
    private String strusername;

    public Form makeFormChangeName()
    {
        fchangename=new Form("Change Your Name:");
        tname=new TextField("New Name:",null,25,TextField.ANY);
        fchangename.append(tname);
        ok1=new Command("Change",Command.OK,1);
        back1=new Command("Back",Command.BACK,1);
        fchangename.addCommand(ok1);
        fchangename.addCommand(back1);
        fchangename.setCommandListener(this);
        return fchangename;
    }

    static Form getFormChangename()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeName();
    }

     public Form makeFormChangeAddress()
    {
        fchangeaddress=new Form("Change Your Address:");
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

    static Form getFormChangeAddress()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeAddress();
    }

     public Form makeFormChangeContact()
    {
        fchangecontact=new Form("Change Your Contact:");
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

    static Form getFormChangeContact()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangeContact();
    }

     public Form makeFormChangePassword()
    {
        System.out.println("ddddddddddddddddddddddddddddddddddddd");
        fchangepassword=new Form("Change Your Password:");
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

    static Form getFormChangePassword()
    {
        FormChangeAny fc = new FormChangeAny();
        return fc.makeFormChangePassword();
    }

    private boolean changePassword()
    {
        try
        {
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.print("Changing Password to: " + tnewpass.getString());
            if(tnewpass.getString().indexOf(" ")!=-1 || spacedate.indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                String password = StringUtilities.replaceData(tnewpass.getString()," ","%20");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                while(tnewpass.getString().indexOf(" ")!=-1)
                {
                    password=StringUtilities.replaceData(tnewpass.getString()," ","%20");
                }

                System.out.println("11111111111111111111111111111111");
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=6&requestsourceid=1&password="+ password+ "&username="+FormLogin.getUserName()+ "&date="+spacedate);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=6&requestsourceid=1&password="+ tnewpass.getString()+ "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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
            DateGetter d = new DateGetter();
            String date = d.getDate();
            DataInputStream dis;
            String contact= tmobile.getString();
            String email= temail.getString();
            System.out.print("Changing Contact to: " + contact + email);

            contact = StringUtilities.replaceData(contact, " ", "%20");

            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=5&requestsourceid=1&contact=" + contact + "&email=" + email + "&username=" + FormLogin.getUserName()+ "&date="+date);
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
            DateGetter d = new DateGetter();
            String date = d.getDate();
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

                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=4&requestsourceid=1&address="+ address+ "&city="+city+"&state="+state+"&pincode="+pincode+ "&username="+FormLogin.getUserName()+ "&date="+date);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=4&requestsourceid=1&address="+ address+"&city="+city+"&state="+state+"&pincode="+pincode+  "&username="+FormLogin.getUserName()+ "&date="+date);
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
            DateGetter d = new DateGetter();
            String spacedate = d.getDate();
            DataInputStream dis;
            System.out.print("Changing Name to: " + tname.getString());
            if(tname.getString().indexOf(" ")!=-1)
            {
                spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                String newarr = StringUtilities.replaceData(tname.getString()," ","%20");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                while(tname.getString().indexOf(" ")!=-1)
                {
                    newarr=StringUtilities.replaceData(tname.getString()," ","%20");
                }
                System.out.println(spacedate + " : date is thissssssssssss ");
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=3&requestsourceid=1&name="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=3&requestsourceid=1&name="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=3&requestsourceid=1&name="+ newarr+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                dis = new DataInputStream(con.openInputStream());
            }
            else
            {
                System.out.println(spacedate + " : date is this ");
                while(spacedate.indexOf(" ")!=-1)
                {
                    spacedate = StringUtilities.replaceData(spacedate, " ", "%20");
                }
                System.out.println(spacedate + " : date is this ");
                System.out.println("http://localhost:8084/MBA/MainHandler?serviceid=3&requestsourceid=1&name="+ tname.getString()+ "&username="+FormLogin.getUserName() + "&date="+spacedate);
                HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/MainHandler?serviceid=3&requestsourceid=1&name="+ tname.getString()+ "&username="+FormLogin.getUserName()+ "&date="+spacedate);
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

     private boolean validLoginChange()
    {
        try
        {
            DataInputStream dis;
            
            strusername=FormLogin.getUserName();   // try to keep it inside the if block below
            System.out.print("SENDING : " + strusername + " ----- " + toldpass.getString());
            HttpConnection con = (HttpConnection) Connector.open("http://localhost:8084/MBA/Login?serviceid=1&requestsourceid=1&username="+ FormLogin.getUserName()+ "&password="+toldpass.getString());
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



    public void commandAction(Command c, Displayable d)
    {
        if(d==fchangename)
        {
            if(c==back1)
            {
                tname.setString("");
                formchange = FormChange.getFormChange();
                MobileMidlet1.switchDisplay(formchange);
            }
            else if(c==ok1)
            {
                if(changeName())
                {
                    Alert alert= new Alert("NAME CHANGED::","Your Account Name Has Been Changed To::"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet1.switchDisplay(alert);
                }
            }
        }
        else if(d==fchangeaddress)
        {
            if(c==back2)
            {
                taddress1.setString("");
                taddress2.setString("");
                tcity.setString("");
                tstate.setString("");
                tpincode.setString("");
                formchange = FormChange.getFormChange();
                MobileMidlet1.switchDisplay(formchange);
            }
            else if(c==ok2)
            {
                if(changeAddress())
                {
                    Alert alert= new Alert("ADDRESS CHANGED::","Your Account Address Has Been Changed To::"+tcity.getString()+"::"+tstate.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet1.switchDisplay(alert);
                }
            }
        }
        else if(d==fchangecontact)
        {
            if(c==back3)
            {
                tmobile.setString("");
                temail.setString("");
                formchange= FormChange.getFormChange();
                MobileMidlet1.switchDisplay(formchange);
            }
            else if(c==ok3)
            {
                if(changeContact())
                {
                    Alert alert= new Alert("CONTACT CHANGED::","Your Account Contact Has Been Changed To::"+tmobile.getString()+"::"+temail.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet1.switchDisplay(alert);
                }
                else
                {
                    Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.ERROR);
                    MobileMidlet1.switchDisplay(alert);
                }
            }
        }
        else if(d==fchangepassword)
        {
            if(c==back4)
            {
                toldpass.setString("");
                tnewpass.setString("");
                tconfirmnewpass.setString("");
                formchange=FormChange.getFormChange();
                MobileMidlet1.switchDisplay(formchange);
            }
            else if(c==ok4)
            {
                System.out.println("iiiiiiiiiiiiiiiiiiiiiii");
                if(!(tnewpass.getString().equals("")) && tnewpass.getString().equals(tconfirmnewpass.getString()) && validLoginChange())
                {
                    System.out.println("jjjjjjjjjjjjjjjjjjjjjjj");
                    if(changePassword())
                    {
                        System.out.println("kkkkkkkkkkkkkkkkkkkkk");

                        Alert alert= new Alert("PASSWORD CHANGED::","Your Account Password Has Been Changed To::"+tnewpass.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.INFO);
                        MobileMidlet1.switchDisplay(alert);
                    }
                    else
                    {
                        Alert alert= new Alert("SORRY::","Some ERROR has been occured on the server"+tname.getString(),null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet1.switchDisplay(alert);
                    }
                }
                else
                {
                        Alert alert= new Alert("SORRY PASSWORD NOT CHANGED::","Your Account Password is wrong or two different Passwords are Entered or Blank Passwords Are not allowed",null,null);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setType(AlertType.ERROR);
                        MobileMidlet1.switchDisplay(alert);

                }
            }
        }
    }
}
