package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    public LoginCP(WebDriver Driver){
        driver = Driver;

    }
    public void testCPLogin(){
        Assert.assertEquals(header.getText(), "Welcome to Dawak");
        test.log(Status.PASS, "Header is Verified");
        userName.sendKeys("anzacp@mailinator.com");
        password.sendKeys("Akhil@2929");
        signInButton.click();
        test.log(Status.PASS, "Sign In is Successful");

    }
}
