package pageObject;

// Importing necessary libraries for WebDriver, WebElement, Actions, etc.
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

// Utility class for Excel operations
import Utility.ExcelUtility;

// Class to handle the page object for upcoming bikes
public class UpcomingBikes {

    // WebDriver instance for browser interaction
    WebDriver driver;
    // Actions class instance for performing complex user gestures
    Actions act;
    // WebDriverWait instance for handling dynamic element waits
    WebDriverWait wait;
    
    // Constructor to initialize the driver, actions, and wait objects
    public UpcomingBikes(WebDriver driver) {
        this.driver = driver;
        // Initializing page elements with the driver instance
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
        // Setting up explicit wait with a 10-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators for various elements on the upcoming bikes page
    @FindBy(xpath="//div[@class='relative']//select") WebElement dropdown;
    @FindBy(xpath="//span[@id='loadmoreBtn']") WebElement loadMore;
    @FindBy(xpath="//div[@class='bg-gray-50 p-2 rounded-md']//div") List<WebElement> expectedPrice;
    @FindBy(xpath="//div[@class='p-4 pt-2 max-w-full']/a") List<WebElement> bikeNames;
    @FindBy(xpath="//div[@class='bg-gray-50 p-2 rounded-md block']//div") List<WebElement> bikeLaunch;
    
    // Method to select a bike brand from the dropdown
    public void selectBike() throws InterruptedException {
        // Waiting until the dropdown is visible
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        // Creating a Select object to interact with the dropdown
        Select s = new Select(dropdown);
        // Selecting 'Honda' from the dropdown
        s.selectByVisibleText("Honda");
        // Pausing execution for 3 seconds
        Thread.sleep(3000);        
        // Clicking the 'Load More' button if it's displayed
        if(loadMore.isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOf(loadMore));
            loadMore.click();
        }
    }
    
    // Method to filter bikes with a price less than 4 lakhs
    public boolean bikesLessthanFourLakhs() throws Exception {
        
        // Waiting until all elements representing expected prices are visible
        wait.until(ExpectedConditions.visibilityOfAllElements(expectedPrice));
        
        // Array to hold the split price range
        String[] expPrice = new String[expectedPrice.size()];
        
        // List to hold the result prices after parsing
        List<Double> resultPrice = new ArrayList<>();
        
        // Counter for the number of bikes under 4 lakhs
        int j=1;
        
        // Iterating over the expected prices
        for(int i=0;i<expectedPrice.size();i++) {
            // Getting the text of the price element
            String p = expectedPrice.get(i).getText();
            
            // Checking if the price is a range
            if(p.contains(" - ")) {
                // Splitting the price range and parsing the upper limit
                expPrice = p.split(" - ");
                String maxPrice = expPrice[1].replaceAll("[^0-9.]", "");
                double price = Double.parseDouble(maxPrice);
                resultPrice.add(price);
            }    
            else {
                // Parsing the price if it's not a range
                String maxPrice = p.replaceAll("[^0-9.]", "");
                double price = Double.parseDouble(maxPrice);
                resultPrice.add(price);
            }
            
            // Checking if the parsed price is less than 4 lakhs
            if(resultPrice.get(i) < 4.00) {
                // Writing the bike details to an Excel sheet if the condition is met
                ExcelUtility.setCellData(bikeNames.get(i).getText(), "NewBikes", j, 0);
                ExcelUtility.setCellData(p, "NewBikes", j, 1);
                ExcelUtility.setCellData(bikeLaunch.get(i).getText(), "NewBikes", j, 2);
                // Printing the bike details to the console
                System.out.println((j)+" : "+bikeNames.get(i).getText()+  "\nExpected Price : " + p + "\nExpected Launch Date : "+bikeLaunch.get(i).getText());
                System.out.println();
                j++;    
            }
        }
        // Printing a header for a section presumably for used cars
        System.out.println("===============Used Cars In Chennai================\n");
        // Returning true if all lists are not empty, indicating successful data retrieval
        return (bikeNames.size()!=0 && bikeLaunch.size()!=0 && expectedPrice.size()!=0);
    }
    
}
