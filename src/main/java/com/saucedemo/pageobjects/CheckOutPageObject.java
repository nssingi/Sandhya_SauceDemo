package com.saucedemo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;
import com.saucedemo.util.CommonUtils;
import com.saucedemo.util.SauceDemoConstants;

/*
 *  CheckOutPageObject
 */
public class CheckOutPageObject extends BaseTestObject {

	@FindBy(how = How.CSS, using = "input#first-name")
	static WebElement fname;

	@FindBy(how = How.CSS, using = "input#last-name")
	static WebElement lname;

	@FindBy(how = How.CSS, using = "input#postal-code")
	static WebElement zipcode;

	@FindBy(how = How.CSS, using = "input.btn_primary.cart_button")
	static WebElement continuebtn;

	
	/*
	 * Constructor for CheckOutPageObject
	 */
	public CheckOutPageObject() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Check Firstname is entered or not
	 */
	public boolean isFnameEntered() {
		String fnm = fname.getAttribute("value");
		System.out.println("Fname is:" + fnm + ":");
		if (fnm == null || fnm.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Check LastName is entered or not
	 */
	public boolean isLnameEntered() {
		String lnm = lname.getAttribute("value");
		System.out.println("Fname is:" + lnm + ":");
		if (lnm == null || lnm.trim().length() < 1) {
			return false;
		} else {
			return true;
		}

	}

	/*
	 * Check ZipCode is entered or not
	 */
	public boolean isZipCodeeEntered() {
		String zip = zipcode.getAttribute("value");
		System.out.println("Fname is:" + zip + ":");
		if (zip == null || zip.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Check ZipCode is valid or not
	 */
	public boolean isZipCodeValid() {
		String zip = zipcode.getAttribute("value");
		return CommonUtils.validatePattern(SauceDemoConstants.ZIPCODE_PATTERN, zip); 
	}


	/*
	 * Enter the user details
	 */
	public void enterCheckOutDetails() {
		fname.sendKeys(props.getProperty("fname"));
		lname.sendKeys(props.getProperty("lname"));
		zipcode.sendKeys(props.getProperty("zipcode"));
	}

	/*
	 * Click continue button
	 */
	public CheckOutFinalPageObject chkoutContinue() {
		continuebtn.click();
		return new CheckOutFinalPageObject();

	}

	/*
	 *  Enter checkout details and click continue
	 */
	public CheckOutFinalPageObject enterCheckOutDetailsAndContinue() {
		enterCheckOutDetails();
		return chkoutContinue();
	}

}
