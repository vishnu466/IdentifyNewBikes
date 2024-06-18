package pageObject;

// Importing necessary libraries for WebDriver, WebElement, Actions, etc.
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

// Utility class for Excel operations
import Utility.ExcelUtility;

// Class to handle the page object for used cars
public class UsedCars {
    // WebDriver instance for browser interaction
    WebDriver driver;
    // Actions class instance for performing complex user gestures
    Actions act;
    // WebDriverWait instance for handling dynamic element waits
    WebDriverWait wait;
    
    // Constructor to initialize the driver, actions, and wait objects
    public UsedCars(WebDriver driver) {
        this.driver = driver;
        // Initializing page elements with the driver instance
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
        // Setting up explicit wait with a 20-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Locators for various elements on the used cars page
    @FindBy(xpath="//li[@id='menuusedcars3']/child::span") WebElement usedCars;
    @FindBy(xpath="//li[@id='submenu_4usedcars']") WebElement inChennai;
    @FindBy(xpath="//span[@class='underline cursor-pointer']") WebElement readMore;
    @FindBy(xpath="//td[1]") List<WebElement> carNames;
    @FindBy(xpath="//td[2]") List<WebElement> carPrice;
    
    // Method to hover over the 'Used Cars' menu
    public void hoverUsedCars() {
        // Waiting until the 'Used Cars' menu is visible
        wait.until(ExpectedConditions.visibilityOf(usedCars));
        // Hovering over the 'Used Cars' menu and clicking it
        act.moveToElement(usedCars).click().perform();
    }
    
    // Method to click on 'Used Cars in Chennai'
    public void clickUsedCarsChennai() {
        // Waiting until the 'Used Cars in Chennai' option is visible
        wait.until(ExpectedConditions.visibilityOf(inChennai));
        // Clicking the 'Used Cars in Chennai' option
        inChennai.click();
    }
    
    // Method to click on 'Read More' for additional details
    public void clickReadMore() {
        // Waiting until the 'Read More' link is visible
        wait.until(ExpectedConditions.visibilityOf(readMore));
        // Clicking the 'Read More' link
        readMore.click();
    }
    
    // Method to process and display popular cars and their prices
    public boolean popularCars() throws IOException {
        // Iterating over the list of car names and prices
        for(int i=0;i<carNames.size();i++) {
            // Printing the car name and price to the console
            System.out.println((i+1)+ ": "+carNames.get(i).getText()+" --> "+carPrice.get(i).getText());
            // Writing the car name and price to an Excel sheet
            ExcelUtility.setCellData(carNames.get(i).getText(), "UsedCars", i+1, 0);
            ExcelUtility.setCellData(carPrice.get(i).getText(), "UsedCars", i+1, 1);
        }
        // Returning true if both lists are not empty, indicating successful data retrieval
        return (carNames.size()!=0 && carPrice.size()!=0);
    }
}
