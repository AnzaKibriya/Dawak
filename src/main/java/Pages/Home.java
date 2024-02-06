package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;

import static Helper.BaseClass.test;
import static Helper.BaseClass.waitForLoaderInvisibility;

public class Home {
    WebDriver driver;
    @FindBy(xpath = "//h2[text()='Central Pharmacist Task List']")
    WebElement homePageHeader;

    @FindBy(xpath = "//*[@id='mat-tab-content-0-0']//tbody/tr/td[1]/span")
    WebElement encounter;
    @FindBy(xpath = "//input[@placeholder='Search by Attribute']")
    WebElement search;
    @FindBy(xpath = "//tr/td[2]")
    WebElement taskName;
    @FindBy(xpath = "//img[@mattooltip='Assign']")
    WebElement assignButton;
    @FindBy(xpath = "//span[text()=' In-Progress ']")
    WebElement inProgressTabButton;
    String OrderIDText;

    public Home(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyHomePageHeader() throws IOException {
        waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeader"));
    }

    public void SearchForOrder() throws IOException {
        OrderIDText = encounter.getText();
        search.sendKeys(OrderIDText);
        Assert.assertEquals(encounter.getText(), OrderIDText);
        test.log(Status.PASS, "Encounter text verified");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified");
    }

    public void moveOrderToInProgressStateAndVerify() throws IOException {
        JavascriptExecutor Assign = (JavascriptExecutor) driver;
        Assign.executeScript("arguments[0].click();", assignButton);
        JavascriptExecutor inProgress = (JavascriptExecutor) driver;
        inProgress.executeScript("arguments[0].click();", inProgressTabButton);
        OrderIDText = encounter.getText();
        search.sendKeys(OrderIDText);
        waitForLoaderInvisibility();
        test.log(Status.PASS, "text searched");
        search.clear();
        search.sendKeys(OrderIDText);
        waitForLoaderInvisibility();
        search.sendKeys(Keys.BACK_SPACE);
        search.sendKeys(Keys.chord(Keys.CONTROL, "z"));
        Assert.assertEquals(encounter.getText(), OrderIDText);
        test.log(Status.PASS, "Encounter text verified in progress tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified progress tab");
    }
}
