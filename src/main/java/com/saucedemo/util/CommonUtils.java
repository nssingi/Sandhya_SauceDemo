package com.saucedemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

/*
 * Common Utility functions
 */
public class CommonUtils {
	
	/*
	 * Refresh the page.
	 */
	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/*
	 * Regex pattern match for a given Pattern and the value
	 */
	public static boolean validatePattern(String patternStr, String valuetoCheck) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(valuetoCheck);
		return matcher.find();
		
	}
	
}
