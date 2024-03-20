package Enum;

public enum FilterDataEnum {


    patientPrescription("patientPrescription"),


    patientEID("patientEID"),

    patientFullName("patientFullName"),

    patientMRN("patientMRN");







    public String value;


    FilterDataEnum(String value) {
        this.value = value;

    }

    public void setValue(String value) {
        this.value = value;
    }

}
