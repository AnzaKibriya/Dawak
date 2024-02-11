package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static Helper.BaseClass.*;

public class OrderDetails {

    WebDriver driver;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tbody/tr/td[8]/div/img[2]")
    WebElement actionButton;
    String medicinePendingInfoInTable = "//app-pending-medication-info//mat-drawer-content//tbody//tr//td[%s]";
    String deliveryDetails = "//app-address-detail//div//table//tbody//tr//td[%s]";
    String colum = "//app-address-detail//div//table//tbody//tr//td";
    String orderDetailColumn = "//app-pending-medication-info//mat-drawer-content//tbody//tr//td";

    String trackingDetails = "//app-tracking-info//div//table//tbody//tr//td[%s]";

    String trackDetailsColumn = "//app-tracking-info//div//table//tbody//tr//td";


    @FindBy(xpath = "//span[text()='New Prescription']")
    WebElement newPrescriptionText;

    @FindBy(xpath = "//span[text()='Insurance Pending Approval']")
    WebElement insuranceApprovalText;

    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void openOrderDetailPage() {
        actionButton.click();
        waitForLoaderInvisibility();
    }


    public void verifyDeliveryDetailTable() {
        List<WebElement> orderDeliveryTable = driver.findElements(By.xpath(colum));
        for (int i = 1; i <= orderDeliveryTable.size(); i++) {
            WebElement orderDeliveryDetail = driver.findElement(By.xpath(String.format(deliveryDetails, i)));
            checkElementIsEmpty(orderDeliveryDetail.getText());
        }

        test.log(Status.PASS, " Verified  DeliveryDetails  Data");
    }

    public void verifyOrderDetailTable() {
        List<WebElement> orderDetailTable = driver.findElements(By.xpath(orderDetailColumn));
        for (int i = 1; i <= orderDetailTable.size(); i++) {
            WebElement orderDetails = driver.findElement(By.xpath(String.format(medicinePendingInfoInTable, i)));
            System.out.println(orderDetails.getText());
            checkElementIsEmpty(orderDetails.getText());
        }
        test.log(Status.PASS, " Verified  orderDetails Data");
    }

    public void verifyTrackDetailTable() {

        List<WebElement> trackDeliveryTable = driver.findElements(By.xpath(trackDetailsColumn));

        for (int j= 1; j <= trackDeliveryTable.size(); j++) {
            WebElement trackDeliveryDetail = driver.findElement(By.xpath(String.format(trackingDetails, j)));
            checkElementIsEmpty(trackDeliveryDetail.getText());
        }
        test.log(Status.PASS, " Verified  TrackDetails  Data");

    }

    public void verifyOrderDetailsHeader() {
        System.out.println(newPrescriptionText.getText());
        Assert.assertEquals(newPrescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(insuranceApprovalText.getText(), BaseClass.propertyFile("config", "InsurenceText"));
        test.log(Status.PASS, " Verified  New prescriptionText and Insurance text");

    }


}