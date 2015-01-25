/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.help.mail;

import java.io.Serializable;

/**
 *
 * @author Ravee
 */
public class ServerSettings implements Serializable{
    public static String mailID="best.mart.2011@gmail.com",mailPassword = "9975776839";
    public static String  mailPort, mailServer;
    public String ftpURL, ftpUser, ftpPassword, ftpDir;
    public String mobile;

    public ServerSettings()
    {
        mailID = "best.mart.2011@gmail.com";
        mailPassword = "9975776839";
        mailServer = "smtp.gmail.com";
        mailPort = "587";

        ftpURL = "";
        ftpUser = "";
        ftpPassword = "";
        ftpDir = "";

        mobile = "";
    }

    public ServerSettings(String mail, String password)
    {
        mailID = mail;
        mailPassword = password;
        mailServer = "smtp.gmail.com";
        mailPort = "587";

        ftpURL = "";
        ftpUser = "";
        ftpPassword = "";
        ftpDir = "";

        mobile = "";
    }

}
