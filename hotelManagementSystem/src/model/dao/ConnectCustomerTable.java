package model.dao;

import model.table.Customer;
import org.springframework.jdbc.core.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectCustomerTable {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    JdbcTemplate template = new JdbcTemplate(PoolUtils.getDataSource());

    public boolean customerLogin(String phoneNumber, String cusPassword) {
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM customer WHERE customer_phone = ? AND customer_password = HEX(AES_ENCRYPT(?,2))" +
                    "and is_delete = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, cusPassword);
            rs = pstmt.executeQuery();
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn, rs);
            return flag;
        }
    }

    //查询素有顾客信息
    public List<Customer> queryAllCustomer(int startItem, int pageSize, String recCusPhone, String recCusRoNum, String cName) {
        List<Customer> list = null;
        String str = "SELECT * FROM customer WHERE is_delete = 0 ";
        str += judgeContion(recCusPhone,recCusRoNum,cName);
        str += " ORDER BY check_in_time DESC limit "+startItem+","+pageSize;
        list = template.query(str,new BeanPropertyRowMapper<>(Customer.class));
        return list;
    }

    public List<Customer> queryCus() {
        List<Customer> list = null;
        String str = "SELECT * FROM customer WHERE is_delete = 1 ";
        list = template.query(str,new BeanPropertyRowMapper<>(Customer.class));
        return list;
    }

    //查询总共有多少个顾客
    public int queryAllCustomerCount(String recCusPhone, String recCusRoNum, String cName) {
        int count = 0;
        String str = "SELECT COUNT(*) FROM customer WHERE is_delete = 0";
        str += judgeContion(recCusPhone,recCusRoNum,cName);
        count = template.queryForObject(str,Integer.class);
        return count;
    }


    public String judgeContion(String recCusPhone, String recCusRoNum, String cName) {
        String str = "";
        if(!recCusPhone.equals("")) {
            str += "AND customer_phone = "+recCusPhone;
        }
        if (!recCusRoNum.equals("")) {
            str += " AND room_num LIKE '"+recCusRoNum+"%'";
        }
        if (!cName.equals("")) {
            str += " AND customer_name LIKE +"+"'%"+cName+"%'";
        }
        return str;
    }

    public int queryCusCount() {
        int count = 0;
        String str = "SELECT COUNT(*) FROM customer WHERE is_delete = 1";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(str);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //添加新的顾客
    public int insertCustomer(String cusName, String cusPhone, String cusSex,
                              String cusRoNum, int lenOfDay) {
        int countCus = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO customer (customer_name,customer_phone,customer_password," +
                    "customer_sex,room_num,length_of_stay)VALUES" +
                    "(?,?,HEX(AES_ENCRYPT(?,2)),?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cusName);
            pstmt.setString(2, cusPhone);
            pstmt.setString(3, "000000");
            pstmt.setString(4, cusSex);
            pstmt.setString(5, cusRoNum);
            pstmt.setInt(6, lenOfDay);
            countCus = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
            return countCus;
        }
    }

    //    恢复数据
    public int recoverData(String cusRoomNum, int lenOfDay, String cusName, String cusPhone) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE customer SET is_delete = 0 ,room_num = ?,\n" +
                    " check_in_time = NOW(), length_of_stay = ?,\n" +
                    " customer_name = ? WHERE customer_phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cusRoomNum);
            pstmt.setInt(2, lenOfDay);
            pstmt.setString(3, cusName);
            pstmt.setString(4, cusPhone);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
            return count;
        }
    }

    //    逻辑删除顾客
    public int logicalDeletion(int cusId) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE customer SET is_delete = 1 WHERE customer_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cusId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
            return count;
        }
    }

    //    重置密码和修改密码
    public int updatePassword(int cusId, String newPassword) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE customer SET customer_password = HEX(AES_ENCRYPT(?,2)) WHERE customer_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, cusId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
            return count;
        }
    }

    public List<Object> addIt(int startItem,int size,List<Object> allMessage) {
        allMessage.add(startItem);
        allMessage.add(size);
        return allMessage;
    }

}