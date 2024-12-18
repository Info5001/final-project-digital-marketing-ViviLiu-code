/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {
  ArrayList<SolutionOffer> so;
  ArrayList<MarketChannelAssignment> marketChannelCombs;
  ArrayList<String> characteristics;
  ArrayList<Market> submarkets;
  int size;

  public Market(String s) {
    characteristics = new ArrayList<String>();
    characteristics.add(s);
    marketChannelCombs = new ArrayList<MarketChannelAssignment>();
  }

  public MarketChannelAssignment getMarketChannelComb(Channel c) {
    
    for (MarketChannelAssignment mca : marketChannelCombs) {
      if (mca.getChannel().equals(c))
        return mca;
    }
    MarketChannelAssignment newMca = new MarketChannelAssignment(this, c);
    marketChannelCombs.add(newMca);
    return newMca;
  }

  public String getName() {
    return characteristics.get(0);
  }

  public MarketChannelAssignment pickRandomMCA() {
    if (marketChannelCombs.size() == 0)
      return null;
    int randomIndex = (int) (Math.random() * marketChannelCombs.size());
    return marketChannelCombs.get(randomIndex);
  }

  public ArrayList<MarketChannelAssignment> getMCAList() {
    return marketChannelCombs;
  }
  
}