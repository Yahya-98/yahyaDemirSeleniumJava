package com.seleniumProject.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.seleniumProject.utils.*;
import lombok.Getter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class BaseTest {
    @Getter
    protected WebDriver driver;
    private static final ExtentReports extent = ReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final Map<String, ExtentTest> parentMap = new ConcurrentHashMap<>();
    protected ConfigReader config;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(Method method, String browser) {
        config = new ConfigReader();

        this.driver = DriverFactory.setDriver(browser, config.getBrowserHeadless());
        driver.get(config.getBaseUrl());

        String className = this.getClass().getSimpleName();

        Test testAnnotation = method.getAnnotation(Test.class);
        String description = testAnnotation != null && !testAnnotation.description().isEmpty()
                ? testAnnotation.description()
                : method.getName();
        parentMap.computeIfAbsent(className, name -> extent.createTest(name));

        ExtentTest child = parentMap.get(className).createNode(description);
        testThread.set(child);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        ExtentTest test = testThread.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            getScreenShot();
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Passed");
        } else {
            test.skip("Skipped: " + result.getThrowable());
        }

        DriverFactory.quitDriver();
        testThread.remove();
    }


    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

    protected static ExtentTest getTest() {
        return testThread.get();
    }

    private void getScreenShot() {
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
    }
}
