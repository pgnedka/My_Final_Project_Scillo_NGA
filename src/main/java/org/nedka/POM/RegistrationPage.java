package org.nedka.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends BasePage {

    public static final String REG_PAGE_SUFFIX = "/users/register";

    //2.LOCATORS
    //Registration form
    @FindBy(xpath = "//h4")
    private WebElement regFormTitle;

    @FindBy(xpath = "//input[contains(@name, \"username\")]") // = driver.findElement(By.xpath(USERNAME_REG_INPUT_FIELD_XPATH));
    private WebElement regUsernameInputField;

    @FindBy (xpath = "//input[contains(@type, \"email\")]")
    private WebElement regEmailInputField;

    @FindBy (xpath = "//input[contains(@formcontrolname, \"birthDate\")]")
    private WebElement regBirthDateInputField;

    @FindBy (id = "defaultRegisterFormPassword" )
    private WebElement regPasswordInputField;

    @FindBy (id = "defaultRegisterPhonePassword")
    private WebElement regConfirmPasswordInputField;

    @FindBy (xpath = "//textarea")
    private WebElement publicInfoTextArea;

    @FindBy (id = "sign-in-button" )
    private WebElement registrationFormSubmitButton;

    @FindBy(xpath = "//div[contains(@aria-label,\"Successful registration!\")]")
    private WebElement successfulRegistrationToastMsg;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    // Navigation to the page
    public void navigateToRegistrationPageByURL(){
        navigateTo(REG_PAGE_SUFFIX);
    }

    //User Actions
    //Populate data in input fields
    public void provideUserName(){
        String providedDemoUser = demoUsername();
        provideText(regUsernameInputField,providedDemoUser);
    };

    public void provideEmail(){
        provideText(regEmailInputField,randomValidEmail());
    };

    public void provideBDayInfo(){
        provideText(regBirthDateInputField,"22022022");
    };

    public void providePass(){
        provideText(regPasswordInputField,"22022022!A");
    };

    public void providePassConfirm(){
        provideText(regConfirmPasswordInputField,"22022022!A");
    };

    public void providePublicInfo(){
        provideText(publicInfoTextArea,"Public profile");
    }

    public void clickOnSubmitBtn(){
        clickOn(registrationFormSubmitButton);
    }

    //Support methods for reg page
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    //Support utils for test data gen
    public String getRegPageTitle() {
        return driver.getTitle();
    }

    public String getRegFormHeaderText() {
        return getElementText(regFormTitle);
    }

    public String demoUsername() {
        String username = "DemoMe" + getCurrentTime();
        return username;
    }

    public String randomValidEmail() {
        String email = "demo" + getCurrentTime() + "@gmail.com";
        return email;
    }

    public Boolean isRegFormSuccessMessageShown() {
        return isElementPresented(successfulRegistrationToastMsg);
    }

    public String getRegFormSuccessMessageText() {
        return getElementText(successfulRegistrationToastMsg);
    }
}
