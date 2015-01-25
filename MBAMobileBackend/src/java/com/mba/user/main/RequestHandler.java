/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.user.main;

import com.mba.user.main.TransactionAdder;
import com.mba.user.main.TransactionIdGetter;
import com.mba.user.main.TransactionStatus;
import com.mba.user.data.AccessAuthenticate;
import com.mba.user.data.AccessAuthenticationGetter;
import com.mba.user.data.LoginAuthenticate;
import com.mba.user.data.LoginAuthenticationGetter;
import com.mba.user.data.PasswordRetainer;
import com.mba.user.data.ReplayAttackCheckGetter;
import com.mba.user.data.TransactionStatusCode;
import com.mba.user.data.UserDetails;
import com.mba.user.data.TransactionHelper;
import com.mba.user.data.UserDetailsGetter;
import com.mba.user.transaction.CartSend;
import com.mba.user.updater.AddressUpdate;
import com.mba.user.updater.ContactUpdate;
import com.mba.user.updater.NameUpdate;
import com.mba.user.updater.PasswordUpdate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author VISHAL
 */
public class RequestHandler
{

    private static ArrayList<TransactionHelper> transactionHelpers = new ArrayList<TransactionHelper>();


    static
    {
        transactionHelpers.add(new UserDetailsGetter());
        transactionHelpers.add(new LoginAuthenticationGetter());
        transactionHelpers.add(new AccessAuthenticationGetter());
        transactionHelpers.add(new ReplayAttackCheckGetter());
    }
    public static TransactionStatus processUserRequest(Map<String,String> requestDataMap)
    {

        TransactionStatus ts = new TransactionStatus( TransactionIdGetter.getTransactionId());
        Map<String, Object> transactionObjects = new HashMap<String, Object>();

        System.out.println("Password is : "+PasswordRetainer.getPassword());
        System.out.println("1111111111111111111111:");
                

                for(TransactionHelper sp : transactionHelpers)
                {
                    sp.serviceRequest(requestDataMap, ts, transactionObjects);

//                    AccessAuthenticate aa = (AccessAuthenticate) transactionObjects.get("AccessAuthenticateObject");
//                    System.out.println("this is the manager status : "+aa.getIsManager());
//                    System.out.println("this is the customer status : "+aa.getIsCustomer());

//                    LoginAuthenticate la = (LoginAuthenticate) transactionObjects.get("LoginAuthenticateObject");
//                    System.out.println("this is my login id : " + la.getUsername());
//                    System.out.println("this is my login password : " + la.getPassword());

//                    UserDetails t = (UserDetails) transactionObjects.get("UserDetailsObject");
//                    System.out.println("This is my cid : "+t.getCid());
//                    System.out.println("This is my cname : "+t.getCname());
//                    System.out.println("This is my Last transaction time : " + t.getLast_transaction_date());

                    if(ts.getStatusCode() != TransactionStatusCode.SUCCESS )
                    {
                        return ts;
                    }
                }

                String serviceid = requestDataMap.get("serviceid");
                if(serviceid == null)
                {
                    ts.setStatusCode(TransactionStatusCode.INVALID_PARAMETERS);
                    ts.setTransactionStatusMessage("Invalid request received. Cannot process request.");
                    return ts;
                }
                System.out.println("2222222222222222222222:");

                int intserviceid = Integer.parseInt(serviceid);
                System.out.println("Our service id is : "+intserviceid);

                switch(intserviceid)
                {
                    case 2:     ts = CartSend.sendCart(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Cart send " + ts.getTransactionStatusMessage());
                                break;
                            
                    case 3:     ts = NameUpdate.updateName(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Change name"+ts.getTransactionStatusMessage());
                                break;
                            
                    case 4:     ts = AddressUpdate.updateAddress(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Change Address "+ts.getTransactionStatusMessage());
                                break;
                            
                    case 5:     ts = ContactUpdate.updateContact(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Change Contact "+ts.getTransactionStatusMessage());
                                break;
                            
                    case 6:     ts = PasswordUpdate.updatePassword(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Change Password "+ts.getTransactionStatusMessage());
                                break;
                }


                TransactionAdder ta = new TransactionAdder(ts);
            return ts;
    }
}
