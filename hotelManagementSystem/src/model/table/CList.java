package model.table;

import java.util.Date;

public class CList {

    private Integer cListId;
    private String resName;
    private Double resPrice;
    private Date orderTime;

    public CList() {
    }

    public CList(int cListId, String resName, Double resPrice, Date orderTime) {
        this.cListId = cListId;
        this.resName = resName;
        this.resPrice = resPrice;
        this.orderTime = orderTime;
    }

    public int getcListId() {
        return cListId;
    }

    public void setcListId(int cListId) {
        this.cListId = cListId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Double getResPrice() {
        return resPrice;
    }

    public void setResPrice(Double resPrice) {
        this.resPrice = resPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "CList{" +
                "cListId=" + cListId +
                ", resName='" + resName + '\'' +
                ", resPrice=" + resPrice +
                ", orderTime=" + orderTime +
                '}';
    }
}
