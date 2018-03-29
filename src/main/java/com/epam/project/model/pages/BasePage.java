package com.epam.project.model.pages;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.core.reporter.TestReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.util.concurrent.TimeUnit;

public abstract class BasePage<PAGE extends BasePage<PAGE>> {
    //    private String pageURL;
    protected WebDriver driver;

    public BasePage() {
        if ("firefox".equalsIgnoreCase(PropertiesData.GLOBAL.browser())) {
            driver = WebDriverPool.DEFAULT.getDriver("firefox");
         } else {
            driver = WebDriverPool.DEFAULT.getDriver("chrome");

        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
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

    /**
     * Wait page loading.
     * Loading locator use from constructor <b>pageLoadingLocator</b> argument.
     *
     * @param time wait time
     * @return current page instance
     */
    public PAGE waitPageLoading(long time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        TestReporter.reportDebugStep("Wait loading %s page", this.getClass().getSimpleName());
        wait.until(ExpectedConditions.elementToBeClickable(webElement));

        return (PAGE) this;
    }


    public String getTitle() {
        return driver.getTitle();
    }
}
