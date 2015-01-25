/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.help.mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author VISHAL
 */
public class SendMail
{
    public Session session;
    ServerSettings serverSettings;
    public static String status;
    
    public SendMail(String query)
    {
        System.out.println("1111111111");
        if(session==null)
        {
            System.out.println("222222222222");
            createSession();
            System.out.println("333333333333");
        }
        System.out.println("55555555555");
        SendViaGMail sendGMail = new SendViaGMail(serverSettings.mailID);
        if(sendGMail.sendMail(session,/*jTextMailID.getText()*/"best.mart.2011@gmail.com", "FROMSERVER: TEST MAIL", query, "ADMIN")) {
            System.out.println("Message Sent dude");
            status="yes";
        }
        else
        {
            System.out.println("Sorry Message Sent dude");
            status="no";
        }
    }

    public SendMail(String fromMail, String query)
    {
        System.out.println("1111111111");
        if(session==null)
        {
            System.out.println("222222222222");
            createSession();
            System.out.println("333333333333----------");
        }
        System.out.println("55555555555");
        SendViaGMail sendGMail = new SendViaGMail(serverSettings.mailID);
        if(sendGMail.sendMail(session,/*jTextMailID.getText()*/fromMail, "FROMSERVER: TEST MAIL", query, "ADMIN")) {
            System.out.println("Message Sent dude");
        }
        else
        {
            System.out.println("Sorry Message Sent dude");
        }

    }

    public SendMail(String mail,String password,String query)
    {
//        serverSettings.mailID = mail;
//        serverSettings.mailPassword = password;

        System.out.println("1111111111");
        if(session==null)
        {
            System.out.println("222222222222");
            createSession();
            System.out.println("333333333333");
        }
        System.out.println("55555555555");
        SendViaGMail sendGMail = new SendViaGMail(serverSettings.mailID);
        if(sendGMail.sendMail(session,/*jTextMailID.getText()*/"vishal.shubham@gmail.com", "FROMSERVER: TEST MAIL", query, "ADMIN")) {
            System.out.println("Message Sent dude");
        }
        else
        {
            System.out.println("Sorry Message Sent dude");
        }
    }

    public void createSession()
    {
        try {
            System.out.println("44");
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            System.out.println("55");
            Authenticator auth = new SMTPAuthenticator();
            System.out.println("66");
            props.setProperty("mail.store.protocol", "imaps");
            session = Session.getDefaultInstance(props, auth);
            System.out.println("77");
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            System.out.println("Authenticating : "+serverSettings.mailID+" : "+serverSettings.mailPassword);
            return new PasswordAuthentication(serverSettings.mailID, serverSettings.mailPassword);
        }
    }

    public static void main(String args[])
    {
        SendMail s = new SendMail("vishal.shubham@gmail.com","Baby pagal hai i luv u  http://www.google.com");
    }
}
