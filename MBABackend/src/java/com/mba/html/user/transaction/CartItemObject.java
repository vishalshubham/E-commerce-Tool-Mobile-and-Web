
package com.mba.html.user.transaction;

/**
 *
 * @author VISHAL
 */
public class CartItemObject
{
    String itemId;
    String itemName;
    String itemPrice;
    String category;
    String quantity;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    

}

