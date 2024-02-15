package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.util.List;
import static Helper.BaseClass.*;

public class Common {

    static WebDriver driver;

    public static String value;
    static String toDotableRow = "//table//tr[1]//td";


    public Common(WebDriver Driver) {
        driver = Driver;

    }


    public void verifyWebtableData() {
        for (int i = 3; i <= toDotableRow.length(); i++) {
            value = driver.findElement(By.xpath("//table//tr[1]//td[" + i + "]")).getText();
            if (value.isEmpty())
                break;
        }
        if (value.isEmpty()) {
            test.log(Status.FAIL, "WebTable  cell not contain  data");
            Assert.fail("Webtable cell does not contain data");

        } else {
            test.log(Status.PASS, "WebTable  contains data");
        }
    }
    public void checkElementIsEmpty(String details) {

        if (details.isEmpty()) {
            test.log(Status.FAIL, "WebTable  Does not contain data");

             Assert.fail("No Data present in webTable cell");


        } else {
            test.log(Status.PASS, "WebTable cell  contains data");
        }
    }
    public void waitForLoaderInvisibility() {

        List<WebElement> loaderElement = driver.findElements(By.xpath("//ngx-spinner//img"));
        if (!loaderElement.isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(loaderElement));
        }
    }


    public void waitForJustnowElementvisibility() {
        WebElement justnow = driver.findElement(By.xpath("//td[contains(text(),'just now')]"));
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(justnow));

    }


    public void waitForDetailedButtonClickable() throws InterruptedException {
        Thread.sleep(360000);        //6 min
        driver.getCurrentUrl();

    }

}

