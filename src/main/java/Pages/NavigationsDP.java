package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static Helper.BaseClass.javascriptExecutor;
import static Helper.BaseClass.test;

public class NavigationsDP {

    WebDriver driver;

    @FindBy(xpath = "//span[text()=' In-Progress ']")
    WebElement inProgressTabButton;

    @FindBy(xpath = "//span[text()=' Dispensing In-Progress ']")
    WebElement dispensingInProgressTab;
    public NavigationsDP(WebDriver Driver) {

        driver=Driver;
    }

    public void navigateTOInprogressTab()
    {
        test.log(Status.PASS, "Navigated to  in Inprogress tab");
        javascriptExecutor().executeScript("arguments[0].click();", inProgressTabButton);


    }

    public void navigateTODispensingInProgressTab()
    {
        test.log(Status.PASS, "Navigated to  DispensingInProgressTab");
        javascriptExecutor().executeScript("arguments[0].click();", dispensingInProgressTab);
    }
}
