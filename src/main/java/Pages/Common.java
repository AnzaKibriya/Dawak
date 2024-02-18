package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static Helper.BaseClass.*;

public class Common {

    static WebDriver driver;

    public static String value;

    String taskTable = "//app-task-list//table/tbody//tr//td[%s]";

    String justNowText = "//td[contains(text(),'just now')]";

    String loader="//ngx-spinner//img";


    public Common(WebDriver Driver) {
        driver = Driver;

    }


    public void verifyWebtableData() {
        for (int i =1; i <= 7; i++) {
            value = driver.findElement(By.xpath(String.format(taskTable, i))).getText();

            if (value.isEmpty()) {
                Assert.fail("Webtable cell does not contain" + i + " data");

            } else {
                test.log(Status.PASS, "WebTable  contains data in " + i + " column ");
            }
        }
    }

    public void checkElementIsEmpty(String details,int h) {

        if (details.isEmpty()) {

            Assert.fail("No Data present in  1 row and "+h+" column webTable cell");


        } else {
            test.log(Status.PASS, "WebTable cell contains data in 1 row "+h+" column");
        }

    }

    public void  waitForElementInteractivity(WebElement element)  {
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForLoaderInvisibility() {

        List<WebElement> loaderElement = driver.findElements(By.xpath(loader));
        if (!loaderElement.isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(loaderElement));
        }
    }

    public void waitForJustnowElementvisibility() {
        WebElement justnow = driver.findElement(By.xpath(justNowText));
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(justnow));

    }


    public void waitForDetailedButtonClickable() throws InterruptedException {
        Thread.sleep(360000);        //6 min
        driver.getCurrentUrl();

    }

}

