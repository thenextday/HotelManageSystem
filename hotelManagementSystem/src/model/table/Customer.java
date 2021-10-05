package model.table;

import java.util.Date;

/**
 *
 */
public class Customer {
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String customerPassword;
    private String customerSex;
    private String roomNum;
    private Date checkInTime;
    private Integer lengthOfStay;
    private Integer isDelete;

    public Customer() {

    }

    public Customer(int customerId, String customerName, String customerPhone, String customerPassword, String customerSex,
                    String roomNum, Date checkInTime, int lengthOfStay, int isDelete) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerPassword = customerPassword;
        this.customerSex = customerSex;
        this.roomNum = roomNum;
        this.checkInTime = checkInTime;
        this.lengthOfStay = lengthOfStay;
        this.isDelete = isDelete;
    }
//
    /*
    *
    *
    *
    * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId () {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerSex='" + customerSex + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", checkInTime=" + checkInTime +
                ", lengthOfStay=" + lengthOfStay +
                ", isDelete=" + isDelete +
                '}';
    }
}
