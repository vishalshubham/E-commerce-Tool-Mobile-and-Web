package com.mba.user.main;

import com.mba.user.data.*;
import com.mba.user.help.*;
import com.mba.user.transaction.*;
import com.mba.user.updater.*;
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
                System.out.println("Our service id is :"+intserviceid+"no");

                switch(intserviceid)
                {
                    default :   System.out.println("Our service id is :"+intserviceid+"no");
//                    case 1:     ts = SignUp.signUpUser(requestDataMap, ts, transactionObjects);
//                                System.out.println("Our result is : Sign Up " + ts.getTransactionStatusMessage());
//                                break;
                                
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

                    case 7:     ts = OrderCancel.cancelOrder(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Cancel Order "+ts.getTransactionStatusMessage());
                                break;
                                
                    case 8:     ts = GeneralQuery.sendGeneralQuery(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : General query");
                                break;

                    case 9:     ts = SpecificQuery.sendSpecificQuery(requestDataMap, ts, transactionObjects);
                                System.out.println("Our result is : Specific query");
                                break;

                }
                TransactionAdder ta = new TransactionAdder(ts,requestDataMap);
            return ts;
    }
}
