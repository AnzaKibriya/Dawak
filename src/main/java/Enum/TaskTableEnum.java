package Enum;

public enum TaskTableEnum {

    Encounter("1"),

    Patient_Emirates_ID("4"),


    Patient_Name("5"),

    Patient_MRN("6");







    public String value;


    TaskTableEnum(String value) {
        this.value = value;

    }

    public void setValue(String value) {
        this.value = value;
    }

}
