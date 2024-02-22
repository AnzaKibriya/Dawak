package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import javax.xml.xpath.XPath;
import java.awt.*;
import java.awt.event.KeyEvent;

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
    WebElement encounterNumberDespencingInProgressPage;

    @FindBy(xpath = "//i[@mattooltip='Detail']")
    WebElement detailButton;

    @FindBy(xpath = "//span[text()=' Dispensing In-Progress ']")
    WebElement despensingInprogressTab;



    public HomeDP(WebDriver Driver) {

      driver=Driver;


    }
    public void verifyHomePageHeader() {
        Pages.Common().waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeaderDP"));
        test.log(Status.PASS, "Header is Verified");

    }

    public void SearchForOrder() throws InterruptedException {
        //Thread.sleep(9000);
        Pages.Common().waitForLoaderInvisibility();
        search.sendKeys(prescriptionOrderID);
        Assert.assertEquals(encounterNumberTodoPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Todo Tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskNameDP"));
        test.log(Status.PASS, "TaskName text Verified in Todo Tab");
        test.log(Status.PASS, "Order verified in TODO TAB");

    }

    public void moveTOInprogress() throws AWTException, InterruptedException {
        javascriptExecutor().executeScript("arguments[0].click();", assignButton);
        test.log(Status.PASS, "successfully clicked on  assignButton");
        Pages.Common().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to  in Inprogress tab");
        javascriptExecutor().executeScript("arguments[0].click();", inProgressTabButton);
        Pages.Common().waitForLoaderInvisibility();
        webWait.until(ExpectedConditions.visibilityOf(encounterNumberInProgressPage));
        search.sendKeys(prescriptionOrderID);
        Assert.assertEquals(encounterNumberDespencingInProgressPage.getText(), prescriptionOrderID);
        test.log(Status.PASS, "Encounter text verified in Inprogress tab");
        Assert.assertEquals(taskName.getText(), BaseClass.propertyFile("config", "TaskNameDP"));
        test.log(Status.PASS, "TaskName text Verified in Inprogress tab");
        javascriptExecutor().executeScript("arguments[0].click();", detailButton);


    }


    public void verifyOrderInDispensingInProgress()
    {
        javascriptExecutor().executeScript("arguments[0].click();", despensingInprogressTab);
        Pages.Common().waitForLoaderInvisibility();
        search.sendKeys(prescriptionOrderID);
        javascriptExecutor().executeScript("arguments[0].click();", detailButton);



    }
}


