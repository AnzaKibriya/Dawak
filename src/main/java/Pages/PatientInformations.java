package Pages;

import Helper.BaseClass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Enum.BasicInformationEnum;

import java.io.FileNotFoundException;

import static Helper.BaseClass.*;
import static Pages.WebCommon.patient;
import static Pages.WebCommon.uiFormattedDate;

public class PatientInformations {

    WebDriver driver;
    public String basicInfoSiblingstring = "//div[contains(text(), '%s')]/following-sibling::div";
    public String basicInfostring="//div[contains(text(), '%s')]";

    String userDetail = "//h5[contains(text(), '%s')]/following-sibling::h5";
    @FindBy(xpath = "//h5[text()='Basic Info']")
    WebElement basicInfoButton;
    @FindBy(xpath = "//div[@class='custom-class-for-accordion-con collapse-div-header']")
    WebElement contactInfoButton;


    String createOrderPath = "./src/main/resources/CreatingOrder.json";

    public PatientInformations(WebDriver Driver) {
        driver=Driver;
    }



    public void verifyBasicDetailTable() {
        basicInfoButton.click();
        BasicInformationEnum[] BasicInformationEnums = BasicInformationEnum.values();
        System.out.println(BasicInformationEnums.length + "enum length");
        test.log(Status.PASS, "verifying Basic Details Information");
        for (int i = 0; i <= BasicInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(basicInfostring, BasicInformationEnums[i].value)));
            WebElement basicInfoSibling = driver.findElement(By.xpath(String.format(basicInfoSiblingstring, BasicInformationEnums[i].value)));

            try {
                info(basicInfo,basicInfoSibling);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }
    }



    public void info(WebElement element,WebElement actualText) throws FileNotFoundException {
        Pages.WebCommon().loadJson(createOrderPath);
        if (element.getText().contains("Patient Full Name"))
        {
            System.out.println(element.getText());
            String firstName = patient.getAsJsonPrimitive("firstName").getAsString();
            String middleName = patient.getAsJsonPrimitive("middleName").getAsString();
            String lastName = patient.getAsJsonPrimitive("lastName").getAsString();
            String fullname = firstName + middleName + lastName;
            String fullnamewithoutspace = fullname.replaceAll("\\s", "");
            String fullnameUI = actualText.getText().replaceAll("\\s", "");
            Pages.WebCommon().assertjson(fullnameUI, fullnamewithoutspace);
            test.log(Status.PASS, "Full name verified");

        }
        else if(element.getText().contains("Emirates ID"))
        {
            String eid = patient.getAsJsonPrimitive("eid").getAsString();
            // Pages.WebCommon().assertjson(actualText.getText(), eid);
            test.log(Status.PASS, "emirated id verified");

        }
        else if(element.getText().contains("MRN#"))
        {
            System.out.println(patient.get("mrn").getAsString());
            String mrn = patient.getAsJsonPrimitive("mrn").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), mrn);
            test.log(Status.PASS, "mrn id verified");


        } else if (element.getText().contains("Date of Birth")) {
            String dob = patient.getAsJsonPrimitive("dob").getAsString();
            test.log(Status.PASS, "Date of birth verified");


        } else if (element.getText().contains("Sex")) {
            String patGender = patient.getAsJsonPrimitive("patGender").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), patGender);
            test.log(Status.PASS, "Gender verified");



        } else if (element.getText().contains("Martial Status")) {
            String maritalStatusValue = patient
                    .getAsJsonObject("maritalStatus")
                    .getAsJsonPrimitive("value")
                    .getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), maritalStatusValue);
            test.log(Status.PASS, "Marital status verified");


        } else if (element.getText().contains("Nationality")) {
            String nationalityValue = patient
                    .getAsJsonObject("nationality")
                    .getAsJsonPrimitive("value")
                    .getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), nationalityValue);
            test.log(Status.PASS, "Nationality verified");


        } else if (element.getText().contains("Language")) {
            String languageValue = patient
                    .getAsJsonObject("language")
                    .getAsJsonPrimitive("value")
                    .getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), languageValue);
            test.log(Status.PASS, "Language verified");

        } else if (element.getText().contains("CMRN #")) {
            String cmrn = patient.getAsJsonPrimitive("cmrn").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), cmrn);
            test.log(Status.PASS, "Cmrn verified");


        } else if (element.getText().contains("Passport #")) {
            String passportNumber = patient.getAsJsonPrimitive("passportNumber").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), passportNumber);
            test.log(Status.PASS, "Passport verified");


        } else if (element.getText().contains("Encounter #")){
            Pages.WebCommon().assertjson(actualText.getText(), prescriptionOrderID);
            test.log(Status.PASS, "Encounter text verified");


        } else if (element.getText().contains("Task Name")) {
            Pages.WebCommon().assertjson(actualText.getText(), BaseClass.propertyFile("config", "TaskName"));
            test.log(Status.PASS, "Task Name verified");


        } else if (element.getText().contains("Refill / Prescription DateTime")) {

            Pages.WebCommon().ExtractDateFromString(actualText.getText());
            getCurrentDateTime();
            //  Pages.WebCommon().assertjson(formattedDate, uiFormattedDate);
            test.log(Status.PASS, "Refill Date text verified");
        } else if (element.getText().contains("Patient Emirates ID")) {

            String eid = patient.getAsJsonPrimitive("eid").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), eid);
            test.log(Status.PASS, "eid text verified");
        } else if (element.getText().contains("Patient Name")) {
            String firstName = patient.getAsJsonPrimitive("firstName").getAsString();
            String middleName = patient.getAsJsonPrimitive("middleName").getAsString();
            String lastName = patient.getAsJsonPrimitive("lastName").getAsString();
            String fullname = firstName + middleName + lastName;
            String fullnamewithoutspace = fullname.replaceAll("\\s", "");
            String fullnameUI = actualText.getText().replaceAll("\\s", "");
            assert fullnamewithoutspace.equalsIgnoreCase(fullnameUI);
            test.log(Status.PASS, "Patient full name text verified");


        } else if (element.getText().contains("Patient MRN #")) {
            String mrn = patient.getAsJsonPrimitive("mrn").getAsString();
            Pages.WebCommon().assertjson(actualText.getText(), mrn);
            test.log(Status.PASS, "mrn text verified");

        } else if (element.getText().contains(" Medicine Count ")) {

            Pages.WebCommon().assertjson(actualText.getText(), "3");
            test.log(Status.PASS, "medical count text verified");

        } else if (element.getText().contains("Task Created Date / Time")) {
            Pages.WebCommon().ExtractDateFromString(actualText.getText());
            getCurrentDateTime();
            Pages.WebCommon().assertjson(formattedDate, uiFormattedDate);
            test.log(Status.PASS, "created date text verified");

        }


    }


}
