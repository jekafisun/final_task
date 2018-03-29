package com.epam.project.automation;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.core.driver.DriverFactory;
import com.epam.project.core.listener.CustomTestListener;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(CustomTestListener.class)
public abstract class BaseTest {
    private static final int TIMEOUT = 5;
    protected WebDriver driver;
    protected LoginPageHelper loginPageHelper;
    protected InboxMailPageHelper inboxMailPageHelper;

    @BeforeClass(description = "Creating WebDriver")
    public void setupTest() {

        driver = DriverFactory.setUpDriver(PropertiesData.GLOBAL.browser());
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
//        if ("firefox".equalsIgnoreCase(PropertiesData.GLOBAL.browser())) {
//            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
//            driver = new FirefoxDriver();
//        } else {
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
//            driver = new ChromeDriver();
//        }
    }

    @AfterClass(description = "Closing WebDriver")
    public void tearDown() {
        driver.quit();
    }
}
