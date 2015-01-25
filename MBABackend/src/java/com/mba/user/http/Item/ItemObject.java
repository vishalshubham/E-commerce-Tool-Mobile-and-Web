package com.mba.user.http.Item;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author VISHAL
 */
public class ItemObject
{
    public String itemId;
    private String itemName;
    private String itemPrice;

    public String getItemId()
    {
        return itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

}
