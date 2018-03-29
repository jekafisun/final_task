package com.epam.project.automation;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.core.listener.CustomTestListener;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(CustomTestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPageHelper loginPageHelper;
    protected InboxMailPageHelper inboxMailPageHelper;

    @BeforeMethod(description = "Creating WebDriver")
    public void setupTest() {

        if ("firefox".equalsIgnoreCase(PropertiesData.GLOBAL.browser())) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

    @AfterMethod(description = "Closing WebDriver")
    public void tearDown() {
        driver.quit();
    }
}
