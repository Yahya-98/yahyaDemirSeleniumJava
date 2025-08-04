package com.seleniumProject.Steps;

import com.seleniumProject.Locators.QADepartmentPageLoators;
import com.seleniumProject.utils.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class QADepartmentsSteps extends QADepartmentPageLoators {

    public QADepartmentsSteps() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    String tmpString;

    public void seeAllQAJobsClick() {
        clickMethod(btnSeeAllQAJobs, "see all qa jobs click");
        waitVisibleElement(positionTitle);
        waitUntilAttributeContains(DepartmentDropdown, "title", "Quality Assurance");
        tmpString = getTextElement(resultCount);
    }

    public void openCityList() {
        clickMethod(cityList, "city list");
    }

    public void selectIstanbulTr() {
        clickMethod(dropdownIstanbulTr, "select in dropdown Istanbul, Turkey");
    }

    public void checkDropdownDepartment() {
        softAssert(getTextElement(DepartmentDropdown), "×\nQuality Assurance",
                "Selected department not QA");
    }

    public void checkJobListFiltered() {
        waitUntilStringChanged(resultCount, tmpString);
        scrollElementWithJS(positionLocationList.get(0));
        List<String> actualList = getTextElement(positionDepartmentList);
        softAssert(containsList(actualList, "Quality Assurance")
                , "Jobs department not filtered! Actual List: " + actualList);
        actualList = getTextElement(positionLocationList);
        softAssert(containsList(actualList, "Istanbul, Turkiye")
                , "Jobs location not filtered! Actual List: " + actualList);

    }

    public void viewRoleClick() {
        tmpString = getTextElement(positionTitle);
        scrollElementWithJS(positionLocationList.get(0));
        hoverElement(btnViewRole, 2, "Hovering view role");
        clickMethod(btnViewRole, "view role click");
        switchToOtherTab();
    }

    public void checkViewRolePageOpened() {
        softAssert(getTextElement(jobTitleViewRolePage), tmpString, "Job title not match in view role page!");
        Assert.assertEquals(getTextElement(btnApplyThisJob), "APPLY FOR THIS JOB"
                , "Apply for this job button text don't match");
    }

}
