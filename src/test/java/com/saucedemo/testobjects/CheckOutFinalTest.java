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
import com.saucedemo.util.CommonUtils;
import com.saucedemo.util.SauceDemoConstants;

/*
 * CheckOutFinalTest
 */
public class CheckOutFinalTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	CheckOutFinalPageObject checkFinalObject = null;
	
	
	/**
	 * Call the Refresh of page before each method
	 */
	@BeforeMethod
	public void beforeMethod() {
		
	}

	/**
	 * Load properties and initialize the driver in the BasePageObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		BaseTestObject.getInstance();
		loginPgObject = new LoginPageObject();
	}

	/*
	 * Validates enter details and checkout funcitonality 
	 */
	@Test(priority = 1)
	public void validateEnterCheckOutDetails() {
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		inventoryPageObj.getAllProductsCount(props.getProperty("buyproducttype"));
		ShoppingCartPageObject shoppingCart = inventoryPageObj.getAllProductsAndCheckOut(props.getProperty("buyproducttype"));
		System.out.println("ShoppingCartTest getAllProductsAndCheckOut is done");
		CheckOutPageObject checkOutObject = shoppingCart.chkOut();
		checkFinalObject = checkOutObject.enterCheckOutDetailsAndContinue();
		Assert.assertTrue(true);
	}
	
	
	/*
	 * Validates the pay Info label
	 */
	@Test(priority = 2)
	public void isPayInfoLabelPresent() {
		Assert.assertTrue(checkFinalObject.isPayInfoLabelPresent());
	}
	
	/*
	 * Validates the pay Info Value
	 */
	@Test(priority = 3)
	public void isPayInfoValuePresent() {
		Assert.assertTrue(checkFinalObject.isPayInfoValuePresent());
	}
	
	/*
	 * Validates the Ship Info label
	 */
	@Test(priority = 4)
	public void isShipInformaitonLablePresent() {
		Assert.assertTrue(checkFinalObject.isShipInformaitonLablePresent());
	}
	
	/*
	 * Validates the Ship Info value
	 */
	@Test(priority = 5)
	public void isShipInformaitonValuePresent() {
		Assert.assertTrue(checkFinalObject.isShipInformaitonValuePresent());
	}
	
	/*
	 * Validates the Item Total
	 */
	@Test(priority = 6)
	public void isItemTotalPresent() {
		Assert.assertTrue(checkFinalObject.isItemTotalPresent());
	}
	
	/*
	 * Validates the Tax
	 */
	@Test(priority = 7)
	public void isTaxPresent() {
		Assert.assertTrue(checkFinalObject.isTaxPresent());
	}
	
	/*
	 * Validates the Total
	 */
	@Test(priority = 8)
	public void isTotalPresent() {
		Assert.assertTrue(checkFinalObject.isTotalPresent());
	}
	
	
	/*
	 * Validates PayLabel value 
	 */
	@Test(priority = 9)
	public void validatePayInfoLabel() {
		String payInfo = checkFinalObject.getPayInfoLabel();
		Assert.assertEquals(SauceDemoConstants.PAY_INFO_LABEL, payInfo);
	}
	
	
	/*
	 * Validates PayValue Pattern 
	 */
	@Test(priority = 10)
	public void validatePayValue() {
		Assert.assertTrue(checkFinalObject.checkPayInfoValuePattern());
	}
	
	/*
	 * Validates ShipLabel value 
	 */
	@Test(priority = 11)
	public void validateShipInfoLabel() {
		String shipInfo = checkFinalObject.getShipInformaitonLable();
		Assert.assertEquals(SauceDemoConstants.SHIP_INFORMAITON_LABLE, shipInfo);
	}
	
	
	/*
	 * Validates PayValue value 
	 */
	@Test(priority = 12)
	public void validateShipInfoValue() {
		String shipInfoValue = checkFinalObject.getShipInformaitonValue();
		Assert.assertEquals(SauceDemoConstants.SHIP_INFORMATION_VALUE, shipInfoValue);
	}
	
	
	/*
	 * Validates Item Total Pattern 
	 */
	@Test(priority = 13)
	public void validateItemTotalPattern() {
		Assert.assertTrue(checkFinalObject.checkItemTotalPattern());
	}
	
	
	/*
	 * Validates Tax Pattern 
	 */
	@Test(priority = 14)
	public void validateTaxPattern() {
		Assert.assertTrue(checkFinalObject.checkTaxPattern());
	}
	
	/*
	 * Validates Total Pattern 
	 */
	@Test(priority = 15)
	public void validateTotalPattern() {
		Assert.assertTrue(checkFinalObject.checkTotalPattern());
	}
	
	
	
	
	
	/*
	 * Validates the Finish button
	 */
	//@Test(priority = 16)
	public void clickFinish() {
		CheckOutCompletePageObject checkOutCompleteObj = checkFinalObject.clickFinish();
		Assert.assertTrue(checkOutCompleteObj != null);
	}
	
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}


}

