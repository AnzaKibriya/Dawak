package Enum;

public enum viewDetailsInformationEnum {
    Brand_Name("Brand Name "),
    Drug_Description("Drug Description"),
    Manufacturer_Name("Manufacturer Name"),
    NDC_Code("NDC Code");

    public String value;

    viewDetailsInformationEnum(String value) {

        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
