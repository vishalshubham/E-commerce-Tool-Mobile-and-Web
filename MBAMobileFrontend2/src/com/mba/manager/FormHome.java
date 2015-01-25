
package com.mba.manager;

import javax.microedition.lcdui.*;
/**
 *
 * @author VISHAL
 */
public class FormHome implements CommandListener
{

    private List fhome;
    private List formgroupsalesanalysis;
    private List formchange;
    private String home="Category Sales Analysis,Product Sales Analysis,Revenue Generated,Zero Sales Analysis,Home Delivery Calls,Expiry Date Analysis,Stock Quantity Analysis,Help,Exit,";
    // Group Sales Analysis,Product Sales Analysis,Revenue Generated,Zero Sales Analysis,Home Delivery Calls,Expiry Calls,Stock Quantity Analysis,Help,Exit,
    private char chome[]=home.toCharArray();
    private Form formcheckyourcart;
    private Form formcancelorder;
    private List formproductsalesanalysis;
    private List formhomedeliveryanalysis;
    private List formstockquantityanalysis;
    private List formrevenuegenerated;
    private List formexpirydateanalysis;



    public List makeFormHome()
    {
        fhome=new List("Enter Your Choice:",List.IMPLICIT);
        returnMenu(fhome,chome);
        fhome.setCommandListener(this);
        return fhome;
    }
    static List getFormHome()
    {
        FormHome fh = new FormHome();
        return fh.makeFormHome();
    }
    
    public void returnMenu(List cg,char menu[])
    {
        String name="";
        int i;
        for(i=0;i<menu.length;i++)
        {

                    if(menu[i]!=',')
                    {
                        name=name+menu[i];
                    }
                    else
                    {
                        cg.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    public void commandAction(Command c, Displayable d)
    {
        if(c==List.SELECT_COMMAND)
            {
                if(fhome.getString(fhome.getSelectedIndex()).equals("Category Sales Analysis"))
                {
                    formgroupsalesanalysis=FormCategorySalesAnalysisMenu.getFormMenu();                         // Form Item Menu Getter
                    MobileMidlet.switchDisplay(formgroupsalesanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Product Sales Analysis"))
                {
                    formproductsalesanalysis=FormProductSalesAnalysisMenu.getFormMenu();                         // Form Item Menu Getter
                    MobileMidlet.switchDisplay(formproductsalesanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Home Delivery Calls"))
                {
                    formhomedeliveryanalysis=FormHomeDeliveryAnalysisTimePeriod.getFormHomeDeliveryAnalysisTimePeriod();
                    MobileMidlet.switchDisplay(formhomedeliveryanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Stock Quantity Analysis"))
                {
                    formstockquantityanalysis=FormStockQuantityAnalysisMenu.getFormMenu();
                    MobileMidlet.switchDisplay(formstockquantityanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Zero Sales Analysis"))
                {
                    formstockquantityanalysis=FormZeroSalesAnalysisMenu.getFormZeroSalesAnalysisMenu();
                    MobileMidlet.switchDisplay(formstockquantityanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Revenue Generated"))
                {
                    formrevenuegenerated=FormRevenueGeneratedTimePeriod.getFormRevenueGeneratedTimePeriod();
                    MobileMidlet.switchDisplay(formrevenuegenerated);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }

                else if(fhome.getString(fhome.getSelectedIndex()).equals("Expiry Date Analysis"))
                {
                    formexpirydateanalysis=FormExpiryDateAnalysisMenu.getFormMenu();
                    MobileMidlet.switchDisplay(formexpirydateanalysis);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Help"))
                {
//                    formmenu=FormMenu.getFormMenu();                         // Form Item Menu Getter
//                    MobileMidlet.switchDisplay(formmenu);
                    System.out.println("Choosed : "+fhome.getString(fhome.getSelectedIndex()));
                }
                else if(fhome.getString(fhome.getSelectedIndex()).equals("Exit"))
                {
                    System.out.println("Choosed : Exit");
//                    destroyApp(true);
//                    notifyDestroyed();
//                    switchDisplay(null);
                }
            }
    }
}
