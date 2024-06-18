package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// Utility classes for Excel operations and base setup
import Utility.ExcelUtility;
import factory.BaseClass;

// Cucumber annotations for defining the behavior-driven development (BDD) steps
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Page object classes for encapsulating web page details
import pageObject.HomePage;
import pageObject.UpcomingBikes;


public class UpcomingBikesDefinition {

    // WebDriver instance for controlling the browser
	public static WebDriver driver;
	// Page objects for the home page and upcoming bikes page
	HomePage hp;
	UpcomingBikes ub;
	
	
	@Given("navigating to webpage")
	public void navigatingtowebpage()  {
	   // Get the WebDriver instance from the BaseClass
	   driver = BaseClass.getDriver();
	   // Initialize the HomePage object
	   hp = new HomePage(driver);
	   // Code to handle the 'Maybe Later' popup, currently commented out
	}

	
	@When("clicking on Upcoming Bikes")
	public void clicking_on_upcoming_bikes() throws Exception{
	    // Create a new Excel file for storing data
		ExcelUtility.createExcelFile();
		// Navigate to the upcoming bikes page using the HomePage object
	    hp.navigateToUpcomingBikes();
	    // Log the navigation action
	    BaseClass.getLogger().info("Navigated to upcoming bikes page");
	}

	@When("selecting Honda in the dropdown")
	public void selectingHonda() throws InterruptedException {
	    // Initialize the UpcomingBikes object
	    ub = new UpcomingBikes(driver);
	    // Select the 'Honda' brand from the dropdown
	    ub.selectBike();
	    // Log the selection action
	    BaseClass.getLogger().info("Selected Honda in dropdown");
	}

	
	@Then("printing the upcoming bikes with price less than four lakhs")
	public void printingUpcomingBikes() throws Exception {
	    // Assert that the bikes listed are less than four lakhs
	    Assert.assertEquals(ub.bikesLessthanFourLakhs(), true);
	    // Log the printing action
	    BaseClass.getLogger().info("Printed the Upcoming Bikes");
	}
}
