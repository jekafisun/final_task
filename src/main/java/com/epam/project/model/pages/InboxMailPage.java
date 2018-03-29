package com.epam.project.model.pages;

import com.epam.project.core.reporter.TestReporter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxMailPage extends BasePage {

    @FindBy(xpath = "*//div[contains(text(), 'НАПИСАТЬ')]")
    private WebElement newMessageButton;

    @FindBy(css = "div[role=dialog]")
    private WebElement newMessageBox;

    @FindBy(name = "to")
    private WebElement toWhomField;

    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    @FindBy(css = "div[role='textbox']")
    private WebElement messageBody;

    @FindBy(xpath = "*//div[contains(text(), 'Отправить')]")
    private WebElement sendButton;

    //    @FindBy(xpath = "*//input[@type='file']")
    @FindBy(name = "Filedata")
    private WebElement attachmentField;

    @FindBy(css = "span.bog>b")
    private List<WebElement> messagesSubjects;

    @FindBy(css = "span.gb_db")
    private WebElement userMenuButton;

    @FindBy(css = "div.gb_Eb.gbip")
    private WebElement userIcon;

    @FindBy(linkText = "Выйти")
    private WebElement logoutButton;

    public InboxMailPage() {
        super();
    }

    public String getPageTitle() {
        TestReporter.reportStep("Login completed");
        waitPageLoading(10, newMessageButton);
        return getTitle();
    }

    public void openMessageForm() {
        newMessageButton.click();
        waitPageLoading(30, newMessageBox);
        TestReporter.reportDebugStep("Opened message dialog");
    }

    public void enterRecipient(String recipient) {
        toWhomField.clear();
        toWhomField.sendKeys(recipient);
        TestReporter.reportDebugStep("Recipient \"%s\" is entered in recipient's field", recipient);
    }

    public void enterSubject(String subject) {
        subjectField.clear();
        subjectField.sendKeys(subject);
        TestReporter.reportDebugStep("Subject \"%s\" was entered to subject field", subject);
    }

    public void enterMessage(String message) {
        messageBody.sendKeys(message);
        TestReporter.reportDebugStep("Message was entered");
    }

    public void addAttachment(String pathToAttachment) {
        attachmentField.sendKeys(pathToAttachment);
        TestReporter.reportDebugStep("Attachment was added");
    }

    public void sendMessage() {
        waitPageLoading(30, sendButton);
        sendButton.click();
        waitPageLoading(30, messagesSubjects.get(0));
        TestReporter.reportDebugStep("Send button was clicked");
    }

    public boolean messageBoxDisappeared() {
        TestReporter.reportDebugStep("Check that the message box is disappeared");
        return !newMessageBox.isDisplayed();
    }

    public List<WebElement> getSubjectElements() {
        TestReporter.reportDebugStep("Get list of subjects");
        return messagesSubjects;
    }

    public void openUserMenu() {
        try {
            TestReporter.reportDebugStep("Click on user menu icon");
            userMenuButton.click();
        } catch (UnhandledAlertException f) {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert data: " + alertText);
                alert.accept();
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
//        TestReporter.reportDebugStep("Click user icon");
    }

    public void clickLogout() {
        if (logoutButton.isEnabled()) {
            logoutButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.titleIs("Gmail"));
            driver.manage().deleteAllCookies();
        } else {
            throw new UnsupportedOperationException("warning!");
        }
//        TestReporter.reportDebugStep("Clicked exit button");
    }
}
