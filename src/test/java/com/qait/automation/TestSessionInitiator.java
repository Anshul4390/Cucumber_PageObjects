package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.hbs.keywords.DefectManagementHomePageActions;
import com.hbs.keywords.HBSSecureHomePageActions;
import com.hbs.keywords.HBSSecureLoginPageActions;
import com.hbs.keywords.ServiceNowHomePageActions;
import com.hbs.keywords.YamlInformationProvider;
import com.qait.automation.utils.TakeScreenshot;

public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	static String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	public HBSSecureLoginPageActions hbsSecureLoginPage;
	public ServiceNowHomePageActions serviceNowHomePage;
	public HBSSecureHomePageActions hbsSecureHomePage;
	public DefectManagementHomePageActions defectPage;
	public YamlInformationProvider yaml;
	/**
	 * Initiating the page objects
	 */
	
	public TakeScreenshot takescreenshot;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
		hbsSecureLoginPage = new HBSSecureLoginPageActions(driver);
		serviceNowHomePage = new ServiceNowHomePageActions(driver);
		hbsSecureHomePage = new HBSSecureHomePageActions(driver);
		defectPage = new DefectManagementHomePageActions(driver);
		yaml = new YamlInformationProvider();
	}

	/**
	 * Page object Initiation done
	 */

	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
		launchApplication();
	}

	public static String getBrowser(){
		if(System.getProperty("browser")==null){
			browser =  getProperty("./Config.properties", "browser");
		}else
			browser = System.getProperty("browser");
		return browser;
	}
	
	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumServer",
				"seleniumServerHost", "timeout" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("baseURL"));
	}

	public void launchApplication(String baseurl) {
		System.out.println("\n"+
				"The test browser is :- " + getBrowser()
						+ "\n");
		deleteAllCookies();
		driver.get(baseurl);
		System.out.println("\nThe application url is :- " + baseurl);
		//handleSSLCertificateCondition();
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		driver.quit();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void closeBrowserWindow() {
		driver.close();
	}

	public void handleSSLCertificateCondition() {
		if (driver.getTitle().contains("Certificate Error")) {
			driver.get("javascript:document.getElementById('overridelink').click();");
			System.out.println("Step : handle SSL certificate condition\n");
		}
		if (driver.getTitle().contains("Certificate Error")) {
			driver.get("javascript:document.getElementById('overridelink').click();");
			System.out.println("Step : handle SSL certificate condition\n");

		} else {

		}

	}
	
}
