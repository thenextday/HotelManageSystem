package model.dao;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private  static String password;
    private static String driver;
    /*
    * @文件读取一次即可拿到这些值,
    * 使用静态代码快只会随着类的加载而加载，只会执行一次。
    * */
    static {
        try {
//            创建properties集合类
            Properties pro = new Properties();
//            通过类加载器获取文件
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
//            在ClassLoader类下的getResource方法就是将指定名字的文件所在的绝对路径给返回。
//            URL表示统一资源定位符，可以给定一个资源的绝对路径
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();//获取字符串路径
//            加载文件
            pro.load(new FileReader(path));

//            获取赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
//            注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


//    获取连接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

//    用方法重载释放资源
    public static void close (Statement stmt,Connection conn) {
        /*if (stmt != null) {
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
        }*/
        close(stmt,conn,null);
    }

    public static void close(Statement stmt , Connection conn, ResultSet rs) {
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

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}