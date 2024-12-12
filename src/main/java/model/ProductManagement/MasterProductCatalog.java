/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;


/**
 *
 * @author kal bugrara
 */
public class MasterProductCatalog {

    Business business;
    ArrayList<Product> products; // list of products initially empty

    public MasterProductCatalog(Business b) {
        business = b;
        products = new ArrayList<Product>(); /// create the list of elements otherwise it is null
    }

    public Product newProduct(int fp, int cp, int tp) {
        Product p = new Product(fp, cp, tp);
        products.add(p);
        return p;
    }

    public Product newProduct(String n, int fp, int cp, int tp) {
        Product p = new Product(n, fp, cp, tp);
        products.add(p);
        return p;
    }

    public ProductsReport generateProductPerformanceReport(String sortingRule) {
        ProductsReport productsReport = new ProductsReport(sortingRule);

        for (Product p : products) {
            ProductSummary ps = new ProductSummary(p);
            productsReport.addProductSummary(ps);
        }
        return productsReport;
    }

    public ArrayList<Product> getProductList() {
        return products;
    }

    public Product pickRandomProduct() {
        if (products.size() == 0)
            return null;
        Random r = new Random();
        int randomIndex = r.nextInt(products.size());
        return products.get(randomIndex);
    }

    public void printShortInfo() {
        System.out.println("There are " + products.size() + " products in the business catalog");
    }

    public void printProductList() {
        for (Product p : products) {
            System.out.println(p.getName());
        }
    }

    public MarketProfitabilityReport generateMarketProfitabilityReport(String sortingRule) {
        MarketProfitabilityReport marketProfitabilityReport = new MarketProfitabilityReport(sortingRule);
        for (Product p : products) {
            MarketProfitability mp = new MarketProfitability(p);
            marketProfitabilityReport.addMarketProfitability(mp);
        }
        return marketProfitabilityReport;
    }

    public ChannelProfitabilityReport generateChannelProfitabilityReport(String sortingRule) {
        ChannelProfitabilityReport channelProfitabilityReport = new ChannelProfitabilityReport(sortingRule);
        for (Product p : products) {
            ChannelProfitability cp = new ChannelProfitability(p);
            channelProfitabilityReport.addChannelProfitability(cp);
        }
        return channelProfitabilityReport;
    }


}
