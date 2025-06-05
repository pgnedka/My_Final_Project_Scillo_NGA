package org.nedka.registration;

import org.nedka.POM.RegistrationPage;
import org.nedka.base.BaseTest;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

//Step 1: Open the registration page
//    1.1: verify registration URL is correctly loaded
//    1.2: verify "Sign up" title
//
//Step 2: Fill all mandatory fields
//
//Step 3: Click on registration form Submit button
//
//Step 4: Verify success message is presented to the user
//
//Step 5: All post page (http://training.skillo-bg.com:4300/posts/all)
//    4.1: verify user is redirected to user suggestions page
//    4.2: verify that the Logout button is visible
//    4.3: verify "Profile" is visible in navigation bar
//    4.4: verify the user is not existed

    @Test
    public void registerWithValidData() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);  //Взима драйвъра, който е инициилизиран в BasePage
        registrationPage.navigateToRegistrationPageByURL();

        registrationPage.provideUserName();
        registrationPage.provideEmail();
        registrationPage.provideBDayInfo();
        registrationPage.providePass();
        registrationPage.providePassConfirm();
        registrationPage.providePublicInfo();
        registrationPage.clickOnSubmitBtn();


        Thread.sleep(3000);
    }
}
