package org.nedka.profile;

import org.nedka.POM.LoginPage;
import org.nedka.base.BaseTest;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void verifyIfTheProfileLinkIsLoadedForNotLoggedInUser() {
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.
    }

}
