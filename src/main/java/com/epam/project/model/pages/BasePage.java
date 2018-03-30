package com.epam.project.model.pages;

import com.epam.project.core.reporter.TestReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage<P extends BasePage> {

    private static final int IMPLICITY_WAIT_TIMEOUT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 60;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait page loading.
     * Loading locator use from constructor <b>pageLoadingLocator</b> argument.
     *
     * @param time wait time
     * @return current page instance
     */
    protected P waitElementIsVisible(long time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        TestReporter.reportDebugStep("Wait loading %s page", this.getClass().getSimpleName());

        return (P) this;
    }

    protected P waitElementDisappear(long time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        TestReporter.reportDebugStep("Wait loading %s page", this.getClass().getSimpleName());
        wait.until(ExpectedConditions.invisibilityOf(webElement));

        return (P) this;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
