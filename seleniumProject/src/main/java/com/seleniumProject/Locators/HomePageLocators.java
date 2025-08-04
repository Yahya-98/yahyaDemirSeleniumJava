package com.seleniumProject.Locators;

import com.seleniumProject.utils.GlobalMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePageLocators extends GlobalMethods {

    @FindBy(css = "img[alt='insider_logo']")
    public static WebElement imgInsiderLogo;

    @FindBy(xpath = "//a[@id='navbarDropdownMenuLink' and contains(.,'Company')]")
    public static WebElement navBarBtnCompany;

    @FindBy(xpath = "//a[@class='dropdown-sub' and contains(.,'Careers')]")
    public static WebElement navBarBtnCareers;


}
