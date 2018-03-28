package com.epam.project.automation.implementation;

import com.epam.project.automation.BaseTest;
import com.epam.project.model.data.DataProviders;
import com.epam.project.model.entities.User;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class GmailTests extends BaseTest {

    @BeforeMethod(description = "Initializing page helpers")
    public void initPageObjects() {
        loginPageHelper = new LoginPageHelper();
        inboxMailPageHelper = new InboxMailPageHelper();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to check opening of Login page")
    public void checkOpenLoginPageTest() {
        loginPageHelper.openLoginPage();
        assertThat(loginPageHelper.getPageTitle(), equalToIgnoringCase("gmail"));
    }

    @Test(dataProvider = "validUser", dataProviderClass = DataProviders.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check login of valid user")
    public void checkValidUserCanLoginTest(User user) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(user);
        assertThat(inboxMailPageHelper.getPageTitle(), containsString(user.getUsername()));
//        inboxMailPageHelper.userLogout();
    }

    //TODO - check!
    @Test(enabled = false, dataProvider = "invalidUser", dataProviderClass = DataProviders.class)
    @Description("Check that invalid user can't login")
    public void test_3(User invalidUser) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(invalidUser);
        assertThat(loginPageHelper.getErrorMessage(), containsString("Неверный пароль"));
    }
}
