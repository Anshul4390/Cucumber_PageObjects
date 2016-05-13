package hbs.schoolwide.tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import hbs.schoolwide.StepDefs.CucumberHooks;

@CucumberOptions(features = "src/it/resources/features/Story_C5938.feature",
glue = "hbs.schoolwide.StepDefs")
public class Story_C5938_Test extends AbstractTestNGCucumberTests {

	@BeforeClass
	public void takeParameter(ITestContext context){
		CucumberHooks.setBrowser(context.getCurrentXmlTest().getParameter("browser"));
	}
}