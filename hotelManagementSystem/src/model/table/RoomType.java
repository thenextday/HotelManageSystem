package model.table;

public class RoomType {

    private Integer rt_id;
    private String rt_type;
    private Float rt_price;

    public RoomType() {
    }

    public RoomType(int rt_id, String rt_type, float rt_price) {
        this.rt_id = rt_id;
        this.rt_type = rt_type;
        this.rt_price = rt_price;
    }

    public int getRt_id() {
        return rt_id;
    }

    public void setRt_id(int rt_id) {
        this.rt_id = rt_id;
    }

    public String getRt_type() {
        return rt_type;
    }

    public void setRt_type(String rt_type) {
        this.rt_type = rt_type;
    }

    public float getRt_price() {
        return rt_price;
    }

    public void setRt_price(float rt_price) {
        this.rt_price = rt_price;
    }

    @Override
    public String toString() {
        return "rooms_type{" +
                "rt_id=" + rt_id +
                ", rt_type='" + rt_type + '\'' +
                ", rt_price=" + rt_price +
                '}';
    }

}
