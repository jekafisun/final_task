package com.epam.project.automation;

import com.epam.project.model.helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import ru.stqa.selenium.factory.WebDriverPool;

public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPageHelper loginPageHelper;

    @BeforeMethod
    public void initTestSuite() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
    }

    @AfterSuite
    public void tearDownSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
