package com.epam.project.model.helpers;

import com.epam.project.model.entities.Message;
import com.epam.project.model.pages.InboxMailPage;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InboxMailPageHelper {
    private InboxMailPage inboxMailPage;

    public InboxMailPageHelper(){
        inboxMailPage = new InboxMailPage();
    }

    public String getPageTitle() {
        return inboxMailPage.getPageTitle();
    }

    private void createMessage(Message message) {
        inboxMailPage.enterRecipient(message.getRecipient());
        inboxMailPage.enterSubject(message.getSubject());
        if(message.getAttachment()!=null){
            inboxMailPage.addAttachment(message.getAttachment());
        }
        inboxMailPage.enterMessage(message.getBody());
    }

    public void sendMessage(Message message){
        inboxMailPage.openMessageForm();
        createMessage(message);
        inboxMailPage.sendMessage();
    }

    public List<String> getSubjects(){
        List<String> subjects = new ArrayList<>();
        for (WebElement e: inboxMailPage.getSubjectElements()){
            subjects.add(e.getText());
        }
        return subjects;
    }

    public boolean checkMessageIsSent(){
        return inboxMailPage.messageBoxDisappeared();
    }

    public void userLogout() {
        inboxMailPage.openUserMenu();
        inboxMailPage.clickLogout();
    }
}
