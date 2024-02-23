import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.awt.*;

public class TestDawakDP extends BaseClass {
    @Test(priority = 1)
    public void verifyCPLogin() {
        test = extent.createTest("Login to DP Portal");
        Pages.LoginDP().DPLogin();
//        Pages.Mailinator().verifyDpOtp();
        Pages.LoginDP().verifyEnteringOtp();
    }

    @Test(priority = 2)
    public void verifyOrderInTOdo() throws InterruptedException {
        test = extent.createTest("Verify Making Order In TODO");
        Pages.HomeDP().verifyHomePageHeader();
        Pages.HomeDP().SearchForOrder();

    }

    @Test(priority = 3)
    public void verifyorderInInprogress() throws InterruptedException, AWTException {
        test = extent.createTest("Verify Making Order In InProgressTAB");

        Pages.HomeDP().moveTOInprogress();


    }

    @Test(priority = 4)
    public void verifyOrderDespencing() throws InterruptedException {
        test = extent.createTest("Verify Making Order In Despencing TAB");
        Pages.OrderDetailsDP().dispencingOrder();
        Pages.HomeDP().verifyOrderInDispensingInProgress();
        Pages.OrderDetailsDP().ordrReadyForDelivery();

    }

}
