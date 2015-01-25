/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.main;

import com.mba.manager.data.AccessAuthenticationGetter;
import com.mba.manager.data.LoginAuthenticationGetter;
import com.mba.manager.data.PasswordRetainer;
import com.mba.manager.data.ReplayAttackCheckGetter;
import com.mba.manager.data.TransactionStatusCode;
import com.mba.manager.data.TransactionHelper;
import com.mba.manager.data.UserDetailsGetter;
import com.mba.manager.services.categorysalesanalysis.CategorySalesAnalysisDaily;
import com.mba.manager.services.categorysalesanalysis.CategorySalesAnalysisMonthly;
import com.mba.manager.services.categorysalesanalysis.CategorySalesAnalysisYearly;
import com.mba.manager.services.expirydateanalysis.ExpiryDateAnalysis;
import com.mba.manager.services.homedeliveryanalysis.HomeDeliveryAnalysisDaily;
import com.mba.manager.services.homedeliveryanalysis.HomeDeliveryAnalysisMonthly;
import com.mba.manager.services.homedeliveryanalysis.HomeDeliveryAnalysisYearly;
import com.mba.manager.services.productsalesanalysis.ProductSalesAnalysisDaily;
import com.mba.manager.services.productsalesanalysis.ProductSalesAnalysisMonthly;
import com.mba.manager.services.productsalesanalysis.ProductSalesAnalysisYearly;
import com.mba.manager.services.revenuegenerated.RevenueGeneratedDaily;
import com.mba.manager.services.revenuegenerated.RevenueGeneratedMonthly;
import com.mba.manager.services.revenuegenerated.RevenueGeneratedYearly;
import com.mba.manager.services.stockquantity.StockQuantityAnalysis;
import com.mba.manager.services.zerosalesanalysis.ZeroSalesAnalysisDaily;
import com.mba.manager.services.zerosalesanalysis.ZeroSalesAnalysisTillDate;
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

    public static String finalResult;

    static
    {
        transactionHelpers.add(new UserDetailsGetter());
        transactionHelpers.add(new LoginAuthenticationGetter());
        transactionHelpers.add(new AccessAuthenticationGetter());
        transactionHelpers.add(new ReplayAttackCheckGetter());
    }
    public static TransactionStatus processUserRequest(Map<String,String> requestDataMap)
    {
        finalResult = "";

        TransactionStatus ts = new TransactionStatus( TransactionIdGetter.getTransactionId());
        Map<String, Object> transactionObjects = new HashMap<String, Object>();

        System.out.println("Password is : "+PasswordRetainer.getPassword());
        System.out.println("1111111111111111111111:");
                

                for(TransactionHelper sp : transactionHelpers)
                {
                    sp.serviceRequest(requestDataMap, ts, transactionObjects);

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
                    /***************************************************************************************************************************************************************/
                    case 21:    ts = CategorySalesAnalysisDaily.getDailyReport(requestDataMap, ts, transactionObjects);//CartSend.sendCart(requestDataMap, ts, transactionObjects);
                                finalResult = CategorySalesAnalysisDaily.categorySalesAnalysisDailyResult;
                                System.out.println("Our result is : Category Sales Analysis daily" + ts.getTransactionStatusMessage());
                                break;
                    case 22:    ts = CategorySalesAnalysisMonthly.getDailyReport(requestDataMap, ts, transactionObjects);//CartSend.sendCart(requestDataMap, ts, transactionObjects);
                                finalResult = CategorySalesAnalysisMonthly.categorySalesAnalysisMonthlyResult;
                                System.out.println("Our result is : Category Sales Analysis monthly" + ts.getTransactionStatusMessage());
                                break;
                    case 23:    ts = CategorySalesAnalysisYearly.getDailyReport(requestDataMap, ts, transactionObjects);//CartSend.sendCart(requestDataMap, ts, transactionObjects);
                                finalResult = CategorySalesAnalysisYearly.categorySalesAnalysisYearlyResult;
                                System.out.println("Our result is : Category Sales Analysis yearly" + ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                                
                    case 24:    ts = ProductSalesAnalysisDaily.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ProductSalesAnalysisDaily.productSalesAnalysisDailyResult;
                                System.out.println("Our result is : Product Sales Analysis daily"+ts.getTransactionStatusMessage());
                                break;
                    case 25:    ts = ProductSalesAnalysisMonthly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ProductSalesAnalysisMonthly.productSalesAnalysisMonthlyResult;
                                System.out.println("Our result is : Product Sales Analysis monthly"+ts.getTransactionStatusMessage());
                                break;
                    case 26:    ts = ProductSalesAnalysisYearly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ProductSalesAnalysisYearly.productSalesAnalysisYearlyResult;
                                System.out.println("Our result is : Product Sales Analysis yearly"+ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                    case 27:    ts = HomeDeliveryAnalysisDaily.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = HomeDeliveryAnalysisDaily.homeDeliveryAnalysisDailyResult;
                                System.out.println("Our result is : Home Delivery Analysis daily"+ts.getTransactionStatusMessage());
                                break;
                    case 28:    ts = HomeDeliveryAnalysisMonthly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = HomeDeliveryAnalysisMonthly.homeDeliveryAnalysisMonthlyResult;
                                System.out.println("Our result is : Home Delivery Analysis monthly"+ts.getTransactionStatusMessage());
                                break;
                    case 29:    ts = HomeDeliveryAnalysisYearly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = HomeDeliveryAnalysisYearly.homeDeliveryAnalysisYearlyResult;
                                System.out.println("Our result is : Home Delivery Analysis yearly"+ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                    case 30:    ts = StockQuantityAnalysis.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = StockQuantityAnalysis.stockQuantityAnalysisDailyResult;
                                System.out.println("Our result is : stock quantity Analysis daily"+ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                    case 31:    ts = ZeroSalesAnalysisTillDate.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ZeroSalesAnalysisTillDate.zeroSalesAnalysisTillDateResult;
                                System.out.println("Our result is : Zero Sales Analysis Till date"+ts.getTransactionStatusMessage());
                                break;
                    case 32:    ts = ZeroSalesAnalysisDaily.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ZeroSalesAnalysisDaily.zeroSalesAnalysisDailyResult;
                                System.out.println("Our result is : Zero Sales Analysis daily"+ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                    case 33:    ts = RevenueGeneratedDaily.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = RevenueGeneratedDaily.revenueGeneratedDailyResult;
                                System.out.println("Our result is : Revenue generated daily"+ts.getTransactionStatusMessage());
                                break;
                    case 34:    ts = RevenueGeneratedMonthly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = RevenueGeneratedMonthly.revenueGeneratedMonthlyResult;
                                System.out.println("Our result is : Revenue generated monthly"+ts.getTransactionStatusMessage());
                                break;
                    case 35:    ts = RevenueGeneratedYearly.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = RevenueGeneratedYearly.revenueGeneratedYearlyResult;
                                System.out.println("Our result is : Revenue generated yearly"+ts.getTransactionStatusMessage());
                                break;
                    /***************************************************************************************************************************************************************/
                    case 36:    ts = ExpiryDateAnalysis.getDailyReport(requestDataMap, ts, transactionObjects);
                                finalResult = ExpiryDateAnalysis.expiryDateAnalysisResult;
                                System.out.println("Our result is : Expiry Date"+ts.getTransactionStatusMessage());
                                break;
                }


                TransactionAdder ta = new TransactionAdder(ts);
            return ts;
    }
}
