package com.saucedemo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;

/*
 * CheckOutCompletePageObject
 */
public class CheckOutCompletePageObject extends BaseTestObject {
	
	@FindBy(how = How.CSS, using = "#checkout_complete_container>h2")
	static WebElement checkOutPageTitle;

	@FindBy(how = How.CSS, using = "#checkout_complete_container > div.complete-text")
	static WebElement thankYouText;

	
	/*
	 * Constructor for CheckOutCompletePageObject
	 */
	public CheckOutCompletePageObject() {
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Returns the Checkout Page Title
	 */
	public String getCheckOutCompletePageTitle() {
		return checkOutPageTitle.getText();
	}

	
	/*
	 * Returns the ThanksText
	 */
	public String getThankYouText() {
		return thankYouText.getText();
	}
}

