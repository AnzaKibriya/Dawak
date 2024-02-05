import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

import static Helper.BaseClass.extent;
import static Helper.BaseClass.test;

public class TestInvalidLogin {

    @Test
    public void invalidlogin() throws InterruptedException, IOException {
        test= extent.createTest("invalid login ");
        Pages.LoginCP().invalidlogin();
    }
}
