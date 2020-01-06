/* Author Sandhya Singireddy
 * /*
 * BaseTestObject: Class to load properties and initialize the Driver and make it accessible to all the classes
 */
 
package com.saucedemo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BaseTestObject {

	public static Properties props = null;
	public static WebDriver driver = null;
	private static BaseTestObject obj = null;
	
	/*
	 * Singleton to return the BaseTestObject
	 */
	public static BaseTestObject getInstance() {
		if(obj == null) {
			obj = new BaseTestObject();
			BaseTestObject.loadProperties();
			BaseTestObject.initializeDriver();
		}
		return obj;
	}

	/**
	 * Loads the properties file
	 */
	private static void loadProperties() {

		try {
			props = new Properties();
			FileInputStream inputStream = new FileInputStream("src\\main\\resources\\saucedemo.properties");
			props.load(inputStream);

			System.out.println("Number of properties loaded are: " + props.size());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Initializes the Driver
	 */
	private static void initializeDriver() {

		String browser = props.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty("pageLoadTimeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("implicitlyWait")),
				TimeUnit.SECONDS);
		driver.get(props.getProperty("url"));
	}

}
