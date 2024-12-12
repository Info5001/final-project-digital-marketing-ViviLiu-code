/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Business.Business;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {
    Business business;
    ArrayList<Market> markets;

    public MarketCatalog(Business b) {
        business = b;
        markets = new ArrayList<Market>();
    }

    public Market addMarket(String s) {
        Market newMarket = new Market(s);
        markets.add(newMarket);
        return newMarket;
    }

    public ArrayList<Market> getMarketList() {
        return markets;
    }

}