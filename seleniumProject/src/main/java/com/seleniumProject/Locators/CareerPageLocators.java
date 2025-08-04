package com.seleniumProject.Locators;

import com.seleniumProject.utils.GlobalMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class CareerPageLocators extends GlobalMethods {


    @FindBy(id = "location-slider")
    public static WebElement locationSlider;

    @FindBy(xpath = "//div[@id='location-slider']/parent::div/parent::div//h3")
    public static WebElement tittleOurLocations;

    @FindBy(xpath = "//div[contains(@class,'job-image')]")
    public static WebElement imgJobTeams;

    @FindBy(xpath = "//h2[@class='elementor-heading-title elementor-size-default' and contains(.,'Life at Insider')]")
    public static WebElement titleLifeAtInsider;

    @FindBy(xpath = "//a[contains(@class,'btn btn-outline') and contains(.,'See all teams')]")
    public static WebElement btnSeeMoreTeams;

    @FindBy(xpath = "//h3[contains(@class,'text-center') and contains(.,'Quality Assurance')]")
    public static WebElement btnQAPositions;
}
