package controllor;
/*
* 学习使用Druid数据库连接池的工具类
* 使用方法
* 1.导入jar包
* 2.定义配置文件
* 3.加载配置文件
* 4.获取连接池对象*/
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {

    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        DataSource ds =  DruidDataSourceFactory.createDataSource(pro);

        Connection conn = ds.getConnection();

        System.out.println(conn);
    }

}
