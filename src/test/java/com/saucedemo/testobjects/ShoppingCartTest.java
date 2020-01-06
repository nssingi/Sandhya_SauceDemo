package com.saucedemo.testobjects;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.saucedemo.pageobjects.CheckOutPageObject;
import com.saucedemo.pageobjects.InventoryPageObject;
import com.saucedemo.pageobjects.LoginPageObject;
import com.saucedemo.pageobjects.ShoppingCartPageObject;
import com.saucedemo.util.BaseTestObject;

/*
 * ShoppingCartTest
 */
public class ShoppingCartTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	ShoppingCartPageObject shoppingCart = null;
	
	public ShoppingCartTest() {
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
	 * Load properties and initialize the driver in the BaseTestObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		initialize();
	}


	/**
	 * Validates the Cart Title
	 */
	@Test(priority = 1)
	public void validateCartTitle() {
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		shoppingCart = inventoryPageObj.getAllProductCountsAndCheckOut(props.getProperty("buyproducttype"));
		System.out.println("ShoppingCartTest getAllProductsAndCheckOut is done");
		String title = shoppingCart.getShoppingCartTitle();
		Assert.assertEquals("Your Cart", title);
	}
	
	/**
	 * Validates the number of objects in the cart
	 */
	@Test(priority = 2)
	public void checkCartList() {
		int cartSize= shoppingCart.getCartItems();
		Assert.assertTrue(cartSize>0);
		
	}
	
	/**
	 * Validates the checkout button
	 */
	@Test(priority = 3)
	public void checkOutShop() {
		CheckOutPageObject chkOutPgObject= shoppingCart.chkOut();
		Assert.assertTrue(chkOutPgObject!=null);
	}
		
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}


}
