package com.saucedemo.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;

/*
 * InventoryPageObject
 */
public class InventoryPageObject extends BaseTestObject {

	@FindBy(how = How.TAG_NAME, using = "img")
	static List<WebElement> listimages;

	@FindBy(how = How.CSS, using = "div.inventory_item_name")
	static List<WebElement> listlabels;

	@FindBy(how = How.CSS, using = "button.btn_primary.btn_inventory")
	static List<WebElement> addcarts;

	@FindBy(how = How.CSS, using = "#shopping_cart_container>a.shopping_cart_link.fa-layers.fa-fw")
	static WebElement shpopingcatlink;

	@FindBy(how = How.CSS, using = "#shopping_cart_container>a.shopping_cart_link>span.fa-layers-counter.shopping_cart_badge")
	static WebElement shpopingcartcount;

	/*
	 * Constructor for InventoryPageObject
	 */
	public InventoryPageObject() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Returns boolean true if any of messages are displayed. Returns boolean false
	 * when all the message are not displayed.
	 */
	public boolean checkImageDisplayed() {

		boolean isImageDisplayed = false;
		for (WebElement image : listimages) {
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					image);
			if (!ImagePresent) {
				System.out.println("Image not displayed.");
			} else {
				System.out.println("Image displayed.");
				isImageDisplayed = true;
				return isImageDisplayed;
			}
		}
		return isImageDisplayed;
	}
	

	/**
	 * Returns boolean true if producttype is present is the list of Inventory
	 *         boolean false if producttype is NOT present is the list of Inventory
	 * 
	 */
	public boolean containsProducts(String productType) {
		boolean containsProdcts = false;
		for (int i = 0; i < listlabels.size(); i++) {
			WebElement label = listlabels.get(i);
			System.out.println(label.getText());
			if (label.getText().contains(productType)) {
				containsProdcts = true;
				break;
			}
		}

		return containsProdcts;
	}

	/**
	 * Returns int the number of products added to shopping cart for a given product type
	 * 
	 */
	public int getAllProductsCount(String productType) {
		
		getProds(productType);
		String shCartCount = shpopingcartcount.getText();
		return Integer.parseInt(shCartCount);
	}
	
	
	/**
	 * Returns ShoppingCartPageObject after adding all products to shopping cart for a given product type
	 * 
	 */
	public ShoppingCartPageObject getAllProductsAndCheckOut(String productType) {
		//getProds(productType);
		shpopingcatlink.click();
		return (new ShoppingCartPageObject());
	}
	
	
	/**
	 * Returns ShoppingCartPageObject after adding all products to shopping cart for a given product type
	 * 
	 */
	public ShoppingCartPageObject getAllProductCountsAndCheckOut(String productType) {
		getProds(productType);
		shpopingcatlink.click();
		return (new ShoppingCartPageObject());
	}
	
	
	/**
	 * Returns add all products to shopping cart for a given product type and click to add to shopping cart
	 * 
	 */
	private void getProds(String productType) {
		List<WebElement> addCartList = new ArrayList<WebElement>();
		for (int i = 0; i < addcarts.size(); i++) {
			addCartList.add(addcarts.get(i));
		}

		for (int i = 0; i < listlabels.size(); i++) {
			WebElement label = listlabels.get(i);
			System.out.println(label.getText());
			if (label.getText().contains(productType)) {
				addCartList.get(i).click();
			}
		}
	}
	
	
}
