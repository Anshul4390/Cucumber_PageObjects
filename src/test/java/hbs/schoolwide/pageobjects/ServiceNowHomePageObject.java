package hbs.schoolwide.pageobjects;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import hbs.itg.automation.commonpageobjects.BasePage;
import hbs.itg.automation.lib.Utils;

public class ServiceNowHomePageObject extends BasePage {
	
	private WebDriver driver;
	private String searchTerm;

	Utils util = new Utils(this.driver);

	@FindBy(id = "loggedin")
	WebElement txt_loggedIn;

	@FindBy(id = "gsft_nav")
	WebElement iframe_filter;

	@FindBy(id = "home_title")
	WebElement txt_mainHeading;
	
	@FindBy(id="add_icon")
	WebElement btn_add;
	
	@FindBy(id = "filter")
	WebElement inp_searchFilter;

	@FindBy(xpath = "//label[text()='Defect']/following-sibling::ul[contains(@class,'submenu opened')]")
	WebElement list_openedPanel;
	
	@FindBy(xpath = "//label[text()='Defect']/following-sibling::ul[contains(@class,'submenu opened')]/li//a[text()='Create New']")
	WebElement lnk_defectPanelCreateNew;
	
	@FindBy(id="gsft_main")
	WebElement iframe_mainContent;
	
	@FindBy(id="home_title")
	WebElement inp_mainContentHeading;
	
	

    public ServiceNowHomePageObject(WebDriver driver) throws InterruptedException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
	}

	public boolean verifyUserIsOnServiceNowHomePage() {
		boolean isActive = false;
		boolean isActive2 = false;
		try {
			if (this.iframe_filter != null) {
				isActive = true;
				try {
					if (this.txt_loggedIn != null) {
						isActive2 = true;
					}
				} catch (NoSuchElementException e) {
					isActive2 = false;
				}
				return isActive;
			}
		} catch (NoSuchElementException e) {
			isActive = false;
		}
		return isActive && isActive2;
	}

	public void enterTermIntoFilterBox(String term) {
		switchToIFrame(iframe_mainContent);
		
		WaitForAjaxElement(btn_add, 30);
		WaitForAjaxElement(txt_mainHeading, 30);
		
		driver.switchTo().defaultContent();
		
		searchTerm = term;
		
		switchToIFrame(iframe_filter);
		WaitForAjaxElement(inp_searchFilter,30);
		inp_searchFilter.sendKeys(searchTerm + Keys.RETURN);
		System.out.println("User entered '" + term + "' in the filter search box and pressed enter");
	}

	public void verifyFilterSearch() {
		WaitForAjaxElement(list_openedPanel, 30);
		WaitForAjaxElement(lnk_defectPanelCreateNew, 30);
		
		driver.switchTo().defaultContent();
		
		switchToIFrame(iframe_mainContent);
		WaitForAjaxElement(inp_mainContentHeading,30);
		Assert.assertTrue(inp_mainContentHeading.getAttribute("value").trim().equals("Defect Overview"),
				"Assertion Failed :: Defect Overview section does not appear after putting in 'Defect' as filter");

		driver.switchTo().defaultContent();
		
		System.out.println("Assertion Passed :: Filtering with term '" + searchTerm + "' is working fine");
	}

	public void clickCreateNewLinkForDefect() {
		driver.switchTo().defaultContent();
		
		switchToIFrame(iframe_filter);
		WaitForAjaxElement(lnk_defectPanelCreateNew, 30);
		click(lnk_defectPanelCreateNew);
		
		driver.switchTo().defaultContent();
	}
}
