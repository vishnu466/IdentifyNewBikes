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
	}

	@Given("sign in with google")
	public void signInWithGoogle() throws InterruptedException {
	    lp.googleSignIn();
	}

	@When("enter invalid email id")
	public void enteringEmailId() {
	    lp.switchWindow();
	    lp.mailValidation();  
	}

	@Then("print could not find your google account")
	public void printErrorMsg() throws IOException {
		String msg = lp.printMessage();
		Assert.assertEquals((msg!=null), true);
	}
}
