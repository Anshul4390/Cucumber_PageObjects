package hbs.schoolwide.StepDefs;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hbs.schoolwide.pageobjects.HBSSecureLoginPageObject;
import hbs.schoolwide.pageobjects.ServiceNowHomePageObject;
import hbs.schoolwide.utils.YamlReader;

public class Stepdefs_ServiceNowHomePage {
	private WebDriver driver = CucumberHooks.getDriver();
	ServiceNowHomePageObject serviceNow = null;
	HBSSecureLoginPageObject loginPage = null;
	
	@Given("^I am on service now home page$")
	public void I_am_on_service_now_page() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		loginPage = PageFactory.initElements(driver, HBSSecureLoginPageObject.class);
		loginPage.open(YamlReader.getYamlValue("hbsLoginUrl"));
		loginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("username"), 
				YamlReader.getYamlValue("password"));
		loginPage.hitGoButton();
		serviceNow = PageFactory.initElements(driver, ServiceNowHomePageObject.class);
		serviceNow.verifyUserIsOnServiceNowHomePage();
	}
	
	@Then("^I land on Service now home page$")
	public void I_land_on_service_now_home_page(){
		serviceNow = PageFactory.initElements(driver, ServiceNowHomePageObject.class);
		serviceNow.verifyUserIsOnServiceNowHomePage();
	}
	
	@When("^I type the term (.*) in filter text box$")
	public void I_type_the_term_Defect_in_filter_text_box(String term){
		serviceNow.enterTermIntoFilterBox(term);
	}
	
	@Then("^I see Defect module and other links inside the left pane window$")
	public void I_see_Defect_module_and_other_links_inside_the_left_pane_window(){
		serviceNow.verifyFilterSearch();
	}
	
	@When("^I click on Create New link in the left navigation menu$")
	public void I_click_on_Create_New_link_in_the_left_navigation_menu(){
		serviceNow.clickCreateNewLinkForDefect();
	}
	
}
