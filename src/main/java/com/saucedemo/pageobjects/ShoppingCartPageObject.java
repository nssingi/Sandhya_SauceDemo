package com.saucedemo.pageobjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;


/*
 * ShoppingCartPageObject
 */
public class ShoppingCartPageObject extends BaseTestObject {
	
	@FindBy(how = How.CSS, using = "#cart_contents_container>div>div.cart_footer>a.btn_action.checkout_button")
	static WebElement chkout;
	@FindBy(how = How.CSS, using = "#contents_wrapper>div.subheader")
	static WebElement cartHeader;
	@FindBy(how = How.CSS, using = "#cart_contents_container > div > div.cart_list  div.cart_item")
	static List<WebElement> cartItemList;
	
	
	/*
	 * Constructor for ShoppingCartPageObject
	 */
	public ShoppingCartPageObject() {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Validates the Login page Title
	 */
	public String getShoppingCartTitle() {
		System.out.println("cartHeader.getText() is: " + cartHeader.getText());
		
		return cartHeader.getText();
	}
	

	/**
	 * Returns the InventoryPageObject after successful login for a given username and password
	 */
	public CheckOutPageObject chkOut() {
		chkout.click();
		
		return new CheckOutPageObject();
	}
	
	/**
	 * Returns Cart Item count
	 */
	public int getCartItems() {
		System.out.println("cart list is :"+ cartItemList.size());
		return cartItemList.size();
	}
	

	
}