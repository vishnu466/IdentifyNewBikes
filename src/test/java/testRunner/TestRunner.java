package testRunner;


import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
	
@Test
@CucumberOptions(
		features = {".//Feature/IdentifyNewBikes.feature"},
		glue = {"stepDefinition","hooks"} ,
		plugin = {"pretty","html:reports/cucumberReport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		dryRun = false,
		monochrome = true,
		publish =true
		
		)
public class TestRunner extends AbstractTestNGCucumberTests{
	
}
