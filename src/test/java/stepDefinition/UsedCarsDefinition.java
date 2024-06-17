package stepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObject.UsedCars;

public class UsedCarsDefinition {

	WebDriver driver;
	UsedCars uc;
	
	@Given("hovering on UsedCars")
	public void hoveringUsedCars() {
		driver = BaseClass.getDriver();
	    uc = new UsedCars(driver);
	    uc.hoverUsedCars();
	}

	@Given("clicking used cars in chennai")
	public void clickingUsedcarsChennai() {
		uc.clickUsedCarsChennai();  
	}

	@When("click read more to get popular models")
	public void getPopularModels() {
	    uc.clickReadMore();
	}

	@Then("print all the popular models")
	public void printPopularModels() throws IOException {
	    Assert.assertEquals(uc.popularCars(), true);
	}

}
