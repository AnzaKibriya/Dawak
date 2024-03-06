package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

import static Helper.BaseClass.*;
import static java.time.Duration.ofSeconds;

public class MobileCommon {
    AndroidDriver androidDriver;
    By dawakAppLoader = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/success_logo_v\")");
    By addressCheckBox = AppiumBy.className("android.view.ViewGroup");

    public MobileCommon(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void waitForLoaderInvisibility() {
        List<WebElement> loaderElement = androidDriver.findElements(dawakAppLoader);
        if (!loaderElement.isEmpty()) {
            WebElement topLoaderElement = loaderElement.get(0);
            webWait.until(ExpectedConditions.invisibilityOf(topLoaderElement));
        }
    }

    public void waitForAddress() {
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(addressCheckBox));
    }
    public void  waitForAPIResponseToMirrorInAPP() throws InterruptedException {
        Thread.sleep(7000);
    }

    public void waitForElementsInteractions() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void scrollInMobile(WebElement webElement, String direction, String percentage){
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        scrollObject.put("element", ((RemoteWebElement) webElement).getId());
        scrollObject.put("percent", percentage);
        mobileJavascriptExecutor().executeScript("mobile: scrollGesture", scrollObject);
    }
}
