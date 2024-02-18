package Enum;

public enum PendingMedicineInformationEnum {
    Patient_Full_Name("Patient Full Name"),
    Emirates_ID("Emirates ID");

    public String value;

    PendingMedicineInformationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
