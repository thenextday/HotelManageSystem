package controllor;

import model.dao.ConnectionRoomTypeTable;
import model.table.RoomType;

import java.util.List;

public class RoomTypeManagement {
    ConnectionRoomTypeTable connectionRoomTypeTable = new ConnectionRoomTypeTable();

    /*查询房间类型的所有信息*/
    public Object[][] displayAllRoomType (int isDelete) {
        List<RoomType> list = connectionRoomTypeTable.queryAllRoomType(isDelete);
        Object [][] reciveRoomType = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            reciveRoomType[i][0] =list.get(i).getRt_id();
            reciveRoomType[i][1] =list.get(i).getRt_type();
            reciveRoomType[i][2] =list.get(i).getRt_price();
        }
        return reciveRoomType;
    }

    /*添加房间类型*/
    public int addRoomType (String roomType,int roomPrice){
        int count = connectionRoomTypeTable.insertRoomType(roomType,roomPrice);
        return count;
    }

    /*删除房间类型*/
    public int removeRoomType (int rt_Id) {
        int count = connectionRoomTypeTable.deleteRoomType(rt_Id);
        return count;
    }

    /*恢复已删除数据*/
    public int renewRoomType (String roomType,int roomPrice) {
        int count = connectionRoomTypeTable.resumeRoomType(roomType,roomPrice);
        return count;
    }

    /*修改房间类型的价格*/
    public int modifyRoomType (int typeId,double room_price) {
        int count = connectionRoomTypeTable.updateRoomPrice(typeId,room_price);
        return count;
    }
}
