package com.epam.project.model.pages.implementation;

import com.epam.project.core.reporter.TestReporter;
import com.epam.project.model.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxMailPage extends BasePage {

    private static final int ELEMENT_TIMEOUT_SECONDS = 30;
    private static final int TIME_OUT_IN_SECONDS = 30;

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

    @FindBy(css = "img[alt='Приложение']")
    private WebElement attachmentIcon;

    @FindBy(css = "div[aria-label='Информация об аккаунте']")
    private WebElement userPopupMenu;

    public InboxMailPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        TestReporter.reportStep("Login completed");
        waitPageLoading(10, newMessageButton);
        return getTitle();
    }

    public void openMessageForm() {
        newMessageButton.click();
        waitPageLoading(ELEMENT_TIMEOUT_SECONDS, newMessageBox);
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
        waitPageLoading(ELEMENT_TIMEOUT_SECONDS, sendButton);
        sendButton.click();
        waitPageLoading(ELEMENT_TIMEOUT_SECONDS, messagesSubjects.get(0));
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

    public boolean checkAttachment() {
        waitPageLoading(ELEMENT_TIMEOUT_SECONDS, newMessageButton);
        TestReporter.reportDebugStep("Check presence of the attachment");
        return attachmentIcon.isDisplayed();
    }

    public void openUserMenu() {
        TestReporter.reportDebugStep("Click on user menu icon");
        userMenuButton.click();
        waitPageLoading(5, userPopupMenu);
    }

    public void clickLogout() {
        TestReporter.reportDebugStep("Click on logout button");
        logoutButton.click();

        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.titleIs("Gmail"));
        driver.manage().deleteAllCookies();
    }
}
