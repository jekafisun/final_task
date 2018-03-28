package com.epam.project.model.helpers;

import com.epam.project.model.pages.InboxMailPage;

public class InboxMailPageHelper {
    private InboxMailPage inboxMailPage;

    public InboxMailPageHelper(){
        inboxMailPage = new InboxMailPage();
    }

    public String getPageTitle() {
        return inboxMailPage.getPageTitle();
    }

//    public void userLogout() {
//        inboxMailPage.openUserMenu();
//        inboxMailPage.clickLogout();
//    }
}
