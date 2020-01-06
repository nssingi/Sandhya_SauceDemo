package com.saucedemo.testobjects;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.saucedemo.pageobjects.InventoryPageObject;
import com.saucedemo.pageobjects.LoginPageObject;
import com.saucedemo.pageobjects.ShoppingCartPageObject;
import com.saucedemo.util.BaseTestObject;
import com.saucedemo.util.CommonUtils;

/*
 * InventoryTest
 */
public class InventoryTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	InventoryPageObject inventoryPageObj = null;
	ShoppingCartPageObject shoppingCart = null;
	int productCountShCart = 0;
	
	public InventoryTest() {
		initialize();
	}
	
	public void initialize() {
		BaseTestObject.getInstance();
		loginPgObject = new LoginPageObject();
	}
	
	@BeforeMethod
	public void beforeMethod() {
	}

	/**
	 * Load properties and initialize the driver in the BasePageObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inventory page before suite started ");
		 initialize();
		System.out.println("inventory page before suite started "+ loginPgObject);
	}


	/**
	 * Check whether Product exist for a given producttype
	 */
	@Test(priority = 1)
	public void validateProduct() {
		
		System.out.println("inventory page validateProduct ");
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		boolean hasProducts = inventoryPageObj.containsProducts(props.getProperty("buyproducttype"));  
		Assert.assertTrue(hasProducts);
	}
	
	/**
	 * Check whether Product exist for a given producttype and then add to the cart and check the product count in shopping cart.
	 */
	@Test(priority = 2)
	public void validateAddToCart() {
		CommonUtils.refreshPage(driver);
		driver.get(props.getProperty("url"));
		inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		boolean hasProducts = inventoryPageObj.containsProducts(props.getProperty("buyproducttype"));  
		if(hasProducts) {
			productCountShCart = inventoryPageObj.getAllProductsCount(props.getProperty("buyproducttype")); 
			Assert.assertTrue(productCountShCart>0);
		} else {
			Assert.assertTrue(hasProducts);
		}
	}
	
	/**
	 * Check whether Product exist for a given producttype and then add to the cart and check the product count in shopping cart.
	 */
	@Test(priority = 3)
	public void validateAddToCartAndCheckout() {
		if(productCountShCart >0) {
			ShoppingCartPageObject shopCartPageObject = inventoryPageObj.getAllProductsAndCheckOut(props.getProperty("buyproducttype")); 
			Assert.assertTrue(shopCartPageObject != null);
		} else {
			Assert.assertTrue(false);
		}
		
	}
	
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}


}
