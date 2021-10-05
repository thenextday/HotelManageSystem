package model.dao;
import model.table.*;
import java.sql.*;
import java.util.*;

public class ConnectionClistTable {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    /*添加新的订单*/
    public int insertList (int cusId,int resId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO clist (customer_id,restaurant_id) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,cusId);
            pstmt.setInt(2,resId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }
// 查询顾客的餐饮订单信息
    public List<CList> queryLists (String cusPhone) {
        List<CList> list = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT cl.`clist_id`,res.`restaurant_name`,\n" +
                    "res.`restaurant_price`, cl.`order_time`  \n" +
                    "FROM restaurant res,clist cl,customer cus \n" +
                    "WHERE cl.`restaurant_id` = res.`restaurant_id` \n" +
                    "AND cl.`customer_id` = cus.`customer_id`\n" +
                    "AND cl.`is_delete` = 0  \n" +
                    "AND cus.`customer_phone` = ? ORDER BY cl.`order_time` DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,cusPhone);
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            CList cList = null;
            while (rs.next()) {
                cList = new CList(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDate(4));
                list.add(cList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return list;
        }
    }
//    查询顾客住房费用
    public List<PersonTotalCost> queryLiveCost (String cusPhone,int isAll,int startItem,int perPageSize ) {
        List<PersonTotalCost> list = null;
        String sql = "";
        try {
            conn = JDBCUtils.getConnection();
            if (isAll == 0) {
                sql = "SELECT cus.`customer_phone`, cus.`customer_name`,cus.`length_of_stay`*rt.`rt_price`\n" +
                        "FROM customer cus,rooms ro,rooms_type rt\n" +
                        "WHERE cus.`room_num` = ro.`room_num`\n" +
                        "AND ro.`r_type` = rt.`rt_type` \n" +
                        "AND cus.`customer_phone` = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,cusPhone);
            } else if (isAll == 1) {
                sql = "SELECT cus.`customer_phone`, cus.`customer_name`,cus.`length_of_stay`*rt.`rt_price`\n" +
                        "FROM customer cus,rooms ro,rooms_type rt\n" +
                        "WHERE cus.`room_num` = ro.`room_num`\n" +
                        "AND ro.`r_type` = rt.`rt_type`\n" +
                        "AND rt.`is_delete` = 0\n" +
                        "AND ro.`is_delete` = 0\n" +
                        "AND cus.`is_delete` = 0\n" +
                        "ORDER BY check_in_time DESC\n" +
                        "LIMIT ?,?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,startItem);
                pstmt.setInt(2,perPageSize);
            }
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            PersonTotalCost personTotalCost = null;
            while (rs.next()) {
                personTotalCost = new PersonTotalCost(rs.getString(1),rs.getString(2),rs.getInt(3));
                list.add(personTotalCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return list;
        }
    }

    /*查询顾客的所有数量*/
    public int queryAll() {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*)\n" +
                    "FROM customer cus,rooms ro,rooms_type rt\n" +
                    "WHERE cus.`room_num` = ro.`room_num`\n" +
                    "AND ro.`r_type` = rt.`rt_type` \n" +
                    "AND cus.`is_delete` = 0\n" +
                    "AND ro.`is_delete` = 0\n" +
                    "AND rt.`is_delete` = 0";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return count;
        }
    }

//    返回餐饮消费总和
    public List<PersonTotalCost> queryDietCost (String cusPhone,int isAll) {
        List<PersonTotalCost> list = null;
        String sql = "";
        try {
            conn = JDBCUtils.getConnection();
            if (isAll == 0) {
                sql = "SELECT cus.`customer_phone`, cus.`customer_name`, SUM(res.`restaurant_price`)\n" +
                        "FROM `clist` cls,`customer` cus,`restaurant` res \n" +
                        "WHERE cls.`customer_id` = cus.`customer_id` \n" +
                        "AND cls.`is_delete` = 0\n" +
                        "AND cls.`restaurant_id` = res.`restaurant_id` \n" +
                        "AND cus.`customer_phone` = ?\n" +
                        " GROUP BY cus.`customer_phone`";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,cusPhone);
            } else if (isAll == 1) {
                sql = "SELECT cus.`customer_phone`, cus.`customer_name`, SUM(res.`restaurant_price`)\n" +
                        "FROM `clist` cls,`customer` cus,`restaurant` res \n" +
                        "WHERE cls.`customer_id` = cus.`customer_id` \n" +
                        "AND cls.`is_delete` = 0\n" +
                        "AND cls.`restaurant_id` = res.`restaurant_id` \n" +
                        " GROUP BY cus.`customer_phone`";
                pstmt = conn.prepareStatement(sql);
            }
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            PersonTotalCost personTotalCost = null;
            while (rs.next()) {
                personTotalCost = new PersonTotalCost(rs.getString(1),rs.getString(2),rs.getInt(3));
                list.add(personTotalCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn,rs);
            return list;
        }
    }

    /*逻辑删除顾客的订单*/
    public int deleteClist(int cusId) {
        int count = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE clist SET is_delete = 1 WHERE customer_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,cusId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return count;
        }
    }

}
