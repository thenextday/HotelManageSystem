package model.table;

public class HotelManger {
    private Integer hotelMangerId;
    private String hotelMangerName;
    private String hotelMangerPassword;
    private String hotelMangerPhone;

    public HotelManger() {
    }

    public HotelManger(int hotelMangerId, String hotelMangerName, String hotelMangerPassword, String hotelMangerPhone) {
        this.hotelMangerId = hotelMangerId;
        this.hotelMangerName = hotelMangerName;
        this.hotelMangerPassword = hotelMangerPassword;
        this.hotelMangerPhone = hotelMangerPhone;
    }

    public int getHotelMangerId() {
        return hotelMangerId;
    }

    public void setHotelMangerId(int hotelMangerId) {
        this.hotelMangerId = hotelMangerId;
    }

    public String getHotelMangerName() {
        return hotelMangerName;
    }

    public void setHotelMangerName(String hotelMangerName) {
        this.hotelMangerName = hotelMangerName;
    }

    public String getHotelMangerPassword() {
        return hotelMangerPassword;
    }

    public void setHotelMangerPassword(String hotelMangerPassword) {
        this.hotelMangerPassword = hotelMangerPassword;
    }

    public String getHotelMangerPhone() {
        return hotelMangerPhone;
    }

    public void setHotelMangerPhone(String hotelMangerPhone) {
        this.hotelMangerPhone = hotelMangerPhone;
    }

    @Override
    public String toString() {
        return "HotelManger{" +
                "hotelMangerId=" + hotelMangerId +
                ", hotelMangerName='" + hotelMangerName + '\'' +
                ", hotelMangerPassword='" + hotelMangerPassword + '\'' +
                ", hotelMangerPhone='" + hotelMangerPhone + '\'' +
                '}';
    }
}
