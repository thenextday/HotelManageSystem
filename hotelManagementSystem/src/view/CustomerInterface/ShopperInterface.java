package view.CustomerInterface;

import controllor.ClassPassParameter.DietTrans1_1;
import controllor.*;
import view.Interface.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ShopperInterface {

    int width = 1225;
    int height = 876;
    int y1 = 35;
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width)/2;
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height)/2;
    int perPageSize = 5;
    int y700 = 700;
    String numberRegular = "^[0-9]*$";
    NewJPanel newJPanel = new NewJPanel();
    NewJDialog newJDialog = new NewJDialog();
    newJLabel nJLabel = new newJLabel();
    NewTextField newTextField = new NewTextField();
    NewButtton newButtton = new NewButtton();
    ShopBusiness shopBusiness = new ShopBusiness();
    Administrator administrator = new Administrator();
    AddTable addTable = new AddTable();
    Refresh refresh = new Refresh();
    Object receCusMessage [][];
    int width100 = 100;
    int width150 = 150;
    int width300 = 300;
    int height25 = 25;
//    我的模块
    public String cusPhone = "";
    String cusSex;
    String cusRoNum;
    String cusInDate;
    String cusStayLen;
//    菜单模块
    int dietPage = 0;
    int totPage = 0;
    int totCount = 0;
    String enterDietNameText;
    String enterDietMinText;
    String enterDietMaxText;
    JTable dietTable;
    DefaultTableModel dietTableModel;
    JScrollPane dietJScoll;
    Object receAllDiet [][];
    DietBussiness dietBussiness = new DietBussiness();
    ListBussiness listBussiness = new ListBussiness();
    DietTrans1_1 dietTrans1_1;
//    消费记录模块
    int liveCost = 0;
    Object [][] receAllCList;
    ListBussiness listBus = new ListBussiness();
    int totCost;
    DefaultTableModel listTableModel;
    JTable listTable;
    JScrollPane listJs;

//    新建界面方法
    public void buildSurface () {

        JFrame jFrame = new JFrame();
//        个人信息点餐卡
        JPanel myBussiness = newJPanel.newJPanel(width,height);
        myBussiness.setLayout(null);
//      显示顾客姓名
        JLabel displayCusName = nJLabel.addJLabel("姓名： ",200,100,width300,height25);
        myBussiness.add(displayCusName);
//        显示顾客账号
        JLabel displayCusPhone = nJLabel.addJLabel("电话： ",750,100,width300,height25);
        myBussiness.add(displayCusPhone);
//        显示顾客性别
        JLabel displayCusSex = nJLabel.addJLabel("性别： ",200,300,width300,height25);
        myBussiness.add(displayCusSex);
//        显示顾客房间号
        JLabel displayCusRoNum = nJLabel.addJLabel("房间号： ",750,300,width300,height25);
        myBussiness.add(displayCusRoNum);
//        显示顾客入住时间
        JLabel displayInDate = nJLabel.addJLabel("入住时间： ",200,500,width300,height25);
        myBussiness.add(displayInDate);
//        显示入住时长
        JLabel displayStayLen = nJLabel.addJLabel("入住时长： ",750,500,width300,height25);
        myBussiness.add(displayStayLen);

        receCusMessage = shopBusiness.displayAllCustomer(0,5,cusPhone,"","",0);

        displayCusName.setText("姓名；"+receCusMessage[0][1].toString());
        displayCusPhone.setText("电话："+receCusMessage[0][2].toString());
        displayCusSex.setText("性别："+receCusMessage[0][3].toString());
        displayCusRoNum.setText("房间号："+receCusMessage[0][4].toString());
        displayInDate.setText("入住时间："+receCusMessage[0][5].toString());
        displayStayLen.setText("入住时长："+receCusMessage[0][6].toString());

//        修改密码按钮
        JButton changePasswordBtn = newButtton.addButton("修改密码",500,700,width150,height25);
        myBussiness.add(changePasswordBtn);
//        修改密码弹窗
        JDialog changeCipher = newJDialog.addJDialog();


        changeCipher.setLayout(null);
//        添加按键监听
        changePasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJDialog.setJDialog(changeCipher,"修改密码弹窗");
            }
        });
//        显示账号
        JLabel showAccount = nJLabel.addJLabel("手机号：     "+cusPhone,150,100,width300,height25);
        changeCipher.add(showAccount);
//        输入新密码模块
        JLabel xinMiMa = nJLabel.addJLabel("新密码： ",150,200,width150,height25);
        changeCipher.add(xinMiMa);
        JPasswordField enterNewPassword = new JPasswordField();
        enterNewPassword.setBounds(280,200,width150,height25);
        changeCipher.add(enterNewPassword);
