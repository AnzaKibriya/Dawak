package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.List;

import Enum.viewDetailsInformationEnum;
import Enum.BasicInformationEnum;

import static Helper.BaseClass.*;

public class OrderDetails {
    WebDriver driver;

    String medicinePendingInfoInTable = "//app-pending-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//td[%s]";
    String deliveryDetails = "//app-address-detail//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td[%s]";
    String deliveryDetailcolum = "//app-address-detail//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";
    String orderDetailColumn = "//app-pending-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//td";
    String trackingDetails = "//app-tracking-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td[%s]";
    String trackDetailsColumn = "//app-tracking-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//td";
    String viewDetails = "//label[contains(text(), '%s')]//following::h5[1]";
    String basicInString = "//div[contains(text(), '%s')]/following-sibling::div";

    String viewDetailscolumn = "//mat-drawer-content//tbody//button[2]/span[contains(@class, 'mat-mdc-button-persistent-ripple')]";


    String healthPlan = "(//mat-label[text()='Health Plans'])[%s]";
    String enterQty = "(//input[@placeholder='Enter Qty'])[%s]";
    String payAmount = "(//input[@placeholder='Enter Co-Pay Amount in AED'])[%s]";
    String commentTextbox = "(//textarea[@id='mat-input-15'])[%s]";
    String saveButton = ("(//span[normalize-space()='Save'])[%s]");
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

    @FindBy(xpath = "//app-pending-medication-info//tbody[contains(@class, 'mdc-data-table__content')]//tr[1]//span[text()=' View Details ']")
    WebElement viewDetailsButton;

    @FindBy(xpath = "//span[normalize-space()='Send Insurance for Approval']")
    WebElement sendInsuranceApprovalButton;


    @FindBy(xpath = "//input[@placeholder='Search by Attribute']")
    WebElement search;

    @FindBy(xpath = "//img[@mattooltip='Details']")
    WebElement details;

    @FindBy(xpath = "//h5[text()='Basic Info']")
    WebElement basicInfoButton;


    @FindBy(xpath = "//span[normalize-space()='Co Pay']")
    WebElement coPay;

    @FindBy(xpath = "//span[text()='No Pay']")
    WebElement noPay;

    @FindBy(xpath = "//span[text()='Self Pay']")
    WebElement selfPay;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-flex ng-tns-c6-28']//textarea[@rows='5']")
    WebElement comment;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix ng-tns-c6-45']//textarea[@rows='5']")
    WebElement rejectionReasonSecondMedicine;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix ng-tns-c6-45']//textarea[@rows='5']")
    WebElement rejectionReasonThirdMedicine;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix ng-tns-c6-31']//textarea[@rows='5']")
    WebElement ThirdMedicineComment;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix ng-tns-c6-31']//textarea[@rows='5']")
    WebElement secondMedicineComment;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix ng-tns-c6-14']//textarea[@rows='5']")
    WebElement reason;


    @FindBy(xpath = "//span[text()=' Task Complete ']")
    WebElement taskCompletedButton;

    @FindBy(xpath = "//app-task-list//table//tr[1]//td[1]")
    WebElement encounterNumberInProgressPage;


    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyDeliveryDetailTable() {
        List<WebElement> orderDeliveryTable = driver.findElements(By.xpath(deliveryDetailcolum));
        test.log(Status.PASS, " Started verfying Data in Delivery Details Table");
        for (int i = 1; i <= orderDeliveryTable.size() - 2; i++) {
            WebElement orderDeliveryDetail = driver.findElement(By.xpath(String.format(deliveryDetails, i)));
            Pages.WebCommon().checkElementIsEmpty(orderDeliveryDetail.getText(), i);
        }
        test.log(Status.PASS, " Verified  DeliveryDetails  Data");
        copy.click();
        Assert.assertEquals(addressCopied.getText(), BaseClass.propertyFile("config", "AddressText"));
        test.log(Status.PASS, " copy functionality verified in delivery details");

    }

    public void verifyOrderDetailTable() {
        List<WebElement> orderDetailTable = driver.findElements(By.xpath(orderDetailColumn));
        test.log(Status.PASS, " Started verifying Data in Order Details Table");
        for (int i = 1; i <= 6; i++) {
            System.out.println(orderDetailTable.size());
            WebElement orderDetails = driver.findElement(By.xpath(String.format(medicinePendingInfoInTable, i)));
            Pages.WebCommon().checkElementIsEmpty(orderDetails.getText(), i);

        }
        test.log(Status.PASS, " Verified  orderDetails Data");
    }

