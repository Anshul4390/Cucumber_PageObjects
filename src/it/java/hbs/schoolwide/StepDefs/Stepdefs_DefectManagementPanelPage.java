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
import hbs.schoolwide.pageobjects.DefectManagementHomePageObject;
import hbs.schoolwide.pageobjects.HBSSecureLoginPageObject;
import hbs.schoolwide.pageobjects.ServiceNowHomePageObject;
import hbs.schoolwide.utils.YamlReader;

public class Stepdefs_DefectManagementPanelPage {
	private WebDriver driver = CucumberHooks.getDriver();
	HBSSecureLoginPageObject loginPage = null;
	ServiceNowHomePageObject serviceNow = null;
	DefectManagementHomePageObject defect = null;
	
	@Then("^I see a new Defect form opens in the right pane enabling the user to enter a defect$")
	public void I_see_a_new_Defect_form_opens_in_the_right_pane_enabling_the_user_to_enter_a_defect(){
		defect = PageFactory.initElements(driver, DefectManagementHomePageObject.class);
		defect.verifyUserIsOnDefectManagementPage();
	}
	
	@Given("^The defect creation form is open in the default state$")
	public void The_defect_creation_form_is_open_in_the_default_state() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		loginPage = PageFactory.initElements(driver, HBSSecureLoginPageObject.class);
		loginPage.open(YamlReader.getYamlValue("hbsLoginUrl"));
		loginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("username"), 
				YamlReader.getYamlValue("password"));
		loginPage.hitGoButton();
		serviceNow = PageFactory.initElements(driver, ServiceNowHomePageObject.class);
		serviceNow.enterTermIntoFilterBox("Defect");
		serviceNow.clickCreateNewLinkForDefect();
		defect = PageFactory.initElements(driver, DefectManagementHomePageObject.class);
		defect.verifyUserIsOnDefectManagementPage();
	}
	
	@When("^I click on the Submit Button$")
	public void i_click_on_the_Submit_Button_Button() throws Throwable {
		defect.clickSubmitButton();
	}
	
	
	@Then("^I observe that an error message pop-up window appears with the following text: (.*)$")
	public void  I_observe_Error_Message_On_Blank_Defect_Form_Submission(String actualMessage){
		defect.verifyJSErrorMessageOnBlankSubmission(actualMessage);
	}
	
	
}
