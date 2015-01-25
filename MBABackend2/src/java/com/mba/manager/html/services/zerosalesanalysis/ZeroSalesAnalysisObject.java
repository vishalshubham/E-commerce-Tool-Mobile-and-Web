/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.zerosalesanalysis;

/**
 *
 * @author VISHAL
 */
public class ZeroSalesAnalysisObject
{
    String itemid;
    String item_name;
    String category;
    String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItemid() {
        return itemid;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
}
