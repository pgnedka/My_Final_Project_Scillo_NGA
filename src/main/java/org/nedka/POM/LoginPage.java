package org.nedka.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_SUFFIX = "/users/login";
    private static final String USER = "DemoMe";
    private static final String PASS = "Password1";
    //UI MAP
    @FindBy(css = "p.h4")
    private WebElement loginFormTitle;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement userName;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;

    @FindBy(id = "sign-in-button")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//div[contains(@aria-label,\"Successful login!\")]")
    private WebElement signInToastMessage;

    @FindBy(xpath = "//div[contains(@aria-label,\"Wrong username or password!\")]")
    private WebElement failedLogInToastMessage;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToLoginPage() {
        navigateTo(LOGIN_PAGE_SUFFIX);
    }
    public void provideUser(String userNameText) {
        wait.until(ExpectedConditions.visibilityOf(userName));
        userName.clear();
        userName.sendKeys(userNameText);
    }

    public void providePass(String pass) {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(pass);
    }

    public void clickOnLoginFormSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        loginSubmitButton.click();
    }

    public void loginWithTestUser() {
        navigateToLoginPage();
        provideUser(USER);
        providePass(PASS);
        clickOnLoginFormSubmitButton();
    }

    public String getLoginFormHeaderText() {
        return getElementText(loginFormTitle);
    }

    public String getUsernamePlaceHolderText() {
        return getElementPlaceholderValue(userName);
    }

    public String getPassPlaceHolderText() {
        return getElementPlaceholderValue(passwordInputField);
    }

    public String getLoginFormSubmitButtonLabel() {
        return getElementText(loginSubmitButton);
    }

    public String getLoginPageToastSuccessfulMsg(){
        return getElementText(signInToastMessage);
    }

    public boolean isLoginFormHeaderTextShown() {
        return isElementPresented(loginFormTitle);
    }

    public String getLoginPageToastFailedMsg() {
    return getElementText(failedLogInToastMessage);
    }
}
