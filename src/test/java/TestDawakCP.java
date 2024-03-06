import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.awt.*;
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
    public void verifyOpeningProfile()
    {
        Pages.Home().verifyHomePageHeader();
        Pages.Profile().openProfile();

    }

    @Test(priority = 3)
    public void verifyEditprofile() throws InterruptedException, AWTException {

        Pages.Profile().editProfile();
        Pages.Profile().uploadProfilePicture();
        Pages.Profile().editName();
    }


    @Test(priority = 4)
    public void verifyOrderInTOdo() throws InterruptedException{
        test = extent.createTest("Verify Making Order In TODO");
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.Home().SearchForOrder(prescriptionOrderID);
    }

    @Test(priority = 5)
    public void verifyTodoColumnData() {
        test = extent.createTest("Verify Data present in Todo  column");
        Pages.Home().verifyDataInWebTable();
    }

    @Test(priority = 6)
    public void verifyMakingOrderInProgress() throws InterruptedException {
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.NavigationsCP().moveToNewPrescription();
        Pages.Home().clearSearch();
        Pages.Home().SearchForOrder(prescriptionOrderID);
        Pages.Home().clickOnAssign();
        Pages.NavigationsCP().navigateTOInprogressTab();
        Pages.Home().moveOrderToInProgressStateAndVerify();
    }

    @Test(priority = 7)
    public void verifyInProgressColumnData() {
        test = extent.createTest("Verify Data present in  In-progress column");
        Pages.Home().verifyDataInWebTable();
    }

   /* @Test(priority = 8)
    public void verifyUnAssignFunctionality() throws InterruptedException {
        test = extent.createTest("Verify un-assign functionality");
        Pages.Home().verifyReAssign();
        Pages.Home().SearchForOrder();
        Pages.Home().moveOrderToInProgressStateAndVerify();
    }*/

    @Test(priority = 9)
    public void verifyOrderDetails()  {
        test = extent.createTest("Verify order details data and Header text ");
        Pages.NavigationsCP().openOrderDetailPage();
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyBasicDetailTable();
        Pages.OrderDetails().verifyContactDetail();
        Pages.OrderDetails().verifyOrderDetailTable();
        Pages.OrderDetails().verifyOrderDetailsHeader();
        Pages.OrderDetails().verifyTrackDetailTable();
//        Pages.OrderDetails().verifyViewDetailsInformation();
        Pages.OrderDetails().verifyRemoveFunctionality();
    }

    @Test(priority = 10)
    public void verifyInsuranceApproval() throws InterruptedException {
        test = extent.createTest("Verify Insurance Approval functionality");
        Pages.OrderDetails().clickOnSendInsurenceApproval();
        Pages.NavigationsCP().navigateTOInsurenceInprogressTab();
        Pages.OrderDetails().verifySendInsuranceApproval();
        Pages.OrderDetails().approveMedicineInsurance();
    }

    @Test(priority = 11)
    public void verifyLogoutFunctionality() {
        test = extent.createTest("Logout Functionality");
        Pages.Logout().verifyLogout();
    }
}