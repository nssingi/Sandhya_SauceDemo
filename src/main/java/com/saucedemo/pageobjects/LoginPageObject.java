/* Author Sandhya Singireddy
 * /*
 * LoginPageObject: Identify login page object by @Findby Annotations
 * Methods to login with different user types and returns the next page object for stndard_user
 */


package com.saucedemo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;

/*
 * LoginPageObject
 */
public class LoginPageObject extends BaseTestObject {
	@FindBy(how = How.CSS, using = "input#user-name")
	static WebElement txtbx_UserName;

	@FindBy(how = How.CSS, using = "input#password")
	static WebElement txtbx_Passwd;

	@FindBy(how = How.CSS, using = "input.btn_action")
	static WebElement btn_submit;

	@FindBy(how = How.CSS, using = "#login_button_container h3")
	static WebElement txt_error;

	/*
	 * Constructor for LoginPageObject
	 */
	public LoginPageObject() {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Validates the Login page Title
	 */
	public String getLogInPageTitle() {
		return driver.getTitle();
	}
	

	/**
	 * Returns the InventoryPageObject after successful login for a given username and password
	 */
	public InventoryPageObject login(String arguname, String argpasswd) {
		txtbx_UserName.sendKeys(arguname);
		txtbx_Passwd.sendKeys(argpasswd);
		btn_submit.click();
		return (new InventoryPageObject());

	}

	/**
	 * Returns the Locked User message for given username and password
	 */
	public String getLockedUserMessage(String arguname, String argpasswd) {
		PageFactory.initElements(driver, this);
		txtbx_UserName.sendKeys(arguname);
		txtbx_Passwd.sendKeys(argpasswd);
		btn_submit.click();
		String errormsg = txt_error.getText();
		System.out.println("error id " + errormsg);
		return (errormsg);

	}

}
