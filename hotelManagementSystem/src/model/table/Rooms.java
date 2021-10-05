package model.table;

public class Rooms {
    private Integer roomId;
    private String roomNum;
    private String roomType;
    private Integer c_number;

    public Rooms() {
    }

    public Rooms(int roomId, String roomNum, String roomType, Integer c_number) {
        this.roomId = roomId;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.c_number = c_number;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getC_number() {
        return c_number;
    }

    public void setC_number(Integer c_number) {
        this.c_number = c_number;
    }
}