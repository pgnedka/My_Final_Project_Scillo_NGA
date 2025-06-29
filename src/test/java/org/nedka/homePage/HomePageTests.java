package org.nedka.homePage;

import org.nedka.POM.HomePage;
import org.nedka.POM.LoginPage;
import org.nedka.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    private static String HOME_LINK;

    @Test
    public void verifyTheSearchBarAfterLoggedIn() throws InterruptedException {
        log.info("STEP 1: Verify the user is logged in.");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();
        HomePage homePage = new HomePage(super.driver, log);
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);

        log.info("Step 2: Click on search bar.");
        boolean isSearchInputFieldShown = homePage.isSearchInputShown();
        Assert.assertTrue(isSearchInputFieldShown);
        homePage.clickOnSearchBar();

        log.info("Input data.");
        homePage.setSearchBarInput();

        log.info("The search element is existing.");
        boolean isDropDownSearchBarPresented = homePage.isDropDownSearchResultsShown();
        Assert.assertTrue(isDropDownSearchBarPresented);
        homePage.clickOnDropDownSearchResult();

        Thread.sleep(3000);
        System.out.println("The end!");
    }
}
