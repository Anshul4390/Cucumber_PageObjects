package com.qait.hbs.StepDefs;


import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs_HBSSecureLoginPage {
	
	TestSessionInitiator test = CucumberHooks.test;
	
	@Given("^I am on hbs secure login page$")
	public void I_am_on_Service_now_login_Page(){
		test.hbsSecureLoginPage.verifyUserIsOnHBSSecureLoginPage(YamlReader.getYamlValue("loginPage.serviceNowSignInMessage"));
	}
	
	@When("^I login with valid credentials$")
	public void I_enter_valid_username_and_password(){
		test.hbsSecureLoginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("loginPage.validUsername"),
				YamlReader.getYamlValue("loginPage.validPassword"));
		test.hbsSecureLoginPage.hitGoButton();
	}

}
