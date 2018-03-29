package com.epam.project.automation.implementation;

import com.epam.project.automation.BaseTest;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItem;


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
        inboxMailPageHelper.userLogout();
    }


    @Test(dataProvider = "invalidUser", dataProviderClass = DataProviders.class)
    @Description("Check that invalid user can't login")
    public void checkInvalidUserCanNotLoginTest(User invalidUser) {
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(invalidUser);
        assertThat(loginPageHelper.getErrorMessage(), containsString("Wrong password"));
    }

    @Test(dataProvider = "validMessage",dataProviderClass = DataProviders.class)
    @Description("Check that the user can send a completed letter")
    public void checkSendCompletedLetterTest(Message message){
        User user = User.builder().username("jeka.fisun.test").password("QweAsd123").build();
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(user);
        inboxMailPageHelper.sendMessage(message);
        Assert.assertTrue(inboxMailPageHelper.checkMessageIsSent());
        inboxMailPageHelper.userLogout();
    }

    @Test(dataProvider = "validMessage",dataProviderClass = DataProviders.class,dependsOnMethods = "checkSendCompletedLetterTest")
    @Description("Check that the user receive sent message")
    public void checkReceivingOfMessageTest(Message message){
        User user = User.builder().username("jeka.fisun.test").password("QweAsd123").build();
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(user);
        assertThat(inboxMailPageHelper.getSubjects(), hasItem(message.getSubject()));
        inboxMailPageHelper.userLogout();
    }

    @Test(enabled = false,dataProvider = "validMessageWithAttachment",dataProviderClass = DataProviders.class)
    @Description("Check sending message with attachment")
    public void checkSendMessageWithAttachment(Message message){
        User user = User.builder().username("jeka.fisun.test").password("QweAsd123").build();
        loginPageHelper.openLoginPage();
        loginPageHelper.loginAs(user);
        inboxMailPageHelper.sendMessage(message);
        assertThat(inboxMailPageHelper.getSubjects(), hasItem(message.getSubject()));
        inboxMailPageHelper.userLogout();
    }

//    @AfterMethod
//    public void tearDown(){
//        try {
//            driver.manage().deleteAllCookies();
//        } catch (UnhandledAlertException f) {
//            try {
//                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
//                System.out.println("Alert data: " + alertText);
//                alert.accept();
//            } catch (NoAlertPresentException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

}
