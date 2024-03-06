import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;
public class TestDawakDP extends BaseClass {

    @Test(priority = 1)
    public void verifyCPLogin() {
        test = extent.createTest("Login to DP Portal");
        Pages.LoginDP().DPLogin();
        Pages.Mailinator().verifyDpOtp();
        Pages.LoginDP().verifyEnteringOtp();
    }

    @Test(priority = 2)
    public void verifyOrderInTOdo()  {
        test = extent.createTest("Verify Making Order In TODO");
        Pages.HomeDP().verifyHomePageHeader();
        Pages.HomeDP().SearchForOrder(prescriptionOrderID);
        Pages.HomeDP().clickonAssign();
    }

    @Test(priority = 3)
    public void verifyOrderInProgress() throws InterruptedException {
        test = extent.createTest("Verify Making Order In InProgressTAB");
        Pages.NavigationsDP().navigateTOInprogressTab();
        Pages.HomeDP().moveToInprogressandVerify();
        Pages.HomeDP().clickonDetailButtonInInprogressTab();
    }



    @Test(priority = 4)
    public void verifyDataInOrderDispensing()
    {

        test = extent.createTest("Verify Data in order Dispensing");
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyBasicDetailTable();
        Pages.OrderDetails().verifyContactDetail();
        Pages.OrderDetailsDP().verifyMedicationDetailTable();
        Pages.OrderDetailsDP().verifyDeliveryDetail();
        Pages.OrderDetailsDP().verifyOrderDetailsHeader();
        Pages.OrderDetails().verifyTrackDetailTable();
//

    }

    @Test(priority = 5)
    public void verifyOrderDispensing() throws InterruptedException {
        test = extent.createTest("Verify Making Order In Dispensing TAB");

//
        Pages.OrderDetailsDP().dispensingOrder();

    }


    @Test(priority = 6)
    public void verifyDataInReadyforDelivery()
    {
        test = extent.createTest("Verify Data In ReadyForDelivery");
        Pages.NavigationsDP().navigateTODispensingInProgressTab();
        Pages.HomeDP().searchOrderInDispensingInProgress();
        Pages.HomeDP().clickDetailButtonInDispensingInprogress();
        Pages.OrderDetails().verifyDeliveryDetailTable();
        Pages.OrderDetails().verifyBasicDetailTable();
        Pages.OrderDetails().verifyContactDetail();
        Pages.OrderDetailsDP().verifyMedicationDetailTable();
        Pages.OrderDetailsDP().verifyDeliveryDetail();
        Pages.OrderDetailsDP().verifyDespensingOrderDetailsHeader();


    }

    @Test(priority = 7)
    public void verifyOrderReadyforDelivery() throws InterruptedException {

        test = extent.createTest("verifying order ready for Delivery ");
        Pages.OrderDetailsDP().orderReadyForDelivery();

    }
    @Test(priority = 8)
    public void verifyLogoutFunctionality() {
        test = extent.createTest("Logout Functionality");
        Pages.Logout().verifyLogout();
    }
}
