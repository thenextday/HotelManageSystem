package controllor;

import model.dao.ConnectionClistTable;
import model.table.*;

import java.util.List;

public class ListBussiness {

    ConnectionClistTable cct = new ConnectionClistTable();
    int count = 0;

    public int addList (int cusId,int resId) {
        count = cct.insertList(cusId,resId);
        return count;
    }

    /*查询顾客订单信息*/
    public Object [][] displayAllList (String cusPhone) {
        List<CList> list = cct.queryLists(cusPhone);
        Object [][] allList = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                allList[i][0] = list.get(i).getcListId();
                allList[i][1] = list.get(i).getResName();
                allList[i][2] = list.get(i).getResPrice();
                allList[i][3] = list.get(i).getOrderTime();
            }
        }
        return allList;
    }

//    返回顾客住房费用
    public int liveCost (String cusPhone) {
        List<PersonTotalCost> list = cct.queryLiveCost(cusPhone,0,0,0);
        int c = list.get(0).getTotCost();
        return c;
    }

//    返回单个顾客餐饮消费总和
    public int oneDietCost(String cusPhone) {
        List<PersonTotalCost> list = cct.queryDietCost(cusPhone,0);
        int c = list.get(0).getTotCost();
        return c;
    }

    /*逻辑删除*/
    public int removeClist (int cusId) {
        int count = cct.deleteClist(cusId);
        return count;
    }

//    返回所有顾客消费总和
    public Object [][] allCustomerCost (int startItem,int perPageSize,String cusPhone,int isAll) {
        List<PersonTotalCost> liveCostList = cct.queryLiveCost(cusPhone,isAll,startItem,perPageSize);
        Object [][] liveCostArr = setToArray(liveCostList);
        List<PersonTotalCost> dietCostList = cct.queryDietCost("",1);
        Object [][] dietCostArr = setToArray(dietCostList);

        for (int i = 0; i < liveCostList.size(); i++) {
            for (int j = 0; j < dietCostList.size(); j++) {
                if (liveCostArr[i][0].equals(dietCostArr[j][0])) {
                    int c = Integer.parseInt(liveCostArr[i][2].toString()) + Integer.parseInt(dietCostArr[j][2].toString());
                    liveCostArr[i][2] = c;
                }
            }
        }

        return liveCostArr;
    }

    /*查询顾客的所有数量*/
    public int findAll () {
        int count = cct.queryAll();
        return count;
    }

//    集合转二维数组
    public Object[][] setToArray (List<PersonTotalCost> list1) {
        Object [][] arr = new Object[list1.size()][3];
        for (int i = 0; i < list1.size(); i++) {
            arr[i][0] = list1.get(i).getCustomerPhone();
            arr[i][1] = list1.get(i).getCustomerName();
            arr[i][2] = list1.get(i).getTotCost();
        }
        return arr;
    }
}
