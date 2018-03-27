package com.epam.project.model.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
//    private String pageURL;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
//        this.pageURL = pageURL;
    }

//    public BasePage openPage() {
//        if (StringUtils.isNotEmpty(pageURL)) {
//            TestReporter.reportStep("Page %s was opened", this.getClass().getSimpleName());
//            driver.get(pageURL);
//        } else {
//            throw new UnsupportedOperationException("Page URL not established");
//        }
//        return (BasePage) this;
//    }



    public String getTitle(){
        return driver.getTitle();
    }
}
