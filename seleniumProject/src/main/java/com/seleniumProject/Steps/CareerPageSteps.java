package com.seleniumProject.Steps;

import com.seleniumProject.Locators.CareerPageLocators;
import com.seleniumProject.utils.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CareerPageSteps extends CareerPageLocators {

    public CareerPageSteps() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void checkCareersPageOpened() {
        waitUntilUrlContains("https://useinsider.com/careers");
        checkOurLocationsDisplayed();
        checkTeamsDisplayed();
        checkLifeAtInsiderBlockDisplayed();
    }

    public void checkOurLocationsDisplayed() {
        Assert.assertTrue(isDisplayElement(locationSlider), "Our locations slider not displayed");
        Assert.assertEquals(getTextElement(tittleOurLocations), "Our Locations", "Our locations title not match!");
    }

    public void checkTeamsDisplayed() {
        Assert.assertTrue(isDisplayElement(imgJobTeams), "Job teams not displayed!");
    }

    public void checkLifeAtInsiderBlockDisplayed() {
        Assert.assertTrue(isDisplayElement(titleLifeAtInsider), "Lif at insider block not diplayed!");
    }

    public void seeAllTeamsClick() {
        clickMethod(btnSeeMoreTeams, "see all teams click");
    }

    public void goToQAPositions() {
        clickMethod(btnQAPositions, "QA teams click");
    }


}
