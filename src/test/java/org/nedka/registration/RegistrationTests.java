package org.nedka.registration;

import org.nedka.POM.RegistrationPage;
import org.nedka.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    private static final String REGISTER_PAGE_TITLE = "ISkillo";
    private static final String EXPECTED_REGISTER_FORM_HEADER_TEXT = "Sign up";
    private static final String EXPECTED_REGISTER_SUCCESSFUL_MESSAGE = "Successful register!";
    private static final String HOMEPAGE_TITLE = "ISkillo";

    @Test
    public void registerWithValidData() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);//Взима драйвъра, който е инициилизиран в BasePage
        log.info("STEP 1: Unregistered user has opened the Skillo Registration Page.");
        registrationPage.navigateToRegistrationPageByURL();

        log.info("Step 1.1: Verify the user is on the registration page.");
        String actualRegPageTitle = registrationPage.getRegPageTitle();
        Assert.assertEquals(actualRegPageTitle,REGISTER_PAGE_TITLE);

        log.info("Step 1.2: Verify the registration form title.");
        String actualRegFormHeaderText = registrationPage.getRegFormHeaderText();
        Assert.assertEquals(actualRegFormHeaderText, EXPECTED_REGISTER_FORM_HEADER_TEXT);

        System.out.println("STEP 2: Fill all mandatory fields.");
        log.info("Step 2.1: Provide demo username.");
        registrationPage.provideUserName();

        log.info("Step 2.2: Provide demo username.");
        registrationPage.provideEmail();

        log.info("Step 2.3: Provide birthdate.");
        registrationPage.provideBDayInfo();

        log.info("Step 2.4: Provide demo password.");
        registrationPage.providePass();

        log.info("Step 2.5: Confirm password.");
        registrationPage.providePassConfirm();

        log.info("Step 2.6: Provide public info.");
        registrationPage.providePublicInfo();

        log.info("STEP 3: Click on registration form Submit button.");
        registrationPage.clickOnSubmitBtn();

        log.info("STEP 4: Verify Toast successful message is presented to the user.");
        Assert.assertTrue(registrationPage.isRegFormSuccessMessageShown());
        String actualSuccessfulRegistrationToastMsg = registrationPage.getRegFormSuccessMessageText();
        Assert.assertEquals(actualSuccessfulRegistrationToastMsg, EXPECTED_REGISTER_SUCCESSFUL_MESSAGE);

        //Step 4: Verify success message is presented to the user
        //
        //Step 5: All post page (http://training.skillo-bg.com:4300/posts/all)
        //    4.1: verify user is redirected to user suggestions page
        //    4.2: verify that the Logout button is visible
        //    4.3: verify "Profile" is visible in navigation bar
        //    4.4: verify the user is not existed

        Thread.sleep(3000);
        System.out.println("The end!");
    }
}
