package hbs.schoolwide.StepDefs;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import hbs.schoolwide.pageobjects.HBSSecureLoginPageObject;
import hbs.schoolwide.utils.YamlReader;

public class Stepdefs_HBSSecureLoginPage {
	
	private WebDriver driver = CucumberHooks.driver;
	HBSSecureLoginPageObject loginPage = null;
	
	@Given("^I am on hbs secure login page$")
	public void I_am_on_Service_now_login_Page(){
		loginPage = PageFactory.initElements(driver, HBSSecureLoginPageObject.class);
		loginPage.open(YamlReader.getYamlValue("hbsLoginUrl"));
		loginPage.verifyUserIsOnHBSSecureLoginPage();
	}
	
	@When("^I login with valid credentials$")
	public void I_enter_valid_username_and_password() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		loginPage.enterCredentialsOnSignInForm(YamlReader.getYamlValue("username"), 
				YamlReader.getYamlValue("password"));
		loginPage.hitGoButton();
	}

}
