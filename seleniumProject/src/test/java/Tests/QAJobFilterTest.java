package Tests;


import com.seleniumProject.Steps.CareerPageSteps;
import com.seleniumProject.Steps.HomePageSteps;
import com.seleniumProject.Steps.QADepartmentsSteps;
import com.seleniumProject.base.BaseTest;

import com.seleniumProject.utils.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class QAJobFilterTest extends BaseTest {

    HomePageSteps homePageSteps;
    CareerPageSteps careerPageSteps;
    QADepartmentsSteps qaDepartmentsSteps;

    @BeforeMethod
    public void beforeMethod() {
        homePageSteps = new HomePageSteps();
        careerPageSteps = new CareerPageSteps();
        qaDepartmentsSteps = new QADepartmentsSteps();
    }

    @Test(description = "QA Jobs Filter Case")
    public void case001() {
        homePageSteps.navigateToInsiderHomePage();
        homePageSteps.checkHomePageOpened();
        homePageSteps.hoverToCompanyInNavBar();
        homePageSteps.navbarCareersSelect();
        careerPageSteps.checkCareersPageOpened();
        careerPageSteps.seeAllTeamsClick();
        careerPageSteps.goToQAPositions();
        qaDepartmentsSteps.seeAllQAJobsClick();
        qaDepartmentsSteps.openCityList();
        qaDepartmentsSteps.selectIstanbulTr();
        qaDepartmentsSteps.checkDropdownDepartment();
        qaDepartmentsSteps.checkJobListFiltered();
        qaDepartmentsSteps.viewRoleClick();
        qaDepartmentsSteps.checkViewRolePageOpened();

    }


}
