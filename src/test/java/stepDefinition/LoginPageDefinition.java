package stepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObject.HomePage;
import pageObject.LoginPage;

public class LoginPageDefinition {

	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	@Given("click login icon")
	public void clickloginBtn() throws InterruptedException {
		driver = BaseClass.getDriver();
	    lp = new LoginPage(driver);
	    hp = new HomePage(driver);
	    hp.loginBtn();
	    BaseClass.getLogger().info("=================Clicking login button================");
	}

	@Given("sign in with google")
	public void signInWithGoogle() throws InterruptedException {
	    lp.googleSignIn();
	    BaseClass.getLogger().info("==================Clicking Sign in with google====================");
	}

	@When("enter invalid email id")
	public void enteringEmailId() throws Exception{
	    lp.switchWindow();
	    BaseClass.getLogger().info("===================Switching to Google sign in window==================");
	    lp.mailValidation();  
	}

	@Then("print could not find your google account")
	public void printErrorMsg() throws IOException {
		String msg = lp.printMessage();
		System.out.println(msg);
		BaseClass.getLogger().info("====================Getting the error Message====================");
		Assert.assertEquals((msg!=null), true);
	}
}
