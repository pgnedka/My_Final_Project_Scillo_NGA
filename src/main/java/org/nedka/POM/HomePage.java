package org.nedka.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    //1. PAGE URL
    public static final String HOME_PAGE_SUFFIX = "/posts/all";

    //2.LOCAL CONSTS
    // LABELS TEXT ETC
    // HOME PAGE TITLE

    //3.UI ELEMENTS
    @FindBy(id = "nav-link-home")
    private WebElement navBarHomeLink;

    @FindBy(id = "nav-link-login")
    private WebElement navBarLoginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfile;

    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPostLink;

    @FindBy(id = "search-bar")
    private WebElement searchBarInput;

    @FindBy(xpath = "//i[contains(@class,'fas fa-sign-out-alt fa-lg')]")
    private WebElement logOutButton;

    @FindBy(xpath = "//*[contains(@class, 'navbarColor01')]/form/div")
    private WebElement dropDownSearchResults;

    //4. CONSTRUCTOR
    public HomePage (WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    //5. BASIC NAVIGATION
   public void navigateToHomePage() {
        navigateTo(HOME_PAGE_SUFFIX);
    }

    //6. USER ACTIONS
    public void clickOnHomeNavBar() {
        wait.until(ExpectedConditions.visibilityOf(navBarHomeLink));
        navBarHomeLink.click();
    }

    public void clickOnLogOutButton() {
        clickOn(logOutButton);
    }

    public void clickOnProfileNavBar() {
        clickOn(navBarProfile);
    }

    public void clickOnNewPostNavBar() {
        clickOn(navBarNewPostLink);
    }

    public void clickOnLoginNavBar() {
        wait.until(ExpectedConditions.visibilityOf(navBarLoginLink));
        navBarLoginLink.click();
    }

    public void clickOnSearchBar() {
        wait.until(ExpectedConditions.visibilityOf(searchBarInput));
        searchBarInput.click();
    }

    public void setSearchBarInput() {
        provideText(searchBarInput, "DemoMe1");
    }

    public void clickOnDropDownSearchResult() {
        wait.until(ExpectedConditions.visibilityOf(dropDownSearchResults));
        clickOn(dropDownSearchResults);
    }

    //SUPPORT METHODS
    public boolean isNavHomeShown() {
        return  isElementPresented(navBarHomeLink);
    }

    public boolean isNavLoginShown(){
        return isElementPresented(navBarLoginLink);
    }

    public boolean isNavBarProfileLinkShown() {
        System.out.println("We are checking profile link shown status");
        System.out.println(navBarProfile);
        return isElementPresented(navBarProfile);
    }

    public boolean isDropDownSearchResultsShown() {
        return isElementPresented(dropDownSearchResults);
    }

    public boolean isNavBarNewPostLinkShown() {
        return isElementPresented(navBarNewPostLink);
    }

    public boolean isSearchInputShown(){
        return isElementPresented(searchBarInput);
    }

    public boolean isLogOutButtonShown(){
        return isElementPresented(logOutButton);
    }
}
