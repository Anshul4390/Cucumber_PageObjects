package com.hbs.keywords;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class HBSSecureHomePageActions extends GetPage{

	public HBSSecureHomePageActions(WebDriver driver) {
		super(driver, "HBSSecureHomePage");
	}
	
	public void verifyUserIsOnHBSSecureHomePage(){
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lnk_myHBS");
		isElementDisplayed("txt_loggedInMessage");
		logMessage("User is on secure HBS home page");
	}
	
	public void navigateToServiceNowPage(String url){
		wait.waitForPageToLoadCompletely();
		launchApplication(url);
	}
	
}
