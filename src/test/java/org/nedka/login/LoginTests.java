package org.nedka.login;

import org.nedka.POM.HomePage;
import org.nedka.POM.LoginPage;
import org.nedka.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private static final String HOMEPAGE_TITLE = "ISkillo";
    private static final String EXPECTED_LOGIN_FORM_TITLE = "Sign in";
    public static final String EXPECTED_LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String EXPECTED_LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";
    public static final String USER = "DemoMe";
    public static final String PASS = "Password1";
    public static final String WRONG_PASS = "Password";

    @Test
    public void verifyTheUserCanLoginWithValidCredentials() {

        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.navigateToHomePage();

        log.info("Step 1.1.Verify the user is on the home page.");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("Step 1.2. Verify that the login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link.");
        homePage.clickOnLoginNavBar();

        log.info("Step 2.1.: The user is successfully on the login page.");
        LoginPage loginPage = new LoginPage(super.driver,log);

        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        Assert.assertEquals(actualLoginFormHeaderText, EXPECTED_LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide username.");
        loginPage.provideUser(USER);

        log.info("STEP 4. Provide password.");
        loginPage.providePass(PASS);

        log.info("STEP 5. Click on loginButton.");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 6. Verify the user successfully login in ISkillo.");
        String actualLoginSuccessfulMsg =  loginPage.getLoginPageToastSuccessfulMsg();
        log.info("Step 6.1. Verify login successful message is presented to the user.");
        Assert.assertEquals(actualLoginSuccessfulMsg, EXPECTED_LOGIN_SUCCESSFUL_MSG);
        log.info("Step 6.2. Verify for an already logged-in user a logout button is shown.");
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);
        log.info("Step 6.3. Verify for an already logged-in user a nav bar profile link is shown.");
        boolean isNavBarProfileShown = homePage.isNavBarProfileLinkShown();
        Assert.assertTrue(isNavBarProfileShown);
        System.out.println("The end!");
    }

    @Test
    public void verifyTheUserCanNotLoginWithInvalidCredentials() {
        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.navigateToHomePage();

        log.info("Step 1.1.Verify the user is on the home page.");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("Step 1.2. Verify that the login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link.");
        homePage.clickOnLoginNavBar();

        log.info("Step 2.1.: The user is successfully on the login page.");
        LoginPage loginPage = new LoginPage(super.driver,log);
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        Assert.assertEquals(actualLoginFormHeaderText, EXPECTED_LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide correct username.");
        loginPage.provideUser(USER);

        log.info("STEP 4. Provide wrong password.");
        loginPage.providePass(WRONG_PASS);

        log.info("STEP 5. Click on loginButton.");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 6: Verify the user is not logged in.");
        boolean isNavBarProfileShown = homePage.isNavBarProfileLinkShown();
        Assert.assertFalse(isNavBarProfileShown);
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonPresented);
        String actualLoginFailedMsg =  loginPage.getLoginPageToastFailedMsg();
        Assert.assertEquals(actualLoginFailedMsg, EXPECTED_LOGIN_NOT_SUCCESSFUL_MSG);
        System.out.println("The end!");
    }

    @Test(priority = 99)
    public void verifyLoginPageLayout() {
        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.navigateToHomePage();

        log.info("Step 1.1.Verify the user is on the home page.");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("Step 1.2. Verify that the login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link.");
        homePage.clickOnLoginNavBar();

        // =====  LOGIN PAGE LAYOUT
        LoginPage loginPage = new LoginPage(super.driver,log);

        // header text form
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        // user place holder
        String actualUsernameInputHeaderText = loginPage.getUsernamePlaceHolderText();

        // pass place holder

        // Remember me - text

        // Siting Btn - label text
        String actualLoginButtonText = loginPage.getLoginFormSubmitButtonLabel();
        System.out.println("The end!");
    }
}
