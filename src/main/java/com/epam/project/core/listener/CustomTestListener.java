package com.epam.project.core.listener;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.stqa.selenium.factory.WebDriverPool;

import static com.epam.project.core.reporter.TestReporter.reportErrorStep;
import static com.epam.project.core.reporter.TestReporter.reportStep;

public class CustomTestListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        reportStep("Test class started: %s", result.getTestClass().getName());
        reportStep("Test started: %s" , result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        makeScreenshot();
        reportStep("Test SUCCESS: %s" , result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
        reportStep("Test FAILED: %s", result.getName());
        if (result.getThrowable()!=null) {
            reportErrorStep(result.getThrowable().getMessage());
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverPool.DEFAULT.getDriver(PropertiesData.GLOBAL.browser()))
                .getScreenshotAs(OutputType.BYTES);
    }
}
