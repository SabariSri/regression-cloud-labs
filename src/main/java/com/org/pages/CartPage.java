package com.org.pages;

import com.org.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutButton;

    public int getNumberOfItemsInCart(){
        return cartItems.size();
    }

    public void clickOnCheckout(){
        checkoutButton.click();
    }

}
