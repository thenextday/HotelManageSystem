package model.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/*
* 数据库连接池的工具类
* */
public class PoolUtils {
    /*
    * 1.定义成员变量 DataSource
    * */
    private static DataSource ds;

    static {
       try {
//            1.加载配置文件
            Properties pro = new Properties();
            pro.load(PoolUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            2.获取DataSource
           ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 获取连接的方法
    * */
    public static Connection getConnection () throws SQLException {
        return ds.getConnection();
    }

    /*
    * 关闭资源的方法,用的是方法重载来实现不同的参数关闭
    * */
    public static void close(ResultSet rs, Statement stmt,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close (Statement stmt,Connection conn) {
        close(null,stmt,conn);
    }

    /*
    * 获取连接池的方法
    * */
    public static DataSource getDataSource () {
        return ds;
    }
}
