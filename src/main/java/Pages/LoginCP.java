package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import static Helper.BaseClass.*;

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
    WebElement invalidLoginErrorMessage;
    @FindBy(name = "otp")
    WebElement otp;
    @FindBy(xpath = "//button[text()='Verify']")
    WebElement verifyButton;


    public LoginCP(WebDriver Driver) {
        driver = Driver;

    }

    public void CPLogin() throws IOException {
        Assert.assertEquals(header.getText(), BaseClass.propertyFile("config", "HeaderText"));
        test.log(Status.PASS, "Header is Verified");
        userName.sendKeys(BaseClass.propertyFile("config", "username"));
        password.sendKeys(BaseClass.propertyFile("config", "password"));
        signInButton.click();
        waitelementToBeClickable();
        otp.click();
        otp.sendKeys(BaseClass.propertyFile("config", "otp"));
        verifyButton.click();
        test.log(Status.PASS, "Home page Header verified");
        test.log(Status.PASS, "Sign In is Successful");
    }

    public void invalidCPLogin() throws IOException {
        userName.sendKeys(BaseClass.propertyFile("config", "username"));
        password.sendKeys(BaseClass.propertyFile("config", "passwordinvalid"));
        signInButton.click();
        waitForLoaderInvisibility();
        Assert.assertEquals(invalidLoginErrorMessage.getText(), BaseClass.propertyFile("config", "InvalidLoginerrormessage"));
        test.log(Status.PASS, "Invalid Login Error Message Verified");
        userName.clear();
        password.clear();
    }
}