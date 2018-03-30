package com.epam.project.model.pages.implementation;

import com.epam.project.core.reporter.TestReporter;
import com.epam.project.model.pages.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private static final String PAGE_URL = "http://gmail.com";
    private static final int ELEMENT_TIMEOUT = 30;

    @FindBy(xpath = "*//input[@id='identifierId']")
    private WebElement loginField;

    @FindBy(xpath = "*//span[contains(node(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "*//div[@id = 'password']//input")
    private WebElement passwordField;

    @FindBy(xpath = "*//div[contains(text(), 'Неверный пароль')]")
    private WebElement passwordError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        if (StringUtils.isNotEmpty(PAGE_URL)) {
            driver.get(PAGE_URL);
            TestReporter.reportStep("Page %s was opened", this.getClass().getSimpleName());
        } else {
            throw new UnsupportedOperationException("Page URL not established");
        }
        return this;
    }

    public void enterLogin(String login) {
        loginField.clear();
        TestReporter.reportDebugStep("Login field cleared");
        loginField.sendKeys(login);
        TestReporter.reportDebugStep("Entered login \"%s\" to login field", login);
    }

    public void enterPassword(String pass) {
        waitElementIsVisible(ELEMENT_TIMEOUT, passwordField);
        passwordField.clear();
        TestReporter.reportDebugStep("Password field cleared");
        passwordField.sendKeys(pass);
        TestReporter.reportDebugStep("Entered password \"%s\" to password field", pass);
    }

    public void clickNextButton() {
        nextButton.click();
        TestReporter.reportDebugStep("Next button clicked");
    }

    public String getPasswordError() {
        waitElementIsVisible(ELEMENT_TIMEOUT, passwordError);
        TestReporter.reportDebugStep("Login failed, wrong password");
        return passwordError.getText();
    }
}
