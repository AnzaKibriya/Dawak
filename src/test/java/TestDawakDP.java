import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.awt.*;

public class TestDawakDP extends BaseClass {

    @Test(priority = 1)
    public void verifyDPLogin() throws InterruptedException {
        test = extent.createTest("Login to DP Portal");
        Pages.LoginDP().DPLogin();
        Pages.Mailinator().verifyDpOtp();
        Pages.LoginDP().verifyEnteringOtp();
//        Pages.ReadyForDelivery().deliveryFunctionality("8664809384");
    }

    @Test(priority = 2)
    public void verifyOrderInTOdo() throws InterruptedException {
        test = extent.createTest("Verify Making Order In TODO");
        Pages.HomeDP().verifyHomePageHeader();
        Pages.HomeDP().SearchForOrder();
    }

    @Test(priority = 3)
    public void verifyOrderInProgress()  {
        test = extent.createTest("Verify Making Order In InProgressTAB");
        Pages.HomeDP().moveToInProgress();
    }

    @Test(priority = 4)
    public void verifyOrderDispensing() throws InterruptedException {
        test = extent.createTest("Verify Making Order In Dispensing TAB");
        Pages.OrderDetailsDP().dispensingOrder();
        Pages.HomeDP().verifyOrderInDispensingInProgress();
        Pages.OrderDetailsDP().orderReadyForDelivery();
    }

    @Test(priority = 5)
    public void verifyLogoutFunctionality() {
        test = extent.createTest("Logout Functionality");
        Pages.Logout().verifyLogout();
    }
}
