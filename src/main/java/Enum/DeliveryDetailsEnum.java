package Enum;

public enum DeliveryDetailsEnum {


    Delivery_Date("Delivery Date"),
    Delivery_Time("Delivery Time"),

    Mode_of_Payment("Mode of Payment"),
    Payment_Status("Payment Status");



    public String value;

    DeliveryDetailsEnum(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
