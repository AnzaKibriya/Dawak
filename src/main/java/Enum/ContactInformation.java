package Enum;

public enum ContactInformation {

    Patient_Contact("Patient Contact#"),

    App_User_Contact("App User Contact#");


    public String value;


    ContactInformation(String value) {
        this.value = value;

    }

    public void setValue(String value) {
        this.value = value;
    }

}
