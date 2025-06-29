package org.nedka.postPage;

import org.nedka.POM.HomePage;
import org.nedka.POM.LoginPage;
import org.nedka.POM.PostPage;
import org.nedka.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostPageTests extends BaseTest {

    private static final String EXPECTED_NEWPOST_PAGE_TITLE = "Post a picture to share with your awesome followers";
    public static final String USER = "DemoMe";
    public static final String PASS = "Password1";
    public static final String CAPTION = "Demo Test";
    public static final String FILE = "D:\\2025_Skillo_AutomationQA\\SkilloWebTesting\\NGA_TAF\\photo-1.jpg";
    public static final String EXPECTED_NEWPOST_CONFIRMATION_TOAST_MESSAGE = "Post created!";

    @Test
    public void verifyUserCanFinishNewPost() throws InterruptedException {
        PostPage postPage = new PostPage(super.driver, log);
        log.info("STEP 1: Verify the New post page is opened");
        postPage.navigateToPostPage();

        log.info("Step 1.1: Logged in.");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();

        log.info("Step 1.2: Verify the user is logged in.");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);

        log.info("STEP 2: Click on [New Post] header link");
        boolean isNewPostLinkPresented = homePage.isNavBarNewPostLinkShown();
        Assert.assertTrue(isNewPostLinkPresented);
        homePage.clickOnNewPostNavBar();

        log.info("Step 2.1: Verify the New Post page header text.");
        String actualNewPostPageText = postPage.getNewPostPageHeaderText();
        Assert.assertEquals(actualNewPostPageText, EXPECTED_NEWPOST_PAGE_TITLE);

        log.info("STEP 2: Attache a photo.");
        postPage.uploadPicture(new File(FILE));
        postPage.providePostCaption(CAPTION);

        log.info("STEP 3: Confirm photo.");
        postPage.clickCreatePostButton();

        log.info("Step 3.1: Verify post creating confirmation message");
        String actualNewPostConfirmationMsg = postPage.getNewPostToastSuccessfulMsg();
        Assert.assertEquals(actualNewPostConfirmationMsg, EXPECTED_NEWPOST_CONFIRMATION_TOAST_MESSAGE);

        Thread.sleep(3000);
        System.out.println("The end!");
    }
}
