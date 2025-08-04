package com.seleniumProject.Locators;

import com.seleniumProject.utils.GlobalMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QADepartmentPageLoators extends GlobalMethods {

    @FindBy(css = "div[class='button-group d-flex flex-row']>a")
    public static WebElement btnSeeAllQAJobs;

    @FindBy(css = "span[data-select2-id='1']")
    public static WebElement cityList;

    @FindBy(xpath = "//li[contains(@id,'select2-filter-by-location-result') and contains(.,'Istanbul, Turkiye')]")
    public static WebElement dropdownIstanbulTr;

    @FindBy(id = "select2-filter-by-department-container")
    public static WebElement DepartmentDropdown;

    @FindBy(xpath = "//p[contains(@class,'position-title')]")
    public static WebElement positionTitle;

    @FindBy(xpath = "//span[contains(@class,'position-department')]")
    public static List<WebElement> positionDepartmentList;

    @FindBy(xpath = "//div[contains(@class,'position-location')]")
    public static List<WebElement> positionLocationList;

    @FindBy(css = "span[class='totalResult']")
    public static WebElement resultCount;

    @FindBy(css = "div[class*='position-list-item']>a")
    public static WebElement btnViewRole;

    @FindBy(css = "div[class='posting-headline']>h2")
    public static WebElement jobTitleViewRolePage;

    @FindBy(css = "a[class='postings-btn template-btn-submit shamrock']")
    public static WebElement btnApplyThisJob;

}
