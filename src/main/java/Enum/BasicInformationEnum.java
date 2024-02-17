package Enum;

import lombok.Getter;

import java.util.stream.Stream;


public enum BasicInformationEnum {

    Patient_Full_Name("Patient Full Name"),
    Emirates_ID("Emirates ID"),

    Mrn("MRN#"),
    Date_of_Birth("Date of Birth"),

    Age("Age"),

    Sex("Sex"),
    Martial_Status("Martial Status"),

    Nationality("Nationality"),

    Language("Language"),

    CMRN("CMRN #"),

    Passport("Passport #");






    public String value;

    BasicInformationEnum(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
