package controllor;

import model.dao.ConnectCustomerTable;
import model.table.Customer;

import java.util.List;

public class ShopBusiness {
    ConnectCustomerTable conectionTable = new ConnectCustomerTable();

    /*顾客登录*/
    public boolean cusRes (String phoneNumber,String cusPassword) {
        boolean flag = flag = conectionTable.customerLogin(phoneNumber,cusPassword);
        return flag;
    }

    /*查询顾客所有信息*/
    public Object[][] displayAllCustomer (int startItem,int pageSize,String recCusPhone,String recCusRoNum,String cName,int isDelete) {
        List<Customer> list = null;
        if (isDelete == 0) {
            list = conectionTable.queryAllCustomer(startItem,pageSize,recCusPhone,recCusRoNum,cName );
        } else if (isDelete == 1) {
            list = conectionTable.queryCus();
        }Object [][] customers = new Object[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            customers[i][0] = list.get(i).getCustomerId();
            customers[i][1] = list.get(i).getCustomerName();
            customers[i][2] = list.get(i).getCustomerPhone();
            customers[i][3] = list.get(i).getCustomerSex();
            customers[i][4] = list.get(i).getRoomNum();
            customers[i][5] = list.get(i).getCheckInTime();
            customers[i][6] = list.get(i).getLengthOfStay();
        }
        return customers;
    }
//  查询顾客数量
    public int displayAllCustomerCount (String recCusPhone,String recCusRoNum,String cName,int isDelete) {
        int count = conectionTable.queryAllCustomerCount(recCusPhone,recCusRoNum,cName);
        return count;
    }

    /*新添加顾客*/
    public int addCustomers (String cusName,String cusPhone,String cusSex,String cusRoNum,int lenOfDay) {
        int count = conectionTable.insertCustomer(cusName,cusPhone,cusSex,cusRoNum,lenOfDay);
        return count;
    }

    /*恢复被删除的顾客信息*/
    public int sqlSever (String cusRoomNum,int lenOfDay,String cusName,String cusPhone) {
        int count = conectionTable.recoverData(cusRoomNum,lenOfDay,cusName,cusPhone);
        return count;
    }

    /*逻辑删除顾客*/
    public int tombstoning (int cusId) {
        int count = conectionTable.logicalDeletion(cusId);
        return count;
    }

//    重置密码
    public int resetPassword (int cusId,String newPassword) {
        int count = 0;
        count = conectionTable.updatePassword(cusId,newPassword);
        return count;
    }
}
