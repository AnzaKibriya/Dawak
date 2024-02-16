import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakCP extends BaseClass {
    @Test(priority = 1)
    public void verifyCPLogin() throws IOException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().invalidCPLogin();
        Pages.LoginCP().CPLogin();
        Pages.Mailinator().verifyOtp();
        Pages.LoginCP().verifyEnteringOtp();

    }

    @Test(priority = 2)
    public void verifyOrderInTOdo() {
        test = extent.createTest("Verify Making Order In TODO");
        Pages.Home().verifyHomePageHeader();
        Pages.Home().SearchForOrder();

    }

    @Test(priority = 3)
    public void verifyTodoColumnData() {

        test = extent.createTest("Verify Data present in in Todo or Inprogress column");
        Pages.Home().verifyDataInWebTable();

    }

    @Test(priority = 4)
    public void verifyMakingOrderInProgress() {
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.Home().MoveToNewPriscription();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();

    }


    @Test(priority = 5)
    public void verifyInprogressColumnData() {

        test = extent.createTest("Verify Data present in in Todo or Inprogress column");
        Pages.Home().verifyDataInWebTable();

    }

    @Test(priority = 6)
    public void verifyunassignfunctionality() {
        test = extent.createTest("Verify unassign functinality");
        Pages.Home().verifyReassign();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();
    }

    @Test(priority = 7)
    public void verifyOrderDetails() {
        test = extent.createTest("Verify order details data and Header text ");
        Pages.OrderDetails().openOrderDetailPage();
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyBasicInfo();
        Pages.OrderDetails().contactInfoDataTable();
        Pages.OrderDetails().verifyOrderDetailTable();
        Pages.OrderDetails().verifyOrderDetailsHeader();
        Pages.OrderDetails().verifyTrackDetailTable();
        Pages.OrderDetails().verifyViewDetails();
        Pages.OrderDetails().verifyRemovefunctionlity();
    }



    @Test(priority = 8)
    public void verifyInsurenceApproval() throws InterruptedException {
        test = extent.createTest("Verify Insurence Approval functionality ");

        Pages.OrderDetails().verifySendInsurenceApproval();
        Pages.OrderDetails().verifySavingDrugDetails();

    }
}