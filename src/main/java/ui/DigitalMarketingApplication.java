/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;

import com.github.javafaker.Faker;
import com.github.javafaker.Book;


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
    4, 10, 30,30, 10);
    

  }
}
