package com.saucedemo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.util.BaseTestObject;
import com.saucedemo.util.CommonUtils;
import com.saucedemo.util.SauceDemoConstants;

/*
 * CheckOutFinalPageObject
 */
public class CheckOutFinalPageObject extends BaseTestObject {
	
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div:nth-child(1)")
	static WebElement payInfoLabel;
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div:nth-child(2)")
	static WebElement payInfoValue;
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div:nth-child(3)")
	static WebElement shipInforamtionlabel;
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div:nth-child(4)")
	static WebElement shipInforamtionValue;
	
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div.summary_subtotal_label")
	static WebElement itemTotal;
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div.summary_tax_label")
	static WebElement tax;
	
	@FindBy(how = How.CSS, using = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
	static WebElement total;
	

	@FindBy(how = How.CSS, using = "#checkout_summary_container>div>div.summary_info>div.cart_footer> a.btn_action.cart_button")
	static WebElement finish;
	
	/*
	 * Constructor for CheckOutFinalPageObject
	 */
	public CheckOutFinalPageObject() {
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Checks if Pay Info Label is Present or not
	 */
	public boolean isPayInfoLabelPresent() {
		String info = payInfoLabel.getText();
		System.out.println("payInfoLabel :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Checks if Pay Info Value is Present or not
	 */
	public boolean isPayInfoValuePresent() {
		String info = payInfoValue.getText();
		System.out.println("payInfoValue :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Checks if Ship Information is Present or not
	 */
	public boolean isShipInformaitonLablePresent() {
		String info = shipInforamtionlabel.getText();
		System.out.println("shipInforamtionlabel :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Checks if Ship Value is Present or not
	 */
	public boolean isShipInformaitonValuePresent() {
		String info = shipInforamtionValue.getText();
		System.out.println("shipInforamtionValue :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}

	
	/*
	 * Checks if Item Total is Present or not
	 */
	public boolean isItemTotalPresent() {
		String info = itemTotal.getText();
		System.out.println("itemTotal :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Checks if Tax is Present or not
	 */
	public boolean isTaxPresent() {
		String info = tax.getText();
		System.out.println("tax :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Checks if Total is Present or not
	 */
	public boolean isTotalPresent() {
		String info = total.getText();
		System.out.println("total :" + info);
		if (info == null || info.trim().length() < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/*
	 * Returns the PayLabel 
	 */
	public String getPayInfoLabel() {
		return payInfoLabel.getText();
	}
	
	/*
	 * Checks the PayInfo pattern 
	 */
	public boolean checkPayInfoValuePattern() {
		String payValue = payInfoValue.getText().toUpperCase();
		System.out.println("payValue : " + payValue);
		return CommonUtils.validatePattern(SauceDemoConstants.PAYVALUE_PATTERN, payValue); 
	}
	
	/*
	 * Returns the ShipInfoLable 
	 */
	public String getShipInformaitonLable() {
		return shipInforamtionlabel.getText();
	}
	
	/*
	 * Returns the ShipInfoLable 
	 */
	public String getShipInformaitonValue() {
		return shipInforamtionValue.getText();
	}
	
	/*
	 * Checks the Item Total pattern 
	 */
	public boolean checkItemTotalPattern() {
		String itemTotalStr = itemTotal.getText().toUpperCase();
		System.out.println("itemTotal : " + itemTotalStr);
		return CommonUtils.validatePattern(SauceDemoConstants.ITEM_TOTAL_PATTERN, itemTotalStr); 
	}
	
	/*
	 * Checks the Item Tax pattern 
	 */
	public boolean checkTaxPattern() {
		String taxValue = tax.getText().toUpperCase();
		System.out.println("tax : " + taxValue);
		return CommonUtils.validatePattern(SauceDemoConstants.TAX_PATTERN, taxValue); 
	}
	
	/*
	 * Checks the Total pattern 
	 */
	public boolean checkTotalPattern() {
		String totalValue = total.getText().toUpperCase();
		System.out.println("totalValue : " + totalValue);
		return CommonUtils.validatePattern(SauceDemoConstants.TOTAL_PATTERN, totalValue); 
	}
	
	
	/*
	 * Checks the Finish button
	 * 
	 */
	public CheckOutCompletePageObject clickFinish() {
		finish.click();
		return new CheckOutCompletePageObject();
	}



}
