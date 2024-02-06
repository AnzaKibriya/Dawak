import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakCP extends BaseClass {
    @Test(priority = 1)
    public void verifyCPLogin()throws IOException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().invalidCPLogin();
        Pages.LoginCP().CPLogin();
    }
     @Test (priority = 2)
    public void verifyMakingOrderInProgress(){
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.Home().verifyHomePageHeader();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();
     }
     @Test (priority = 3)
    public void verifyOrderDetails(){
        Pages.OrderDetails().openOrderDetailPage();
        Pages.OrderDetails().verifyOrderDetailTable();
     }
}
