/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.extra;

/**
 *
 * @author VISHAL
 */
public class Extra
{
    public Extra()
    {
        String extra = "select sum(qty) from TransactionBuyItemDetails where itemid IN (select itemid from iteminfo where category-?) and tid IN (select tid from TransactionDettemp where tdate between ? and ?)";
        String extra1 = "select sum(qty) from TransactionBuyItemDetails where itemid-? and tid IN (select tid from TransactionDettemp where tdate between ? and ?)";
        System.out.println(" This is the query : " + extra + "--" + extra1);
    }    
}
