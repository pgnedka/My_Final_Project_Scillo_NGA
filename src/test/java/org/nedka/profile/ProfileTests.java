package org.nedka.profile;

import org.nedka.POM.HomePage;
import org.nedka.POM.LoginPage;
import org.nedka.POM.ProfilePage;
import org.nedka.base.BaseTest;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void verifyTheProfileLinkLoadedForNOTLoggedInUser() {

       log.info("STEP 1: Verify the user is on the Home page.");
       HomePage homePage = new HomePage(super.driver, log);
       homePage.navigateToHomePage();

       log.info("STEP 2: Verify the user is not logged in.");
       boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
       Assert.assertFalse(isLogOutButtonPresented);

       log.info("STEP 3: Check if the Profile link is visible on the screen.");
       Assert.assertFalse(homePage.isNavBarProfileLinkShown());

       System.out.println("The end!");
    }

    @Test
    public void checkTheUserPostsCount() {
        log.info("STEP 1: The user is logging in.");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();
        log.info("Step 1.1: Verify the user is logged in.");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);

        log.info("STEP 2: Click on Profile page navigation link.");
        Assert.assertTrue(homePage.isNavBarProfileLinkShown());
        homePage.clickOnProfileNavBar();

        log.info("STEP 3: Get posts count.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        String userName = profilePage.getUsername();
        int postCount = profilePage.getPostCount();
        System.out.printf("User \"%s\" has %d posts. %n", userName, postCount);

        log.info("STEP 4: Log out.");
        homePage.clickOnLogOutButton();

        System.out.println("The end!");
    }

    @Test
    public void deletePostIfExist() {
        log.info("STEP 1: The user is logging in.");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();

        log.info("Step 1.1: Verify the user is logged in.");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);

        log.info("STEP 2: Click on Profile page navigation link.");
        Assert.assertTrue(homePage.isNavBarProfileLinkShown());
        homePage.clickOnProfileNavBar();

        log.info("Step 2.1. Verify the user name.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        String userName = profilePage.getUsername();
        System.out.printf("User \"%s\" is successfully logged in. %n", userName);

        log.info("STEP 3: Open a post");
        int postCount = profilePage.getPostCount();
        if (postCount > 0) {
            profilePage.clickPost(0);
        } else {
            Assert.fail("Test stopped: No posts available to delete.");
        }

        log.info("STEP 4: Delete a post.");
        profilePage.ClickOnDeleteButton();
        profilePage.ClickOnYesButton();
        boolean isDeletedMsgVisible = profilePage.isDeletedMessageVisible();
        Assert.assertTrue(isDeletedMsgVisible);

        System.out.println("The end!");
    }
}
