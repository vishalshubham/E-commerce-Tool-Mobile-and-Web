import java.util.Vector;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.ConnectionNotFoundException;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Choice;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.UUID;

import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.L2CAPConnectionNotifier;
import javax.bluetooth.L2CAPConnection;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.ServiceRegistrationException;

public class ChatController extends MIDlet implements CommandListener
{
 private Display display = null;
 private Form mainForm = null;
 private ChoiceGroup devices = null;
 private TextField inTxt = null;
 private TextField outTxt = null;
 private Command exit = null;
 private Command start = null;
 private Command connect = null;
 private Command send = null;
 private Command select = null;
 private StringItem status = null;

 private LocalDevice local = null;
 private RemoteDevice rDevices[];
 private ServiceRecord service = null;
 private DiscoveryAgent agent = null;
 private L2CAPConnectionNotifier notifier;
 private L2CAPConnection connection = null;

 private static final String UUID_STRING = "112233445566778899AABBCCDDEEFF";
 private String next2send="";
 private boolean running = false;
 private static final String req_id="req_1";
 private boolean flag=false;
 public ChatController()
	{
     super();
     display = Display.getDisplay(this);
	 mainForm = new Form("CHAT");
	 devices = new ChoiceGroup(null,Choice.EXCLUSIVE);
	 inTxt = new TextField("incoming msg:","",256,TextField.ANY);
	 outTxt = new TextField("outgoing msg:","",256,TextField.ANY);
	 exit = new Command("EXIT",Command.EXIT,1);
	 start = new Command("START",Command.SCREEN,2);
	 connect = new Command("CONNECT",Command.SCREEN,2);
	 send = new Command("SEND",Command.SCREEN,2);
	 select = new Command("SELECT",Command.SCREEN,2);
	 status = new StringItem("status : ",null);
	 mainForm.append(status);
	 mainForm.addCommand(exit);
	 mainForm.setCommandListener(this);
	}


 protected void startApp() throws MIDletStateChangeException
	{
     running = true;
	 mainForm.addCommand(start);
     mainForm.addCommand(connect);
	 display.setCurrent(mainForm);
     try
       {
        local = LocalDevice.getLocalDevice();
	    agent = local.getDiscoveryAgent();
       }
     catch(BluetoothStateException bse)
       {
        status.setText("BluetoothStateException unable to start:"+bse.getMessage()); 
		try
		  {
		   Thread.sleep(1000);	
	      }
	 	catch(InterruptedException ie)
		  {}
        notifyDestroyed();
       }
    }

 protected void pauseApp()
	{
     running = false;
	 releaseResources();
	}

 protected void destroyApp(boolean uncond) throws MIDletStateChangeException
	{
     running = false;
	 releaseResources();
    }