    public void verifyTrackDetailTable() {

        List<WebElement> trackDeliveryTable = driver.findElements(By.xpath(trackDetailsColumn));
        System.out.println(trackDeliveryTable.size());
        test.log(Status.PASS, " Started verfying Data in Track Details Table");
        for (int j = 1; j <= trackDeliveryTable.size() - 1; j++) {
            WebElement trackDeliveryDetail = driver.findElement(By.xpath(String.format(trackingDetails, j)));
            Pages.WebCommon().checkElementIsEmpty(trackDeliveryDetail.getText(), j);
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
        webJavascriptExecutor().executeScript("arguments[0].click();", removeButton);
        leaveComment.sendKeys("these");
        submitButton.click();
        Pages.WebCommon().waitForLoaderInvisibility();
        addBack.click();

    }

    public void clickOnSendInsurenceApproval() throws InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.WebCommon().waitForElementsInteractions();
        webJavascriptExecutor().executeScript("arguments[0].click();", sendInsuranceApprovalButton);
        test.log(Status.PASS, " order sent for insurance Approval");
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.WebCommon().waitForElementsInteractions();

    }

    public void verifySendInsuranceApproval() throws InterruptedException, FileNotFoundException {

        Pages.WebCommon().waitForLoaderInvisibility();
        driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), BaseClass.propertyFile("config", "InsuurenceInprogressUrl"));
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.Home().SearchForOrder(prescriptionOrderID);

        test.log(Status.PASS, " Verified Insurance approval request in Insurance in progress");
        details.click();
        Pages.WebCommon().waitForDetailedButtonClickable();
        Pages.WebCommon().waitForLoaderInvisibility();


    }

    public void verifyBasicDetail() {
        basicInfoButton.click();
        BasicInformationEnum[] BasicInformationEnums = BasicInformationEnum.values();
        System.out.println(BasicInformationEnums.length + "enum length");
        for (int i = 0; i <= BasicInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(basicInString, BasicInformationEnums[i].value)));
            Pages.WebCommon().waitForElementInteractivity(basicInfo);
            if (basicInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + BasicInformationEnums[i].value);
            }
        }
        test.log(Status.PASS, " Verified Basic Information Details successfully");
    }


    public void verifyViewDetailsInformation() {
        viewDetailsButton.click();
        test.log(Status.PASS, " Navigated to view details page");
        viewDetailsInformationEnum[] viewDetailsInformationEnums = viewDetailsInformationEnum.values();
        for (int i = 0; i <= viewDetailsInformationEnums.length - 91; i++) {
            WebElement viewInfo = driver.findElement(By.xpath(String.format(viewDetails, viewDetailsInformationEnums[i].value)));
            Pages.WebCommon().waitForLoaderInvisibility();
            Pages.WebCommon().waitForElementInteractivity(viewInfo);
            if (viewInfo.getText().isEmpty()) {
                Assert.fail("No Data present in " + viewDetailsInformationEnums[i].value);

            }
            test.log(Status.PASS, " Verified View Details successfully");
        }
        crossIcon.click();
        test.log(Status.PASS, " Navigated back from view details page");
    }


    public void approveMedicineInsuranceUsingCopay() throws InterruptedException {

        List<WebElement> detail = driver.findElements(By.xpath(viewDetailscolumn));
        int counter=0;
        for (int i = 1; i <= detail.size(); i++) {
            webJavascriptExecutor().executeScript("arguments[0].click();", viewDetailsButton);
            if(i==3)
            {
                i--;
            }
                Pages.WebCommon().waitForElementsInteractions();
                test.log(Status.PASS, " View Detail button gets clicked successfully");
                WebElement enterQuantity = driver.findElement(By.xpath(String.format(enterQty, i)));
                enterQuantity.sendKeys(BaseClass.propertyFile("config", "enterQuantity"));
                WebElement clickHealthPlan = driver.findElement(By.xpath(String.format(healthPlan, i)));
                clickHealthPlan.click();
                coPay.click();
                WebElement enterPaymentAmount = driver.findElement(By.xpath(String.format(payAmount, i)));
                enterPaymentAmount.sendKeys(BaseClass.propertyFile("config", "Amount"));
            if(i==1) {
                //webJavascriptExecutor().executeScript("arguments[0].value = '" + "Test" + "'", comment);
                reason.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
            }
            if(i==2)
            {  if(counter==1) {
                secondMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
            }
                if(counter==2)
                {
                    ThirdMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));

                }
            }

            WebElement clickSaveBtn = driver.findElement(By.xpath(String.format(saveButton, i)));
                clickSaveBtn.click();
                Pages.WebCommon().waitForLoaderInvisibility();
                test.log(Status.PASS, " Drug Details gets saved successfully ");
                driver.navigate().refresh();
                Pages.WebCommon().waitForLoaderInvisibility();
                counter++;
                if(counter==3)
                {
                    i++;
                }


        }
        taskCompletedButton.click();
        test.log(Status.PASS, " clicked on Task completed Button ");

    }

    public void approveMedicineInsuranceselfpay() throws InterruptedException {
        List<WebElement> detail = driver.findElements(By.xpath(viewDetailscolumn));
        int count=0;
        for (int i = 1; i <= detail.size(); i++) {
            webJavascriptExecutor().executeScript("arguments[0].click();", viewDetailsButton);
            if(i==3)
            {
                i--;

            }
                Pages.WebCommon().waitForElementsInteractions();
                test.log(Status.PASS, " View Detail button gets clicked successfully");
                WebElement enterQuantity = driver.findElement(By.xpath(String.format(enterQty, i)));
                enterQuantity.sendKeys(BaseClass.propertyFile("config", "enterQuantity"));
                WebElement clickHealthPlan = driver.findElement(By.xpath(String.format(healthPlan, i)));
                clickHealthPlan.click();
                selfPay.click();
                WebElement enterPaymentAmount = driver.findElement(By.xpath(String.format(payAmount, i)));
                enterPaymentAmount.sendKeys(BaseClass.propertyFile("config", "Amount"));

            if(i==1) {
                //webJavascriptExecutor().executeScript("arguments[0].value = '" + "Test" + "'", comment);
                    comment.sendKeys(BaseClass.propertyFile("config", "selfpaypaymentComment"));
                    reason.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
                }
                if(i==2)
                {  if(count==1) {
                    rejectionReasonSecondMedicine.sendKeys(BaseClass.propertyFile("config", "selfpaypaymentComment"));
                    secondMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
                }
                    if(count==2)
                    {
                        rejectionReasonThirdMedicine.sendKeys(BaseClass.propertyFile("config", "selfpaypaymentComment"));
                        ThirdMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));

                    }
                }
                WebElement clickSaveBtn = driver.findElement(By.xpath(String.format(saveButton, i)));
                Pages.WebCommon().waitForElementsInteractions();
                clickSaveBtn.click();
                Pages.WebCommon().waitForLoaderInvisibility();
                test.log(Status.PASS, " Drug Details gets saved successfully");
                driver.navigate().refresh();
                Pages.WebCommon().waitForLoaderInvisibility();
                count++;
                if(count==3)
                {
                    i++;
                }


        }
        taskCompletedButton.click();
        test.log(Status.PASS, " clicked on Task completed Button ");

    }


    public void insuranceApprovalUsingAllPayments() throws InterruptedException {
        List<WebElement> detail = driver.findElements(By.xpath(viewDetailscolumn));
        for (int i = 1; i <= detail.size(); i++) {
            webJavascriptExecutor().executeScript("arguments[0].click();", viewDetailsButton);

            if (i == 1 || i == 2) {
                Pages.WebCommon().waitForElementsInteractions();
                test.log(Status.PASS, " View Detail button gets clicked successfully");
                WebElement enterQuantity = driver.findElement(By.xpath(String.format(enterQty, i)));
                enterQuantity.sendKeys(BaseClass.propertyFile("config", "enterQuantity"));
                WebElement clickHealthPlan = driver.findElement(By.xpath(String.format(healthPlan, i)));
                clickHealthPlan.click();
                if (i == 1) {
                    coPay.click();
                }

                if (i == 2) {
                    selfPay.click();
                }
                WebElement enterPaymentAmount = driver.findElement(By.xpath(String.format(payAmount, i)));
                enterPaymentAmount.sendKeys(BaseClass.propertyFile("config", "Amount"));
                if(i==1) {
                    //webJavascriptExecutor().executeScript("arguments[0].value = '" + "Test" + "'", comment);
                    reason.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
                }
                if(i==2) {
                        rejectionReasonSecondMedicine.sendKeys(BaseClass.propertyFile("config", "selfpaypaymentComment"));
                        secondMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));

                }

                    WebElement clickSaveBtn = driver.findElement(By.xpath(String.format(saveButton, i)));
                clickSaveBtn.click();
                Pages.WebCommon().waitForLoaderInvisibility();
                test.log(Status.PASS, " Drug Details gets saved successfully");
                driver.navigate().refresh();
                Pages.WebCommon().waitForLoaderInvisibility();


            }

            if (i == 3) {
                i--;
                Pages.WebCommon().waitForElementsInteractions();
                test.log(Status.PASS, " View Detail button gets clicked successfully");
                WebElement enterQuantity = driver.findElement(By.xpath(String.format(enterQty, i)));
                enterQuantity.sendKeys(BaseClass.propertyFile("config", "enterQuantity"));
                WebElement clickHealthPlan = driver.findElement(By.xpath(String.format(healthPlan, i)));
                clickHealthPlan.click();
                noPay.click();
                ThirdMedicineComment.sendKeys(BaseClass.propertyFile("config", "paymentComment"));
                WebElement clickSaveBtn = driver.findElement(By.xpath(String.format(saveButton, i)));
                clickSaveBtn.click();
                Pages.WebCommon().waitForLoaderInvisibility();
                test.log(Status.PASS, " Drug Details gets saved successfully");
                driver.navigate().refresh();
                Pages.WebCommon().waitForLoaderInvisibility();
                i++;
            }

        }

        taskCompletedButton.click();
        test.log(Status.PASS, " clicked on Task completed Button ");


    }
}

