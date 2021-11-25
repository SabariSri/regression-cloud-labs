package com.org.base;

import com.org.constants.Constants;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTestZalenium {
    public Properties prop;
    private RemoteWebDriver driver;
    DesiredCapabilities caps = new DesiredCapabilities();
    public BaseTestZalenium() {
        FileInputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir")+ Constants.PROPERTIES_PATH);
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    @Parameters("browser_name")
    public void setup(@Optional("firefox") String browser_name) throws MalformedURLException {
        caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        caps.setBrowserName(BrowserType.FIREFOX);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    protected WebDriver getDriver (){
        return driver;
    }
}
