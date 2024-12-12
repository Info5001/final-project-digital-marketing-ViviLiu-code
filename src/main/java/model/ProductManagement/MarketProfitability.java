/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.HashMap;

import model.MarketModel.Market;

/**
 *
 * @author kal bugrara
 */

public class MarketProfitability {
    int rank; // will be done later
    Product subjectProduct;

    HashMap<Market, Integer> revenueByMarket;
    HashMap<Market, Integer> costByMarket;
    HashMap<Market, Integer> profitByMarket;
    int totalProfit;
    int totalRevenue;
    int totalCost;

    public MarketProfitability(Product p) {

        rank = 0;
        subjectProduct = p;
        revenueByMarket = p.getSalesVolumeList();
        costByMarket = p.getAdBudgetList();

        profitByMarket = new HashMap<Market, Integer>();
        totalProfit = 0;
        totalRevenue = 0;
        totalCost = 0;
        for (Market m : revenueByMarket.keySet()) {
            int profit = revenueByMarket.get(m) - costByMarket.get(m);
            profitByMarket.put(m, profit);
            totalProfit += profit;
            totalRevenue += revenueByMarket.get(m);
            totalCost += costByMarket.get(m);
        }
    }

    public Product getSubjectProduct() {
        return subjectProduct;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String printMarketProfitability() {
        String output = String.format("%-30s", subjectProduct.getName());
        for (Market m : revenueByMarket.keySet()) {
            output += String.format("%-10d", revenueByMarket.get(m));
        }
        for (Market m : costByMarket.keySet()) {
            output += String.format("%-10d", costByMarket.get(m));
        }
        for (Market m : profitByMarket.keySet()) {
            output += String.format("%-10d", profitByMarket.get(m));
        }
        output += String.format("%-10d", totalProfit);
        return output;
    }

}
