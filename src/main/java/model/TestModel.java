package model;

public class TestModel {
    String NDC_Code;
    String Drug_Description;

    public String getNDC_Code() {
        return NDC_Code;
    }

    public void setNDC_Code(String NDC_Code) {
        this.NDC_Code = NDC_Code;
    }

    public String getDrug_Description() {
        return Drug_Description;
    }

    public void setDrug_Description(String drug_Description) {
        Drug_Description = drug_Description;
    }

    public TestModel(String NDC_Code, String Drug_Description) {
        this.NDC_Code = NDC_Code;
        this.Drug_Description = Drug_Description;
    }


}
