package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Helper.BaseClass.*;
import static java.time.Duration.ofSeconds;

public class WebCommon {

    static WebDriver driver;

    public static String value;

    String taskTable = "//app-task-list//table/tbody//tr//td[%s]";

    String justNowText = "//td[contains(text(),'just now')]";

    String loader = "//ngx-spinner//img";


    public WebCommon(WebDriver Driver) {
        driver = Driver;

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
        Thread.sleep(7000);        //7 seconds
        driver.getCurrentUrl();
    }

    public void waitForElementsInteractions() throws InterruptedException {
        Thread.sleep(5000);
    }

}

