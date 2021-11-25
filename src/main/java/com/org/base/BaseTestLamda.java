package com.org.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.org.constants.Constants;

public class BaseTestLamda {
	public Properties prop;
	private RemoteWebDriver driver;
	protected String status = "failed";
	DesiredCapabilities caps = new DesiredCapabilities();

	public BaseTestLamda() {
		FileInputStream input;
		try {
			input = new FileInputStream(System.getProperty("user.dir") + Constants.PROPERTIES_PATH);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	@Parameters("browser_name")
	public void setup(@Optional("Chrome") String browser_name) throws MalformedURLException {

		String username = System.getProperty("LT_USERNAME");
		String authkey = System.getProperty("LT_ACCESS_KEY");
		String hub = "@hub.lambdatest.com/wd/hub";

		// Configure capabilities
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("browserName", browser_name);
		caps.setCapability("version", "latest");
		caps.setCapability("build", "SwagLabs_01");
		// caps.setCapability("name", m.getName() + this.getClass().getName());
		caps.setCapability("console", true);
		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + status);
		driver.quit();
	}

	protected WebDriver getDriver() {
		return driver;
	}

}
