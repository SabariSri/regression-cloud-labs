package com.org.pages;

import com.org.base.BasePage;
import com.org.exceptions.ElementNotClickableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@id='SW']//li[@data-cy='account']")
    WebElement login;

    @FindBy(xpath = "//input[@id='username']")
    WebElement emailPlaceHolder;

    @FindBy(xpath = "//button[@data-cy='continueBtn']")
    WebElement continueButton;

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void clickOnLogin(){
       waitForElement(login).click();
    }

    public void enterEmail(String email){

        waitForElement(emailPlaceHolder).sendKeys(email);
//        if(!validateEmail(email)){System.out.println("INVALID EMAIL");
//            throw new InvalidEmailException(email);}
    }

    private boolean validateEmail(String email) {
        boolean validEmail =  false;
        if(email.contains("@")){
            validEmail = true;
        }
        return validEmail;
    }

    public void clickContinue() throws ElementNotClickableException {

        try {
            waitForElement(continueButton).click();
        }catch (Exception e){
            throw  new ElementNotClickableException("Element not found");
        }


    }
}
