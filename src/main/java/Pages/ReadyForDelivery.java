package Pages;

import model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static Helper.BaseClass.webJavascriptExecutor;

public class ReadyForDelivery {
    WebDriver driver;
    @FindBy(xpath = "//*[@id='mat-tab-label-0-3']/span[2]/span")
    WebElement readyDelivery;
    @FindBy(xpath = "//*[@id=\"mat-tab-content-0-3\"]/div/app-dispencing-task-list/div/div[2]/table/tbody/tr/td[9]/div")
    WebElement details;

    public ReadyForDelivery(WebDriver Driver) {
        driver = Driver;
    }

    public void deliveryFunctionality(String ID) {
        webJavascriptExecutor().executeScript("arguments[0].click();", readyDelivery);
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.HomeDP().SearchForOrder();
        Pages.WebCommon().waitForLoaderInvisibility();
        details.click();
        String getUrl = driver.getCurrentUrl();
        String[] tokens = getUrl.split("/");
        GetShipaIdApiCall.setDeliveryID(tokens[tokens.length-2]);
        LoginDpApiCall.makeLoginApiCall();
        CreateOtpApiCall.createOtpApiCall();
        String dpAccessToken = PutOTPApiCall.OTPApiCall();
        String shipaOrderNum = GetShipaIdApiCall.makeShipaIdApiCall(dpAccessToken);
        ShipaInitiateEventApiCall.makeShipaInitiateEventApiCall(dpAccessToken, shipaOrderNum, "initiated");
        ShipaInitiateEventApiCall.makeShipaInitiateEventApiCall(dpAccessToken, shipaOrderNum, "Completed");

    }
}
