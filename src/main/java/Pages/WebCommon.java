package Pages;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static Helper.BaseClass.*;
import static java.time.Duration.ofSeconds;

public class WebCommon {

    static String uiFormattedDate;

    static WebDriver driver;

    public static String value;

    String taskTable = "//app-task-list//table/tbody//tr//td[%s]";

    String taskTableColumn="//app-task-list//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";

    String taskTableValues="//app-task-list//tbody[contains(@class, 'mdc-data-table__content')]//td[%s]";

    String justNowText = "//td[contains(text(),'just now')]";

    String taskTableHeading="//app-task-list//table[contains(@class, 'mat-mdc-table mdc-data-table__table cdk-table mat-sort')]//th[%s]";


    String loader = "//ngx-spinner//img";

   static public JsonObject patient;
   static public JsonObject order;

   public WebCommon(WebDriver Driver) {
        driver = Driver;

    }


    public void verifyTaskTable() throws FileNotFoundException {
        List<WebElement> taskDetailsTable = driver.findElements(By.xpath(taskTableColumn));
        System.out.println(taskDetailsTable.size());
        test.log(Status.PASS, "verifying task table information");

        for(int i=1;i<=taskDetailsTable.size()-1;i++)
        {
            WebElement tableHeading = driver.findElement(By.xpath(String.format(taskTableHeading, i)));
            WebElement tasktableData = driver.findElement(By.xpath(String.format(taskTableValues, i)));
            Pages.PatientInformations().info(tableHeading,tasktableData);

        }


    }




    public void verifyWebTableData() {
        for (int i = 1; i <= 7; i++) {
            value = driver.findElement(By.xpath(String.format(taskTable, i))).getText();
            if (value.isEmpty()) {
                softAssert.fail("Webtable cell does not contain" + i + " data");
            } else {
                test.log(Status.PASS, "WebTable  contains data in " + i + " column ");
            }
        }
    }

    public void checkElementIsEmpty(String details, int h) {
        if (details.isEmpty()) {
            softAssert.fail("No Data present in  1 row and " + h + " column webTable cell");
        } else {
            test.log(Status.PASS, "WebTable cell contains data in 1 row " + h + " column");
        }
    }

    public void waitForElementInteractivity(WebElement element) {
        webWait = new WebDriverWait(driver, ofSeconds(60));
        webWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForLoaderInvisibility() {
        List<WebElement> loaderElement = driver.findElements(By.xpath(loader));
        if (!loaderElement.isEmpty()) {
            webWait.until(ExpectedConditions.invisibilityOfAllElements(loaderElement));
        }
    }

    public void waitForJustNowElementVisibility() {
        WebElement justNow = driver.findElement(By.xpath(justNowText));
        webWait = new WebDriverWait(driver, ofSeconds(60));
        webWait.until(ExpectedConditions.visibilityOf(justNow));
    }


    public void waitForDetailedButtonClickable() throws InterruptedException {
        Thread.sleep(7000);
        driver.navigate().refresh();//7 seconds
    }

    public void waitForElementsInteractions() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void ExtractDateFromString(String timeDate) {
        // Parse the original string to LocalDateTime
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeDate, originalFormatter);

        // Format the LocalDateTime to the desired date format
        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        uiFormattedDate = dateTime.format(desiredFormatter);

        // Display the formatted date
        System.out.println("Formatted Date: " + uiFormattedDate);
    }


    public void assertjson(String expected, String actual) {
        Assert.assertEquals(expected, actual);
    }

    public void loadJson(String path) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        FileReader reader;
        reader = new FileReader(path);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        // Extract patient information
        patient = jsonObject.getAsJsonObject("patient");
        order = jsonObject.getAsJsonObject("order");


    }

}

