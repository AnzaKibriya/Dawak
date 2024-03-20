package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import Enum.TaskTableEnum;

import java.io.FileNotFoundException;
import java.util.List;

import Enum.FilterDataEnum;

import static Helper.BaseClass.*;
import static Pages.WebCommon.patient;

public class Home {
    WebDriver driver;


    String taskTableColumn = "//app-task-list//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";

    // String taskTableValues="//app-task-list//tbody[contains(@class, 'mdc-data-table__content')]//td[%s]";

    String taskTableValues = "//app-task-list//tbody//td[%s]";

    String createOrderPath = "./src/main/resources/CreatingOrder.json";

    String taskTableHeading = "//app-task-list//table[contains(@class, 'mat-mdc-table mdc-data-table__table cdk-table mat-sort')]//th[%s]";

    String filterData = "//input[@formcontrolname='%s']";


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


    @FindBy(xpath = "//button[text()=' Filter ']")
    WebElement filters;

    @FindBy(xpath = "//span[text()=' Clear All ']")
    WebElement clearAll;

    @FindBy(xpath = "//input[@formcontrolname='patientPrescription']")
    WebElement prescriptionFilter;

    @FindBy(xpath = "//input[@formcontrolname='patientMRN']")
    WebElement mrnFilter;

    @FindBy(xpath = "//input[@formcontrolname='patientEID']")
    WebElement eidFilter;

    @FindBy(xpath = "//input[@formcontrolname='patientFullName']")
    WebElement fullNameFilter;
    @FindBy(xpath = "//span[normalize-space()='Apply']")
    WebElement apply;


    public Home(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyHomePageHeader() {
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(homePageHeader.getText(), BaseClass.propertyFile("config", "HomepageHeader"));
    }

    public void SearchForOrder(String orderid) throws InterruptedException, FileNotFoundException {
        Thread.sleep(3000);
        search.sendKeys(orderid);
        Pages.WebCommon().verifyTaskTable();
        // Assert.assertEquals(encounterNumberTodoPage.getText(), orderid));

    }

    public void clearSearch() {

        Pages.WebCommon().waitForLoaderInvisibility();
        search.clear();


    }

    public void verifyDataInWebTable() {
        Pages.WebCommon().verifyWebTableData();
    }


    public void clickOnAssign() {
        webJavascriptExecutor().executeScript("arguments[0].click();", assignButton);
        test.log(Status.PASS, "successfully clicked on  assignButton");
    }

    public void moveOrderToInProgressStateAndVerify() throws FileNotFoundException, InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.WebCommon().waitForLoaderInvisibility();
        webWait.until(ExpectedConditions.visibilityOf(encounterNumberInProgressPage));
        Pages.Home().SearchForOrder(prescriptionOrderID);
        //  Assert.assertEquals(encounterNumberInProgressPage.getText(), prescriptionOrderID);

    }

    public void verifyReAssign() {
        unAssign.click();
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.NavigationsCP().navigateTOTodoTab();
        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Reassign functionality passed");
    }


    public void verifyFilters() throws FileNotFoundException, InterruptedException {
        TaskTableEnum[] TaskTableFilters = TaskTableEnum.values();
        FilterDataEnum[] FilterDataEnums = FilterDataEnum.values();

        for (int i = 0; i <= TaskTableFilters.length - 1; i++) {
            WebElement tableHeading = driver.findElement(By.xpath(String.format(taskTableHeading, TaskTableFilters[i].value)));
            WebElement filterValue = driver.findElement(By.xpath(String.format(taskTableValues, TaskTableFilters[i].value)));
            validateFilter(tableHeading, FilterDataEnums[i].value, TaskTableFilters[i].value);
        }


    }

    public void applyFilters(String filterValue, String value) throws InterruptedException {
        Pages.WebCommon().waitForElementsInteractions();
        filters.click();
        WebElement input = driver.findElement(By.xpath(String.format(filterData, value)));
        input.sendKeys(filterValue);
        apply.click();
        Pages.WebCommon().waitForElementsInteractions();


    }


    public void clearFilter() {
        webJavascriptExecutor().executeScript("arguments[0].click();", filters);
        webJavascriptExecutor().executeScript("arguments[0].click();", clearAll);
    }


    public void validateFilter(WebElement tableHeading, String filterName, String taskTabletext) throws FileNotFoundException, InterruptedException {
        Pages.WebCommon().loadJson(createOrderPath);


        if (tableHeading.getText().contains("Encounter #")) {
            Pages.Home().applyFilters(prescriptionOrderID, filterName);
            WebElement Encounterid = driver.findElement(By.xpath(String.format(taskTableValues, taskTabletext)));
            Pages.WebCommon().waitForElementInteractivity(Encounterid);
            System.out.println(Encounterid);
            Assert.assertEquals(prescriptionOrderID, Encounterid.getText());
            Pages.Home().clearFilter();

        } else if (tableHeading.getText().contains("Patient Emirates ID")) {
            String eid = patient.getAsJsonPrimitive("eid").getAsString();
            Pages.Home().applyFilters(eid, filterName);
            WebElement Emiratesid = driver.findElement(By.xpath(String.format(taskTableValues, taskTabletext)));
            Assert.assertEquals(eid, Emiratesid.getText());
            Pages.Home().clearFilter();


        } else if (tableHeading.getText().contains("Patient Name")) {
            String firstName = patient.getAsJsonPrimitive("firstName").getAsString();
            String middleName = patient.getAsJsonPrimitive("middleName").getAsString();
            String lastName = patient.getAsJsonPrimitive("lastName").getAsString();
            String fullname = firstName + middleName + lastName;
            Pages.Home().applyFilters(fullname, filterName);
            Pages.Home().clearFilter();


        } else if (tableHeading.getText().contains("Patient MRN #")) {
            String mrn = patient.getAsJsonPrimitive("mrn").getAsString();
            Pages.Home().applyFilters(mrn, filterName);
            WebElement mrnvalue = driver.findElement(By.xpath(String.format(taskTableValues, taskTabletext)));
            Assert.assertEquals(mrn, mrnvalue.getText());
            Pages.Home().clearFilter();


        }
    }


}