//        输入确认密码模块
        JLabel queRenMiMa = nJLabel.addJLabel("确认密码：",150,300,width150,height25);
        changeCipher.add(queRenMiMa);
        JPasswordField enterConPassword = new JPasswordField();
        enterConPassword.setBounds(280,300,width150,height25);
        changeCipher.add(enterConPassword);
//        添加确认修改按钮
        JButton confirmChange = newButtton.addButton("确认修改",225,400,width150,height25);
        changeCipher.add(confirmChange);
//        添加按键监听
        confirmChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = new String(enterNewPassword.getPassword());
                String conPassword = new String(enterConPassword.getPassword());
                if (newPassword.equals("") || conPassword.equals("")) {
                    JOptionPane.showMessageDialog(null,"输入框都不能为空",
                            "输入提示",JOptionPane.ERROR_MESSAGE);
                } else {
                    if (newPassword.equals(conPassword)) {
                        int cusId = Integer.parseInt(receCusMessage[0][0].toString());
                        int c = shopBusiness.resetPassword(cusId,newPassword);
                        administrator.disExecuRes(c,"密码重置");
                    } else {
                        JOptionPane.showMessageDialog(null,"两次输入的密码不一致",
                                "密码输入提示",JOptionPane.ERROR_MESSAGE);
                    }
                }
                changeCipher.dispose();
            }
        });




//        我的账单模块
        JPanel costDetails = newJPanel.newJPanel(width,height);
//        标题
        JLabel zhuFangFeiYong = nJLabel.addJLabel("住房费用： ",0,0,width150,height25);
        costDetails.add(zhuFangFeiYong);
        liveCost = listBus.liveCost(receCusMessage[0][2].toString());
        JLabel liveCostLabel = nJLabel.addJLabel(liveCost+""+"元",150,0,width150,height25);
        costDetails.add(liveCostLabel);
//        订单表格
        String listHeader [] = {"订单号","餐饮名称","价格","订购日期"};
        receAllCList = listBus.displayAllList(receCusMessage[0][2].toString());
        listTableModel = new DefaultTableModel(receAllCList,listHeader);
        listTable = addTable.addTable(listTableModel);
        listTable.setRowHeight(50);

        listJs = new JScrollPane(listTable);
        listJs.setBounds(0,100,width,700);
        costDetails.add(listJs);
//        所有费用模块
        JLabel suoYouFeiYong = nJLabel.addJLabel("消费总计；",900,0,width300,height25);
        costDetails.add(suoYouFeiYong);
//        totPage =
        if (receAllCList.length > 0) {
            totCost = listBus.oneDietCost(receCusMessage[0][2].toString()) + liveCost;
        } else {
            totCost = liveCost;
        }
        suoYouFeiYong.setText("消费总计； "+totCost+"元");




//        菜单选项卡
        JPanel orderMealBussiness = newJPanel.newJPanel(width,height);
//        菜名
        JLabel caiMing = nJLabel.addJLabel("菜肴名称：",0,y1,width150,height25);
        orderMealBussiness.add(caiMing);
        JTextField enterDietName = newTextField.addTextField(150,y1,width100,height25);
        orderMealBussiness.add(enterDietName);
        enterDietNameText = enterDietName.getText();
//        最低价格
        JLabel zuiDiJiaGe = nJLabel.addJLabel("最低价格：",300,y1,width150,height25);
        orderMealBussiness.add(zuiDiJiaGe);
        JTextField enterMinPrice = newTextField.addTextField(450,y1,width100,height25);
        orderMealBussiness.add(enterMinPrice);
        enterDietMinText = enterMinPrice.getText();
//        最高价格
        JLabel zuiGaoJiaGe = nJLabel.addJLabel("最高价格：",600,y1,width150,height25);
        orderMealBussiness.add(zuiGaoJiaGe);
        JTextField enterMaxPrice = newTextField.addTextField(750,y1,width100,height25);
        orderMealBussiness.add(enterMaxPrice);
        enterDietMaxText = enterMaxPrice.getText();
//        添加查询按钮
        JButton queryDiet = newButtton.addButton("查询",950,y1,width100,height25);
        orderMealBussiness.add(queryDiet);
//        餐饮表
        String restHeader [] = {"id","名称","价格"};
        receAllDiet = dietBussiness.diplayAllDiets(dietPage*perPageSize,perPageSize,"",0,0,0);
        System.out.println(receAllDiet.length);
        dietTableModel = new DefaultTableModel(receAllDiet,restHeader);
        dietTable = addTable.addTable(dietTableModel);
        dietTable.setRowHeight(100);
        dietJScoll = new JScrollPane(dietTable);
        dietJScoll.setBounds(0,100,width,550);
        orderMealBussiness.add(dietJScoll);
