/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.expirydateanalysis;

/**
 *
 * @author VISHAL
 */
public class ExpiryDateAnalysisObject
{
    String itemid;
    String item_name;
    String noOfDays;

    public String getItem_name() {
        return item_name;
    }

    public String getItemid() {
        return itemid;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    

}
