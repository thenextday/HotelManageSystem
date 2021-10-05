package controllor.ClassPassParameter;

import model.dao.PoolUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/*
* JdbcTemplate入门学习
* */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
//        1.导入jar包
//        2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(PoolUtils.getDataSource());
//        3.调用方法
        String sql = "UPDATE rooms SET is_delete = 1 WHERE room_id = ?";
        int count = template.update(sql,21);
        System.out.println(count);
        template.execute("start transaction ");
    }

}
//        try {
//        int startItem,int pageSize,String recCusPhone,String recCusRoNum,String cName,int isDelete
//            conn = JDBCUtils.getConnection();
//            if (recCusPhone.equals("") && recCusRoNum.equals("") && cName.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 ORDER BY check_in_time DESC limit ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1,startItem);
//                pstmt.setInt(2,pageSize);
//            } else if (recCusRoNum.equals("") && cName.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_phone = ? " +
//                        "ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,recCusPhone);
//                pstmt.setInt(2,startItem);
//                pstmt.setInt(3,pageSize);
//            } else if (recCusPhone.equals("") && cName.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND room_num LIKE ? " +
//                        "ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,recCusRoNum+"%");
//                pstmt.setInt(2,startItem);
//                pstmt.setInt(3,pageSize);
//            } else if (recCusPhone.equals("") && recCusRoNum.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_name = ? " +
//                        "ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,cName);
//                pstmt.setInt(2,startItem);
//                pstmt.setInt(3,pageSize);
//            } else if (cName.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_phone = ? " +
//                        "AND room_num LIKE ? ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,recCusPhone);
//                pstmt.setString(2,recCusRoNum+"%");
//                pstmt.setInt(3,startItem);
//                pstmt.setInt(4,pageSize);
//            } else if (recCusRoNum.equals("") && isDelete == 0) {
//               String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_phone = ? AND customer_name = ? " +
//                       "ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,recCusPhone);
//                pstmt.setString(2,cName);
//                pstmt.setInt(3,startItem);
//                pstmt.setInt(4,pageSize);
//            } else if (recCusPhone.equals("") && isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_name = ? AND room_num LIKE ? " +
//                        "ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,cName);
//                pstmt.setString(2,recCusRoNum+"%");
//                pstmt.setInt(3,startItem);
//                pstmt.setInt(4,pageSize);
//            } else if (isDelete == 0) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 0 AND customer_phone = ? AND room_num LIKE ? AND " +
//                        "customer_name = ? ORDER BY check_in_time DESC LIMIT ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,recCusPhone);
//                pstmt.setString(2,recCusRoNum+"%");
//                pstmt.setString(3,cName);
//                pstmt.setInt(4,startItem);
//                pstmt.setInt(5,pageSize);
//            } else if (recCusPhone.equals("") && recCusRoNum.equals("") && cName.equals("") && isDelete == 1) {
//                String sql = "SELECT * FROM customer WHERE is_delete = 1 ORDER BY check_in_time DESC limit ?,?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1,startItem);
//                pstmt.setInt(2,pageSize);
//            }
//            rs = pstmt.executeQuery();
//            Customer customer = null;
//            list = new ArrayList<>();
//            while (rs.next()) {
//                int cusId = rs.getInt(1);
//                String cusName = rs.getString(2);
//                String cusPhone = rs.getString(3);
//                String cusPassword = rs.getString(4);
//                String cusSex = rs.getString(5);
//                String roNum = rs.getString(6);
//                Date cheInTime = rs.getDate(7);
//                int lenOfStay = rs.getInt(8);
//                int isDelet = rs.getInt(9);
//                customer = new Customer(cusId,cusName,cusPhone,cusPassword,cusSex,roNum,cheInTime,lenOfStay,isDelet);
//                list.add(customer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(pstmt,conn,rs);
//            return list;
//        }