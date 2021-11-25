package com.org.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactory extends BaseTestBrowserStack {
    static Map<String, Supplier<WebDriver>> drivers = new HashMap<>();
    private final Supplier<WebDriver> edge = () -> {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    };

    private final Supplier<WebDriver> chrome =  () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };
    private final Supplier<WebDriver> firefox = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    //Java 9 Map initialize map in the declaration
    public BrowserFactory(){
        drivers.put("chrome", chrome);
        drivers.put("firefox", firefox);
        drivers.put("edge",edge);
    }
    public  WebDriver getDriver(String browser) {
        return  drivers.get(browser.toLowerCase()).get();
    }
}

