/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.help;

import com.mba.user.help.mail.SendMail;

/**
 *
 * @author VISHAL
 */

//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example
//Just an extra example

public class SendQuery
{
    public SendQuery(String query)
    {
        SendMail s = new SendMail(query);
    }

    public SendQuery(String mail, String password, String query)
    {
        SendMail s = new SendMail(mail,password,query);
    }

    public static void main(String args[])
    {
//        SendQuery s = new SendQuery("hjhjhjhj");
        SendQuery s1 = new SendQuery("breetlee.2010@gmail.com","05081990","this is just a demo2");
    }
}
