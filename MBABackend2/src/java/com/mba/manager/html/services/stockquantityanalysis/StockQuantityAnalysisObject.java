/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.html.services.stockquantityanalysis;

/**
 *
 * @author VISHAL
 */
public class StockQuantityAnalysisObject
{
    String itemid;
    String item_name;
    String category;
    String quantity;

    public String getCategory() {
        return category;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItemid() {
        return itemid;
    }

    public String getQuantity() {
        return quantity;
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

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    

}
