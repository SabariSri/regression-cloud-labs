package com.org.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.org.constants.Constants;

public class BaseTestBrowserStack {
	public Properties prop;
	protected WebDriver driver;
	protected JavascriptExecutor jse;
	public static final String AUTOMATE_USERNAME = System.getProperty("browserStack_user_name");
	public static final String AUTOMATE_ACCESS_KEY = System.getProperty("browserStack_access_key");
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public BaseTestBrowserStack() {
		FileInputStream input;
		try {
			input = new FileInputStream(System.getProperty("user.dir") + Constants.PROPERTIES_PATH);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	@Parameters("browser_name")
	public void initialize(@Optional("chrome") String browser) throws MalformedURLException {
		if (prop.getProperty("remote", "false").contains("true")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", browser);
			caps.setCapability("browser_version", "latest");
			caps.setCapability("resolution", "2048x1536");
			// caps.setCapability("browserstack.video",false);
			caps.setCapability("name", "Sauce Labs website");
			caps.setCapability("build", "Sauce Labs website Build 1");
			driver = new RemoteWebDriver(new URL(URL), caps);
			jse = (JavascriptExecutor) driver;
		}

		else
			driver = new BrowserFactory().getDriver(browser);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

	}

	protected WebDriver getDriver() {
		return driver;
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

}
