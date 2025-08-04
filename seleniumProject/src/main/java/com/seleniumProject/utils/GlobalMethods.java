package com.seleniumProject.utils;

import com.seleniumProject.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GlobalMethods extends BaseTest {

    public WebDriver driver = DriverFactory.getDriver();
    Actions action = new Actions(driver);
    Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(java.time.Duration.ofSeconds(5))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);


    public WebElement waitClickableElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Log.info("Web element is not clickable!");

        }
        return element;
    }

    public WebElement waitVisibleElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Log.info("Web element is not visible!");
        }
        return element;
    }

    public List<WebElement> waitVisibleElement(List<WebElement> element) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (Exception e) {
            Log.warning("Web element is not visible!");
        }
        return element;
    }

    public void waitUntilUrlContains(String expectedUrl) {
        try {
            wait.until(ExpectedConditions.urlContains(expectedUrl));
        } catch (Exception e) {
            getScreenShot();
            Log.warning("urls not contains! Actual url: " + driver.getCurrentUrl() + " Expected url: " + expectedUrl);
        }
    }

    public void waitMSC(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitUntilStringChanged(WebElement element, String oldText) {
        for (int i = 1; i < 21; i++) {
            if (getTextElement(element).equals(oldText)) {
                Log.info(i + " second wait for text changed");
                waitMSC(1000);
            } else {
                break;
            }
        }
    }

    public void waitUntilAttributeContains(WebElement element, String attribute, String expectedAttributeValue) {
        wait.until(ExpectedConditions.attributeContains(element, attribute, expectedAttributeValue));
    }

    public void scrollToElementWithAction(WebElement element) {
        try {
            Log.info("Scrolling to element.");
            action.scrollToElement(element).perform();
        } catch (Exception e) {
            Log.info("Error to scrolling");
        }
    }

    public void scrollElementWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    public void clickWithJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ignored) {
        }
    }

    public void clickMethod(WebElement element, String logMessage) {
        waitClickableElement(element);
        waitVisibleElement(element);
        Log.info(logMessage);
        scrollToElementWithAction(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("click with js");
            clickWithJS(element);
        }
    }

    public boolean isDisplayElement(WebElement element) {
        waitVisibleElement(element);
        return element.isDisplayed();
    }

    public void navigateToUrl(String url) {
        Log.info("go to " + url);
        driver.get(url);
    }


    public void getScreenShot() {
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
    }

    public void hoverElement(WebElement webElement, int second, String logMessage) {
        Log.info(logMessage);
        action.moveToElement(webElement).pause(Duration.ofSeconds(second)).perform();
    }

    public String getTextElement(WebElement element) {
        String text = waitVisibleElement(element).getText();
        Assert.assertNotNull(text, "Element text is null!");
        return element.getText();
    }

    public List<String> getTextElement(List<WebElement> element) {
        waitVisibleElement(element);
        List<String> textList = new ArrayList<>();
        for (WebElement webElement : element) {
            textList.add(webElement.getText());
        }
        Assert.assertNotNull(textList, "Element text is null!");
        return textList;
    }

    public boolean containsList(List<String> stringList, String expectedContainsKey) {
        boolean isContains = false;
        for (String s : stringList) {
            if (s.contains(expectedContainsKey)) {
                isContains = true;
            } else {
                return false;
            }
        }
        return isContains;
    }

    public void switchToOtherTab() {
        String currentTab = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        String newTab = null;
        for (String handle : handles) {
            if (!handle.equals(currentTab)) {
                newTab = handle;
                break;
            }
        }
        driver.switchTo().window(newTab);
    }
}
