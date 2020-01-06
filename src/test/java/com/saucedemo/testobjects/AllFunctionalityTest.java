
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
 * SauceDemoShoppingProjectTest
 */
public class AllFunctionalityTest extends BaseTestObject {

	LoginPageObject	loginPageObject = null;
	InventoryPageObject inventoryPageObj = null;
	ShoppingCartPageObject shopCartPageObject = null;
	CheckOutPageObject chkOutPgObject = null;
	CheckOutFinalPageObject checkFinalObject =null;
	CheckOutCompletePageObject checkOutCompleteObj =null;

	
	
	@BeforeMethod
	public void beforeMethod() {
	
	}

	/**
	 * Load properties and initialize the driver in the BaseTestObject
	 */
	@BeforeSuite
	public void beforeSuite() {
		BaseTestObject.getInstance();
		loginPageObject = new LoginPageObject();
	}


	//Test all methods of Login page object 
	@Test(priority = 1)
	public void validateLogInPageTitle() {
		String pageTitle = loginPageObject.getLogInPageTitle();
		Assert.assertEquals(pageTitle, "Swag Labs");
	}
	
	@Test(priority=2)
	public void validateLockedUser() {
		String errMsg = loginPageObject.getLockedUserMessage(props.getProperty("locked_userid"), props.getProperty("password"));
		Assert.assertTrue(errMsg.contains(" user has been locked out"));
	}
	
	/**
	 * Validates the Problem User id
	 */
	@Test(priority = 3)
	public void validateProblemUser() {
		CommonUtils.refreshPage(driver);
		InventoryPageObject inventoryPageObj = loginPageObject.login(props.getProperty("problem_userid"),props.getProperty("password"));
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
		inventoryPageObj = loginPageObject.login(props.getProperty("standard_userid"),props.getProperty("password"));
		boolean isImageDisp = inventoryPageObj.checkImageDisplayed();
		Assert.assertTrue(isImageDisp);
	}
	
	//Test InventoryPageObject
	@Test(priority = 5)
	public void validateProduct() {
		boolean hasProducts = inventoryPageObj.containsProducts(props.getProperty("buyproducttype"));  
		Assert.assertTrue(hasProducts);
	}
	
	@Test(priority = 6)
	public void validateAddToCart() {
		int productCountShCart = inventoryPageObj.getAllProductsCount(props.getProperty("buyproducttype")); 
		Assert.assertTrue(productCountShCart>0);
	}
	
	@Test(priority = 7)
	public void validateAddToCartAndCheckout() {
		shopCartPageObject = inventoryPageObj.getAllProductsAndCheckOut(props.getProperty("buyproducttype")); 
		Assert.assertTrue(shopCartPageObject != null);
	}
	
	//Test ShoppingCartPageObject
	@Test(priority = 8)
	public void validateCartTitle() {
		String title = shopCartPageObject.getShoppingCartTitle();
		Assert.assertEquals("Your Cart", title);
	}
	
	
	@Test(priority = 9)
	public void checkCartList() {
		int cartSize= shopCartPageObject.getCartItems();
		Assert.assertTrue(cartSize>0);
		
	}
	
	@Test(priority = 10)
	public void checkOutShop() {
		chkOutPgObject = shopCartPageObject.chkOut();
		Assert.assertTrue(chkOutPgObject!=null);
	}
	
	//Test CheckOutPageObject
	@Test(priority = 11)
	public void isFnameEntered() {
		chkOutPgObject.enterCheckOutDetails();
		boolean isFname= chkOutPgObject.isFnameEntered();
		Assert.assertTrue(isFname);
	}
	
	/**
	 *  Checks whether the Lastname is entered or not after checkout
	 */
	@Test(priority = 12)
	public void isLnameEntered() {
		boolean isLname= chkOutPgObject.isLnameEntered();
		Assert.assertTrue(isLname);
	}
	
	/**
	 *  Checks whether the Zipcode is entered or not after checkout
	 */
	@Test(priority = 13)
	public void isZipCodeeEntered() {
		boolean isZipcode= chkOutPgObject.isZipCodeeEntered();
		Assert.assertTrue(isZipcode);
	}
	
	
	/**
	 *  Checks whether the Zipcode is valid or not after checkout
	 */
	@Test(priority = 14)
	public void isZipcodeValid() {
		boolean isValidZip= chkOutPgObject.isZipCodeValid();
		Assert.assertTrue(isValidZip);
		
	}
	
