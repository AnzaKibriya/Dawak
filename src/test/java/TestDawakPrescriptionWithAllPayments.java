import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDawakPrescriptionWithAllPayments extends BaseClass {

    @Test(priority = 1)
    public void verifyCPLogin() throws IOException, InterruptedException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().invalidCPLogin();
        Pages.LoginCP().CPLogin();
//        Pages.Mailinator().verifyOtp();
        Pages.LoginCP().verifyEnteringOtp();


    }

    @Test(priority = 2)
    public void verifyFilterValidation() throws InterruptedException, FileNotFoundException {

        test = extent.createTest("Verify Filter Validation");
        Pages.WebCommon().waitForElementsInteractions();
        Pages.Home().verifyFilters();


    }

   @Test(priority = 3)
    public void verifyOrderInTOdo() throws InterruptedException, FileNotFoundException {
        test = extent.createTest("Verify Making Order In TODO");
        Pages.WebCommon().waitForLoaderInvisibility();
        Pages.Home().SearchForOrder(prescriptionOrderID);
    }

    @Test(priority = 4)
    public void verifyTodoColumnData() {
        test = extent.createTest("Verify Data present in Todo  column");
        Pages.Home().verifyDataInWebTable();
    }

    @Test(priority = 5)
    public void verifyMakingOrderInProgress() throws InterruptedException, FileNotFoundException {
        test = extent.createTest("Verify Making Order In Progress State");
        Pages.NavigationsCP().moveToNewPrescription();
        Pages.Home().clearSearch();
        Pages.Home().SearchForOrder(prescriptionOrderID);
        Pages.Home().clickOnAssign();
        Pages.NavigationsCP().navigateTOInprogressTab();
        Pages.Home().moveOrderToInProgressStateAndVerify();
    }

    @Test(priority = 6)
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

    @Test(priority =7)
    public void verifyMoveToOrderDetails() throws InterruptedException {
        test = extent.createTest("Verify Navigation to order details page ");
        Pages.NavigationsCP().openOrderDetailPage();



    }
    @Test(priority = 8)
    public void verifyPatientInformation()
    {
        test = extent.createTest("Verify Navigation to order details page ");
        Pages.PatientInformations().verifyBasicDetailTable();
        Pages.PatientInformations().verifyContactDetail();
        Pages.PatientInformations().userDetails();



    }
    @Test(priority = 9)
    public void verifyOrderDetails() throws FileNotFoundException {
        test = extent.createTest("Verify order details data and Header text ");
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyOrderDetailTable();
        Pages.OrderDetails().verifyOrderDetailsHeader();
        Pages.OrderDetails().verifyTrackDetailTable();
//        Pages.OrderDetails().verifyViewDetailsInformation();
        Pages.OrderDetails().verifyRemoveFunctionality();
    }

    @Test(priority = 10)
    public void verifyInsuranceApproval() throws InterruptedException, FileNotFoundException {
        test = extent.createTest("Verify Insurance Approval functionality");
        Pages.OrderDetails().clickOnSendInsurenceApproval();
        Pages.NavigationsCP().navigateTOInsuranceInprogressTab();
        Pages.OrderDetails().verifySendInsuranceApproval();
        Pages.OrderDetails().insuranceApprovalUsingAllPayments();
    }

    @Test(priority = 11)
    public void verifyLogoutFunctionality() {
        test = extent.createTest("Logout Functionality");
        Pages.Logout().verifyLogout();
    }
}
