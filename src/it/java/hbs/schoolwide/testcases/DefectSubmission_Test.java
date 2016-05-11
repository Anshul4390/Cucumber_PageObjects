package hbs.schoolwide.testcases;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

//HBS imports
import hbs.itg.automation.dataproviders.*;
import hbs.itg.automation.lib.Browser;
import hbs.itg.automation.commonpageobjects.*;
import hbs.schoolwide.pageobjects.*;

public class DefectSubmission_Test {
	private WebDriver driver = null;

	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) throws Exception {
		Browser browser = new Browser(driver);
		driver = browser.start(context.getCurrentXmlTest().getParameter("browserUnderTest"));
	}

	@AfterMethod(alwaysRun = true) // This is where you do stuff AFTER every
									// test.
	public void postTestTasks(ITestResult testResult) {

		if (testResult.getStatus() == ITestResult.FAILURE) {
			BasePage util = PageFactory.initElements(driver, BasePage.class);

			util.captureScreenShot("Failure_" + testResult.getName());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test(groups = { "Smoke",
			"Integration" }, dataProviderClass = TestNGDataProviders.class, dataProvider = "excelDataProvider")
	@TestNGDataProviderArguments({ "fileName=Sample.xlsx", "sheetName=Login", "tableName=HBSLogin" })
	public void verifyEmptyDefectFormSubmissionError(Hashtable<String, String> TestData, ITestContext context)
			throws Exception {

		HBSSecureLoginPageObject login = PageFactory.initElements(driver, HBSSecureLoginPageObject.class);
		ServiceNowHomePageObject serviceNow = PageFactory.initElements(driver, ServiceNowHomePageObject.class);
		DefectManagementHomePageObject defectPage = PageFactory.initElements(driver,
				DefectManagementHomePageObject.class);
		login.open();
		login.enterCredentialsOnSignInForm(TestData.get("Username"), (TestData.get("Pwd")));
		login.hitGoButton();

		Assert.assertTrue(serviceNow.verifyUserIsOnServiceNowHomePage());
		serviceNow.enterTermIntoFilterBox(TestData.get("Filter"));

		Assert.assertEquals(serviceNow.verifyFilterSearch(), "Defect Overview",
				"Assertion Failed :: Defect Overview section does not appear after putting in 'Defect' as filter");
		Reporter.log("Assertion Passed :: Filtering with term '" + TestData.get("Filter") + "' is working fine", true);

		serviceNow.clickCreateNewLinkForDefect();
		Assert.assertEquals(defectPage.isUserOnDefectManagementPage(), TestData.get("Filter"),
				"Assertion Failed :: Defect management panel's heading is incorrect");
		Reporter.log("User is now successfully landed on Defect panel - Create New page", true);
		defectPage.clickSubmitButton();

		Assert.assertEquals(defectPage.getJSErrorMessageOnBlankSubmission(),
				TestData.get("JSErrorMessageForBlankSubmission"),
				"Assertion Failed :: The javascript error pop up shows incorrect error message");
		Reporter.log(
				"Assertion Passed :: Correct error message appears on the JS pop up on submitting blank defect form",
				true);

		defectPage.alertAccept();
		Reporter.log("Alert for blank defect submission form is accepted", true);

	}
	 
}
