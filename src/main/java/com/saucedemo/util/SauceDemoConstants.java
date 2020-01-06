package com.saucedemo.util;

/*
 * Constants for the project
 */
public final class SauceDemoConstants {
	
	public final static String ZIPCODE_PATTERN = "\\b[0-9]{5}(?:-[0-9]{4})?\\b";
	public final static String PAYVALUE_PATTERN = "([A-Z]{9}.#)([1-9]{5})";
	public final static String ITEM_TOTAL_PATTERN = "([A-Z]{4}.[A-Z]{5}:.\\$[1-9]+.[1-9]{2})";
	public final static String TAX_PATTERN   = "([A-Z]{3}:.\\$[1-9]+.[1-9]{2})";
	public final static String TOTAL_PATTERN = "([A-Z]{5}:.\\$[1-9]+.[1-9]{2})";
	
	public final static String THANK_NOTE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
	public final static String COMPLETE_PAGE_TITLE = "THANK YOU FOR YOUR ORDER";
	
	public final static String PAY_INFO_LABEL = "Payment Information:";
	public final static String SHIP_INFORMAITON_LABLE = "Shipping Information:";
	public final static String SHIP_INFORMATION_VALUE = "FREE PONY EXPRESS DELIVERY!";
	
}
