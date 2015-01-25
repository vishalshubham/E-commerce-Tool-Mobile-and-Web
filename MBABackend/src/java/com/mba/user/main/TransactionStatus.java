
package com.mba.user.main;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author VISHAL
 */
public class TransactionStatus
{
    private int tid;
    private int request_type;
    private int statuscode;
    private String transaction_date_time;
    private String transactionStatusMessage;

    private Date date;
    public TransactionStatus(int transactionId)
    {
        DateGetter date = new DateGetter();
        tid = transactionId;
        transaction_date_time  = date.getDate();
//        transaction_date_time = Integer.toString(date.getYear())+Integer.toString(date.getMonth())+Integer.toString(date.getDate())+Integer.toString(date.getHours())+Integer.toString(date.getMinutes())+Integer.toString(date.getSeconds());
    }
    
    public int getTid()
    {
        return tid;
    }
   
    public int getRequestType()
    {
        return request_type;
    }

    public void setRequestType(int request_type)
    {
        this.request_type = request_type;
    }

    public int getStatusCode()
    {
        return statuscode;
    }

    public void setStatusCode(int statuscode)
    {
        this.statuscode = statuscode;
    }


    public String getTransactionStatusMessage() {
        return transactionStatusMessage;
    }

    public void setTransactionStatusMessage(String transactionStatusMessage) {
        this.transactionStatusMessage = transactionStatusMessage;
    }

    public String getTransaction_date_time() {
        return transaction_date_time;
    }

    
    


//    public void setTid(int tid)
//    {
//        this.tid = tid;
//    }
    
   
}
