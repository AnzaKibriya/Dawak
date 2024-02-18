package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import Enum.PendingMedicineInformationEnum;

import static Helper.BaseClass.waitForLoaderInvisibility;

public class OrderDetails {
    int i = 2;
    WebDriver driver;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tbody/tr/td[8]/div/img[2]")
    WebElement actionButton;
    String medicinePendingInfoInTable = "//div[contains(text(), '%s')]/following-sibling::div";

    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void openOrderDetailPage() {
        actionButton.click();
        waitForLoaderInvisibility();
        waitForLoaderInvisibility();

    }

    public void verifyOrderDetailTable() {
        PendingMedicineInformationEnum[] pendingMedicineInformationEnums = PendingMedicineInformationEnum.values();
        for (int i = 0; i <= pendingMedicineInformationEnums.length; i++) {
            WebElement ele = driver.findElement(By.xpath(String.format(medicinePendingInfoInTable, pendingMedicineInformationEnums[i].value)));
            Assert.assertNotEquals(ele.getText(), "");
        }
//         try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
//                 .getResourceAsStream("/CreatingOrder.json")))) {
//             TestModel[] result = new Gson().fromJson(reader, TestModel[].class);
//             for (int i=2; i <=ele.size();i++){
//                 String AB = result[i-2].getNDC_Code();
//                 String ABC = ele.get(i).getText();
//                 Assert.assertEquals(ABC, AB);
//             }
//         } catch (IOException ignored) {
//         }
    }
}
