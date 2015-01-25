
package com.mba.user.data;

import com.mba.user.main.TransactionStatus;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public abstract class TransactionHelper {

    
    public abstract void serviceRequest(Map<String, String> requestData, TransactionStatus ts, Map<String , Object> transactionObjects);

}
