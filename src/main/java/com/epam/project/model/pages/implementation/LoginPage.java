package com.epam.project.model.pages.implementation;

import com.epam.project.core.reporter.TestReporter;
import com.epam.project.model.pages.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {
    private static final String PAGE_URL = "http://gmail.com";

    @FindBy(xpath = "*//input[@id = 'identifierId']")
    private WebElement loginField;

    @FindBy(xpath = "*//span[contains(node(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "*//div[@id = 'password']//input")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage openPage() {
        if (StringUtils.isNotEmpty(PAGE_URL)) {
            TestReporter.reportStep("Page %s was opened", this.getClass().getSimpleName());
            driver.get(PAGE_URL);
        } else {
            throw new UnsupportedOperationException("Page URL not established");
        }
        return this;
    }

    private LoginPage waitPageLoading(long time, TimeUnit timeUnit) {
        TestReporter.reportDebugStep("Wait loading %s page", this.getClass().getSimpleName());
        wait.until(ExpectedConditions.visibilityOf(passwordField));

        return this;
    }

    public void enterLogin(String login){
        loginField.clear();
        TestReporter.reportDebugStep("Login field cleared");
        loginField.sendKeys(login);
        TestReporter.reportDebugStep("Entered login \"%s\" to login field", login);
    }

    public void enterPassword(String pass){
        waitPageLoading(10, TimeUnit.SECONDS);
        passwordField.clear();
        TestReporter.reportDebugStep("Password field cleared");
        passwordField.sendKeys(pass);
        TestReporter.reportDebugStep("Entered password \"%s\" to password field", pass);
    }

    public void clickNextButton(){
        nextButton.click();
        TestReporter.reportDebugStep("Next button clicked");
    }

}