 public void commandAction(Command cmd,Displayable disp)
	{
     if(cmd==exit)
		{
	     running = false;
		 releaseResources();
		 notifyDestroyed();
	    }
     else if(cmd==start)
		   {
	        new Thread()
			   {
			    public void run()
				   {
				    startServer();
				   }
			   }.start();
 		   }
          else if(cmd==connect)
		         {
		          status.setText("searching for devices...");
				  mainForm.removeCommand(connect);
				  mainForm.removeCommand(start);
				  mainForm.append(devices);
				  devices.deleteAll();
				  
				  flag=true;	
				  DeviceDiscoverer discoverer = new DeviceDiscoverer(ChatController.this);
				  try
				     {
				  	  agent.startInquiry(DiscoveryAgent.GIAC,discoverer);
				     }
                     
				  catch(IllegalArgumentException iae)
				     {
                      status.setText("BluetoothStateException :"+iae.getMessage());  
				     }
				  catch(NullPointerException npe)
				     {
                      status.setText("BluetoothStateException :"+npe.getMessage());  
				     }
				  catch(BluetoothStateException bse1)
				     {
                      status.setText("BluetoothStateException :"+bse1.getMessage());  
				     }
                  }
                else if(cmd==select)
		              {
				       status.setText("searching devices for service...");
					   int index = devices.getSelectedIndex();
					   mainForm.delete(mainForm.size()-1);//deletes choiceGroup
					   mainForm.removeCommand(select);

					   ServiceDiscoverer serviceDListener = new ServiceDiscoverer(ChatController.this);
					   int attrSet[] = {0x0100}; //returns service name attribute
                       UUID[] uuidSet = {new UUID(UUID_STRING,false)}; 

					   try
					      {
					   	   agent.searchServices(attrSet,uuidSet,rDevices[index],serviceDListener);
					      }
					    catch(IllegalArgumentException iae1)
				          {
                           status.setText("BluetoothStateException :"+iae1.getMessage());  
				          }
				        catch(NullPointerException npe1)
				          {
                           status.setText("BluetoothStateException :"+npe1.getMessage());  
				          }
				        catch(BluetoothStateException bse11)
				          {
                           status.setText("BluetoothStateException :"+bse11.getMessage());  
				          }
				       }
                      else if(cmd==send)
		                     {
					          new Thread()
								 {
							      public void run()
									 {
								      sendMessage();
								     }
							     }.start();
							 }

	 }

//this method is called from DeviceDiscoverer when device inquiry finishes
 public void deviceInquiryFinished(RemoteDevice[] rDevices,String message)
	{
     this.rDevices = rDevices;
	 String deviceNames[] = new String[rDevices.length]; 
	 for(int k=0;k<rDevices.length;k++)
		{
	     try
	       {
	     	deviceNames[k] = rDevices[k].getFriendlyName(false);
	       }
	     catch(IOException ioe)
	       {
	        status.setText("IOException :"+ioe.getMessage());
		   }
	    }
     for(int l=0;l<deviceNames.length;l++)
		{
	     devices.append(deviceNames[l],null);
	    }
     mainForm.addCommand(select);
	 status.setText(message);
    }

//called by ServiceDiscoverer when service search gets completed  
 public void serviceSearchFinished(ServiceRecord service,String message)
	{
	 String url = "";
     this.service = service;
	 status.setText(message+"happy");
	 try
	   {
	    url = service.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT,false);	
	   }
	 catch (IllegalArgumentException iae1)
	   {
	   		 message=message+" "+iae1.getMessage();
	   	}
     try
       {
       	releaseResources();
     	connection = (L2CAPConnection)Connector.open(url);
		status.setText("connected...");
		new Thread()
		   {
		    public void run()
			   {
			   	mainForm.addCommand(send);
			   	
			    startReciever();
			   }
		   }.start();
       }
     catch(IOException ioe1)
       {
        status.setText("IOException :"+ioe1.getMessage());
       }
	}

 // this method starts L2CAPConnection chat server from server mode 
 public void startServer()
	{
     status.setText("server starting...");
	 mainForm.removeCommand(connect);
	 mainForm.removeCommand(start);
	 try
	   {
	 	local.setDiscoverable(DiscoveryAgent.GIAC);

		notifier = (L2CAPConnectionNotifier)Connector.open("btl2cap://localhost:"+UUID_STRING+";name=L2CAPChat");

		ServiceRecord record = local.getRecord(notifier);
		String conURL = record.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT,false);
		status.setText("server running...");
        
		connection = notifier.acceptAndOpen();
		new Thread()
		   {
		    public void run()
			   {
			  	mainForm.addCommand(send);
			    startReciever();
			   }
		   }.start();
	   }
	 catch(IOException ioe3)
	   {
        status.setText("IOException :"+ioe3.getMessage());
	   }
    }

 //starts a message reciever listening for incomming message
 public void startReciever()
	{
     //mainForm.addCommand(send);
     mainForm.append(inTxt);
	 mainForm.append(outTxt);
     outTxt.setString(next2send);
	 while(running)
		{
	     try
	        {
	     	 if(connection.ready())
				{
			     int receiveMTU = connection.getReceiveMTU();
				 byte[] data = new byte[receiveMTU];
				 int length = connection.receive(data);
                                     
				 String message = new String(data,0,length);
				 inTxt.setString(message);
				 next2send=message+""+local.getFriendlyName()+"$";
				 outTxt.setString(next2send);
				  
				 //*****
				  status.setText("searching for devices...");
				  mainForm.removeCommand(send);
				  //releaseResources();
				  devices.deleteAll();
				  mainForm.append(devices);
				  
				 
				  DeviceDiscoverer discoverer = new DeviceDiscoverer(ChatController.this);
				  try
				     {
				  	  agent.startInquiry(DiscoveryAgent.GIAC,discoverer);
				     }
                     
				  catch(IllegalArgumentException iae)
				     {
                      status.setText("BluetoothStateException :"+iae.getMessage());  
				     }
				  catch(NullPointerException npe)
				     {
                      status.setText("BluetoothStateException :"+npe.getMessage());  
				     }
				  catch(BluetoothStateException bse1)
				     {
                      status.setText("BluetoothStateException :"+bse1.getMessage());  
				     }
				  mainForm.addCommand(select);
					
				 //****
				 
			    }
			    
	        }
	     catch(IOException ioe4)
	        {
	        status.setText("IOException :"+ioe4.getMessage());
			}
	    }
     }

 //sends a message over L2CAP
 public void sendMessage()
	{
		String message="";
     try
       {
       	if(!flag)
       	{
     	    message =req_id+"$"+local.getFriendlyName()+"$"+outTxt.getString()+"$$$";
        }
        else
        {
        	message=outTxt.getString();
        }
		byte[] data = message.getBytes();
		int transmitMTU = connection.getTransmitMTU();
		if(data.length <= transmitMTU)
		 {
		  connection.send(data);
		 }
        else
		 {
		  status.setText("message ....");
		 }
       }
     catch (IOException ioe5)
       {
        status.setText("IOException :"+ioe5.getMessage());
       }
   //*****
	  status.setText("searching for devices...");
	  mainForm.removeCommand(send);
	  // releaseResources();
	  devices.deleteAll();
	  mainForm.append(devices);
	  
	 
	  DeviceDiscoverer discoverer = new DeviceDiscoverer(ChatController.this);
	  try
	     {
	  	  agent.startInquiry(DiscoveryAgent.GIAC,discoverer);
	     }
        
	  catch(IllegalArgumentException iae)
	     {
         status.setText("BluetoothStateException :"+iae.getMessage());  
	     }
	  catch(NullPointerException npe)
	     {
         status.setText("BluetoothStateException :"+npe.getMessage());  
	     }
	  catch(BluetoothStateException bse1)
	     {
         status.setText("BluetoothStateException :"+bse1.getMessage());  
	     }
	  mainForm.addCommand(select);
		
	 //****
    }

 //closes L2CAP connection
 public void releaseResources()
	{
     try
       {
     	if(connection != null)
			connection.close();
		if(notifier != null)
			 notifier.close(); 
       }
     catch(IOException ioe6)
       {
        status.setText("IOException :"+ioe6.getMessage());
	   }
    }
}