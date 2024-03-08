package Pages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;

import Enum.BasicInformationEnum;

import static Helper.BaseClass.test;


public class BasicDetails {

    WebDriver driver;

    @FindBy(xpath = "//h5[text()='Basic Info']")
    WebElement basicInfoButton;

    String basicInString = "//div[contains(text(), '%s')]/following-sibling::div";


    public BasicDetails(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyBasicDetailTable() throws FileNotFoundException {
        basicInfoButton.click();
        BasicInformationEnum[] BasicInformationEnums = BasicInformationEnum.values();
        System.out.println(BasicInformationEnums.length + "enum length");
        for (int i = 0; i <= BasicInformationEnums.length - 1; i++) {
            WebElement basicInfo = driver.findElement(By.xpath(String.format(basicInString, BasicInformationEnums[i].value)));
            System.out.println(basicInfo.getText());
            JsonParser jsonParser = new JsonParser();
            FileReader reader;
            reader = new FileReader("./src/main/resources/CreatingOrder.json");
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            // Extract patient information
            JsonObject patient = jsonObject.getAsJsonObject("patient");
            // Pretty-print patient information
            String patientJsonString = gson.toJson(patient);

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
                    assertjson(fullnameUI, fullnamewithoutspace);
                    break;
                case 1:
                    String eid = patient.getAsJsonPrimitive("eid").getAsString();
                    assertjson(basicInfo.getText(), eid);
                    break;
                case 2:
                    String mrn = patient.getAsJsonPrimitive("mrn").getAsString();
                    Assert.assertEquals(basicInfo.getText(), mrn);
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
                    assertjson(basicInfo.getText(), patGender);
                    break;
                case 6:
                    String maritalStatusValue = patient
                            .getAsJsonObject("maritalStatus")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    assertjson(basicInfo.getText(), maritalStatusValue);
                    break;
                case 7:
                    String nationalityValue = patient
                            .getAsJsonObject("nationality")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    assertjson(basicInfo.getText(), nationalityValue);
                    break;

                case 8:
                    String languageValue = patient
                            .getAsJsonObject("language")
                            .getAsJsonPrimitive("value")
                            .getAsString();
                    assertjson(basicInfo.getText(), languageValue);
                    break;
                case 9:
                    String cmrn = patient.getAsJsonPrimitive("cmrn").getAsString();
                    assertjson(basicInfo.getText(), cmrn);
                    break;
                case 10:
                    String passportNumber = patient.getAsJsonPrimitive("passportNumber").getAsString();
                    assertjson(basicInfo.getText(), passportNumber);
                    break;
                default:
                    System.out.println("value not found");
                    // code block
            }


        }

    }

    public void assertjson(String expected, String actual) {
        Assert.assertEquals(expected, actual);
    }
}




