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

    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tr[1]/td[1]/span")
    WebElement encounterNumberInProgressPage;



    @FindBy(xpath = "//img[@mattooltip='Un-Assign']")
    WebElement unAssign;



    public Home(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyHomePageHeader() {
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeader"));
    }

    public void SearchForOrder() throws InterruptedException {
        Thread.sleep(9000);
        search.sendKeys(prescriptionOrderID);
        Assert.assertEquals(encounterNumberTodoPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Todo Tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskName"));
        test.log(Status.PASS, "TaskName text Verified in Todo Tab");
    }

    public void clearSearch() {

        Pages.WebCommon().waitForLoaderInvisibility();
        search.clear();


    }

    public void verifyDataInWebTable() {
        Pages.WebCommon().verifyWebTableData();
    }



    public void clickOnAssign()
    {

        javascriptExecutor().executeScript("arguments[0].click();", assignButton);
        test.log(Status.PASS, "successfully clicked on  assignButton");
    }
    public void moveOrderToInProgressStateAndVerify() {
        Pages.WebCommon().waitForLoaderInvisibility();
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
        Pages.NavigationsCP().navigateTOTodoTab();
        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Reassign functionality passed");
    }
}