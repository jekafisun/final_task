package com.epam.project.model.helpers;

import com.epam.project.model.entities.User;
import com.epam.project.model.pages.implementation.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginPageHelper {

    private LoginPage loginPage;

    public LoginPageHelper(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public String getPageTitle(){
        return loginPage.getTitle();
    }

    public void openLoginPage() {
        loginPage.openPage();
    }

    private void enterLogin(User user) {
        loginPage.enterLogin(user.getUsername());
        loginPage.clickNextButton();
    }

    private void enterPassword(User user) {
        loginPage.enterPassword(user.getPassword());
        loginPage.clickNextButton();
    }

    public void loginAs(User user) {
        enterLogin(user);
        enterPassword(user);
    }

    public String getErrorMessage() {
        return loginPage.getPasswordError();
    }
}
