package pageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ExcelUtility;

public class LoginPage {
	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(xpath="//div[@class='S9gUrf-YoZ4jf']") WebElement googleSignIn;
	@FindBy(xpath="//input[@type='email']") WebElement inputBox;
	@FindBy(xpath="//span[contains(normalize-space(),'Next')]") WebElement nextBtn;
	@FindBy(xpath="//div[@class='Ekjuhf Jj6Lae']") WebElement errorMsg;
	@FindBy(xpath="//i[@class='ic_x text-2xl']") WebElement close;
	
	public void googleSignIn() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(googleSignIn));
		Thread.sleep(3000);
		googleSignIn.click();
	}
	
	public void switchWindow() {
		Set<String> win = driver.getWindowHandles();
		List<String> wins = new ArrayList<>(win);
		driver.switchTo().window(wins.get(1));
	}
	
	public void mailValidation() {
		wait.until(ExpectedConditions.visibilityOf(inputBox));
		inputBox.sendKeys("qweds@gamil.com");
		wait.until(ExpectedConditions.visibilityOf(nextBtn));
		nextBtn.click();
	}
	
	public String printMessage() throws IOException {
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		String error = errorMsg.getText();
		ExcelUtility.setCellData(error, "GoogleLogin", 1, 0);
		return error;
	}
}
