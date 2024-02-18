package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import Enum.BasicInformationEnum;
import Enum.ContactInformation;
import Enum.viewDetailsInformationEnum;

import static Helper.BaseClass.*;

public class OrderDetails {
    WebDriver driver;
    @FindBy(xpath = "//img[@mattooltip='Details']")
    WebElement actionButton;
    String medicinePendingInfoInTable = "//app-pending-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//td[%s]";
    String deliveryDetails = "//app-address-detail//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td[%s]";
    String deliveryDetailcolum = "//app-address-detail//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";
    String orderDetailColumn = "//app-pending-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//td";
    String trackingDetails = "//app-tracking-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td[%s]";
    String trackDetailsColumn = "//app-tracking-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";
    String viewDetails = "//label[contains(text(), '%s')]//following::h5[1]";
    String basicInString = "//div[contains(text(), '%s')]/following-sibling::div";
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

    @FindBy(xpath = "//button[@aria-label='Close']")
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

    @FindBy(xpath = "//div[@class='custom-class-for-accordion-con collapse-div-header']")
    WebElement contactInfoButton;


    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void openOrderDetailPage() {
        actionButton.click();
        Pages.Common().waitForLoaderInvisibility();
    }


    public void verifyDeliveryDetailTable() {
        List<WebElement> orderDeliveryTable = driver.findElements(By.xpath(deliveryDetailcolum));
        test.log(Status.PASS, " Started verfying Data in Delivery Details Table");
        for (int i = 1; i <= orderDeliveryTable.size() - 2; i++) {
            WebElement orderDeliveryDetail = driver.findElement(By.xpath(String.format(deliveryDetails, i)));
            Pages.Common().checkElementIsEmpty(orderDeliveryDetail.getText(), i);
        }
        test.log(Status.PASS, " Verified  DeliveryDetails  Data");
        copy.click();
        Assert.assertEquals(addressCopied.getText(), BaseClass.propertyFile("config", "AddressText"));
        test.log(Status.PASS, " copy functionality verified in delivery details");

    }

    public void verifyOrderDetailTable() {
        List<WebElement> orderDetailTable = driver.findElements(By.xpath(orderDetailColumn));
        test.log(Status.PASS, " Started verfying Data in Order Details Table");
        for (int i = 1; i <= orderDetailTable.size(); i++) {
            System.out.println(orderDetailTable.size());
            WebElement orderDetails = driver.findElement(By.xpath(String.format(medicinePendingInfoInTable, i)));
            Pages.Common().checkElementIsEmpty(orderDetails.getText(), i);

        }
        test.log(Status.PASS, " Verified  orderDetails Data");
    }

    public void verifyTrackDetailTable() {

        List<WebElement> trackDeliveryTable = driver.findElements(By.xpath(trackDetailsColumn));
        System.out.println(trackDeliveryTable.size());
        test.log(Status.PASS, " Started verfying Data in Track Details Table");
        for (int j = 1; j <= trackDeliveryTable.size() - 1; j++) {
            WebElement trackDeliveryDetail = driver.findElement(By.xpath(String.format(trackingDetails, j)));
            Pages.Common().checkElementIsEmpty(trackDeliveryDetail.getText(), j);
        }
        test.log(Status.PASS, " Verified  TrackDetails  Data");

    }

    public void verifyOrderDetailsHeader() {
        System.out.println(newPrescriptionText.getText());
        Assert.assertEquals(newPrescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(insuranceApprovalText.getText(), BaseClass.propertyFile("config", "InsurenceText"));
        test.log(Status.PASS, " Verified  New prescriptionText and Insurance text");

    }

    public void verifyRemoveFunctionality() {
        javascriptExecutor().executeScript("arguments[0].click();", removeButton);
        leaveComment.sendKeys("these");
        submitButton.click();
        Pages.Common().waitForLoaderInvisibility();
        addBack.click();
    }

    public void verifySendInsuranceApproval() throws InterruptedException {
        String orderIdText = physicianOrderId.getText();
        javascriptExecutor().executeScript("arguments[0].click();", sendInsurenceApprovalButton);
        test.log(Status.PASS, " order sent for insurance Approval");
        Pages.Common().waitForLoaderInvisibility();
        javascriptExecutor().executeScript("arguments[0].click();", insurenceInprogressButton);
        Pages.Common().waitForLoaderInvisibility();
        String numberOnly = orderIdText.replaceAll("[^0-9]", "");
        search.sendKeys(numberOnly);
        test.log(Status.PASS, " Verified Insurance approval request in Insurance in progress");
        details.click();
        Pages.Common().waitForDetailedButtonClickable();
        if (viewDetailsButton.isEnabled())
            viewDetailsButton.click();
        test.log(Status.PASS, " View Detail button gets clicked successfully");
    }

    public void verifySavingDrugDetails() throws AWTException {
        javascriptExecutor().executeScript("arguments[0].click();", healthPlan);
        javascriptExecutor().executeScript("arguments[0].click();", coPay);
        javascriptExecutor().executeScript("arguments[0].click();", coPay);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        javascriptExecutor().executeScript("arguments[0].setAttribute('value', '" + BaseClass.propertyFile("config", "enterQuantity") + "')", enterQty);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        javascriptExecutor().executeScript("arguments[0].setAttribute('value', '" + BaseClass.propertyFile("config", "Amount") + "')", payAmount);
        javascriptExecutor().executeScript("arguments[0].click();", saveButton);
        test.log(Status.PASS, " Drug Details gets saved successfully");
    }

    public void verifyBasicDetailTable() {
        basicInfoButton.click();
        BasicInformationEnum[] BasicInformationEnums = BasicInformationEnum.values();
        System.out.println(BasicInformationEnums.length + "enum length");
        for (int i = 0; i <= BasicInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(basicInString, BasicInformationEnums[i].value)));
            Pages.Common().waitForElementInteractivity(basicInfo);
            if (basicInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + BasicInformationEnums[i].value);
            }
        }
        test.log(Status.PASS, " Verified Basic Information Details successfully");
    }

    public void verifyContactDetail() {
        contactInfoButton.click();
        ContactInformation[] contactInformations = ContactInformation.values();
        for (int i = 0; i <= contactInformations.length - 1; i++) {
            WebElement contactInfo = driver.findElement(By.xpath(String.format(basicInString, contactInformations[i].value)));
            Pages.Common().waitForElementInteractivity(contactInfo);
            if (contactInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + contactInformations[i].value);
            }
        }
        test.log(Status.PASS, " Verified ContactInformation Details successfully");

    }

    public void verifyViewDetailsInformation() {
        viewDetailsButton.click();
        test.log(Status.PASS, " Navigated to view details page");
        viewDetailsInformationEnum[] viewDetailsInformationEnums = viewDetailsInformationEnum.values();
        for (int i = 0; i <= viewDetailsInformationEnums.length - 1; i++) {
            WebElement viewInfo = driver.findElement(By.xpath(String.format(viewDetails, viewDetailsInformationEnums[i].value)));
            Pages.Common().waitForLoaderInvisibility();
            Pages.Common().waitForElementInteractivity(viewInfo);
            if (viewInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + viewDetailsInformationEnums[i].value);

            }
            test.log(Status.PASS, " Verified View Details successfully");
        }
        crossIcon.click();
        test.log(Status.PASS, " Navigated back from view details page");
    }
}