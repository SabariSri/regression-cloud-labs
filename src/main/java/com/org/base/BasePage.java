package com.org.base;

import com.org.exceptions.ElementNotClickableException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private  static int TIMEOUT = 10;
    protected WebDriverWait wait;
    protected Wait<WebDriver> fluentWait;
    protected  WebDriver driver;
    
    public  BasePage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver,TIMEOUT),this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // explicit wait initialization
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        //fluent wait initialization
        fluentWait =  new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofSeconds(TIMEOUT)).
                pollingEvery(Duration.ofSeconds(4)).
                ignoring(ElementNotClickableException.class,ElementNotVisibleException.class);

        PageFactory.initElements(driver,this);

    }
    protected WebElement waitForElement (WebElement element){
       return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void fluentWait(WebElement element){
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public String  getUrl() {
        return driver.getCurrentUrl();
    }


}
