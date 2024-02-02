package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import static Helper.BaseClass.test;

public class LoginCP {
    WebDriver driver;
    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(xpath = "//h1")
    WebElement header;
    @FindBy(className = "btn-custom-primary")
    WebElement signInButton;
    @FindBy(xpath = "//div//div[@aria-label='Invalid credentials. Please Login Again']")
    WebElement InvalidLoginErrorMessage;
    @FindBy(name = "otp")
    WebElement otp;
    @FindBy(xpath = "//button[text()='Verify']")
    WebElement verifyButton;

    @FindBy(xpath = "//h2[text()='Central Pharmacist Task List']")
    WebElement HomePageHeader;

    public LoginCP(WebDriver Driver) {
        driver = Driver;

    }

    public void testCPLogin() throws IOException {
        Assert.assertEquals(header.getText(), BaseClass.propertyfile("config", "HeaderText"));
        test.log(Status.PASS, "Header is Verified");
        userName.sendKeys(BaseClass.propertyfile("config", "username"));
        password.sendKeys(BaseClass.propertyfile("config", "passwordinvalid"));
        signInButton.click();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(InvalidLoginErrorMessage));
        Assert.assertEquals(InvalidLoginErrorMessage.getText(),BaseClass.propertyfile("config", "InvalidLoginerrormessage"));
        test.log(Status.PASS, "Invalid Login Error Message Verified");
        userName.clear();
        password.clear();
        userName.sendKeys(BaseClass.propertyfile("config", "username"));
        password.sendKeys(BaseClass.propertyfile("config", "password"));
        signInButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(otp));
        otp.click();
        otp.sendKeys(BaseClass.propertyfile("config", "otp"));
        verifyButton.click();
        wait.until(ExpectedConditions.visibilityOf(HomePageHeader));
        Assert.assertEquals(HomePageHeader.getText(), BaseClass.propertyfile("config", "HomepageHeader"));
        test.log(Status.PASS, "Home page Header verified");
        test.log(Status.PASS, "Sign In is Successful");







    }
}