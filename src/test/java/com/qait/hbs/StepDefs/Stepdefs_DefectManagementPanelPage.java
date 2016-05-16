package com.qait.hbs.StepDefs;


import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs_DefectManagementPanelPage {
	
	TestSessionInitiator test = CucumberHooks.test;
	
	@Then("^I see a new Defect form opens in the right pane enabling the user to enter a defect$")
	public void I_see_a_new_Defect_form_opens_in_the_right_pane_enabling_the_user_to_enter_a_defect(){
		test.defectPage.verifyUserIsOnDefectManagementPage();
	}
	
	@Given("^The defect creation form is open in the default state$")
	public void The_defect_creation_form_is_open_in_the_default_state(){
		test.hbsSecureLoginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("loginPage.validUsername"),
				YamlReader.getYamlValue("loginPage.validPassword"));
		test.hbsSecureLoginPage.hitGoButton();
		test.serviceNowHomePage.verifyUserIsOnServiceNowHomePage();
		test.serviceNowHomePage.enterTermIntoFilterBox("Defect");
		test.serviceNowHomePage.clickCreateNewLinkForDefect();
		test.defectPage.verifyUserIsOnDefectManagementPage();
		test.defectPage.verifyDefectPanelIsOpenWithSubmittedTabActive();
	}
	
	@When("^I click on the (.*) Button$")
	public void I_click_on_the_Submit_Button_Button(String button) throws Throwable {
		test.defectPage.clickSubmitButton(button);
	}
	
	
	@Then("^I observe that an error message pop-up window appears with the following text: (.*)$")
	public void  I_observe_Error_Message_On_Blank_Defect_Form_Submission(String actualMessage){
		test.defectPage.verifyJSErrorMessageOnBlankSubmission(actualMessage);
	}
	
	
}
