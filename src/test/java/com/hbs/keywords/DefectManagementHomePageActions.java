package com.hbs.keywords;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class DefectManagementHomePageActions extends GetPage{

	private String searchTerm = null;
	public DefectManagementHomePageActions(WebDriver driver) {
		super(driver, "DefectManagementHomePage");
	}
	
	public void verifyUserIsOnDefectManagementPage(){
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("iframe_mainWindow");
		switchToFrame(element("iframe_mainWindow"));
		Assert.assertTrue("Assertion Failed :: Defect management panel's heading is incorrect",
				element("txt_mainHeading").getText().trim().equals("Defect"));
		Assert.assertTrue("Assertion Failed :: The default active tab is not 'Submitted'", 
				element("lnk_activeTab").getText().trim().equals("Submitted"));
		logMessage("User is now successfully landed on Defect panel - Create New page");
	}
	
	public void clickSubmitButton(String buttonName) {
		wait.hardWait(1);
		if (buttonName.equals("Submit")) {
			element("btn_submit").click();
			logMessage("User clicked on Submit button for creating a defect");
		} else {
			logMessage("Incorrect button label mentioned in test data");
		}
	}
	
	public void verifyJSErrorMessageOnBlankSubmission(String expectedErrorMessage){
		
		wait.hardWait(1);
		String actualMessage = getAlertText();
		Assert.assertTrue("Assertion Failed :: The javascript error pop up shows incorrect error message",
				actualMessage.contains(expectedErrorMessage));
		logMessage("Assertion Passed :: Correct error message appears on the JS pop up on submitting blank defect form");
		wait.hardWait(1);
		handleAlert();
		wait.hardWait(1);		
		
	}

	public void verifyDefectPanelIsOpenWithSubmittedTabActive() {
		Assert.assertTrue("Assertion Failed :: The default active tab is not 'Submitted'", 
				element("lnk_activeTab").getText().trim().equals("Submitted"));
		logMessage("'Submitted' tab is active by default on defect management panel - Create New page");
	}
	
}
