package stepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// BaseClass for common setup and utilities
import factory.BaseClass;
// Cucumber annotations for BDD steps
import io.cucumber.java.en.*;
// Page object for used cars functionality
import pageObject.UsedCars;


public class UsedCarsDefinition {

    // WebDriver instance for controlling the browser
	WebDriver driver;
	// Page object for the used cars page
	UsedCars uc;
	
	@Given("hovering on UsedCars")
	public void hoveringUsedCars() {
		// Get the WebDriver instance from the BaseClass
		driver = BaseClass.getDriver();
		// Initialize the UsedCars object
	    uc = new UsedCars(driver);
	    // Perform hover action on the 'Used Cars' menu
	    uc.hoverUsedCars();
	    // Log the hover action
	    BaseClass.getLogger().info("Hovered on Used Cars");
	}

	@Given("clicking used cars in chennai")
	public void clickingUsedcarsChennai() {
		// Click on the 'Used Cars in Chennai' link
		uc.clickUsedCarsChennai();  
		// Log the click action
		BaseClass.getLogger().info("Clicked Used Cars in Chennai");
	}

	
	@When("click read more to get popular models")
	public void getPopularModels() {
	    // Click on the 'Read More' link
	    uc.clickReadMore();
	    // Log the click action
	    BaseClass.getLogger().info("Clicked Read More");
	}

	
	@Then("print all the popular models")
	public void printPopularModels() throws IOException {
	    // Assert that the popular car models are displayed
	    Assert.assertEquals(uc.popularCars(), true);
	    // Log the printing action
	    BaseClass.getLogger().info("Printed Popular Cars");
	}

}
