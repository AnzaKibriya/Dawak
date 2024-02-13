package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static Helper.BaseClass.*;

public class Common {



    public static String value;
    static String toDotableRow = "//table//tr[1]//td";


    public static void verifyWebtableData() {
        for (int i = 3; i <= toDotableRow.length(); i++) {
            value = driver.findElement(By.xpath("//table//tr[1]//td[" + i + "]")).getText();
            if (value.isEmpty())
                break;
        }
        if (value.isEmpty()) {
            test.log(Status.FAIL, "WebTable  cell not contain  data");
            Assert.fail("Webtable");

        } else {
            test.log(Status.PASS, "WebTable  contains data");
        }
    }



}
