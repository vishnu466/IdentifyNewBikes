package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import Utility.ExcelUtility;
import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.UpcomingBikes;


public class UpcomingBikesDefinition {

	public static WebDriver driver;
	HomePage hp;
	UpcomingBikes ub;
	
	@Given("navigating to webpage")
	public void navigatingtowebpage()  {
	   driver = BaseClass.getDriver();
	   hp = new HomePage(driver);
	   try {
		   hp.clickMaybeLater();
		   BaseClass.getLogger().info("====================clicked Maybelater=================");
		   } 
	   catch (Exception e) {
		   System.out.println("Maybe Later not found");
	   }
	}

	@When("clicking on Upcoming Bikes")
	public void clicking_on_upcoming_bikes() throws Exception{
		ExcelUtility.createExcelFile();
	    hp.navigateToUpcomingBikes();
	    BaseClass.getLogger().info("========================navigated to upcoming bikes page========================");
	}

	@When("selecting Honda in the dropdown")
	public void selectingHonda() throws InterruptedException {
	    ub = new UpcomingBikes(driver);
	    BaseClass.getLogger().info("=================Selected Honda in dropdown==================");
	    ub.selectBike();
	}

	@Then("printing the upcoming bikes with price less than four lakhs")
	public void printingUpcomingBikes() throws Exception {
	    Assert.assertEquals(ub.bikesLessthanFourLakhs(), true);
	    BaseClass.getLogger().info("====================Printed the Upcoming Bikes==========================");
	}
}
