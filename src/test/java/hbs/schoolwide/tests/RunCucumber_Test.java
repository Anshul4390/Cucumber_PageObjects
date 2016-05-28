package hbs.schoolwide.tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import hbs.schoolwide.StepDefs.CucumberHooks;

@CucumberOptions(plugin = "json:target/cucumber-report.json",
features = "features",
glue = "hbs.schoolwide.StepDefs")
public class RunCucumber_Test extends AbstractTestNGCucumberTests {
	
	@BeforeClass
	public void takeParameter(ITestContext context){
		CucumberHooks.setBrowser(context.getCurrentXmlTest().getParameter("browser"));
	}
}