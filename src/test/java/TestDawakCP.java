import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakCP extends BaseClass {
    @Test
    public void loginToCP() throws InterruptedException, IOException {
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().testCPLogin();
    }
}
