package com.org.pages;

import com.org.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='inventory_item_description']/div/button[contains(@id,'backpack')]")
    WebElement backPackAddToCart;

    @FindBy(xpath = "//div[@class='inventory_item_description']/div/button[contains(@id,'bolt-t-shirt')]")
    WebElement boltTShirtAddToCart;

    @FindBy(xpath = "//div[@id=\"shopping_cart_container\"]")
    WebElement shoppingCart;

    public void addBackPackAddToCart(){
        backPackAddToCart.click();
    }
    public void addBoltTShirtAddToCart(){
        boltTShirtAddToCart.click();
    }

    public CartPage viewShoppingCart(){
        shoppingCart.click();
        return new CartPage(driver);
    }
}
