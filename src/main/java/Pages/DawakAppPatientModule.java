package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.List;

import static Helper.BaseClass.*;
import static Pages.WebCommon.patient;

public class DawakAppPatientModule {
    AndroidDriver androidDriver;
    String createOrderPath = "./src/main/resources/CreateNewPatient.json";
    String textViews = "//androidx.recyclerview.widget.RecyclerView[@resource-id='ae.purehealth.dawak.qa:id/patient_detail_rv']/android.view.ViewGroup";
    By addFamilyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/add_patient_btn");
    By relationDropdown = AppiumBy.id("ae.purehealth.dawak.qa:id/relation_tv");
    By relationOtherCheckBox = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvRelation\"]/android.view.ViewGroup[5]");
    By confirmRelationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By otpFields = AppiumBy.id("ae.purehealth.dawak.qa:id/otp_view");

    By emiratesIdField = AppiumBy.id("ae.purehealth.dawak.qa:id/mask_et");
    By registerBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/register_btn");
    By verifyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/next_btn");
    By proceedBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/register_btn");
    By successLabel = AppiumBy.id("ae.purehealth.dawak.qa:id/success_label_tv");
    By dashboardNavigateBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/dashbord_navigator_btn");


    public DawakAppPatientModule(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    public void clickOnAddFamilyBtn() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(addFamilyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void addNewPatient() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationDropdown)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationOtherCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmRelationBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(emiratesIdField)).sendKeys(emiratesID);
        mobileWait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
    public void verifyOTP(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(otpFields)).sendKeys("1234");
        mobileWait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void verifyPatientDetailsAndProceed() throws FileNotFoundException {
        List<WebElement> listviews = androidDriver.findElements(By.xpath(textViews));
        Pages.WebCommon().loadJson(createOrderPath);
        softAssert.assertEquals(patient.getAsJsonPrimitive("eid").getAsString(), listviews.get(0).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText());
        softAssert.assertEquals(prescriptionOrderID, listviews.get(0).findElement(By.id("ae.purehealth.dawak.qa:id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonPrimitive("dob").getAsString().replaceAll("/", "-"), listviews.get(1).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText());
        softAssert.assertEquals("9715" + prescriptionOrderID, listviews.get(2).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText());
        softAssert.assertEquals("Others", listviews.get(2).findElement(By.id("ae.purehealth.dawak.qa:id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("nationality").getAsJsonPrimitive("value").getAsString(), listviews.get(3).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonPrimitive("patGender"), listviews.get(3).findElement(By.id("ae.purehealth.dawak.qa:id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("maritalStatus").getAsJsonPrimitive("value").getAsString(), listviews.get(4).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("language").getAsJsonPrimitive("value").getAsString(), listviews.get(4).findElement(By.id("ae.purehealth.dawak.qa:id/description_2_tv")).getText());
        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
    }

    public void navigateBackToDashboard(){
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(successLabel)).getText();
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(successLabel)).getText(), "Patient Added Successfully!");
    }
}
