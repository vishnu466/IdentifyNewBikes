package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {".//Feature/IdentifyNewBikes.feature"},
		//features= {"@target/rerun.txt"},
		glue = {"stepDefinition","hooks"} ,
		plugin = {"pretty","html:reports/cucumberReport.html",
				"rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		
		dryRun = false, // checks mapping between scenario steps and step definition methods
		monochrome = true, // to avoid junk characters in output
		publish =true // to publish report in cucumber server
		
		)
public class TestRunner{
	
}
