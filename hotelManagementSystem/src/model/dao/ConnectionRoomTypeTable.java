package model.dao;

import model.table.RoomType;

import java.sql.*;
import java.util.*;

public class ConnectionRoomTypeTable {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean flag = false;
    int count = 0;

//    插入房间类型
    public int insertRoomType (String roomType,int roomPrice) {

        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into rooms_type (rt_type,rt_price) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomType);
            pstmt.setInt(2,roomPrice);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

//    查询所有房间类型
    public List<RoomType> queryAllRoomType(int isDelete) {
        List<RoomType> list = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from rooms_type WHERE is_delete = ? ORDER BY of_time DESC";
            pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1,isDelete);
            rs = pstmt.executeQuery();
            RoomType roomType = null;
            list = new ArrayList<>();
            while(rs.next()){
                roomType = new RoomType(rs.getInt(1),rs.getString(2),rs.getInt(3));
                list.add(roomType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return list;
        }
    }

    /*恢复已删除数据*/
    public int resumeRoomType(String roomType,int roomPrice) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE rooms_type SET rt_price = ?,of_time = NOW(),is_delete = 0 WHERE rt_type = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,roomPrice);
            pstmt.setString(2,roomType);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }




//    删除房间类型
    public int deleteRoomType (int rtId) {

        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE rooms_type SET is_delete = 1 WHERE rt_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,rtId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

//    更改房间类型的价格
    public int updateRoomPrice (int type_id ,double roomPrice){
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update rooms_type set rt_price = ? where rt_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,roomPrice);
            pstmt.setInt(2,type_id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }
}
