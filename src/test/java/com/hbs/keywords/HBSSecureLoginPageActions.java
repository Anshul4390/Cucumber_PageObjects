package com.hbs.keywords;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class HBSSecureLoginPageActions extends GetPage{

	public HBSSecureLoginPageActions(WebDriver driver) {
		super(driver, "HBSSecureLoginPage");
	}
	
	public void verifyUserIsOnHBSSecureLoginPage(String signInMessage){
		isElementDisplayed("inp_password");
		isElementDisplayed("inp_username");
		Assert.assertTrue("Assertion Failed :: Sign in message on Service Now login page appears to be incorrect", element("txt_signInMessage").getText().trim().equals(signInMessage));
		logMessage("User is on service now login page");
	}
	

	public void enterCredentialsOnSignInForm(String username, String password) {
		wait.waitForPageToLoadCompletely();
		element("inp_username").clear();
		element("inp_username").sendKeys(username);
		element("inp_password").clear();
		element("inp_password").sendKeys(password);
		logMessage("User has entered credentials for the user '"+username+"'");
	}
	
	public void hitGoButton(){
		element("btn_go").click();
		logMessage("User clicked on Go button");
	}
}
