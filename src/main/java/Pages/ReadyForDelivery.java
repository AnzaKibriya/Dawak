package Pages;

import model.GetShipaIdApiCall;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static Helper.BaseClass.accessToken;

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
//        javascriptExecutor().executeScript("arguments[0].click();", readyDelivery);
//        Pages.WebCommon().waitForLoaderInvisibility();
//        Pages.HomeDP().SearchForOrder(ID);
//        Pages.WebCommon().waitForLoaderInvisibility();
//        details.click();
//        String getUrl = driver.getCurrentUrl();
//        String[] tokens = getUrl.split("/");
//        GetShipaIdApiCall.setDeliveryID(tokens[tokens.length-2]);
        GetShipaIdApiCall.makeShipaIdApiCall(accessToken);
//Iterating here for the next element
//        for (String a : tokens) {
//            System.out.println("StringTokenizer Output: " + tokens.length()-1);
//        }
    }
}
