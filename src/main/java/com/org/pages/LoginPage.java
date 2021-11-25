package com.org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.org.base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//text()[contains(.,'_user')]")
	String username;

	@FindBy(xpath = "//div[@class = 'login_password']/text()")
	String password;

	@FindBy(xpath = "//input[@id = 'user-name']")
	WebElement usernameTextBox;

	@FindBy(xpath = "//input[@id = 'password']")
	WebElement passwordTextBox;

	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername() {
		usernameTextBox.sendKeys("standard_user");

	}

	public void enterPassword() {
		passwordTextBox.sendKeys("secret_sauce");
	}

	public InventoryPage clickOnLoginButton() {
		loginButton.click();
		return new InventoryPage(driver);
	}

}
