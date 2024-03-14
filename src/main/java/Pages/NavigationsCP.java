package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Helper.BaseClass.webJavascriptExecutor;
import static Helper.BaseClass.test;

public class NavigationsCP {
    WebDriver driver;
    @FindBy(xpath = "//mat-radio-button[3]/div/div/input")
    WebElement newPrescription;

    @FindBy(xpath = "//span[text()=' In-Progress ']")
    WebElement inProgressTabButton;

    @FindBy(xpath = "//img[@mattooltip='Details']")
    WebElement actionButton;

    @FindBy(xpath = "//span[text()=' To-do ']")
    WebElement toDo;


    @FindBy(xpath = "//span[text()=' Insurance In-Progress ']")
    WebElement insurenceInprogressButton;


    public NavigationsCP(WebDriver Driver) {
        driver = Driver;

    }

    public void navigateTOTodoTab()
    {
        webJavascriptExecutor().executeScript("arguments[0].click();", toDo);
    }

    public void moveToNewPrescription() {
        newPrescription.click();
        test.log(Status.PASS, "Navigated to new Prescription tab");
    }

    public void navigateTOInprogressTab()
    {
        test.log(Status.PASS, "Navigated to  in Inprogress tab");
        webJavascriptExecutor().executeScript("arguments[0].click();", inProgressTabButton);


    }



    public void openOrderDetailPage() {
        actionButton.click();
        Pages.WebCommon().waitForLoaderInvisibility();
    }

    public void navigateTOInsuranceInprogressTab() {
        webJavascriptExecutor().executeScript("arguments[0].click();", insurenceInprogressButton);
    }


}