package com.epam.project.model.helpers;

import com.epam.project.model.entities.Message;
import com.epam.project.model.pages.implementation.InboxMailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InboxMailPageHelper {
    private InboxMailPage inboxMailPage;

    public InboxMailPageHelper(WebDriver driver) {
        inboxMailPage = new InboxMailPage(driver);
    }

    public String getPageTitle() {
        return inboxMailPage.getPageTitle();
    }

    private void createMessage(Message message) {
        if (message.getAttachment() != null) {
            inboxMailPage.addAttachment(message.getAttachment());
        }
        inboxMailPage.enterRecipient(message.getRecipient());
        inboxMailPage.enterSubject(message.getSubject());
        inboxMailPage.enterMessage(message.getBody());
    }

    public void sendMessage(Message message) {
        inboxMailPage.openMessageForm();
        createMessage(message);
        inboxMailPage.sendMessage();
    }

    public List<String> getSubjects() {
        List<String> subjects = new ArrayList<>();
        for (WebElement e : inboxMailPage.getSubjectElements()) {
            subjects.add(e.getText());
        }
        return subjects;
    }

    public boolean checkMessageIsSent() {
        return inboxMailPage.messageBoxDisappeared();
    }

    public void userLogout() {
        inboxMailPage.openUserMenu();
        inboxMailPage.clickLogout();
    }

    public boolean isAttachmentPresent() {
        return inboxMailPage.checkAttachment();
    }
}
