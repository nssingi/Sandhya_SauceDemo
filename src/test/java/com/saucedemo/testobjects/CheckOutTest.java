package com.saucedemo.testobjects;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.saucedemo.pageobjects.CheckOutFinalPageObject;
import com.saucedemo.pageobjects.CheckOutPageObject;
import com.saucedemo.pageobjects.InventoryPageObject;
import com.saucedemo.pageobjects.LoginPageObject;
import com.saucedemo.pageobjects.ShoppingCartPageObject;
import com.saucedemo.util.BaseTestObject;

/*
 * CheckOutTest
 */
public class CheckOutTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	CheckOutPageObject checkOutObj = null;
	
	
	@BeforeMethod
	public void beforeMethod() {
		
	}

	/**
	 * Load properties and initialize the driver in the BaseTestObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		BaseTestObject.getInstance();
		loginPgObject = new LoginPageObject();
	}

	/**
	 *  Checks whether the Firstname is entered or not after checkout
	 */
	@Test(priority = 1)
	public void isFnameEntered() {
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		ShoppingCartPageObject shoppingCart = inventoryPageObj.getAllProductCountsAndCheckOut(props.getProperty("buyproducttype"));
		System.out.println("ShoppingCartTest getAllProductsAndCheckOut is done");
		checkOutObj = shoppingCart.chkOut();
		checkOutObj.enterCheckOutDetails();
		boolean isFname= checkOutObj.isFnameEntered();
		Assert.assertTrue(isFname);

	}
	
	/**
	 *  Checks whether the Lastname is entered or not after checkout
	 */
	@Test(priority = 2)
	public void isLnameEntered() {
		boolean isLname= checkOutObj.isLnameEntered();
		Assert.assertTrue(isLname);
		
	}
	
	/**
	 *  Checks whether the Zipcode is entered or not after checkout
	 */
	@Test(priority = 3)
	public void isZipCodeeEntered() {
		boolean isZipcode= checkOutObj.isZipCodeeEntered();
		Assert.assertTrue(isZipcode);
		
	}
	
	
	/**
	 *  Checks whether the Zipcode is valid or not after checkout
	 */
	@Test(priority = 4)
	public void isZipcodeValid() {
		boolean isValidZip= checkOutObj.isZipCodeValid();
		Assert.assertTrue(isValidZip);
		
	}
	
	/**
	 *  Checks whether the continue button is clicked successfully or not
	 */
	@Test(priority = 5)
	public void validateEnterCheckOutDetails() {
		CheckOutFinalPageObject finalCheckoutObject = checkOutObj.chkoutContinue();
		Assert.assertTrue(finalCheckoutObject != null);
	}
	
	
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}


}

