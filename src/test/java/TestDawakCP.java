import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakCP extends BaseClass {
    @Test
    public void verifyCPLogin()throws IOException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().invalidCPLogin();
        Pages.LoginCP().CPLogin();
    }
     @Test
    public void verifyMakingOrderInProgress() throws IOException {
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.Home().verifyHomePageHeader();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();
     }
}
