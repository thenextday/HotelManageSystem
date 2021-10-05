package controllor;

import model.dao.ConnectionAdministratorTable;

public class UserLogin {
    ConnectionAdministratorTable connectionAdministratorTable = new ConnectionAdministratorTable();

    public boolean administratorLogin (String phoneNumber,String adminpassword) {
        boolean flag = connectionAdministratorTable.administratorLogin(phoneNumber,adminpassword);
        return flag;
    }


}
