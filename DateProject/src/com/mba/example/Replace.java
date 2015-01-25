/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.example;

/**
 *
 * @author VISHAL
 */
public class Replace
{
    public static String replace(String text, String str1, String str2)
    {
		text.replaceAll(" ", "%20");
                return text;
    }
}
