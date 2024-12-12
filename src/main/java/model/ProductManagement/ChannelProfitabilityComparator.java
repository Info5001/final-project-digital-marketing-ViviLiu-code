package model.ProductManagement;

import java.util.Comparator;

public class ChannelProfitabilityComparator implements Comparator<ChannelProfitability> {

    String sortingRule;

    public ChannelProfitabilityComparator(String sortingRule) {
        this.sortingRule = sortingRule;
    }

    @Override
    public int compare(ChannelProfitability o1, ChannelProfitability o2) {
        if (sortingRule.equals("Name")) {
            return o1.getSubjectProduct().getName().compareTo(o2.getSubjectProduct().getName());
        }

        if (sortingRule.equals("Profit")) {
            return (-1) * Float.compare(o1.getTotalProfit(), o2.getTotalProfit());
        }

        if (sortingRule.equals("Cost")) {
            return Float.compare(o1.getTotalCost(), o2.getTotalCost());
        }

        return (-1) * Integer.compare(o1.getTotalRevenue(), o2.getTotalRevenue());
    }

}
