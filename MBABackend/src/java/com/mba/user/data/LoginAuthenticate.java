/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.data;

/**
 *
 * @author VISHAL
 */
public class LoginAuthenticate
{
    private String password = null;
    private String username = null;

    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }
    public String getUsername()
    {
        return username;
    }

}
