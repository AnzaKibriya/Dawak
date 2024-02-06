package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import static Helper.BaseClass.test;

public class Home {
    WebDriver driver;

    @FindBy(xpath = "//h2[text()='Central Pharmacist Task List']")
    WebElement HomePageHeader;


    @FindBy(xpath= "//tr//td")
    WebElement Encounter;

    @FindBy( xpath = "//input[@placeholder='Search by Attribute']")
     WebElement Search;



    @FindBy(xpath =" //ngx-spinner//img")
    WebElement Loader;
    @FindBy(xpath ="//tr//td")
    WebElement VerifyEncounter;
    @FindBy( xpath ="//tr/td[2]")
    WebElement TaskName;

    @FindBy( xpath ="//img[@mattooltip='Assign']")
    WebElement AssignButton;

    @FindBy( xpath ="//span[text()=' In-Progress ']")
    WebElement  InprogressTabButton;



    public Home(WebDriver Driver) {
        driver = Driver;
    }
    public void testHome() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(130));
        wait.until(ExpectedConditions.visibilityOf(HomePageHeader));
        Assert.assertEquals(HomePageHeader.getText(), BaseClass.propertyfile("config", "HomepageHeader"));
        wait.until(ExpectedConditions.invisibilityOf(Loader));
        String Encountertext=Encounter.getText();
        Search.sendKeys(Encountertext);
        test.log(Status.PASS, "text searched");
        wait.until(ExpectedConditions.visibilityOf(VerifyEncounter));
        Assert.assertEquals(VerifyEncounter.getText(),Encountertext);
        test.log(Status.PASS, "Encounter text verified");
        Assert.assertEquals(TaskName.getText(),BaseClass.propertyfile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified");
        JavascriptExecutor Assign = (JavascriptExecutor) driver;
        Assign.executeScript("arguments[0].click();", AssignButton);
        wait.until(ExpectedConditions.invisibilityOf(Loader));
        JavascriptExecutor Inprogress = (JavascriptExecutor) driver;
        Inprogress.executeScript("arguments[0].click();", InprogressTabButton);
        wait.until(ExpectedConditions.visibilityOf(Loader));
        wait.until(ExpectedConditions.invisibilityOf(Loader));
        Search.sendKeys(Encountertext);
        wait.until(ExpectedConditions.invisibilityOf(Loader));
        test.log(Status.PASS, "text searched");
        Search.clear();
        Search.sendKeys(Encountertext);
        wait.until(ExpectedConditions.invisibilityOf(Loader));
        Search.sendKeys(Keys.BACK_SPACE);
        Search.sendKeys(Keys.chord(Keys.CONTROL, "z"));
        Assert.assertEquals(VerifyEncounter.getText(),Encountertext);
        test.log(Status.PASS, "Encounter text verified in progress tab");
        Assert.assertEquals(TaskName.getText(),BaseClass.propertyfile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified progress tab");




    }
}
