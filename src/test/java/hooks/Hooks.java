package hooks;	

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	public static WebDriver driver;
	public static Properties p;
	
	@Before
	public void getBrowser() throws Exception {
		p = BaseClass.getProperties();
		driver = BaseClass.driverSetup();
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	@AfterStep
	public void takeScreenshots(Scenario s) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		byte[] screenshots = ts.getScreenshotAs(OutputType.BYTES);
		s.attach(screenshots,"image/png",s.getName());
		
	}
	
}
