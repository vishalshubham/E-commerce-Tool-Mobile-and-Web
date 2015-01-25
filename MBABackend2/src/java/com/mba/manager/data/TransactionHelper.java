
package com.mba.manager.data;

import com.mba.manager.main.TransactionStatus;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public abstract class TransactionHelper
{
   
    public abstract void serviceRequest(Map<String, String> requestData, TransactionStatus ts, Map<String , Object> transactionObjects);

}