//        显示总条数
        totCount = dietBussiness.displayAllDietCount("",0,0,0);
        JLabel dietTotCount = nJLabel.addJLabel("总条数："+totCount+"",500,y700,width300,height25);
        orderMealBussiness.add(dietTotCount);
//        显示总页数
        totPage = administrator.numOfPage(totCount);
        JLabel dietTotPageLabel = new newJLabel().addJLabel("总页数："+totPage+"",200,y700,width300,height25);
        orderMealBussiness.add(dietTotPageLabel);
//         显示当前页数
//        administrator.currentPage();
        JLabel dietDangQian = new newJLabel().addJLabel("当前页数：",800,y700,width150,height25);
        orderMealBussiness.add(dietDangQian);
        JLabel dietNowPage = new newJLabel().addJLabel(dietPage+1+"",920,y700,width100,height25);
        orderMealBussiness.add(dietNowPage);
//        添加按键监听
        queryDiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietTrans1_1 = new DietTrans1_1(numberRegular,enterMinPrice.getText(),enterMaxPrice.getText());
                boolean isFalse = dietTrans1_1.isFlag();
                if (isFalse) {
                    receAllDiet = dietBussiness.diplayAllDiets(dietPage*perPageSize,perPageSize,
                            enterDietName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                    refresh.refreshTable(receAllDiet,restHeader,dietTableModel,dietTable);
                    judgeQueryResults(receAllDiet);
                } else {
                    JOptionPane.showMessageDialog(null,"价格输入不对，请输入合理数字",
                    "输入提示",JOptionPane.ERROR_MESSAGE);
                }
                totCount = dietBussiness.displayAllDietCount(enterDietName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                totPage = administrator.numOfPage(totCount);
                dietTotCount.setText(totCount+"");
                dietTotPageLabel.setText(totPage+"");
            }
        });
//        给表格添加按键监听
        dietTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = 0;
                int dietId = Integer.parseInt(addTable.addKeyMonitoring(dietTable,c));

                int cusId = Integer.parseInt(receCusMessage[0][0].toString());
                int isOrder = JOptionPane.showConfirmDialog(null,"是否订购该餐饮",
                        "订购提示",JOptionPane.YES_NO_OPTION);
                System.out.println("isOrder"+isOrder);
                if (isOrder == 0) {
                    int count = listBussiness.addList(cusId,dietId);
                    administrator.disExecuRes(count,"订购");
                    totCost = listBus.oneDietCost(receCusMessage[0][2].toString()) + liveCost;
                }
                receAllCList = listBus.displayAllList(receCusMessage[0][2].toString());
                refresh.refreshTable(receAllCList,listHeader,listTableModel,listTable);

                suoYouFeiYong.setText("消费总计； "+totCost+"元");
            }
        });
//        上一页按钮
        JButton dietLastPage = newButtton.addButton("上一页",0,y700,width100,height25);
        orderMealBussiness.add(dietLastPage);
//        添加按键监听
        dietLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietPage = administrator.lastPageMethod(dietPage);
                receAllDiet = dietBussiness.diplayAllDiets(dietPage*perPageSize,perPageSize,
                        enterDietName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                refresh.refreshTable(receAllDiet,restHeader,dietTableModel,dietTable);
                dietNowPage.setText(dietPage+1+"");
            }
        });
//        下一页按钮
        JButton dietNextPage = newButtton.addButton("下一页",1100,y700,width100,height25);
        orderMealBussiness.add(dietNextPage);
//        添加按键监听
        dietNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dietTrans1_1 = new DietTrans1_1(numberRegular,enterMinPrice.getText(),enterMaxPrice.getText());
                totCount = dietBussiness.displayAllDietCount(enterDietName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                totPage = administrator.numOfPage(totCount);
                dietPage = administrator.nextPageMethod(dietPage,totPage);
                receAllDiet = dietBussiness.diplayAllDiets(dietPage*perPageSize,perPageSize,
                        enterDietName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                refresh.refreshTable(receAllDiet,restHeader,dietTableModel,dietTable);
                dietNowPage.setText(dietPage+1+"");

            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("我的信息",myBussiness);
        tabbedPane.add("菜单",orderMealBussiness);
        tabbedPane.add("我的账单",costDetails);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });


        jFrame.setTitle("顾客界面");
        jFrame.setBounds(x,y,width,height);
        jFrame.setLayout(null);
        jFrame.setContentPane(tabbedPane);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jFrame.setVisible(true);
    }

    //    判断查询结果
    public void judgeQueryResults (Object [][] arr) {
        if (arr.length == 0) {
            JOptionPane.showMessageDialog(null,"未找到符合条件的结果",
                    "操作提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
