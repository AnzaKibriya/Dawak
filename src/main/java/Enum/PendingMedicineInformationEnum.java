package Enum;

import java.util.stream.Stream;

public enum PendingMedicineInformationEnum {
    NDC_Code("99991-0098-32"),
    Drug_Description("Montelukast chewable 5mg Tablet"),
    Medicine_Qty_Status("No Change");
    public String value;

    PendingMedicineInformationEnum(String value) {
        this.value = value;
    }

    public String getValue1() {
        return value;
    }
    public void setValue1(String value) {
        this.value = value;
    }


    public static Stream<PendingMedicineInformationEnum> stream() {
        return Stream.of(PendingMedicineInformationEnum.values());
    }
}
