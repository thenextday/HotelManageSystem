package model.dao;

import model.table.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.*;

public class ConnectionRestaurantTable {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    JdbcTemplate template = new JdbcTemplate(PoolUtils.getDataSource());


//    查询所有饮食
    public List<Restaurant> queryAllDiet (int startItem,int pageSize,String resName,int minPrice,int maxPrice) {
        List<Restaurant> list = null;
        String sql = "SELECT * FROM restaurant  WHERE is_delete = 0 ";
        sql += judgeCon(resName,minPrice,maxPrice);
        sql += " ORDER BY of_time DESC LIMIT "+startItem+","+pageSize;
        list = template.query(sql,new BeanPropertyRowMapper<>(Restaurant.class));
        return list;
    }

    public List<Restaurant> queryDiets () {
        List<Restaurant> list = null;
        String sql = "SELECT * FROM restaurant  WHERE is_delete = 1";
        list = template.query(sql,new BeanPropertyRowMapper<>(Restaurant.class));
        return list;
    }

//    查询所有饮食个数
    public int queryAllDietCount (String resName,int minPrice,int maxPrice) {
        String sql = "SELECT COUNT(*) FROM restaurant WHERE is_delete = 0 ";
        sql += judgeCon(resName,minPrice,maxPrice);
        int count = 0;
        count = template.queryForObject(sql,Integer.class);
        return count;
    }

    public int queryDietCoun () {
        String sql = "SELECT COUNT(*) FROM restaurant WHERE is_delete = 1";
        int count = template.queryForObject(sql,Integer.class);
        return count;
    }

    public String judgeCon (String resName,int minPrice,int maxPrice) {
        String sql = "";
        if (!resName.equals("")) {
            sql += "AND restaurant_name LIKE"+"'%"+resName+"%'";
        }
        if (minPrice != 0) {
            sql += " AND restaurant_price >= "+minPrice;
        }
        if (maxPrice != 0) {
            sql += " AND restaurant_price <= "+maxPrice;
        }
        return sql;
    }

//    插入新的菜肴
    public int insertDiet (String dishName,double dishPrice) {
        int countDiet = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into restaurant(restaurant_name,restaurant_price)" +
                    "values (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dishName);
            pstmt.setDouble(2,dishPrice);
            countDiet = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return countDiet;
        }
    }

//    删除菜肴
    public int deleteDiet (int id) {
        int dietCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE restaurant SET is_delete = 1 WHERE restaurant_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            dietCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return dietCount;
        }
    }

//    数据恢复
    public int sqlSever (double resPrice,String resName) {
        int dietCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE restaurant SET restaurant_price = ?,\n" +
                    "is_delete = 0,of_time = NOW() WHERE restaurant_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,resPrice);
            pstmt.setString(2,resName);
            dietCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return dietCount;
        }
    }

//    修改价格
    public int updateDiet(double resPrice,int resId) {
        int dietCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE restaurant SET restaurant_price = ? WHERE restaurant_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,resPrice);
            pstmt.setInt(2,resId);
            dietCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
            return dietCount;
        }
    }

}
