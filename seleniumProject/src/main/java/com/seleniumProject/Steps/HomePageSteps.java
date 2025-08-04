package com.seleniumProject.Steps;

import com.seleniumProject.Locators.HomePageLocators;
import com.seleniumProject.utils.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class HomePageSteps extends HomePageLocators {

    public HomePageSteps() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void navigateToInsiderHomePage() {
        navigateToUrl("https://useinsider.com/");
        waitUntilUrlContains("https://useinsider.com/");
    }

    public void checkHomePageOpened() {
        softAssert(isDisplayElement(imgInsiderLogo), "Insider logo not display in homepage");
    }

    public void hoverToCompanyInNavBar() {
        hoverElement(navBarBtnCompany, 2, "hover to navbar company option");
    }

    public void navbarCareersSelect() {
        clickMethod(navBarBtnCareers, "navbar careers click");
    }

    public void acceptCookies(){
        clickMethod(acceptCookies,"Accept All click");
    }

}
