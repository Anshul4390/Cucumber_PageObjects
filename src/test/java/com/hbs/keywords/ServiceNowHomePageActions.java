package com.hbs.keywords;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class ServiceNowHomePageActions extends GetPage{

	private String searchTerm = null;
	public ServiceNowHomePageActions(WebDriver driver) {
		super(driver, "ServiceNowHomePage");
	}
	
	public void verifyUserIsOnServiceNowHomePage(){
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("iframe_filter");
		isElementDisplayed("txt_loggedIn");
		verifyPageTitleExact();
		logMessage("User is on service now home page");
	}
	
	public void enterTermIntoFilterBox(String term){
		wait.waitForPageToLoadCompletely();
		switchToFrame(element("iframe_mainContent"));
		isElementDisplayed("txt_mainHeading");
		isElementDisplayed("btn_add");
		switchToDefaultContent();
		searchTerm = term;
		switchToFrame(element("iframe_filter"));
		isElementDisplayed("inp_searchFilter");
		wait.hardWait(1);
		element("inp_searchFilter").sendKeys(searchTerm+Keys.RETURN);
		logMessage("User entered '"+term+"' in the filter search box and pressed enter");
	}
	
	public void verifyFilterSearch(){
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("list_openedPanel",searchTerm);
		isElementDisplayed("lnk_defectPanel","Create New");
		switchToDefaultContent();
		wait.hardWait(1);
		switchToFrame(element("iframe_mainContent"));
		isElementDisplayed("inp_mainContentHeading");
		Assert.assertTrue("Assertion Failed :: Defect Overview section does not appear after putting in 'Defect' as filter", 
				element("inp_mainContentHeading").getAttribute("value").trim().equals("Defect Overview"));
		switchToDefaultContent();
		logMessage("Assertion Passed :: Filtering with term '"+searchTerm+"' is working fine");
	}
	
	public void clickCreateNewLinkForDefect(){
		switchToDefaultContent();
		switchToFrame(element("iframe_filter"));
		isElementDisplayed("lnk_defectPanel","Create New");
		element("lnk_defectPanel","Create New").click();
		wait.waitForPageToLoadCompletely();
		switchToDefaultContent();
	}
}
