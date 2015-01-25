/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.data;

/**
 *
 * @author VISHAL
 */
public class PasswordRetainer
{
    public static String password = null;
    public static void setPassword(String pass)
    {
        password = pass;
    }

    public static String getPassword()
    {
        return password;
    }
}
