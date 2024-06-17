package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ExcelUtility;

public class UpcomingBikes {

	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public UpcomingBikes(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath="//div[@class='relative']//select") WebElement dropdown;
	@FindBy(xpath="//span[@id='loadmoreBtn']") WebElement loadMore;
	@FindBy(xpath="//div[@class='bg-gray-50 p-2 rounded-md']//div") List<WebElement> expectedPrice;
	@FindBy(xpath="//div[@class='p-4 pt-2 max-w-full']/a") List<WebElement> bikeNames;
	@FindBy(xpath="//div[@class='bg-gray-50 p-2 rounded-md block']//div") List<WebElement> bikeLaunch;
	
	public void selectBike() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select s = new Select(dropdown);
		s.selectByVisibleText("Honda");
		Thread.sleep(3000);		
		if(loadMore.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(loadMore));
			loadMore.click();
		}
	}
	
	public boolean bikesLessthanFourLakhs() throws Exception {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(expectedPrice));
		
		String[] expPrice = new String[expectedPrice.size()];
		
		List<Double> resultPrice = new ArrayList<>();
		
		int j=1;
		
		for(int i=0;i<expectedPrice.size();i++) {
			String p = expectedPrice.get(i).getText();
			
			if(p.contains(" - ")) {
				expPrice = p.split(" - ");
				String maxPrice = expPrice[1].replace("₹", "");
				String lakh  = maxPrice.replace("Lakh", "");
				String star = lakh.replace("*", "");
				double price = Double.parseDouble(star);
				resultPrice.add(price);
			}	
			else {
				String maxPrice = p.replace("₹", "");
				String lakh  = maxPrice.replace("Lakh", "");
				String star = lakh.replace("*", "");
				double price = Double.parseDouble(star);
				resultPrice.add(price);
			}
			
			if(resultPrice.get(i) < 4.00) {
				ExcelUtility.setCellData(bikeNames.get(i).getText(), "NewBikes", j	, 0);
				ExcelUtility.setCellData(p, "NewBikes", j, 1);
				ExcelUtility.setCellData(bikeLaunch.get(i).getText(), "NewBikes", j	, 2);
				System.out.println((j)+" : "+bikeNames.get(i).getText()+  "\nExpected Price : " + p + "\nExpected Launch Date : "+bikeLaunch.get(i).getText());
				System.out.println();
				j++;	
			}
		}
		System.out.println("===============Used Cars In Chennai================\n");
		return (bikeNames.size()!=0 && bikeLaunch.size()!=0 && expectedPrice.size()!=0);
	}
	
}
