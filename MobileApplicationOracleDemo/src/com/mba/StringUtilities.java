
package com.mba;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author VISHAL
 */
class StringUtilities
{

    static String replaceData(String text, String searchStr, String replacementStr)
    {
        StringBuffer sb = new StringBuffer();

        int searchStringPos = text.indexOf(searchStr);
        int startPos = 0;
        int searchStringLength = searchStr.length();

        while (searchStringPos != -1)
        {
            sb.append(text.substring(startPos, searchStringPos)).append(replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = text.indexOf(searchStr, startPos);
        }

        sb.append(text.substring(startPos,text.length()));
        return sb.toString();
    }
}
