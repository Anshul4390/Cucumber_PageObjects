package com.qait.hbs.StepDefs;


import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs_ServiceNowHomePage {
	
	TestSessionInitiator test = CucumberHooks.test;
	
	@Given("^I am on service now home page$")
	public void I_am_on_service_now_page(){
		test.hbsSecureLoginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("loginPage.validUsername"),
				YamlReader.getYamlValue("loginPage.validPassword"));
		test.hbsSecureLoginPage.hitGoButton();
		test.serviceNowHomePage.verifyUserIsOnServiceNowHomePage();
	}
	
	@Then("^I land on Service now home page$")
	public void I_land_on_service_now_home_page(){
		test.serviceNowHomePage.verifyUserIsOnServiceNowHomePage();
	}
	
	@When("^I type the term (.*) in filter text box$")
	public void I_type_the_term_Defect_in_filter_text_box(String term){
		test.serviceNowHomePage.enterTermIntoFilterBox(term);
	}
	
	@Then("^I see (.*) module and other links inside the left pane window$")
	public void I_see_Defect_module_and_other_links_inside_the_left_pane_window(String module){
		test.serviceNowHomePage.verifyFilterSearch();
	}
	
	@When("^I click on Create New link in the left navigation menu$")
	public void I_click_on_Create_New_link_in_the_left_navigation_menu(){
		test.serviceNowHomePage.clickCreateNewLinkForDefect();
	}
	
}
