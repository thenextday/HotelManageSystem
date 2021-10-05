package model.dao;

import model.table.Rooms;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.*;

public class ConnectionRoomsTable {
    JDBCUtils jdbcUtils = new JDBCUtils();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    List<Rooms> list = null;
    JdbcTemplate template = new JdbcTemplate();


    //  分页查询房间表里面的数据
    public List<Rooms> queryAllRooms (String roomNum,String roomType,int page,int perPageCount,String selectRoomType) {

        String sql = "SELECT * FROM rooms WHERE is_delete = 0 ";
        sql += jdgeCon(roomNum,roomType,selectRoomType);
        sql += " ORDER BY of_time DESC LIMIT "+page+","+perPageCount;
        list = returnList(sql);
        return list;
    }

    public List<Rooms> queryRos () {
        String sql = "SELECT * FROM rooms WHERE is_delete = 1";
        list = returnList(sql);
        return list;
    }

    public List<Rooms> returnList (String sql) {
        List<Rooms> llist = null;
        try {
            conn = jdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Rooms rooms = null;
            llist = new ArrayList<>();
            while (rs.next()) {
                rooms = new Rooms(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                llist.add(rooms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(pstmt,conn,rs);
        }
        return llist;
    }

    //      查询所有房间数
    public int queryRoomsCount (String roomNum,String roomType,String selectRoomType) {
        String sql = "SELECT COUNT(*) FROM rooms WHERE is_delete = 0 ";
        sql += jdgeCon(roomNum,roomType,selectRoomType);
        count = retuCoun(sql);
        return count;
    }

    public int querRoCon () {
        String sql = "SELECT COUNT(*) FROM rooms WHERE is_delete = 1";
        count = retuCoun(sql);
        return count;
    }

    public int retuCoun (String sql) {
        try {
            conn = jdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String jdgeCon (String roomNum,String roomType,String selectRoomType) {
        String sql = "";
        if (!roomNum.equals("")) {
            sql += " AND room_num LIKE '"+roomNum+"%'";
        }
        if (!roomType.equals(selectRoomType)) {
            sql += " AND r_type = '"+roomType+"'";
        }
        return sql;
    }

    //判断是否有该房间
    public boolean judgeRoom (String roomNum) {
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT c_number FROM rooms WHERE room_num = ? and is_delete = 0";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomNum);
            rs = pstmt.executeQuery();
            flag =  rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return flag;
        }
    }

    /*返回房间入住人数*/
    public int queryRooms (String roomNum) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT c_number FROM rooms WHERE room_num = ? and is_delete = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomNum);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt("c_number");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return count;
        }
    }

    //    向房间表中添加数据
    public int insertRooms(String roomNum ,String roomType) {
        String sql = "";
        try {
            conn = JDBCUtils.getConnection();
            sql = "INSERT INTO rooms (room_num,r_type) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomNum);
            pstmt.setString(2,roomType);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

    //    更改房间的房间类型
    public int updateRoomsType(String roomType,int roomId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE rooms SET r_type = ? WHERE room_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomType);
            pstmt.setInt(2,roomId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

    //    删除具体某个房间
    public int deleteRooms(int roomId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE rooms SET is_delete = 1 WHERE room_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,roomId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

    //    更改房间人数
    public int updateRoomNum (String roomNum,int cNumber) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update rooms set c_number = ? where room_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,cNumber);
            pstmt.setString(2,roomNum);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

    //    还原数据
    public int recData (String roomNum,String roomType) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE rooms SET is_delete = 0,of_time = NOW(),r_type = ? WHERE room_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomType);
            pstmt.setString(2,roomNum);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }
}
