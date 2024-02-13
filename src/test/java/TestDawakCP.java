import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakCP extends BaseClass {
    @Test(priority = 1)
    public void verifyCPLogin() throws IOException, InterruptedException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().invalidCPLogin();
        Pages.LoginCP().CPLogin();
        Pages.LoginCP().verifyEnteringOtp();

    }

    @Test(priority = 2)
    public void verifyMakingOrderInProgress() {
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.Home().verifyHomePageHeader();
        Pages.Home().SearchForOrder();
        Pages.Home().MoveToNewPriscription();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();

    }

    @Test(priority = 3)
    public void verifyUnAssignFunctionality() {
        test = extent.createTest("Verify unassign functinality");
        Pages.Home().verifyReassign();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();
    }

    @Test(priority = 4)
    public void verifyOrderDetails() throws IOException {
        test = extent.createTest("Verify order details data and Header text ");
        Pages.OrderDetails().openOrderDetailPage();
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyOrderDetailTable();
        Pages.OrderDetails().verifyOrderDetailsHeader();
        Pages.OrderDetails().verifyBasicInfo();
        Pages.OrderDetails().verifyTrackDetailTable();
        Pages.OrderDetails().verifyviewDetails();
        Pages.OrderDetails().verifyRemovefunctionlity();

    }


    @Test(priority = 5)
    public void verifyInsurenceApproval() {
        test = extent.createTest("Verify Insurence Approval functionality ");

        Pages.OrderDetails().verifySendInsurenceApproval();

    }
}