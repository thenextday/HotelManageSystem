package model.table;

public class PersonTotalCost {

    private  String customerPhone;
    private String customerName;
    private Integer totCost;

    public PersonTotalCost() {
    }

    public PersonTotalCost(String customerPhone, String customerName, int totCost) {
        this.customerPhone = customerPhone;
        this.customerName = customerName;
        this.totCost = totCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotCost() {
        return totCost;
    }

    public void setTotCost(int totCost) {
        this.totCost = totCost;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public String toString() {
        return "PersonTotalCost{" +
                "customerPhone='" + customerPhone + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totCost=" + totCost +
                '}';
    }
}
