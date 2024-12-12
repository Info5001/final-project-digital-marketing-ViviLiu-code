/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kal bugrara
 */
public class MarketProfitabilityReport {
    ArrayList<MarketProfitability> marketProfitabilityList;

    MarketProfitabilityComparator comparator;

    public MarketProfitabilityReport(String sortingRule) {
        marketProfitabilityList = new ArrayList<MarketProfitability>();
        comparator = new MarketProfitabilityComparator(sortingRule);
    }

    public void addMarketProfitability(MarketProfitability mp) {
        marketProfitabilityList.add(mp);
        Collections.sort(marketProfitabilityList, comparator);
    }

    public void printMarketProfitabilityReport() {
        System.out.printf("%-30s%-30s%-30s%-30s%-15s\n", 
        "Market Profitability Report","Revenues by Markets","Costs by Markets","Profits by Markets","Total Profits");
        System.out.printf("%-30s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", 
        "Product", "Kids","Teens","Adults", "Kids","Teens","Adults", "Kids","Teens","Adults");
        for (MarketProfitability mp : marketProfitabilityList) {
            System.out.println(mp.printMarketProfitability());
        }
    }

}
