package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
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
    String trackingDetails = "//app-tracking-info//div//table//tbody//tr[1]//td[%s]";
    String trackDetailsColumn = "//app-tracking-info//div//table//tbody//tr[1]//td";
    String viewDetails = "//mat-drawer/div/div/div[2]/div[3]//div[%s]";
    String basicInfoDetails = "//*[@id='multiCollapseExample1']/div/div/div[%s]//div/following-sibling::div";
    @FindBy(xpath = "//span[text()='New Prescription']")
    WebElement newPrescriptionText;

    @FindBy(xpath = "//span[text()='Insurance Pending Approval']")
    WebElement insuranceApprovalText;
    @FindBy(xpath = "//img[@src='../../../../assets/images/copy.svg']")
    WebElement copy;

    @FindBy(xpath = "//span[normalize-space()='Address Copied']")
    WebElement addressCopied;

    @FindBy(xpath = "//span[normalize-space()='Remove']")
    WebElement removeButton;

    @FindBy(xpath = "//textarea[@id='mat-input-8']")
    WebElement leaveComment;

    @FindBy(xpath = "//i[@class='das das-btn-tick']")
    WebElement submitButton;

    @FindBy(xpath = "//span[normalize-space()='Add Back']")
    WebElement addBack;

    @FindBy(xpath = "//img[@src='../../../assets/images/new_cross.png']")
    WebElement crossIcon;

    @FindBy(xpath = "//span[normalize-space()='View Details']")
    WebElement viewDetailsButton;

    @FindBy(xpath = "//span[normalize-space()='Send Insurance for Approval']")
    WebElement sendInsurenceApprovalButton;

    @FindBy(xpath = "//h5[text()=' Order Details ']")
    WebElement physicianOrderId;

    @FindBy(xpath = "//span[text()=' Insurance In-Progress ']")
    WebElement insurenceInprogressButton;

    @FindBy(xpath = "//input[@placeholder='Search by Attribute']")
    WebElement search;

    @FindBy(xpath = "//img[@mattooltip='Details']")
    WebElement details;

    @FindBy(xpath = "//h5[text()='Basic Info']")
    WebElement basicInfoButton;

    @FindBy(xpath = "//mat-label[text()='Health Plans']")
    WebElement healthPlan;

    @FindBy(xpath = "//input[@placeholder='Enter Qty']")
    WebElement enterQty;

    @FindBy(xpath = "//input[@placeholder='Enter Co-Pay Amount in AED']")
    WebElement payAmount;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    WebElement saveButton;

    @FindBy(xpath = "//span[normalize-space()='Co Pay']")
    WebElement coPay;

    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void openOrderDetailPage() {
        actionButton.click();
        Pages.Common().waitForLoaderInvisibility();
    }


    public void verifyDeliveryDetailTable()  {
        List<WebElement> orderDeliveryTable = driver.findElements(By.xpath(colum));
        for (int i = 1; i <= orderDeliveryTable.size()-2; i++) {
            WebElement orderDeliveryDetail = driver.findElement(By.xpath(String.format(deliveryDetails, i)));
            Pages.Common().checkElementIsEmpty(orderDeliveryDetail.getText());

        }
        copy.click();
        Assert.assertEquals(addressCopied.getText(), BaseClass.propertyFile("config", "AddressText"));
        test.log(Status.PASS, " copy functionality verified in delivery details");


        test.log(Status.PASS, " Verified  DeliveryDetails  Data");
    }

    public void verifyOrderDetailTable() {
        List<WebElement> orderDetailTable = driver.findElements(By.xpath(orderDetailColumn));
        for (int i = 1; i <= orderDetailTable.size(); i++) {
            System.out.println(orderDetailTable.size());
            WebElement orderDetails = driver.findElement(By.xpath(String.format(medicinePendingInfoInTable, i)));
            System.out.println(orderDetails.getText());
            Pages.Common().checkElementIsEmpty(orderDetails.getText());

        }
        test.log(Status.PASS, " Verified  orderDetails Data");
    }

    public void verifyTrackDetailTable() {

        List<WebElement> trackDeliveryTable = driver.findElements(By.xpath(trackDetailsColumn));
        System.out.println(trackDeliveryTable.size());
        for (int j = 1; j <= trackDeliveryTable.size(); j++) {
            WebElement trackDeliveryDetail = driver.findElement(By.xpath(String.format(trackingDetails, j)));
            Pages.Common().checkElementIsEmpty(trackDeliveryDetail.getText());


        }
        test.log(Status.PASS, " Verified  TrackDetails  Data");

    }


    public void verifyBasicInfo() {

        basicInfoButton.click();
        for (int i = 1; i <= 11; i++) {
            WebElement basicDetails = driver.findElement(By.xpath(String.format(basicInfoDetails, i)));
            System.out.println(basicDetails.getText());
            Pages.Common().checkElementIsEmpty(basicDetails.getText());
        }
        test.log(Status.PASS, " Verified  BasicInfo Data");
    }

    public void verifyOrderDetailsHeader() {
        System.out.println(newPrescriptionText.getText());
        Assert.assertEquals(newPrescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(insuranceApprovalText.getText(), BaseClass.propertyFile("config", "InsurenceText"));
        test.log(Status.PASS, " Verified  New prescriptionText and Insurance text");

    }


    public void verifyRemovefunctionlity() {
        javascriptExecutor().executeScript("arguments[0].click();", removeButton);
        leaveComment.sendKeys("these");
        submitButton.click();
        Pages.Common().waitForLoaderInvisibility();
        addBack.click();

    }


    public void verifyViewDetails() {
        viewDetailsButton.click();
        test.log(Status.PASS, " Navigated to view details page");

        for (int i = 2; i <= 8; i++) {
            WebElement view = driver.findElement(By.xpath(String.format(viewDetails, i)));
            Pages.Common().checkElementIsEmpty(view.getText());
        }
        javascriptExecutor().executeScript("arguments[0].click();", crossIcon);
        test.log(Status.PASS, " Navigated back from view details page");

    }

    public void verifySendInsurenceApproval() throws InterruptedException {
        String orderIdText = physicianOrderId.getText();
        javascriptExecutor().executeScript("arguments[0].click();", sendInsurenceApprovalButton);
        test.log(Status.PASS, " order sent for insurence Approval");
        Pages.Common().waitForLoaderInvisibility();
        javascriptExecutor().executeScript("arguments[0].click();", insurenceInprogressButton);
        Pages.Common().waitForLoaderInvisibility();
        String numberOnly = orderIdText.replaceAll("[^0-9]", "");
        search.sendKeys(numberOnly);
        test.log(Status.PASS, " Verified Insurence approval request in Insurence inprogress");
        details.click();
        Pages.Common().waitForDetailedButtonClickable();
        if (viewDetailsButton.isEnabled())
            viewDetailsButton.click();
        test.log(Status.PASS, " viewDetailed button clicked successfully");


    }

    public void verifySavingDrugDetails() {
        javascriptExecutor().executeScript("arguments[0].click();", healthPlan);
        javascriptExecutor().executeScript("arguments[0].click();", coPay);
        javascriptExecutor().executeScript("arguments[0].setAttribute('value', '" + BaseClass.propertyFile("config", "enterQuantity") + "')", enterQty);
        javascriptExecutor().executeScript("arguments[0].setAttribute('value', '" + BaseClass.propertyFile("config", "Amount") + "')", payAmount);
        javascriptExecutor().executeScript("arguments[0].click();", saveButton);
        test.log(Status.PASS, " saved Duug Details  successfully");


    }


}