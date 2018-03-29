package com.epam.project.automation.implementation;

import com.epam.project.automation.BaseTest;
import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.model.data.DataProviders;
import com.epam.project.model.entities.Message;
import com.epam.project.model.entities.User;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GmailTests extends BaseTest {
    private User validUser;

    @BeforeMethod(description = "Initializing page helpers")
    public void initPageObjects() {
        loginPageHelper = new LoginPageHelper(driver);
        inboxMailPageHelper = new InboxMailPageHelper(driver);
        validUser = User.builder()
                .username(PropertiesData.GLOBAL.username())
                .password(PropertiesData.GLOBAL.validPassword())
                .build();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to check opening of Login page")
    public void checkOpenLoginPageTest() {
        loginPageHelper.openLoginPage();
        assertThat(loginPageHelper.getPageTitle(), equalToIgnoringCase("gmail"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check login of valid user")
    public void checkValidUserCanLoginTest() {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(validUser);
        assertThat(inboxMailPageHelper.getPageTitle(), containsString(validUser.getUsername()));
        inboxMailPageHelper.userLogout();
    }


    @Test(dataProvider = "invalidUser", dataProviderClass = DataProviders.class)
    @Description("Check that invalid user can't login")
    public void checkInvalidUserCanNotLoginTest(User invalidUser) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(invalidUser);
        assertThat(loginPageHelper.getErrorMessage(), containsString("Неверный пароль"));
    }

    @Test(dataProvider = "validMessage", dataProviderClass = DataProviders.class)
    @Description("Check that the user can send a completed letter")
    public void checkSendCompletedLetterTest(Message message) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(validUser);
        inboxMailPageHelper.sendMessage(message);
        Assert.assertTrue(inboxMailPageHelper.checkMessageIsSent());
        inboxMailPageHelper.userLogout();
    }

    @Test(dataProvider = "validMessage", dataProviderClass = DataProviders.class, dependsOnMethods = "checkSendCompletedLetterTest")
    @Description("Check that the user receive sent message")
    public void checkReceivingOfMessageTest(Message message) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(validUser);
        assertThat(inboxMailPageHelper.getSubjects(), hasItem(message.getSubject()));
        inboxMailPageHelper.userLogout();
    }

    @Test(dataProvider = "validMessageWithAttachment", dataProviderClass = DataProviders.class)
    @Description("Check sending message with attachment")
    public void checkSendMessageWithAttachment(Message message) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(validUser);
        inboxMailPageHelper.sendMessage(message);
        Assert.assertTrue(inboxMailPageHelper.isAttachmentPresent());
        inboxMailPageHelper.userLogout();
    }
}
