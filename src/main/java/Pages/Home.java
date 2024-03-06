package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static Helper.BaseClass.*;

public class Home {
    WebDriver driver;


    @FindBy(xpath = "//h2[text()='Central Pharmacist Task List']")
    WebElement homePageHeader;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-0']//tbody/tr/td[1]/span")
    WebElement encounterNumberTodoPage;
    @FindBy(xpath = "//input[@placeholder='Search by Attribute']")
    WebElement search;
    @FindBy(xpath = "//tr/td[2]")
    WebElement taskName;
    @FindBy(xpath = "//img[@mattooltip='Assign']")
    WebElement assignButton;
    @FindBy(xpath = "//span[text()=' In-Progress ']")
    WebElement inProgressTabButton;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tr[1]/td[1]/span")
    WebElement encounterNumberInProgressPage;

    @FindBy(xpath = "//input[@id='mat-radio-4-input']")
    WebElement newPrescription;

    @FindBy(xpath = "//img[@mattooltip='Un-Assign']")
    WebElement unAssign;

    @FindBy(xpath = "//span[text()=' To-do ']")
    WebElement toDo;

    public Home(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyHomePageHeader() {
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeader"));
    }

    public void SearchForOrder(String orderId) throws InterruptedException {
        Thread.sleep(9000);
        search.sendKeys(orderId);
        Assert.assertEquals(encounterNumberTodoPage.getText(), orderId);
        test.log(Status.PASS, "Encounter text verified in Todo Tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified in Todo Tab");
    }

    public void moveToNewPrescription() {
        newPrescription.click();
        test.log(Status.PASS, "Navigated to new Prescription tab");
        Pages.WebCommon().waitForLoaderInvisibility();
        search.clear();


    }

    public void verifyDataInWebTable() {
        Pages.WebCommon().verifyWebTableData();
    }

    public void moveOrderToInProgressStateAndVerify() {
        webJavascriptExecutor().executeScript("arguments[0].click();", assignButton);
        test.log(Status.PASS, "successfully clicked on  assignButton");
        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to  in Inprogress tab");
        webJavascriptExecutor().executeScript("arguments[0].click();", inProgressTabButton);
        Pages.WebCommon().waitForLoaderInvisibility();
        webWait.until(ExpectedConditions.visibilityOf(encounterNumberInProgressPage));
        search.sendKeys(prescriptionOrderID);
        Assert.assertEquals(encounterNumberInProgressPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Inprogress tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified in Inprogress tab");
    }

    public void verifyReAssign() {
        unAssign.click();
        Pages.WebCommon().waitForLoaderInvisibility();
        webJavascriptExecutor().executeScript("arguments[0].click();", toDo);
        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Reassign functionality passed");
    }
}