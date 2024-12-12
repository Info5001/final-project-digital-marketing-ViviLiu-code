/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;

import model.CustomerManagement.CustomerDirectory;
import model.MarketModel.ChannelCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketCatalog;
import model.MarketModel.MarketChannelAssignment;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.MasterProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    MasterProductCatalog masterproductcatalog;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;

    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        masterproductcatalog = new MasterProductCatalog(this);
        solutionoffercatalog = new SolutionOfferCatalog(this);
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);

        marketcatalog = new MarketCatalog(this);
        channelcatalog = new ChannelCatalog(this);

    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public SolutionOfferCatalog getSolutionoffercatalog() {
        return solutionoffercatalog;
    }

    public MasterProductCatalog getMasterproductcatalog() {
        return masterproductcatalog;
    }

    public MarketCatalog getMarketcatalog() {
        return marketcatalog;
    }

    public ChannelCatalog getChannelcatalog() {
        return channelcatalog;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();
    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();
    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); // return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public MasterProductCatalog getMasterProductCatalog() {
        return masterproductcatalog;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the business hierarchy.");
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();
    }

    public void printBusinessInfo() {
        masterproductcatalog.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();
        System.out.println("There are " + marketcatalog.getMarketList().size() + " markets in the business catalog");
        System.out.println("There are " + channelcatalog.getChannelList().size() + " channels in the business catalog");
        System.out.println("There are " + solutionoffercatalog.getSolutionOfferList().size() + " solution offers in the business catalog");
        System.out.println("Total sales volume is " + getSalesVolume());
    }

    // TODO : profitability reports
    public void printMarketProfitabilityReport(String sortingRule) {
        masterproductcatalog.generateMarketProfitabilityReport(sortingRule).printMarketProfitabilityReport();
    }
    public void printChannelProfitabilityReport(String sortingRule) {
        masterproductcatalog.generateChannelProfitabilityReport(sortingRule).printChannelProfitabilityReport();
    }
    public void printAdvertisingEfficiencyReport() {
        System.out.printf("%-25s%-10s%-10s%-10s%-10s\n", "Advertising Efficiency", "Youtube", "TikTok", "Goodreads", "Twitter");
        for (Market m : marketcatalog.getMarketList()) {
            String output = String.format("%-25s", m.getName());
            for (MarketChannelAssignment mca : m.getMCAList()) {
                Float efficiency = (float) mca.getSalesVolume() / mca.getAdvertisingBudget();
                output += String.format("%-10.2f", efficiency);
            }
            System.out.println(output);
        }
    }

}