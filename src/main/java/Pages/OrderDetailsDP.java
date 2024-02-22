package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    @FindBy(xpath = "//span[text()='New Prescription']")
    WebElement newPrescriptionText;

    public OrderDetailsDP(WebDriver Driver) {

        driver = Driver;
    }


    public void dispencingOrder() throws InterruptedException {
        Pages.Common().waitForLoaderInvisibility();
            try {

                Pages.Common().WaitforElementsInteractions();
                javascriptExecutor().executeScript("window.scrollBy(0, 500);"); // Scroll down by 500 pixels
                javascriptExecutor().executeScript("arguments[0].scrollIntoView(true);", dispencingOrder);
                dispencingOrder.click();

            } catch (StaleElementReferenceException e) {

            }


            Pages.Common().waitForLoaderInvisibility();
            test.log(Status.PASS, "Navigated to Detail page and clicked on Despencing started");


        }

        public void ordrReadyForDelivery () throws InterruptedException {
            Pages.Common().waitForLoaderInvisibility();
            Pages.Common().WaitforElementsInteractions();
            javascriptExecutor().executeScript("arguments[0].click();", readyForDelivery);
            javascriptExecutor().executeScript("arguments[0].click();", yesButton);
            test.log(Status.PASS, "Navigated to Detail page and clicked on  Ready for Delivery");

        }
    }
