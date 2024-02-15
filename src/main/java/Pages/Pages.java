package Pages;

import Helper.BaseClass;
import org.openqa.selenium.support.PageFactory;

public class Pages {
    public static LoginCP LoginCP() {
        LoginCP pg = new LoginCP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static OrderDetails OrderDetails() {
        OrderDetails pg = new OrderDetails(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Home Home() {
        Home pg = new Home(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Common Common() {
        Common pg = new Common(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Mailinator Mailinator() {
        Mailinator pg = new Mailinator(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }


}
