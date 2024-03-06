package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static Helper.BaseClass.*;
import static Helper.BaseClass.webWait;

public class HomeDP {
    WebDriver driver;

    @FindBy(xpath = "//h2[text()='Dispensing Pharmacist Task List']")
    WebElement homePageHeader;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-0']//tbody/tr/td[1]/span")
    WebElement encounterNumberTodoPage;
    @FindBy(xpath = "//input[@placeholder='Search by Attribute']")
    WebElement search;
    @FindBy(xpath = "//tr/td[2]")
    WebElement taskName;

    @FindBy(xpath = "//a[text()='Task List ']")
    WebElement taskList;

    @FindBy(xpath = "//i[@mattooltip='Assign']")
    WebElement assignButton;

    @FindBy(xpath = "//span[text()=' In-Progress ']")
    WebElement inProgressTabButton;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tr[1]/td[1]/span")
    WebElement encounterNumberInProgressPage;


    @FindBy(xpath ="//app-dispencing-task-list//tbody/tr/td[1]/span")
    WebElement encounterNumberDispensingInProgressPage;

    @FindBy(xpath = "//i[@mattooltip='Detail']")
    WebElement detailButton;

    @FindBy(xpath = "//span[text()=' Dispensing In-Progress ']")
    WebElement dispensingInProgressTab;



    public HomeDP(WebDriver Driver) {
        driver=Driver;
    }
    public void verifyHomePageHeader() throws InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeaderDP"));
        test.log(Status.PASS, "Header is Verified");
        Pages.WebCommon().waitForElementsInteractions();

    }

    public void SearchForOrder() {
        Pages.WebCommon().waitForLoaderInvisibility();
        search.sendKeys(prescriptionOrderID);
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(encounterNumberTodoPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Todo Tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskNameDP"));
        test.log(Status.PASS, "TaskName text Verified in Todo Tab");
        test.log(Status.PASS, "Order verified in TODO TAB");
    }

    public void moveToInProgress() {
        webJavascriptExecutor().executeScript("arguments[0].click();", assignButton);
        test.log(Status.PASS, "successfully clicked on  assignButton");
        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to  in Inprogress tab");
        webJavascriptExecutor().executeScript("arguments[0].click();", inProgressTabButton);
        Pages.WebCommon().waitForLoaderInvisibility();
        webWait.until(ExpectedConditions.visibilityOf(encounterNumberInProgressPage));
        search.sendKeys(prescriptionOrderID);
        Assert.assertEquals(encounterNumberDispensingInProgressPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Inprogress tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskNameDP"));
        test.log(Status.PASS, "TaskName text Verified in Inprogress tab");
        webJavascriptExecutor().executeScript("arguments[0].click();", detailButton);
    }


    public void verifyOrderInDispensingInProgress()
    {
        webJavascriptExecutor().executeScript("arguments[0].click();", dispensingInProgressTab);
        Pages.WebCommon().waitForLoaderInvisibility();
        search.sendKeys(prescriptionOrderID);
        webJavascriptExecutor().executeScript("arguments[0].click();", detailButton);
    }
}


