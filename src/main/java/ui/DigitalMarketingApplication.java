/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import model.Business.Business;
import model.Business.ConfigureABusiness;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Vivi's Bookhouse", 
    35, 10000, 50000, 10);
    business.printBusinessInfo();
    business.printMarketProfitabilityReport("Profit");
    business.printChannelProfitabilityReport("Cost");
    business.printAdvertisingEfficiencyReport();
  }
}
