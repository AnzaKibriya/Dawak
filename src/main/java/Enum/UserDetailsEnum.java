package Enum;

public enum UserDetailsEnum {

    Physician_Encounter_Id("Physician Encounter Id#"),


    Prescribed_By("Prescribed By"),

    Order_Location("Order Location"),
    Channel("Channel"),

    Task_Created_Date_TIme("Task Created Date / TIme");





    public String value;


    UserDetailsEnum(String value) {
        this.value = value;

    }

    public void setValue(String value) {
        this.value = value;
    }

}

