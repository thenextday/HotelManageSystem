package controllor;


import model.dao.ConnectionRestaurantTable;
import model.table.Restaurant;

import java.util.List;

public class DietBussiness {
    ConnectionRestaurantTable connectionTable = new ConnectionRestaurantTable();

    /*查询所有为被删除的餐饮*/
    public Object [][] diplayAllDiets (int startItem,int pageSize,String resName,int minPrice,int maxPrice,int isDelete) {
        List<Restaurant> list = connectionTable.queryAllDiet(startItem,pageSize,resName,minPrice,maxPrice);
        Object arr[][] = setToArray(list);
        return arr;
    }

    /*查询所有被逻辑删除的餐饮*/
    public Object[][] displayDiets (int startItem,int pageSize,String resName,int minPrice,int maxPrice) {
        List<Restaurant> list = connectionTable.queryDiets();
        Object arr[][] = setToArray(list);
        return arr;
    }

    /*集合转数组*/
    public Object[][] setToArray (List<Restaurant> list) {
        Object arr [][] = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            arr[i][0] = list.get(i).getRestaurantId();
            arr[i][1] = list.get(i).getRestaurantName();
            arr[i][2] = list.get(i).getRestaurantPrice();
        }
        return arr;
    }

    /*查询餐饮总数*/
    public int displayAllDietCount (String resName,int minPrice,int maxPrice,int isDelete) {
        int count = 0;
        if (isDelete == 0) {
            count = connectionTable.queryAllDietCount(resName,minPrice,maxPrice);
        } else if (isDelete == 1) {
            count = connectionTable.queryDietCoun();
        }
        return count;
    }

    /*添加新的餐饮*/
    public int addDiet (String dishName,double dishPrice) {
        int countDiet = connectionTable.insertDiet(dishName,dishPrice);
        return countDiet;
    }

    /*逻辑删除餐饮*/
    public int removeDiet (int id) {
        int countDiet = connectionTable.deleteDiet(id);
        return countDiet;
    }

    /*恢复被逻辑删除的餐饮*/
    public int recoverData (double resPrice,String resName) {
        int countDiet = connectionTable.sqlSever(resPrice,resName);
        return countDiet;
    }

    /*修改餐饮的价格*/
    public int alterDiet (double resPrice,int resId) {
        int countDiet = connectionTable.updateDiet(resPrice,resId);
        return countDiet;
    }
}
