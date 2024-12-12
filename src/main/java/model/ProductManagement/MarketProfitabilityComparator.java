package model.ProductManagement;

import java.util.Comparator;

public class MarketProfitabilityComparator implements Comparator<MarketProfitability> {

    String sortingRule;

    public MarketProfitabilityComparator(String sortingRule) {
        this.sortingRule = sortingRule;
    }

    @Override
    public int compare(MarketProfitability o1, MarketProfitability o2) {
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
