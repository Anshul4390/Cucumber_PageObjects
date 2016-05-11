package hbs.schoolwide.pageobjects;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import hbs.itg.automation.commonpageobjects.BasePage;
import hbs.itg.automation.lib.Encryptor;
import hbs.itg.automation.lib.Utils;

public class HBSSecureLoginPageObject extends BasePage {

	private WebDriver driver;

	Utils util = new Utils(this.driver);

	private String pageURL = this.util.getProperty("hbsLoginUrl");
	private Encryptor encryption;

	@FindBy(id = "loginForm")
	WebElement hbsLoginForm;

	@FindBy(id = "username")
	WebElement inp_username;

	@FindBy(id = "password")
	WebElement inp_password;

	@FindBy(id = "submit-button")
	WebElement btn_go;

	public HBSSecureLoginPageObject(WebDriver driver) throws InterruptedException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		this.encryption = new Encryptor();
	}
	
	public boolean verifyUserIsOnHBSSecureLoginPage() {
		boolean isActive = false;
		try {
			if (this.hbsLoginForm != null) {
				isActive = true;
			}
		} catch (NoSuchElementException e) {
			isActive = false;
		}
		return isActive;

	}

	public void enterCredentialsOnSignInForm(String username, String password) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		WaitForAjaxElement(inp_username, 30);
		inp_username.clear();
		inp_username.sendKeys(username);
		inp_password.clear();
		inp_password.sendKeys(this.encryption.decrypt(password));
		System.out.println("User has entered credentials for the user '" + username + "'");
	}

	public void hitGoButton() {
		click(btn_go);
		System.out.println("User clicked on Go button");
	}

	public void open() {
		this.driver.get(this.pageURL);
	}

	public void open(String url) {
		this.driver.get(url);
	}
}
