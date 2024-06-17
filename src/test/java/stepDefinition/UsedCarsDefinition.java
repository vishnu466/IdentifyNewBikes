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
	    BaseClass.getLogger().info("==================Hovered on Used Cars====================");
	}

	@Given("clicking used cars in chennai")
	public void clickingUsedcarsChennai() {
		uc.clickUsedCarsChennai();  
		BaseClass.getLogger().info("==================Clicked Used Cars in Chennai====================");
	}

	@When("click read more to get popular models")
	public void getPopularModels() {
	    uc.clickReadMore();
	    BaseClass.getLogger().info("==================Clicked Read More====================");
	}

	@Then("print all the popular models")
	public void printPopularModels() throws IOException {
	    Assert.assertEquals(uc.popularCars(), true);
	    BaseClass.getLogger().info("==================Printed Popular Cars====================");
	}

}
