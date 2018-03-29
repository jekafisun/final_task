package com.epam.project.automation;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.core.listener.CustomTestListener;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.stqa.selenium.factory.WebDriverPool;

@Listeners(CustomTestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPageHelper loginPageHelper;
    protected InboxMailPageHelper inboxMailPageHelper;

    @BeforeMethod(description = "Creating WebDriver")
    public void initTestSuite() {

        if ("firefox".equalsIgnoreCase(PropertiesData.GLOBAL.browser())) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver=WebDriverPool.DEFAULT.getDriver(PropertiesData.GLOBAL.browser());
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
            System.setProperty("webdriver.chrome.verboseLogging", "true");
            driver=WebDriverPool.DEFAULT.getDriver(PropertiesData.GLOBAL.browser());
        }

    }

    @AfterSuite(description = "WebDriver closed")
    public void tearDownSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
