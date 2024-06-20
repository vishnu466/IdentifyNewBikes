package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath = "(//div[@class='grid grid-cols-2 gap-4']/span)[1]") WebElement maybe;
	@FindBy(xpath = "//li[@id='menubike1']/child::span") WebElement Bikes;
	@FindBy(xpath = "//li[@id='menubike1']/ul/child::li[6]/a") WebElement upcomingBikes;
	@FindBy(xpath = "//div[@class='relative flex items-center justify-center cursor-pointer']") WebElement loginBtn;
	
	
	//Method to click maybe later in the homepage
	public void clickMaybeLater() {
		wait.until(ExpectedConditions.visibilityOf(maybe));
		maybe.click();
	}
	
	//Method to navigate to upcoming bikes
	public void navigateToUpcomingBikes() {
		wait.until(ExpectedConditions.visibilityOf(Bikes));
		act.moveToElement(Bikes).click().perform();
		
		wait.until(ExpectedConditions.visibilityOf(upcomingBikes));
		upcomingBikes.click();
	}
	
	//Method to click login button
	public void loginBtn() throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(loginBtn));
		loginBtn.click();
	}
	
}
