package com.mba.user.help.mail;

import javax.mail.*;
import javax.mail.internet.*;

public class SendViaGMail 
{
    String userName;

    public SendViaGMail(String userName) 
    {
        this.userName = userName;
    }
    
    public boolean sendMail(Session session, String recipient, String subject, String message ,String from) {
        boolean debug = false;
        
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        try {
            // set the from and to address
            System.out.println("1"+recipient);
            InternetAddress addressFrom = new InternetAddress(from + "<" + userName + ">");
            System.out.println("2");
            msg.setFrom(addressFrom);
            System.out.println("3");
            InternetAddress[] addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(recipient);
            System.out.println("4");
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            System.out.println("5");
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            System.out.println("6");
            msg.setContent(message, "text/plain");
            System.out.println("7");
            Transport.send(msg);
            System.out.println("8");
            return true;
        }
        catch(Exception e) 
        {
            System.out.println("Mail Send Exception: " + e);
            return false;
        }
     }  
}
