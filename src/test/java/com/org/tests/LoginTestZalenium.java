package com.org.tests;

import com.org.base.BaseTestZalenium;
import com.org.pages.CartPage;
import com.org.pages.InventoryPage;
import com.org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTestZalenium extends BaseTestZalenium {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @Test(groups = "swaglabs")
    public void loginTest(Method method){
        loginPage = new LoginPage(getDriver());
        loginPage.enterUsername();
        loginPage.enterPassword();
        inventoryPage = loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUrl().contains("inventory"),"Login failed");
        inventoryPage.addBackPackAddToCart();
        inventoryPage.addBoltTShirtAddToCart();
        cartPage = inventoryPage.viewShoppingCart();
        Assert.assertTrue(inventoryPage.getUrl().contains("cart"),"failed to open cart");
        cartPage.clickOnCheckout();
        Assert.assertTrue(cartPage.getUrl().contains("checkout"),"Failed to open check out page");
    }
}
