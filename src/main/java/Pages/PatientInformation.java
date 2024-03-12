package Pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Enum.ContactInformation;
import java.io.FileNotFoundException;
import Enum.BasicInformationEnum;
import Enum.UserDetailsEnum;

import static Helper.BaseClass.prescriptionOrderID;
import static Helper.BaseClass.test;
import static Pages.WebCommon.order;
import static Pages.WebCommon.patient;

public class PatientInformation {

    WebDriver driver;

    @FindBy(xpath = "//h5[text()='Basic Info']")
    WebElement basicInfoButton;
    String basicInString = "//div[contains(text(), '%s')]/following-sibling::div";

    String userDetail = "//h5[contains(text(), '%s')]/following-sibling::h5";
    @FindBy(xpath = "//div[@class='custom-class-for-accordion-con collapse-div-header']")
    WebElement contactInfoButton;


    String createOrderPath = "./src/main/resources/CreatingOrder.json";

    public PatientInformation(WebDriver Driver) {
        driver = Driver;
    }


    public void verifyBasicDetailTable() throws FileNotFoundException {
        basicInfoButton.click();
        BasicInformationEnum[] BasicInformationEnums = BasicInformationEnum.values();
        System.out.println(BasicInformationEnums.length + "enum length");
        for (int i = 0; i <= BasicInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(basicInString, BasicInformationEnums[i].value)));
            Pages.WebCommon().loadJson(createOrderPath);
            switch (i) {
                case 0:
                    String firstName = patient.getAsJsonPrimitive("firstName").getAsString();
                    System.out.println(firstName);
                    String middleName = patient.getAsJsonPrimitive("middleName").getAsString();
                    System.out.println(middleName);
                    String lastName = patient.getAsJsonPrimitive("lastName").getAsString();
                    System.out.println(lastName);
                    String fullname = firstName + middleName + lastName;
                    String fullnamewithoutspace = fullname.replaceAll("\\s", "");
                    String fullnameUI = basicInfo.getText().replaceAll("\\s", "");
                    Pages.WebCommon().assertjson(fullnameUI, fullnamewithoutspace);
                    break;
                case 1:
                    String eid = patient.getAsJsonPrimitive("eid").getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), eid);
                    break;
                case 2:
                    String mrn = patient.getAsJsonPrimitive("mrn").getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), mrn);
                    break;
                case 3:
                    String dob = patient.getAsJsonPrimitive("dob").getAsString();
                    System.out.println(basicInfo.getText());
                    System.out.println(dob);
                    //  assertjson(basicInfo.getText(), dob);
                    break;
                case 4:
                   /* String dob=patient.getAsJsonPrimitive("dob").getAsString();
                    System.out.println(dob);
                    assertjson(basicInfo.getText(),dob);*/
                    break;
                case 5:
                    String patGender = patient.getAsJsonPrimitive("patGender").getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), patGender);
                    break;
                case 6:
                    String maritalStatusValue = patient
                            .getAsJsonObject("maritalStatus")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), maritalStatusValue);
                    break;
                case 7:
                    String nationalityValue = patient
                            .getAsJsonObject("nationality")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), nationalityValue);
                    break;

                case 8:
                    String languageValue = patient
                            .getAsJsonObject("language")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), languageValue);
                    break;
                case 9:
                    String cmrn = patient.getAsJsonPrimitive("cmrn").getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), cmrn);
                    break;
                case 10:
                    String passportNumber = patient.getAsJsonPrimitive("passportNumber").getAsString();
                    Pages.WebCommon().assertjson(basicInfo.getText(), passportNumber);
                    break;
                default:
                    System.out.println("value not found");
                    // code block
            }


        }
        test.log(Status.PASS, " verified Basic Details");


    }
    public void contactDetailsTable() throws FileNotFoundException {
        contactInfoButton.click();
        ContactInformation[] contactInformations = ContactInformation.values();
        for (int i = 0; i <= contactInformations.length - 1; i++) {
            WebElement contactInfo = driver.findElement(By.xpath(String.format(basicInString, contactInformations[i].value)));
            Pages.WebCommon().loadJson(createOrderPath);
            switch (i) {
                case 0:
                    String phoneNumber = patient.getAsJsonPrimitive("phoneNumber").getAsString();
                    System.out.println(contactInfo.getText());
                    System.out.println(phoneNumber);
                    Pages.WebCommon().assertjson(contactInfo.getText(), phoneNumber);
                    break;
                case 1:
                    Pages.WebCommon().assertjson(contactInfo.getText(), "+971502201010");
                    break;
                default:
                    System.out.println("value not found");
            }


        }
        test.log(Status.PASS, " verified Contact Details");


    }

    public void userDetailsValidation() throws FileNotFoundException {
        UserDetailsEnum[] userData=UserDetailsEnum.values();
        for(int i=0;i<=userData.length-1;i++)
        {
            WebElement userInfo=driver.findElement(By.xpath(String.format(userDetail,userData[i].value)));
            Pages.WebCommon().loadJson(createOrderPath);
            switch (i)
            {
                case 0:
                    Pages.WebCommon().assertjson(userInfo.getText(),prescriptionOrderID);
                    break;
                case 1:
                    String prescriberValue = order
                            .getAsJsonObject("physician")
                            .getAsJsonPrimitive("prescriber")
                            .getAsString();
                    Pages.WebCommon().assertjson(userInfo.getText(),prescriberValue);
                    break;
                case 2:
                    String orderLocationValue = order
                            .getAsJsonObject("orderLocation")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    Pages.WebCommon().assertjson(userInfo.getText(),orderLocationValue);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("vale not found");
            }



        }
        test.log(Status.PASS, " verified user Details");


    }
}











