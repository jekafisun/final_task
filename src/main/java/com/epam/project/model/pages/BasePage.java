package com.epam.project.model.pages;

import com.epam.project.core.reporter.TestReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage<P extends BasePage<P>> {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait page loading.
     * Loading locator use from constructor <b>pageLoadingLocator</b> argument.
     *
     * @param time wait time
     * @return current page instance
     */
    protected P waitPageLoading(long time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        TestReporter.reportDebugStep("Wait loading %s page", this.getClass().getSimpleName());
        wait.until(ExpectedConditions.elementToBeClickable(webElement));

        return (P) this;
    }


    public String getTitle() {
        return driver.getTitle();
    }
}
