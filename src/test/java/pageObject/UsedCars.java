package pageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ExcelUtility;

public class UsedCars {
	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public UsedCars(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(xpath="//li[@id='menuusedcars3']/child::span") WebElement usedCars;
	@FindBy(xpath="//li[@id='submenu_4usedcars']") WebElement inChennai;
	@FindBy(xpath="//span[@class='underline cursor-pointer']") WebElement readMore;
	@FindBy(xpath="//td[1]") List<WebElement> carNames;
	@FindBy(xpath="//td[2]") List<WebElement> carPrice;
	
	public void hoverUsedCars() {
		wait.until(ExpectedConditions.visibilityOf(usedCars));
		act.moveToElement(usedCars).click().perform();
	}
	
	public void clickUsedCarsChennai() {
		wait.until(ExpectedConditions.visibilityOf(inChennai));
		inChennai.click();
	}
	
	public void clickReadMore() {
		wait.until(ExpectedConditions.visibilityOf(readMore));
		readMore.click();
	}
	
	public boolean popularCars() throws IOException {
		for(int i=0;i<carNames.size();i++) {
			System.out.println((i+1)+ ": "+carNames.get(i).getText()+" --> "+carPrice.get(i).getText());
			ExcelUtility.setCellData(carNames.get(i).getText(), "UsedCars", i+1, 0);
			ExcelUtility.setCellData(carPrice.get(i).getText(), "UsedCars", i+1, 1);
		}
		return (carNames.size()!=0 && carPrice.size()!=0);
	}
}
