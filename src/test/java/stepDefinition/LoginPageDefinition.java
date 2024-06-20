package stepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// BaseClass for common setup and utilities
import factory.BaseClass;

// Cucumber annotations for BDD steps
import io.cucumber.java.en.*;

// Page objects for home and login pages
import pageObject.HomePage;
import pageObject.LoginPage;


public class LoginPageDefinition {

    // WebDriver instance for controlling the browser
	WebDriver driver;
	// Page objects for the login and home pages
	LoginPage lp;
	HomePage hp;
	
	
	@Given("click login icon")
	public void clickloginBtn() throws InterruptedException {
		// Get the WebDriver instance from the BaseClass
		driver = BaseClass.getDriver();
		// Initialize the LoginPage and HomePage objects
	    lp = new LoginPage(driver);
	    hp = new HomePage(driver);
	    // Click the login button on the home page
	    hp.loginBtn();
	    // Log the click action
	    BaseClass.getLogger().info("Clicked login button");
	}

	
	@Given("sign in with google")
	public void signInWithGoogle() throws InterruptedException {
	    // Perform the Google sign-in action
	    lp.googleSignIn();
	    // Log the Google sign-in action
	    BaseClass.getLogger().info("Clicked Sign in with google");
	}

	@When("enter invalid email id")
	public void enteringEmailId() throws Exception{
	    // Switch to the Google sign-in window
	    lp.switchWindow();
	    // Log the window switch action
	    BaseClass.getLogger().info("Switched to Google sign in window");
	    // Perform email validation
	    lp.mailValidation();  
	}

	
	@Then("print could not find your google account")
	public void printErrorMsg() throws IOException {
		// Retrieve the error message
		String msg = lp.printMessage();
		// Print the error message to the console
		System.out.println(msg);
		// Log the retrieval of the error message
		BaseClass.getLogger().info("Got the error Message");
		// Assert that the message is not null
		Assert.assertEquals((msg!=null), true);
	}
}
