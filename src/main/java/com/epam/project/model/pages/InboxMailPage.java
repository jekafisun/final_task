package com.epam.project.model.pages;

import com.epam.project.core.reporter.TestReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxMailPage extends BasePage {

    @FindBy(xpath = "*//div[contains(text(), 'НАПИСАТЬ')]")
    private WebElement newLetterButton;

//    @FindBy(css = "span.gb_db")
//    private WebElement userMenuButton;

//    @FindBy(css = "div.gb_Eb.gbip")
//    private WebElement userIcon;

//    @FindBy(linkText = "Выйти")
//    private WebElement logoutButton;

    public InboxMailPage() {
        super();
    }

    public String getPageTitle() {
        TestReporter.reportStep("Login completed");
        waitPageLoading(10, newLetterButton);
        return getTitle();
    }

    public void openLetterForm(){
        newLetterButton.click();
    }

//    public void openUserMenu() {
//        userMenuButton.click();
//        safeAlertDismiss();
//        TestReporter.reportDebugStep("Click user icon");
//        waitPageLoading(30, userIcon);
//    }

//    public void clickLogout() {
//        logoutButton.click();
//        safeAlertDismiss();
//        TestReporter.reportDebugStep("Clicked exit button");
//        driver.manage().deleteAllCookies();
//        driver.navigate().refresh();
//    }

//    private void safeAlertDismiss() {
//        try {
//            driver.switchTo().alert().dismiss();
//        } catch (NoAlertPresentException e) {
//            // ничего не делаем, алерта итак нет
//        }
//    }

}