	/**
	 *  Checks whether the continue button is clicked successfully or not
	 */
	@Test(priority = 15)
	public void validateEnterCheckOutDetails() {
		checkFinalObject = chkOutPgObject.chkoutContinue();
		Assert.assertTrue(checkFinalObject != null);
	}


	/*
	 * Validates the pay Info label
	 */
	@Test(priority = 16)
	public void isPayInfoLabelPresent() {
		Assert.assertTrue(checkFinalObject.isPayInfoLabelPresent());
	}
	
	/*
	 * Validates the pay Info Value
	 */
	@Test(priority = 17)
	public void isPayInfoValuePresent() {
		Assert.assertTrue(checkFinalObject.isPayInfoValuePresent());
	}
	
	/*
	 * Validates the Ship Info label
	 */
	@Test(priority = 18)
	public void isShipInformaitonLablePresent() {
		Assert.assertTrue(checkFinalObject.isShipInformaitonLablePresent());
	}
	
	/*
	 * Validates the Ship Info value
	 */
	@Test(priority = 19)
	public void isShipInformaitonValuePresent() {
		Assert.assertTrue(checkFinalObject.isShipInformaitonValuePresent());
	}
	
	/*
	 * Validates the Item Total
	 */
	@Test(priority = 20)
	public void isItemTotalPresent() {
		Assert.assertTrue(checkFinalObject.isItemTotalPresent());
	}
	
	/*
	 * Validates the Tax
	 */
	@Test(priority = 21)
	public void isTaxPresent() {
		Assert.assertTrue(checkFinalObject.isTaxPresent());
	}
	
	/*
	 * Validates the Total
	 */
	@Test(priority = 22)
	public void isTotalPresent() {
		Assert.assertTrue(checkFinalObject.isTotalPresent());
	}
	
	
	/*
	 * Validates PayLabel value 
	 */
	@Test(priority = 23)
	public void validatePayInfoLabel() {
		String payInfo = checkFinalObject.getPayInfoLabel();
		Assert.assertEquals(SauceDemoConstants.PAY_INFO_LABEL, payInfo);
	}
	
	
	/*
	 * Validates PayValue Pattern 
	 */
	@Test(priority = 24)
	public void validatePayValue() {
		Assert.assertTrue(checkFinalObject.checkPayInfoValuePattern());
	}
	
	/*
	 * Validates ShipLabel value 
	 */
	@Test(priority = 25)
	public void validateShipInfoLabel() {
		String shipInfo = checkFinalObject.getShipInformaitonLable();
		Assert.assertEquals(SauceDemoConstants.SHIP_INFORMAITON_LABLE, shipInfo);
	}
	
	
	/*
	 * Validates PayValue value 
	 */
	@Test(priority = 26)
	public void validateShipInfoValue() {
		String shipInfoValue = checkFinalObject.getShipInformaitonValue();
		Assert.assertEquals(SauceDemoConstants.SHIP_INFORMATION_VALUE, shipInfoValue);
	}
	
	
	/*
	 * Validates Item Total Pattern 
	 */
	@Test(priority = 27)
	public void validateItemTotalPattern() {
		Assert.assertTrue(checkFinalObject.checkItemTotalPattern());
	}
	
	
	/*
	 * Validates Tax Pattern 
	 */
	@Test(priority = 28)
	public void validateTaxPattern() {
		Assert.assertTrue(checkFinalObject.checkTaxPattern());
	}
	
	/*
	 * Validates Total Pattern 
	 */
	@Test(priority = 29)
	public void validateTotalPattern() {
		Assert.assertTrue(checkFinalObject.checkTotalPattern());
	}
	
	
	/*
	 * Validates the Finish button
	 */
	@Test(priority = 30)
	public void clickFinish() {
		checkOutCompleteObj = checkFinalObject.clickFinish();
		Assert.assertTrue(checkOutCompleteObj != null);
	}
	
	@Test(priority = 31)
	public void validateCheckOutCompletePageTitle() {
			String completePageTitle = checkOutCompleteObj.getCheckOutCompletePageTitle(); 
			Assert.assertEquals(SauceDemoConstants.COMPLETE_PAGE_TITLE, completePageTitle);
	}
	
	
	/**
	 * Validates the Thank you on Checkout Complete page
	 */
	@Test(priority = 32)
	public void validateThankYouText() {
		String thankyou=checkOutCompleteObj.getThankYouText();
		Assert.assertEquals(SauceDemoConstants.THANK_NOTE, thankyou);
	}

	
	/*
	 * Close all the windows after running the suite
	 */
	@AfterSuite
	public void afterSuite() {
		//driver.quit();
	}


}



