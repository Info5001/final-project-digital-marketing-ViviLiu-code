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

public class ChannelProfitabilityReport {
    ArrayList<ChannelProfitability> channelProfitabilityList;

    ChannelProfitabilityComparator comparator;

    public ChannelProfitabilityReport(String sortingRule) {
        channelProfitabilityList = new ArrayList<ChannelProfitability>();
        comparator = new ChannelProfitabilityComparator(sortingRule);
    }

    public void addChannelProfitability(ChannelProfitability cp) {
        channelProfitabilityList.add(cp);
        Collections.sort(channelProfitabilityList, comparator);
    }

    public void printChannelProfitabilityReport() {
        System.out.printf("%-30s%-40s%-40s%-40s%-15s\n", 
        "Channel Profitability Report","Revenues by Channels","Costs by Channels","Profits by Channels","Total Profits");
        System.out.printf("%-30s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", 
        "Product", "Youtube","TikTok","Goodreads","Twitter", "Youtube","TikTok","Goodreads","Twitter", "Youtube","TikTok","Goodreads","Twitter");
        for (ChannelProfitability cp : channelProfitabilityList) {
            System.out.println(cp.printChannelProfitability());
        }
    }
}
