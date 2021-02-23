/* Author Sandhya Singireddy
 * /*
 * LoginTest: Login TestNG class for Loginpage obect to validate the methods from Login pageobject.
 * Seperate validation layer class so that the object changes are not impacted
 * check the locked user message and images for other users.
 */


package com.saucedemo.testobjects;

import org.testng.annotations.Test;

import com.saucedemo.pageobjects.InventoryPageObject;
import com.saucedemo.pageobjects.LoginPageObject;
import com.saucedemo.util.BaseTestObject;
import com.saucedemo.util.CommonUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

/*
 * LoginTest
 */
public class LoginTest extends BaseTestObject {

	LoginPageObject loginPgObject = null;
	
	
	public LoginTest() {
		initialize();
	}
	
	public void initialize() {
		BaseTestObject.getInstance();
		loginPgObject = new LoginPageObject();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("before method is called in git commet");
		
	}
	

	/**
	 * Load properties and initialize the driver in the BaseTestObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("login before suite started ");
		initialize();
	}

	/**
	 * Validates the Login page Title
	 */
	@Test(priority = 1)
	public void validaeLogInPageTitle() {
		String pageTitle = loginPgObject.getLogInPageTitle();
		Assert.assertEquals(pageTitle, "Swag Labs");
	}
	

	
	/**
	 * Validates the Locked User message
	 */
	@Test(priority=2)
	public void validateLockedUser() {
		String errMsg = loginPgObject.getLockedUserMessage(props.getProperty("locked_userid"), props.getProperty("password"));
		Assert.assertTrue(errMsg.contains(" user has been locked out"));
	}

	/**
	 * Validates the Problem User id
	 */
	@Test(priority = 3)
	public void validateProblemUser() {
		CommonUtils.refreshPage(driver);
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("problem_userid"),props.getProperty("password"));
		boolean isImageDisp = inventoryPageObj.checkImageDisplayed();
		Assert.assertFalse(isImageDisp);
	}
	
	/**
	 * Validates the Standard User id
	 */
	@Test(priority = 4)
	public void validatestandardUser() {
		CommonUtils.refreshPage(driver);
		driver.get(props.getProperty("url"));
		InventoryPageObject inventoryPageObj = loginPgObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		boolean isImageDisp = inventoryPageObj.checkImageDisplayed();
		Assert.assertTrue(isImageDisp);
	}
	
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
		System.out.println("Login tst driver quit");
	}

	
}
