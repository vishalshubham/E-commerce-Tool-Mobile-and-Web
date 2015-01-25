/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.data;

/**
 *
 * @author VISHAL
 */
public class AccessAuthenticate
{
    private String isCustomer = null;
    private String isManager = null;
    
    public void setIsCustomer(String isCustomer)
    {
        this.isCustomer = isCustomer;
    }
    public void setIsManager(String isManager)
    {
        this.isManager = isManager;
    }
   

    public String getIsCustomer()
    {
        return isCustomer;
    }
    public String getIsManager()
    {
        return isManager;
    }
    
}
