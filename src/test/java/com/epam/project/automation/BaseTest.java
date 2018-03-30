package com.epam.project.automation;

import com.epam.project.core.driver.DriverProvider;
import com.epam.project.core.listener.CustomTestListener;
import com.epam.project.model.helpers.InboxMailPageHelper;
import com.epam.project.model.helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(CustomTestListener.class)
public abstract class BaseTest {

    private static final int TIMEOUT = 5;
    protected WebDriver driver;
    protected LoginPageHelper loginPageHelper;
    protected InboxMailPageHelper inboxMailPageHelper;

    @BeforeMethod(description = "Creating WebDriver")
    public void setupTest() {
        driver = DriverProvider.getInstance();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterClass(description = "Closing WebDriver")
    public void tearDown() {
        driver.quit();
        DriverProvider.kill();
    }
}
