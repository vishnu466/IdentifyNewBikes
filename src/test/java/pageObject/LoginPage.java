package pageObject;

// Importing necessary libraries for WebDriver, WebElement, Actions, etc.
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

// Utility class for Excel operations
import Utility.ExcelUtility;

// Class to handle the page object for the login page
public class LoginPage {
    // WebDriver instance for browser interaction
    WebDriver driver;
    // Actions class instance for performing complex user gestures
    Actions act;
    // WebDriverWait instance for handling dynamic element waits
    WebDriverWait wait;
    
    // Constructor to initialize the driver, actions, and wait objects
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Initializing page elements with the driver instance
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
        // Setting up explicit wait with a 10-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators for various elements on the login page
    @FindBy(xpath="//div[@class='S9gUrf-YoZ4jf']") WebElement googleSignIn;
    @FindBy(xpath="//input[@type='email']") WebElement inputBox;
    @FindBy(xpath="//span[contains(normalize-space(),'Next')]") WebElement nextBtn;
    @FindBy(xpath="//div[@class='Ekjuhf Jj6Lae']") WebElement errorMsg;
    @FindBy(xpath="//i[@class='ic_x text-2xl']") WebElement close;
    
    // Method to initiate Google sign-in process
    public void googleSignIn() throws InterruptedException {
        // Waiting until the Google sign-in button is visible
        wait.until(ExpectedConditions.visibilityOf(googleSignIn));
        // Clicking the Google sign-in button
        googleSignIn.click();
    }
    
    // Method to switch to the new window after initiating sign-in
    public void switchWindow() {
        // Retrieving all window handles
        Set<String> win = driver.getWindowHandles();
        // Converting the set to a list for easier access
        List<String> wins = new ArrayList<>(win);
        // Switching to the second window in the list
        driver.switchTo().window(wins.get(1));
    }
    
    // Method to validate email input during sign-in
    public void mailValidation() {
        // Waiting until the email input box is visible
        wait.until(ExpectedConditions.visibilityOf(inputBox));
        // Sending a predefined email to the input box
        inputBox.sendKeys("qweds@gamil.com");
        // Waiting until the 'Next' button is visible
        wait.until(ExpectedConditions.visibilityOf(nextBtn));
        // Clicking the 'Next' button
        nextBtn.click();
    }
    
    // Method to print the error message and write it to an Excel sheet
    public String printMessage() throws IOException {
        // Waiting until the error message is visible
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        // Retrieving the text of the error message
        String error = errorMsg.getText();
        // Writing the error message to an Excel sheet
        ExcelUtility.setCellData(error, "GoogleLogin", 1, 0);
        // Returning the error message
        return error;
    }
}
