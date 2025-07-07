package org.nedka.profile;

import org.nedka.POM.HomePage;
import org.nedka.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void verifyIfTheProfileLinkIsLoadedForNotLoggedInUser() throws InterruptedException {

       log.info("STEP 1: Verify the user is on the Home page.");
       HomePage homePage = new HomePage(super.driver, log);
       homePage.navigateToHomePage();

       log.info("STEP 2: Verify the user is not logged in.");
       boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
       Assert.assertFalse(isLogOutButtonPresented);

       log.info("STEP 3: Check if the Profile link is visible on the screen.");
       Assert.assertFalse(homePage.isNavBarProfileLinkShown());

       Thread.sleep(3000);
       System.out.println("The end!");
    }
}
