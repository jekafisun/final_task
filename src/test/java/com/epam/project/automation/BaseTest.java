package com.epam.project.automation;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.core.listener.CustomTestListener;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.stqa.selenium.factory.WebDriverPool;

@Listeners(CustomTestListener.class)
public abstract class BaseTest {
    protected LoginPageHelper loginPageHelper;
    protected InboxMailPageHelper inboxMailPageHelper;

    @BeforeMethod(description = "Creating WebDriver")
    public void initTestSuite() {
        if ("firefox".equalsIgnoreCase(PropertiesData.GLOBAL.browser())) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        }

    }

    @AfterSuite(description = "WebDriver closed")
    public void tearDownSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
