/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.http.Item;

import com.mba.html.user.transaction.CartItemObject;
import java.util.ArrayList;

/**
 *
 * @author VISHAL
 */
public class ItemList
{
    public ItemList(String category)
    {
        ArrayList<CartItemObject> item = ItemListGetter.getItemList(category);
        for(int i = 0 ; i < item.size() ; i++)
        {
            System.out.println();
            
        }
    }

    public static void main(String args[])
    {
        ItemList i = new ItemList("Vegetables");
    }
}
