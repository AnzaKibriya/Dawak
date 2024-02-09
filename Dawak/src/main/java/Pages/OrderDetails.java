package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import Enum.PendingMedicineInformationEnum;

import static Helper.BaseClass.*;

public class OrderDetails {
   int  i=2;
    WebDriver driver;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tbody/tr/td[8]/div/img[2]")
    WebElement actionButton;
    String medicinePendingInfoInTable = "//app-pending-medication-info//mat-drawer-content//tbody//td['" + i + "']";
    String  deliveryDetails="//app-address-detail//div//table//tbody//tr//td["+i+"]";

    String trackingDetails="//app-tracking-info/div/table/tbody/tr/td["+i+"]";


    @FindBy(xpath="//span[text()='New Prescription']")
    WebElement NewprescriptionText;


    @FindBy(xpath ="//span[text()='Insurance Pending Approval']")
    WebElement insurenceApprovaText;

    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void openOrderDetailPage() {
        actionButton.click();
        waitForLoaderInvisibility();
    }

    public void verifyOrderDetailTable() {
        List<WebElement> ele = driver.findElements(By.xpath(medicinePendingInfoInTable));

        for (int i = 1; i <= 5; i++) {
             details = ele.get(i).getText();
             System.out.println(details);
            if (details.isEmpty())
                break;
        }

        checkElementIsEmpty();



    }


    public void verifyOrderDetailsHeader()
    {
      System.out.println(NewprescriptionText.getText());
        Assert.assertEquals(NewprescriptionText.getText(), BaseClass.propertyFile("config", "Newprescription"));
        Assert.assertEquals(insurenceApprovaText.getText(),BaseClass.propertyFile("config", "InsurenceText"));

    }









//         try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
//                 .getResourceAsStream("/test.json")))) {
//             TestModel[] result = new Gson().fromJson(reader, TestModel[].class);
//             for (int i=2; i <=ele.size();i++){
//                 String AB = result[i-2].getNDC_Code();
//                 String ABC = ele.get(i).getText();
//                 Assert.assertEquals(ABC, AB);
//             }
//         } catch (IOException ignored) {
//         }

}
