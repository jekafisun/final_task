package com.epam.project.core.driver;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import org.openqa.selenium.WebDriver;

public class DriverProvider {
    private DriverProvider(){
        throw new UnsupportedOperationException("Util class. Access denied");
    }
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getInstance(){
        WebDriver instance = webDriverThreadLocal.get();

        if (instance==null){
            instance = DriverFactory.setUpDriver(PropertiesData.GLOBAL.browser());
            webDriverThreadLocal.set(instance);
        }

        return instance;
    }

    public static void kill(){
        webDriverThreadLocal.remove();
    }


}
