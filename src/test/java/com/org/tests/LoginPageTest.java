package com.org.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.Utils;
import com.org.base.BaseTestBrowserStack;
import com.org.pages.CartPage;
import com.org.pages.InventoryPage;
import com.org.pages.LoginPage;

public class LoginPageTest extends BaseTestBrowserStack {
	LoginPage loginPage;
	InventoryPage inventoryPage;
	CartPage cartPage;

	@BeforeTest(groups = "swaglabs")
	public void setLoginPage() {
		loginPage = new LoginPage(getDriver());
	}

	@Test(groups = "swaglabs")
	public void loginTest() {
		loginPage.enterUsername();
		loginPage.enterPassword();
		inventoryPage = loginPage.clickOnLoginButton();
		if (prop.getProperty("remote", "false").contains("false"))
			Assert.assertTrue(loginPage.getUrl().contains("inventory"), "Login failed");
		else {
			try {
				if (loginPage.getUrl().contains("inventory"))
					Utils.markTestStatus(jse, "passed", "URL contains 'Inventory'");
				else
					Utils.markTestStatus(jse, "failed", "URL does not contain 'Inventory'");
			} catch (Exception e) {
				Utils.markTestStatus(jse, "failed", "Failed with exception: \n" + e);
			}
		}
	}

	@Test(groups = "swaglabs", dependsOnMethods = "loginTest")
	public void inventoryPageTest() {
		inventoryPage.addBackPackAddToCart();
		inventoryPage.addBoltTShirtAddToCart();
		cartPage = inventoryPage.viewShoppingCart();

		if (prop.getProperty("remote", "false").contains("false"))
			Assert.assertTrue(inventoryPage.getUrl().contains("cart"), "failed to open cart");

		else {
			try {
				if (inventoryPage.getUrl().contains("cart"))
					Utils.markTestStatus(jse, "passed", "URL contains 'cart'");
				else
					Utils.markTestStatus(jse, "failed", "URL does not contain 'cart'");
			} catch (Exception e) {
				Utils.markTestStatus(jse, "failed", "Failed with exception: \n" + e);
			}
		}

	}

	@Test(groups = "swaglabs", dependsOnMethods = { "loginTest", "inventoryPageTest" }, enabled = false)
	public void cartPageTest() {
		cartPage.clickOnCheckout();

		if (prop.getProperty("remote", "false").contains("false"))
			Assert.assertTrue(cartPage.getUrl().contains("checkout"), "Failed to open check out page");

		else {
			try {
				if (cartPage.getUrl().contains("checkout"))
					Utils.markTestStatus(jse, "passed", "URL contains 'checkout'");
				else
					Utils.markTestStatus(jse, "failed", "URL does not contain 'checkout'");
			} catch (Exception e) {
				Utils.markTestStatus(jse, "failed", "Failed with exception: \n" + e);
			}
		}
	}
}
