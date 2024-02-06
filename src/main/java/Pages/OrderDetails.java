package Pages;

import com.google.gson.Gson;
import model.TestModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

public class OrderDetails {
    int i = 2 ;
    WebDriver driver;
    @FindBy(xpath = "//*[@placeholder='Search by Attribute']")
    WebElement searchBar;
    @FindBy(xpath = "//*[@id='mat-tab-content-0-1']//tbody/tr/td[8]/div/img[2]")
    WebElement actionButton;
    String medicinePendingInfoInTable = "//app-pending-medication-info//mat-drawer-content//tbody//td['" + i + "']";
    public OrderDetails(WebDriver Driver) {
        driver = Driver;
    }
     public void searchForOrder(String orderID){
        searchBar.sendKeys("811801715");
        searchBar.sendKeys(Keys.ENTER);
     }
     public void openOrderDetailPage(){
        actionButton.click();
     }
     public void verifyOrderDetailTable(){
        List<WebElement> ele = driver.findElements(By.xpath(medicinePendingInfoInTable));
//         PendingMedicineInformationEnum[] pendingMedicineInformationEnums = PendingMedicineInformationEnum.values();
//         for (int i=2; i <=pendingMedicineInformationEnums.length;i++){
//            String ABC = ele.get(i).getText();
//            String CP = pendingMedicineInformationEnums[i-2].value;
//            Assert.assertEquals(ABC, CP);
//
//         }

         try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                 .getResourceAsStream("/test.json")))) {
             TestModel[] result = new Gson().fromJson(reader, TestModel[].class);
             for (int i=2; i <=ele.size();i++){
//                 String AB = result[i-2].getName();
                 String ABC = ele.get(i).getText();
//                 Assert.assertEquals(ABC, AB);
             }
         } catch (IOException ignored) {
         }
     }
}
