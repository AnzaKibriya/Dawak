package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static Helper.BaseClass.javascriptExecutor;
import static Helper.BaseClass.test;

public class OrderDetailsDP {

    WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Dispensing Started']")
    WebElement dispencingOrder;

    @FindBy(xpath = "//span[text()=' Ready For Delivery ']")
    WebElement readyForDelivery;

    @FindBy(xpath = "//button//img[@src='/assets/images/btn-tick.svg']")
    WebElement yesButton;
    public OrderDetailsDP(WebDriver Driver) {

        driver=Driver;
    }


    public  void  dispencingOrder() throws InterruptedException {
        Pages.Common().waitForLoaderInvisibility();
        Pages.Common().WaitforElementsInteractions();
        javascriptExecutor().executeScript("arguments[0].scrollIntoView(true);", dispencingOrder);
        javascriptExecutor().executeScript("arguments[0].click();", dispencingOrder);
        Pages.Common().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to Detail page and clicked on Despencing started");


    }

    public void ordrReadyForDelivery()
    {
        Pages.Common().waitForLoaderInvisibility();
        Pages.Common().waitForElementInteractivity(readyForDelivery);
        javascriptExecutor().executeScript("arguments[0].click();", readyForDelivery);
        javascriptExecutor().executeScript("arguments[0].click();", yesButton);
        test.log(Status.PASS, "Navigated to Detail page and clicked on  Ready for Delivery");

    }
}
