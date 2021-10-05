package controllor;

import model.dao.ConnectionRoomsTable;
import model.table.Rooms;

import java.util.List;

public class RoomBusiness {
    ConnectionRoomsTable crt = new ConnectionRoomsTable();
    int count = 0;
//   查询所有房间
    public Object[][] displayAllRooms (String roomNum,String roomType,int page,int perPageCount,String selectRoomType,int isDelete) {
        List<Rooms> list = null;
        if (isDelete == 0) {
            list = crt.queryAllRooms(roomNum,roomType,page,perPageCount,selectRoomType);
        }else if (isDelete == 1) {
            list = crt.queryRos();
        }

        Object [][] roomsMessage = returnArray(list);
        return roomsMessage;
    }

    /*将集合转为数组*/
    public Object[][] returnArray (List<Rooms> list) {
        Object [][] arr = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            arr[i][0] = list.get(i).getRoomId();
            arr[i][1] = list.get(i).getRoomNum();
            arr[i][2] = list.get(i).getRoomType();
            arr[i][3] = list.get(i).getC_number();
        }
        return arr;
    }
//    判断是否有该房间
    public boolean judgeHouse (String roomNum) {
        boolean flag = crt.judgeRoom(roomNum);
        return flag;
    }

//  查询个数
    public int displayRoomCount(String roomNum,String roomType,String selectRoomType,int isDelete) {
        if (isDelete == 0) {
            count = crt.queryRoomsCount(roomNum,roomType,selectRoomType);
        } else if (isDelete == 1) {
            count = crt.querRoCon();
        }
        return count;
    }

//    添加房间
    public int addRooms (String roomNum,String roomType) {
        count = crt.insertRooms(roomNum,roomType);
        return count;
    }

//   添加房间
    public int modifyRoomsType(String roomType,int roomId) {
        count = crt.updateRoomsType(roomType,roomId);
        return count;
    }

//    删除房间
    public int cutRoom(int roomId){
        count = crt.deleteRooms(roomId);
        return count;
    }

//    查询房间人数
    public int toKnowRoomNum (String roomNum) {
        count = crt.queryRooms(roomNum);
        return count;
    }

//    更改房间入住人数
    public int modifyRoNum (String roomNum,int cNumber) {
        count = crt.updateRoomNum(roomNum,cNumber);
        return count;
    }

//    恢复之前已被删除的数据
    public int huifuData (String roomNum,String roomType) {
        int count = crt.recData(roomNum,roomType);
        return count;
    }
}
