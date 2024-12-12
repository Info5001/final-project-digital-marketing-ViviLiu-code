/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.Random;

import com.github.javafaker.Faker;

import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.ChannelCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketCatalog;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;


/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

  static int upperPriceLimit = 50;
  static int lowerPriceLimit = 10;
  static int range = 5;
  static int productMaxQuantity = 5;

  public static Business createABusinessAndLoadALotOfData(String name, int supplierCount, int productCount,
      int customerCount, int orderCount, int itemCount) {

    // Create a new Business: A Book Store
    Business business = new Business(name);

    // Set up Channels: Twitter, Goodreads, Youtube, Tiktok
    ChannelCatalog channelCatalog = business.getChannelcatalog();
    Channel twChannel = channelCatalog.addChannel("Twitter");
    Channel grChannel = channelCatalog.addChannel("Goodreads");
    Channel ytChannel = channelCatalog.addChannel("Youtube");
    Channel ttChannel = channelCatalog.addChannel("Tiktok");

    // Set up Markets: kids, teens, adults
    MarketCatalog marketCatalog = business.getMarketcatalog();
    Market kidsMarket = marketCatalog.addMarket("Kids");
    Market teensMarket = marketCatalog.addMarket("Teens");
    Market adultsMarket = marketCatalog.addMarket("Adults");

    // Set up Market Channels Assignments and Advertising Budgets
    MarketChannelAssignment kidsYoutube = kidsMarket.getMarketChannelComb(ytChannel);
    kidsYoutube.setAdvertisingBudget(1414001);
    MarketChannelAssignment kidsTiktok = kidsMarket.getMarketChannelComb(ttChannel);
    kidsTiktok.setAdvertisingBudget(1072266);
    MarketChannelAssignment kidsGoodreads = kidsMarket.getMarketChannelComb(grChannel);
    kidsGoodreads.setAdvertisingBudget(509258);
    MarketChannelAssignment kidsTwitter = teensMarket.getMarketChannelComb(twChannel);
    kidsTwitter.setAdvertisingBudget(688083);
    MarketChannelAssignment teensYoutube = teensMarket.getMarketChannelComb(ytChannel);
    teensYoutube.setAdvertisingBudget(1663397);
    MarketChannelAssignment teensTiktok = teensMarket.getMarketChannelComb(ttChannel);
    teensTiktok.setAdvertisingBudget(1040847);
    MarketChannelAssignment teensGoodreads = teensMarket.getMarketChannelComb(grChannel);
    teensGoodreads.setAdvertisingBudget(1805058);
    MarketChannelAssignment teensTwitter = teensMarket.getMarketChannelComb(twChannel);
    teensTwitter.setAdvertisingBudget(587244);
    MarketChannelAssignment adultsYoutube = adultsMarket.getMarketChannelComb(ytChannel);
    adultsYoutube.setAdvertisingBudget(1167187);
    MarketChannelAssignment adultsTiktok = adultsMarket.getMarketChannelComb(ttChannel);
    adultsTiktok.setAdvertisingBudget(626880);
    MarketChannelAssignment adultsGoodreads = adultsMarket.getMarketChannelComb(grChannel);
    adultsGoodreads.setAdvertisingBudget(821457);
    MarketChannelAssignment adultsTwitter = adultsMarket.getMarketChannelComb(twChannel);
    adultsTwitter.setAdvertisingBudget(707078);


    // Add Suppliers +
    loadSuppliers(business, supplierCount);

    // Add Products +
    loadProducts(business, productCount);

    // Add Bundles -----
    SolutionOfferCatalog soc = business.getSolutionoffercatalog();
    for (Supplier supplier : business.getSupplierDirectory().getSupplierList()) {
      for (Product p : supplier.getProductCatalog().getProductList()) {
        soc.newBundle(kidsYoutube, p.getTargetPrice(), p);
        soc.newBundle(kidsTiktok, p.getTargetPrice(), p);
        soc.newBundle(kidsGoodreads, p.getCeilingPrice(), p);
        soc.newBundle(kidsTwitter, p.getFloorPrice(), p);
        soc.newBundle(teensYoutube, p.getCeilingPrice(), p);
        soc.newBundle(teensTiktok, p.getTargetPrice(), p);
        soc.newBundle(teensGoodreads, p.getFloorPrice(), p);
        soc.newBundle(teensTwitter, p.getTargetPrice(), p);
        soc.newBundle(adultsYoutube, p.getTargetPrice(), p);
        soc.newBundle(adultsTiktok, p.getTargetPrice(), p);
        soc.newBundle(adultsGoodreads, p.getFloorPrice(), p);
        soc.newBundle(adultsTwitter, p.getTargetPrice(), p);
      }
    }

    // Add Customers
    loadCustomers(business, customerCount);

    // Add Order
    loadOrders(business, orderCount, itemCount);

    return business;
  }

  public static void loadSuppliers(Business b, int supplierCount) {
    Faker faker = new Faker();

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    for (int index = 1; index <= supplierCount; index++) {
      supplierDirectory.newSupplier(faker.company().name());
    }
  }

  static void loadProducts(Business b, int productCount) {
    Faker faker = new Faker();
    SupplierDirectory supplierDirectory = b.getSupplierDirectory();

    for (Supplier supplier : supplierDirectory.getSupplierList()) {

      ProductCatalog productCatalog = supplier.getProductCatalog();
      for (int index = 1; index <= productCount; index++) {
        int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
        int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
        int randomTarget = getRandom(randomFloor, randomCeiling);
        productCatalog.newProduct(faker.book().title(), randomFloor, randomCeiling, randomTarget);
      }
    }
  }


  static int getRandom(int lower, int upper) {
    Random r = new Random();

    // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
    // numbers from 10 to 15
    // I will have result = 10 + nextInt(5)
    int randomInt = lower + r.nextInt(upper - lower);
    return randomInt;
  }

  static void loadCustomers(Business b, int customerCount) {
    CustomerDirectory customerDirectory = b.getCustomerDirectory();
    PersonDirectory personDirectory = b.getPersonDirectory();

    Faker faker = new Faker();

    for (int index = 1; index <= customerCount; index++) {
      Person newPerson = personDirectory.newPerson(faker.name().fullName());
      customerDirectory.newCustomerProfile(newPerson);
    }
  }

  static void loadOrders(Business b, int orderCount, int itemCount) {

    // reach out to masterOrderList
    MasterOrderList mol = b.getMasterOrderList();

    // pick a random customer (reach to customer directory)
    CustomerDirectory cd = b.getCustomerDirectory();

    for (int index = 0; index < orderCount; index++) {

      CustomerProfile randomCustomer = cd.pickRandomCustomer();
      if (randomCustomer == null) {
        System.out.println("Cannot generate orders. No customers in the customer directory.");
        return;
      }

      // create an order for that customer
      Order randomOrder = mol.newOrder(randomCustomer);

      // add order items
      // -- pick a supplier first (randomly)
      // -- pick a product (randomly)
      // -- actual price, quantity

      int randomItemCount = getRandom(1, itemCount);
      for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

        SolutionOfferCatalog soc = b.getSolutionoffercatalog();
        SolutionOffer randomBundle = soc.pickRandomBundle();
        if (randomBundle == null) {
          System.out.println("Cannot generate orders. No products in the product catalog.");
          return;
        }

        int randomQuantity = getRandom(1, productMaxQuantity);

        randomOrder.newOrderItem(randomBundle, randomBundle.getTargetPrice(), randomQuantity);
      }
    }
    // Make sure order items are connected to the order

  }

}