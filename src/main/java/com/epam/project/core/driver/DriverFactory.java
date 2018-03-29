package com.epam.project.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

    private static final String PATH_TO_CHROME_DRIVER = "src/main/resources/drivers/chromedriver.exe";
    private static final String PATH_TO_FIREFOX_DRIVER = "src/main/resources/drivers/geckodriver.exe";

    private DriverFactory() {
        throw new UnsupportedOperationException("This is an utility class, access denied");
    }

    public static WebDriver setUpDriver(String browserName) {
        if ("firefox".equals(browserName)) {
            System.setProperty(FIREFOX_DRIVER_PROPERTY, PATH_TO_FIREFOX_DRIVER);
            return new FirefoxDriver();
        } else {
            System.setProperty(CHROME_DRIVER_PROPERTY, PATH_TO_CHROME_DRIVER);
            return new ChromeDriver();
        }
    }

}
