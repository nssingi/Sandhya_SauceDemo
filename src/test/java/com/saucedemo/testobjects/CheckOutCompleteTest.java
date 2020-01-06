package com.saucedemo.testobjects;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.saucedemo.pageobjects.CheckOutCompletePageObject;
import com.saucedemo.pageobjects.CheckOutFinalPageObject;
import com.saucedemo.pageobjects.CheckOutPageObject;
import com.saucedemo.pageobjects.InventoryPageObject;
import com.saucedemo.pageobjects.LoginPageObject;
import com.saucedemo.pageobjects.ShoppingCartPageObject;
import com.saucedemo.util.BaseTestObject;
import com.saucedemo.util.SauceDemoConstants;

/*
 * CheckOutCompleteTest
 */
public class CheckOutCompleteTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	CheckOutCompletePageObject checkOutComleteObject = null;
	
	
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
	 * Validates the Checkout Complete page Title
	 */
	@Test(priority = 1)
	public void validateCheckOutCompletePageTitle() {
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		ShoppingCartPageObject shoppingCart = inventoryPageObj.getAllProductCountsAndCheckOut(props.getProperty("buyproducttype"));
		CheckOutPageObject checkOutObject = shoppingCart.chkOut();
		CheckOutFinalPageObject checkFinalObject = checkOutObject.enterCheckOutDetailsAndContinue();
		checkOutComleteObject = checkFinalObject.clickFinish();
		String completePageTitle = checkOutComleteObject.getCheckOutCompletePageTitle(); 
		
		Assert.assertEquals(SauceDemoConstants.COMPLETE_PAGE_TITLE, completePageTitle);
	}
	
	
	/**
	 * Validates the Thank you on Checkout Complete page
	 */
	@Test(priority = 2)
	public void validateThankYouText() {
		String thankyou=checkOutComleteObject.getThankYouText();
		Assert.assertEquals(SauceDemoConstants.THANK_NOTE, thankyou);
	}

	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}


}

