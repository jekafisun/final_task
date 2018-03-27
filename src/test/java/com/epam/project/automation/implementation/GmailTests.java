package com.epam.project.automation.implementation;

import com.epam.project.automation.BaseTest;
import com.epam.project.model.data.DataProviders;
import com.epam.project.model.entities.User;
import com.epam.project.model.helpers.LoginPageHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GmailTests extends BaseTest {

    @BeforeMethod
    public void initPageObjects() {
        loginPageHelper = new LoginPageHelper(driver);
    }

    @Test
    public void test_1() {
        loginPageHelper.openLoginPage();
//        TestReporter.reportStep("Opened page's title is %s", loginPage.getTitle());
    }

    @Test(dataProvider = "validUser", dataProviderClass = DataProviders.class)
    public void test_2(User user) {
        loginPageHelper.loginWith(user);
    }
}
