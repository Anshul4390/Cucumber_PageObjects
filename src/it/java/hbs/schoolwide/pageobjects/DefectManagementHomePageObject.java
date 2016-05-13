package hbs.schoolwide.pageobjects;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import hbs.itg.automation.commonpageobjects.BasePage;
import hbs.itg.automation.lib.Utils;

public class DefectManagementHomePageObject extends BasePage{

	private WebDriver driver;

	Utils util = new Utils(this.driver);

	@FindBy(id = "gsft_main")
	WebElement iframe_mainWindow;

	@FindBy(css = "h2[class='form_header navbar-title']")
	WebElement txt_mainHeading;

	@FindBy(css = ".active>a")
	WebElement lnk_activeTab;

	@FindBy(css = ".navbar_ui_actions>#sysverb_insert")
	WebElement btn_submit;
	
	@FindBy(id="u_hbs_defect.short_description")
	WebElement inp_shortDescription;
	

	public DefectManagementHomePageObject(WebDriver driver) throws InterruptedException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
	}
	
	public void verifyUserIsOnDefectManagementPage(){
		WaitForAjaxElement(iframe_mainWindow,30);
		switchToIFrame(iframe_mainWindow);
		Assert.assertTrue(txt_mainHeading.getText().trim().equals("Defect"),
				"Assertion Failed :: Defect management panel's heading is incorrect");
//		Assert.assertTrue(lnk_activeTab.getText().trim().equals("Submitted"),
//				"Assertion Failed :: The default active tab is not 'Submitted'");
		System.out.println("User is now successfully landed on Defect panel - Create New page");
	}
	
	public void clickSubmitButton(){
		try{
			Thread.sleep(1000);
		}catch(InterruptedException ex){
			System.out.println("Problems in putting up with static wait");
		}
		click(btn_submit);
		System.out.println("User clicked on Submit button for creating a defect");
	}
	
	public void verifyJSErrorMessageOnBlankSubmission(String expectedErrorMessage){
		try{
			Thread.sleep(1000);
		}catch(InterruptedException ex){
			System.out.println("Problems in putting up with static wait");
		}
		
		String actualMessage = this.driver.switchTo().alert().getText();
		Assert.assertTrue(actualMessage.equals(expectedErrorMessage),
				"Assertion Failed :: The javascript error pop up shows incorrect error message");
		System.out.println("Assertion Passed :: Correct error message appears on the JS pop up on submitting blank defect form");
		alertAccept();
	}

	
}
