import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;
import Enum.PendingMedicineInformationEnum;

public class TestDawakCP extends BaseClass {
    PendingMedicineInformationEnum pendingMedicineInformationEnum;
    @Test
    public void loginToCP() throws InterruptedException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().testCPLogin();
        Pages.OrderDetails().verifyOrderDetailTable();
    }
}
