package com.org.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.org.constants.Constants;

public class BaseTestSauceLabs {
	public Properties prop;
	protected RemoteWebDriver driver;
	private ChromiumOptions options;
	protected String status = "failed";

	public BaseTestSauceLabs() {
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

		if (browser.equals("chrome"))
			options = new ChromeOptions();
		else if (browser.equals("Edge"))
			options = new EdgeOptions();

		options.setCapability(CapabilityType.PLATFORM_NAME, "windows 10");
		options.setCapability(CapabilityType.BROWSER_VERSION, "latest");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("username", System.getProperty("sauceLabs_username"));
		sauceOptions.put("accessKey", System.getProperty("sauceLabs_accesskey"));
		options.setCapability("sauce:options", sauceOptions);
		URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		driver = new RemoteWebDriver(url, options);

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

	}

	protected WebDriver getDriver() {
		return driver;
	}

	@AfterTest
	public void tearDown() {
		driver.executeScript("sauce:job-result=" + status);
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
