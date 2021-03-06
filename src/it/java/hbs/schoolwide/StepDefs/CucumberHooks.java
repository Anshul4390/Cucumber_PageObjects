package hbs.schoolwide.StepDefs;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import hbs.itg.automation.lib.Browser;

public class CucumberHooks {
	
	
	static String appUrl = null;
	
	public static StringBuffer failedScenario = new StringBuffer(15);
	public static StringBuffer passedScenario= new StringBuffer(15);
	public static String testResults="comment";
	
	public static Map<String,String> SceanrioResults = new HashMap<String,String>();
	public static int count = 0;
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static String browserValue;

	  
	public static WebDriver getDriver() {
	Browser br=null;
	try {
		br = new Browser(driver.get());
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
        if (driver.get() == null) {
            setWebDriver(br.start(getBrowserValue()));
        }
       
        return driver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        CucumberHooks.driver.set(driver);
    }
    
	@Before
	public void tearUp(Scenario scenario){
		printScenarioName(scenario);
	}
	
	@After
	public void tearDown(Scenario scenario){
		getDriver().quit();
	}
	

	public String getStatus(Scenario scenario){
		System.out.println("Scenario ***"+scenario.getStatus());
		switch (scenario.getStatus().toUpperCase()) {
		case "PASSED":
			return "1";
		case "FAILED":
			return "2";
		default:
			return "-1";
		}
	}
	
	public void printScenarioName(Scenario scenario){
		System.out.println("Running scenario:" + scenario.getName());
	}
	
	/*@After
	public void embedJIRAComment(Scenario scenario) throws IOException, ParseException {  
			
	        if (scenario.isFailed()) {  
	            try {
	            	
	            	PropFileHandler.writeToFile(scenario.getName(), scenario.getStatus(),
	     			YamlReader.getYamlValue("propertyfilepath"));
	              	SceanrioResults.put(scenario.getName(), scenario.getStatus());
	            	failedScenario=failedScenario.append("Scenario: "+scenario.getName()+" : - {color: red}* Failed *{color}"+"\n");
	            } catch (WebDriverException wde) {  
	                System.err.println(wde.getMessage());  
	            } catch (ClassCastException cce) {  
	                cce.printStackTrace();  
	            }  
	        } else{
	        	
	             SceanrioResults.put(scenario.getName(), scenario.getStatus());
	             PropFileHandler.writeToFile(scenario.getName(), scenario.getStatus(),
	 					YamlReader.getYamlValue("propertyfilepath"));
	             
	        	passedScenario=passedScenario.append("Scenario: "+scenario.getName()+" : - {color: green}* Passed *{color}"+"\n");
	         }
	        testResults=passedScenario.toString()+failedScenario.toString();
	        getSceanrioResults();
	    }*/
	
	public Map<String,String> getSceanrioResults(){
		for (Map.Entry<String,String> entry : SceanrioResults.entrySet()) {
			  String key = entry.getKey();
			  System.out.println("sceanrio name==="+ key);
			  System.out.println("sceanrio value==="+ entry.getValue());
		
			}
		return SceanrioResults;
		
	}
	
	public static void setBrowser(String browser){
		browserValue = browser;
	}
	
	public static String getBrowserValue(){
		return browserValue;
	}
	
}