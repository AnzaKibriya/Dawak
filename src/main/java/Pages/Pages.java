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


}
