package model.dao;

import java.sql.*;

public class ConnectionAdministratorTable {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /*管理员登录*/
    public boolean administratorLogin(String phoneNumber,String adPassword) {
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM HotelManger WHERE hotelManger_phone = ? AND hotelManger_password = HEX(AES_ENCRYPT(?,2))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,phoneNumber);
            pstmt.setString(2,adPassword);
            rs = pstmt.executeQuery();
            flag = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return flag;
        }
    }
}
