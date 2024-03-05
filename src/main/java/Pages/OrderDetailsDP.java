package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import Enum.DeliveryDetailsEnum;

import java.util.List;

import static Helper.BaseClass.javascriptExecutor;
import static Helper.BaseClass.test;

public class OrderDetailsDP {

    WebDriver driver;
    String deliveryDetails = "//h5[contains(text(), '%s')]/following-sibling::h5";

    String medicationcolumn = "//app-dispensing-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";
    String medicationInformation = "//app-dispensing-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td[%s]";
    @FindBy(xpath = "//span[normalize-space()='Dispensing Started']")
    WebElement dispencingOrder;

    @FindBy(xpath = "//span[text()=' Ready For Delivery ']")
    WebElement readyForDelivery;

    @FindBy(xpath = "//button//img[@src='/assets/images/btn-tick.svg']")
    WebElement yesButton;

    @FindBy(xpath = "//span[text()='New Prescription']")
    WebElement newPrescriptionText;

    @FindBy(xpath = "//span[text()='Order inProgress']")
    WebElement orderInprogressText;

    @FindBy(xpath = "//span[text()='Dispensing In Progress']")
    WebElement dispensingInprogressText;

    public OrderDetailsDP(WebDriver Driver) {
        driver = Driver;
    }


    public void dispensingOrder() throws InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        try {
            Pages.WebCommon().waitForElementsInteractions();
            javascriptExecutor().executeScript("arguments[0].scrollIntoView(true);", dispencingOrder);
            dispencingOrder.click();

        } catch (StaleElementReferenceException e) {
        }

        Pages.WebCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to Detail page and clicked on Despencing started");
    }

    public void orderReadyForDelivery() throws InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.WebCommon().waitForElementsInteractions();
        javascriptExecutor().executeScript("arguments[0].click();", readyForDelivery);
        javascriptExecutor().executeScript("arguments[0].click();", yesButton);
        test.log(Status.PASS, "Navigated to Detail page and clicked on  Ready for Delivery");
    }

    public void verifyDeliveryDetail() {
        DeliveryDetailsEnum[] DeliveryInformationEnums = DeliveryDetailsEnum.values();
        System.out.println(DeliveryInformationEnums.length + "enum length");
        for (int i = 0; i <= DeliveryInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(deliveryDetails, DeliveryInformationEnums[i].value)));
            Pages.WebCommon().waitForElementInteractivity(basicInfo);
            if (basicInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + DeliveryInformationEnums[i].value);
            }
        }
        test.log(Status.PASS, " Verified Basic Information Details successfully");
    }

    public void verifyMedicationDetailTable() {
        List<WebElement> medicationData = driver.findElements(By.xpath(medicationcolumn));
        test.log(Status.PASS, " Started verifying Data in Order Details Table");
        for (int i = 1; i <= medicationData.size(); i++) {
            WebElement orderDetails = driver.findElement(By.xpath(String.format(medicationInformation, i)));
            Pages.WebCommon().checkElementIsEmpty(orderDetails.getText(), i);

        }
        test.log(Status.PASS, " Verified  orderDetails Data");
    }

    public void verifyOrderDetailsHeader() {
        System.out.println(newPrescriptionText.getText());
        Assert.assertEquals(newPrescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(orderInprogressText.getText(), BaseClass.propertyFile("config", "OrderInprogressText"));
        test.log(Status.PASS, " Verified  New prescriptionText and Insurance text");

    }


    public void verifyDespensingOrderDetailsHeader() {
        System.out.println(newPrescriptionText.getText());
        Assert.assertEquals(newPrescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(dispensingInprogressText.getText(), BaseClass.propertyFile("config", "DespensingInprogressText"));
        test.log(Status.PASS, " Verified  New prescriptionText and Insurance text");

    }

}
