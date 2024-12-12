/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.HashMap;

import model.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */

public class ChannelProfitability {
    int rank; // will be done later
    Product subjectProduct;

    HashMap<Channel, Integer> revenueByChannel;
    HashMap<Channel, Integer> costByChannel;
    HashMap<Channel, Integer> profitByChannel;
    int totalProfit;
    int totalRevenue;
    int totalCost;

    public ChannelProfitability(Product p) {

        rank = 0;
        subjectProduct = p;
        revenueByChannel = p.getSalesVolumeListForChannel();
        costByChannel = p.getAdBudgetListForChannel();

        profitByChannel = new HashMap<Channel, Integer>();
        totalProfit = 0;
        totalRevenue = 0;
        totalCost = 0;
        for (Channel c : revenueByChannel.keySet()) {
            int profit = revenueByChannel.get(c) - costByChannel.get(c);
            profitByChannel.put(c, profit);
            totalProfit += profit;
            totalRevenue += revenueByChannel.get(c);
            totalCost += revenueByChannel.get(c);
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

    public String printChannelProfitability() {
        String output = String.format("%-30s", subjectProduct.getName());
        for (Channel c : revenueByChannel.keySet()) {
            output += String.format("%-10d", revenueByChannel.get(c));
        }
        for (Channel c : costByChannel.keySet()) {
            output += String.format("%-10d", costByChannel.get(c));
        }
        for (Channel c : profitByChannel.keySet()) {
            output += String.format("%-10d", profitByChannel.get(c));
        }
        output += String.format("%-10d", totalProfit);
        return output;
    }

}
