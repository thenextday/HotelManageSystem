package view.Interface;

import controllor.*;
import controllor.ClassPassParameter.DietTrans1_1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Administrator {

    //    界面和面板的宽度和高度
    int width = 1225;
    int height = 876;
    int labelWidth = 150;
    int labelHeight = 25;
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width)/2;
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height)/2;
    //    一些组件的宽度和高度
    int width0 = 100;
    int width1 = 150;
    int width2 = 200;
    int width300 = 300;
    int height1 = 25;
    int perPageCount = 5;
    int rTyId = 0;
    int roomsTypeId = 0;
    //    一些组件的y坐标
    int restY0 = 0;
    int restY1 = 50;
    int labelY = 700;
    /*判断是否为数字的正则表达式*/
    String numberRegular = "^[0-9]*$";
    /*添加表格对象*/
    AddTable addTables = new AddTable();
    /*添加按钮对象*/
    NewButtton newButtton = new NewButtton();
    /*添加刷新对象*/
    Refresh rt = new Refresh();
    /*新建面板对象*/
    NewJDialog newJDialog = new NewJDialog();
    /*新建标签对象*/
    newJLabel njl = new newJLabel();
    /*新建输入框对象*/
    NewTextField ntf = new NewTextField();
    //    房间类型业务模块变量
    String theRoomType = null;
    RoomTypeManagement roomTypeManagement = new RoomTypeManagement();
    Object [][] receiveALlRoomType;
    Object [][] theReceiveALlRoomType;
    //    房间业务模块变量
    JTable roomsTable;

    DefaultTableModel roomTableModel;
    RoomBusiness roomsBusiness = new RoomBusiness();
    String pleaseSelectRoomType = "----请选择房间类型----";
    int totalRoomsCount = roomsBusiness.displayRoomCount("",pleaseSelectRoomType,pleaseSelectRoomType,0);
    int totalCount;
    int totalPage;
    int page = 0;
    int theRoCusNum = 0;
    Object [][] receiveAllRoom;
    //    餐饮业务模块变量
    JTable dietTable;
    JScrollPane jsDiet;
    DefaultTableModel dietTableModel;
    DietBussiness dietBussiness = new DietBussiness();
    int dietTotalCount = dietBussiness.displayAllDietCount("",0,0,0);
    int dietTotPage;
    int dietPage = 0;
    int resId = 0;
    Object [][] receiveAllDiet;
    DietTrans1_1 dietTrans1_1;
    //    顾客业务模块
    Object [][] receiveAllCustomer;
    ShopBusiness shopBusiness = new ShopBusiness();
    DefaultTableModel shopTableModel;
    JTable cusTable;
    int cusPage = 0;
    int cusTotalCount = 0;
    int cusTotalPage = 0;
    int cusLabelX = 150;
    int cusJtextX = 250;
    int cusId = 0;
    String cusRoNum;
    //    消费总计模块
    int consumpPage = 0;
    ListBussiness listBussiness = new ListBussiness();
    int consumpTotCount = listBussiness.findAll();
    int consumpTotPage = numOfPage(consumpTotCount);
    String cusPhone = "";
    Object [][] receConsump = null;
    Object [][] receAllClist = null;
    DefaultTableModel consumpTableModel ;
    JTable consumpTable;
    JScrollPane consumpJs;
    DefaultTableModel listTableModel;
    JTable listTable;
    JScrollPane listJs;
//    新增全局变量
//    房间类型模块
    JComboBox selectRoomType = new JComboBox();
    JComboBox srt = new JComboBox();
    JDialog addRT = newJDialog.addJDialog();// 添加房间类型弹窗
//    房间模块
    JTextField irn = ntf.addTextField(200,50,width1,height1);
    String roomHeader [] = {"房间id","房间号","房间类型","人数"};
//    餐饮模块
    JTextField minPrice = new NewTextField().addTextField(500,restY1,width1,height1);//最低价格输入框
    JTextField maxPrice = new NewTextField().addTextField(850,restY1,width1,height1);//最高价格输入框
    //输入菜的名称
    JTextField inputResName = new NewTextField().addTextField(150,restY1,width1,height1);
//    消费记录
    JLabel consumpZongTiaoshu = njl.addJLabel("总条数： "+consumpTotCount,500,labelY,width300,height1);
    JLabel consumpNowPage = njl.addJLabel(consumpPage+1+"",920,labelY,width0,height1);
    JLabel consumpZongYeShu = njl.addJLabel("总页数： "+consumpTotPage,200,labelY,width300,height1);
    String consumpHeader [] = {"顾客账号","顾客姓名","费用总计"};
//    顾客模块
    String customerHeader [] = {"顾客id","顾客姓名","顾客手机号","顾客性别","顾客房间号","顾客入住时间","顾客入住时长",};
    JTextField cPhone = ntf.addTextField(100,50,width1,height1);//输入顾客手机号
    JTextField cRoomNum = ntf.addTextField(450,50,width1,height1);//输入顾客房间号
    JTextField cName = ntf.addTextField(800,50,width1,height1);//输入顾客姓名
    JDialog toAddCustomer = newJDialog.addJDialog();//新顾客入住界面



//  该方法为创建界面的方法
    public void buildBoundary () {
        JFrame frame = new JFrame();

//        房间类型操作
        JPanel roomTypeBusiness = reRoTy();

//      所有房间业务
        JPanel roomBusiness = retuRoom();

//        有关餐饮的所有业务
        JPanel cateringBusiness = retuCater ();

//        消费记录的所有业务
        JPanel consumptionStatistics = retConsump();

//        顾客业务选项卡
        JPanel customerBusiness = forCus ();

//        添加重置密码按钮
        JButton resetPasswordBtn = newButtton.addButton("重置密码",400,0,width1,height1);
        customerBusiness.add(resetPasswordBtn);
//        添加按键监听
        resetPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = shopBusiness.resetPassword(cusId,"000000");
                disExecuRes(count,"密码重置");
            }
        });


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("顾客业务",customerBusiness);
        tabbedPane.add("餐饮业务",cateringBusiness);
        tabbedPane.add("房间业务",roomBusiness);
        tabbedPane.add("房间类型业务",roomTypeBusiness);
        tabbedPane.add("顾客消费统计",consumptionStatistics);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });


        frame.setTitle("管理员界面");
        frame.setBounds(x,y,width,height);
        frame.setLayout(null);

        frame.setContentPane(tabbedPane);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }


//    房间类型模块
    public JPanel reRoTy () {
        JTable roomTypeTable;
        DefaultTableModel tableModel;

        JPanel roomTypeBusiness = new NewJPanel().newJPanel(width,height);
        roomTypeBusiness.setLayout(null);
        String datHeadr [] = {"房间类型id","房间类型","价格"};
        receiveALlRoomType = roomTypeManagement.displayAllRoomType(0);
        tableModel = new DefaultTableModel(receiveALlRoomType,datHeadr);
        receiveALlRoomType  = roomTypeManagement.displayAllRoomType(0);
        roomTypeTable = addTables.addTable(tableModel);
        JButton addRoomType = newButtton.addButton("添加类型",0,50,width1,height1);//添加房间类型按钮

        String[] roomTypelabelName = {"房间类型：","房间价格："};// 在弹出框中添加内容
        JTextField inputRoomType = ntf.addTextField(200,50,width2,height1);
        addRT.add(inputRoomType);
        JTextField inputRoomPrice = ntf.addTextField(200,200,width2,height1);
        addRT.add(inputRoomPrice);
        JButton confirmRoomType = newButtton.addButton("确认",220,400,width0,height1);
        addRT.add(confirmRoomType);
        roomTypeBusiness.add(addRoomType);
        addRT.setLayout(null);
        selectRoomType.addItem(pleaseSelectRoomType);
        srt.addItem(pleaseSelectRoomType);
        for (int i = 0; i < receiveALlRoomType.length; i++) {
            selectRoomType.addItem(receiveALlRoomType[i][1]);
            srt.addItem(receiveALlRoomType[i][1]);
        }
        selectRoomType.setBounds(300,200,width1,height1);
        srt.setBounds(600,50,width1,height1);
        roomTypeTable.setRowHeight(100);
        newJScrollPane(roomTypeTable,roomTypeBusiness,0,200,width,450);
//        添加房间类型按钮按键监听
        addRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJDialog.setJDialog(addRT,"添加房间类型");
            }
        });
        JLabel[] jLabels = new JLabel[2];for (int i = 0; i < jLabels.length; i++) {
            jLabels[i] = njl.addJLabel(roomTypelabelName[i],50,50+50*i*3,labelWidth,labelHeight);
            addRT.add(jLabels[i]);
        }
//        添加按键监听
        confirmRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conAdRoTy(inputRoomType,inputRoomPrice,datHeadr,tableModel,roomTypeTable);
            }
        });

//        给表格添加监听
        roomTypeTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = 0;
                rTyId = Integer.parseInt(addTables.addKeyMonitoring(roomTypeTable,c));
                theRoomType = addTables.addKeyMonitoring(roomTypeTable,1);
            }
        });

//        删除房间类型按钮
        JButton removeRoomType = newButtton.addButton("删除类型",200,50,width1,height1);
        roomTypeBusiness.add(removeRoomType);
        removeRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoRoTy(datHeadr,tableModel,roomTypeTable);
            }
        });
//        修改房间价格类型按钮
        JButton modifyRoomPrice = newButtton.addButton("修改价格",400,50,width1,height1);
        roomTypeBusiness.add(modifyRoomPrice);
//        修改房间价格弹窗
        modifyRoomPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modRoTy(datHeadr,tableModel,roomTypeTable);
            }
        });
        return roomTypeBusiness;
    }
//    添加房间类型方法
    public void conAdRoTy(JTextField inputRoomType,JTextField inputRoomPrice,String [] datHeadr,DefaultTableModel tableModel,JTable roomTypeTable) {
        String irt = inputRoomType.getText();
        String irp = inputRoomPrice.getText();
        if (irt.length() == 0  || irp.equals("") || irp == null) {
            showJo("输入框不能为空");
        }else{
            if (irp.matches(numberRegular)) {
                int thePrice = Integer.parseInt(irp);

                /*判断是否已有此房间类型*/
                theReceiveALlRoomType = roomTypeManagement.displayAllRoomType(0);
                boolean isAlreadyHave = isRepeated(theReceiveALlRoomType,1,irt);
                if (isAlreadyHave) {
                    showJo("已有此房间类型，不能重复添加");
                    inputRoomType.setText("");
                    inputRoomPrice.setText("");
                } else {

                    /*判断以前是否添加过此房间类型*/
                    Object [][] receDeleteRoomType = roomTypeManagement.displayAllRoomType(1);
                    boolean isAlreadyAddThis = isRepeated(receDeleteRoomType,1,irt);
                    if (isAlreadyAddThis) {
                        int count = roomTypeManagement.renewRoomType(irt,thePrice);
                        disExecuRes(count,"房间类型添加");
                    } else {
                        int roomTypeAddResult = roomTypeManagement.addRoomType(irt,thePrice);
                        disExecuRes(roomTypeAddResult,"房间类型添加");
                    }
                }
            }else {
                showJo("房间价格不能为空且必须是数字");
            }
        }
        inputRoomType.setText("");
        inputRoomPrice.setText("");
        theReceiveALlRoomType = roomTypeManagement.displayAllRoomType(0);
        rt.refreshTable(theReceiveALlRoomType,datHeadr,tableModel,roomTypeTable);
        rt.refreshJCombox(selectRoomType,theReceiveALlRoomType,pleaseSelectRoomType);
        rt.refreshJCombox(srt,theReceiveALlRoomType,pleaseSelectRoomType);
        addRT.dispose();
    }
//    删除房间类型方法
    public void remoRoTy(String [] datHeadr,DefaultTableModel tableModel,JTable roomTypeTable) {
        if (rTyId== 0){
            showJo("请点击下表选择房间类型");
        }else{

            /*判断此房间类型的所有房间是否有人*/
            int allRoomNum = roomsBusiness.displayRoomCount("",theRoomType,"",0);
            Object [][] allRooms = roomsBusiness.displayAllRooms("",theRoomType,0,allRoomNum,"",0);
            /*判断入住人数*/
            boolean judgeOccupancy = false;
            for (int i = 0; i < allRooms.length; i++) {
                if (Integer.parseInt(allRooms[i][3].toString()) > 0) {
                    judgeOccupancy = true;
                }
            }

            if (judgeOccupancy) {
                showJo("该类型的房间中有人入住，所以不能删除此房间类型");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否确定删除该房间类型", "删除提醒",JOptionPane.YES_NO_OPTION); //返回值为0或1
                if (n == 0){
                    int roomDeletionResults = roomTypeManagement.removeRoomType(rTyId);
                    if (roomDeletionResults > 0) {
                        /*删除该类型的所有房间*/
                        boolean isDeleteCorrespondRoom = isCheck();

                        if (isDeleteCorrespondRoom) {
                            showJo("对应房间已经全部删除");
                        }

                        rt.refreshJCombox(selectRoomType,theReceiveALlRoomType,pleaseSelectRoomType);
                        rt.refreshJCombox(srt,theReceiveALlRoomType,pleaseSelectRoomType);
                    }else{
                        showJo("没有执行成功");
                    }
                }
            }
        }
    }
//    修改房间类型
    public void modRoTy (String [] datHeadr,DefaultTableModel tableModel,JTable roomTypeTable) {
        /*判断是否选中表格中的房间类型*/
        if (rTyId == 0){
            showJo("您还没有选择房间类型，请先点击下表");
        } else{
            /*判断入住人数*/
            boolean judgeOccupancy = isCheck();

            if (judgeOccupancy) {
                showJo("该类型的房间中有人入住，所以不能修改此房间类型的价格");
            } else {
                String inputRoomPrice = JOptionPane.showInputDialog("请输入选中房间的新价格");
                if (inputRoomPrice == null) {

                } else if (inputRoomPrice.equals("")) {
                    showJo("请输入要修改的价格");
                }
                else{

                    if (inputRoomPrice.matches(numberRegular)) {
                        double price = Double.parseDouble(inputRoomPrice);
                        int count = roomTypeManagement.modifyRoomType(rTyId,price);
                        disExecuRes(count,"房间价格");
                    }
                    else{
                        showJo("请输入数字");
                    }
                }
            }
        }
        Object [][] theReceiveALlRoomType = roomTypeManagement.displayAllRoomType(0);
        rt.refreshTable(theReceiveALlRoomType,datHeadr,tableModel,roomTypeTable);
    }
//    判断该房间是否有人入住
    public boolean isCheck () {
        boolean isCheckIn = false;
        int allRoomNum = roomsBusiness.displayRoomCount("",theRoomType,"",0);
        Object [][] allRooms = roomsBusiness.displayAllRooms("",theRoomType,0,allRoomNum,"",0);
        for (int i = 0; i < allRooms.length; i++) {
            if (Integer.parseInt(allRooms[i][3].toString()) > 0) {
                isCheckIn = true;
            }
        }
        return isCheckIn;
    }


//    房间模块
    public JPanel retuRoom () {

        JPanel roomBusiness = new NewJPanel().newJPanel(width,height);
        roomBusiness.setLayout(null);
        //        查询房间模块
        JLabel qingShuRuFangJianHao = njl.addJLabel("请输入房间号:",0,50,width2,height1);
        roomBusiness.add(qingShuRuFangJianHao);

        roomBusiness.add(irn);

        JLabel qingXuanZeFangJianLeiXing = njl.addJLabel("请选择房间类型：",400,50,width2,height1);
        roomBusiness.add(qingXuanZeFangJianLeiXing);
        roomBusiness.add(srt);
//      房间表格
        receiveAllRoom = judgeQueryConditions( irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
        roomTableModel = new DefaultTableModel(receiveAllRoom,roomHeader);
        roomsTable =addTables.addTable(roomTableModel);
        roomsTable.setRowHeight(100);
        newJScrollPane(roomsTable,roomBusiness,0,100,width,550);
        totalCount = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
        totalPage = numOfPage(totalCount);
//        显示总页数
        JLabel xszys = new newJLabel().addJLabel("总页数："+totalPage,300,labelY,width2,height1);
        roomBusiness.add(xszys);
//      显示总条数
        JLabel xszts = new newJLabel().addJLabel("总条数："+totalCount,500,labelY,width2,height1);
        roomBusiness.add(xszts);
//        显示当前页数
        JLabel dangQianYeShu = new newJLabel().addJLabel("当前页数：",700,labelY,width1,height1);
        roomBusiness.add(dangQianYeShu);
        JLabel dqys = new newJLabel().addJLabel(page+1+"",820,labelY,width0,height1);
        roomBusiness.add(dqys);
//        添加查询按钮
        JButton query = newButtton.addButton("查询",800,50,width0,height1);
        roomBusiness.add(query);
//        上一页按钮
        JButton lastPage = newButtton.addButton("上一页",150,labelY,width0,height1);
        roomBusiness.add(lastPage);
//      下一页按钮
        JButton nextPage = newButtton.addButton("下一页",900,labelY,width0,height1);
        roomBusiness.add(nextPage);
//        添加房间按钮
        JButton addRoomsBtn = newButtton.addButton("添加房间",0,0,width1,height1);
        roomBusiness.add(addRoomsBtn);
//        添加房间弹窗
        JDialog addRoomsDialog = newJDialog.addJDialog();
        addRoomsDialog.setLayout(null);
//      输入房间号
        JLabel shuRuFangJianHao = njl.addJLabel("请输入房间号：",100,100,width2,height1);
        addRoomsDialog.add(shuRuFangJianHao);
        JTextField inputRoomNum = ntf.addTextField(300,100,width1,height1);
        addRoomsDialog.add(inputRoomNum);
//        选择房间类型
        JLabel xuanZeFangJianLeiXing = njl.addJLabel("选择房间类型：",100,200,width2,height1);
        addRoomsDialog.add(xuanZeFangJianLeiXing);
//        添加确认按钮
        JButton confirmAddRoom = newButtton.addButton("确认",250,300,100,height1);
        addRoomsDialog.add(confirmAddRoom);
//        添加取消按钮
        JButton cancelAddRoom = newButtton.addButton("取消",250,350,100,height1);
        addRoomsDialog.add(cancelAddRoom);
//        删除房间按钮
        JButton deleteRoomsBtn = newButtton.addButton("删除房间",200,0,width1,height1);
        roomBusiness.add(deleteRoomsBtn);
//        修改房间按钮
        JButton updateRoomsBtn = newButtton.addButton("修改房间",400,0,width1,height1);
        roomBusiness.add(updateRoomsBtn);
//        添加查询按键监听
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCount = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
                totalPage = numOfPage(judgeQueryCount(irn.getText(),(String)srt.getSelectedItem()));
                totCounAndPage(xszts,xszys,totalCount,totalPage);
                page = 0;
                refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
                judgeQueryResults(receiveAllRoom);

            }
        });
//        添加上一页按键监听
        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = lastPageMethod(page);
                dqys.setText(page+1+"");
                refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
            }
        });
//        添加下一页按键监听
        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCount = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
                totalPage = numOfPage(totalCount);
                page = nextPageMethod(page,totalPage);
                dqys.setText(page+1+"");
                refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
            }
        });
        addRoomsDialog.add(selectRoomType);
//        添加房间按钮监听
        addRoomsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJDialog.setJDialog(addRoomsDialog,"添加房间按钮");
            }
        });

//      确认添加房间按钮监听
        confirmAddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conAdRo(inputRoomNum,xszts,xszys,dqys,addRoomsDialog);
            }
        });
        /*关闭窗口*/
        executeCancel(cancelAddRoom,addRoomsDialog);
//        表格添加按键监听
        roomsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = 0;
                roomsTypeId = Integer.parseInt(addTables.addKeyMonitoring(roomsTable,c));
                theRoCusNum = Integer.parseInt(addTables.addKeyMonitoring(roomsTable,3));
            }
        });
//        添加删除按键监听
        deleteRoomsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoRo(xszts,xszys,dqys);
            }
        });

//      修改房间按钮
        updateRoomsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modRo();
            }
        });
        return roomBusiness;
    }
//    添加房间方法
    public void conAdRo(JTextField inputRoomNum,JLabel xszts,JLabel xszys,JLabel dqys,JDialog addRoomsDialog) {
        if (inputRoomNum.getText().equals("")){
            showJo("房间号输入不能为空");
        }else{
            if (inputRoomNum.getText().matches(numberRegular)) {

                int allRoomCount0 = roomsBusiness.displayRoomCount("",pleaseSelectRoomType,
                        pleaseSelectRoomType,0);
                receiveAllRoom = roomsBusiness.displayAllRooms("",pleaseSelectRoomType,0,
                        allRoomCount0,pleaseSelectRoomType,0);
                boolean repeatTime0 = isRepeated(receiveAllRoom,1,inputRoomNum.getText());
                if (repeatTime0) {
                    showJo("此房间已有请输入其它房间号");
                }else {
                    if (((String)selectRoomType.getSelectedItem()).equals(pleaseSelectRoomType) ){
                        showJo("请选择房间类型");
                    }else{

                        int allRoomCount1 = roomsBusiness.displayRoomCount("",pleaseSelectRoomType,
                                pleaseSelectRoomType,1);
                        receiveAllRoom = roomsBusiness.displayAllRooms("",pleaseSelectRoomType,0,
                                allRoomCount1,pleaseSelectRoomType,1);
                        boolean repeatTime1 =isRepeated(receiveAllRoom,1,inputRoomNum.getText());
                        if (repeatTime1) {
                            int ss = roomsBusiness.huifuData(inputRoomNum.getText(),(String)selectRoomType.getSelectedItem());
                            disExecuRes(ss,"房间添加");
                        } else {
                            int s = roomsBusiness.addRooms(inputRoomNum.getText(),(String)selectRoomType.getSelectedItem());
                            disExecuRes(s,"房间添加");
                        }

                    }
                }

            }else {
                showJo("请输入数字");
                inputRoomNum.setText("");
            }
            inputRoomNum.setText("");
            selectRoomType.setSelectedIndex(0);
        }
        totalCount = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
        totalPage = numOfPage(totalCount);
        totCounAndPage(xszts,xszys,totalCount,totalPage);
        page = 0;
        dqys.setText(page+1+"");
        refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
        addRoomsDialog.dispose();
    }
//    删除房间方法
    public void remoRo(JLabel xszts,JLabel xszys,JLabel dqys) {
        if (roomsTypeId == 0) {
            showJo("请选择要删除的房间");
        }else{
            /*判断该房间是否住的有人*/
            if (theRoCusNum > 0) {
                showJo("此房间现在有顾客入住不能执行删除操作");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否确定删除该房间", "删除提醒",JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    int re = roomsBusiness.cutRoom(roomsTypeId);
                    if (re > 0) {

                        totalCount = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
                        totalPage = numOfPage(totalCount);
                        totCounAndPage(xszts,xszys,totalCount,totalPage);
                        if (page+1 > totalPage) {
                            page = totalPage-1;
                        }
                        dqys.setText(page+1+"");
                        int c = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
                        if (c == 0) {
                            rt.refreshTable(null,roomHeader,roomTableModel,roomsTable);
                            showJo("最后一条数据已经删除");
                        } else {
                            refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
                            showJo("删除成功");
                        }
                    } else{
                        showJo("删除失败");
                    }
                }
            }
        }
    }
//    修改房间方法
    public void modRo() {
        if (roomsTypeId == 0) {
            showJo("请选择要更改类型的房间");
        }else{
            if (theRoCusNum > 0) {
                showJo("此房间现在有顾客入住不能执行修改操作");
            } else {
                receiveALlRoomType  = roomTypeManagement.displayAllRoomType(0);
                Object [] newArr = new Object[receiveALlRoomType.length];
                for (int i = 0; i < receiveALlRoomType.length; i++) {
                    newArr[i] = receiveALlRoomType[i][1];
                }
                String selectRoType = (String)JOptionPane.showInputDialog(null,"请选择房间类型：\n","类型",
                        JOptionPane.PLAIN_MESSAGE,new ImageIcon(),newArr,"");
                if (selectRoType==null){

                }else{
                    int x = roomsBusiness.modifyRoomsType(selectRoType,roomsTypeId);
                    if (x > 0) {
                        int c = judgeQueryCount(irn.getText(),(String)srt.getSelectedItem());
                        if (c == 0) {
                            rt.refreshTable(null,roomHeader,roomTableModel,roomsTable);
                            showJo("最后一条数据已经修改");
                        } else {
                            refreshRoom(irn.getText(),(String)srt.getSelectedItem(),page*perPageCount,perPageCount);
                            showJo("房间类型修改成功");
                        }
                    }else {
                        showJo("更改失败");
                    }
                }
            }
        }
    }


//    餐饮模块
    public JPanel retuCater () {
        JPanel cateringBusiness = new NewJPanel().newJPanel(width,height);
        String resturantHeader [] = {"id","名称","价格"};
        receiveAllDiet = dietBussiness.diplayAllDiets(0,5,"",0,0,0);
        dietTableModel = new DefaultTableModel(receiveAllDiet,resturantHeader);
        dietTable = addTables.addTable(dietTableModel);
        //        输入名称模块
        JLabel shuRuMingCheng = new newJLabel().addJLabel("输入名称：",0,restY1,width1,height1);
        cateringBusiness.add(shuRuMingCheng);
//        名称输入框
        cateringBusiness.add(inputResName);
//        选菜价格模块
        JLabel zuiDiJia = new newJLabel().addJLabel("最低价格：",350,restY1,width1,height1);
        cateringBusiness.add(zuiDiJia);
//        最低价输入框
        cateringBusiness.add(minPrice);
//        最高价输入框
        JLabel zuiGaoJia = new newJLabel().addJLabel("最高价格：",700,restY1,width1,height1);
        cateringBusiness.add(zuiGaoJia);
        cateringBusiness.add(maxPrice);
//        显示总条数
        JLabel dietTotItemLabel = new newJLabel().addJLabel("总条数："+dietTotalCount,500,labelY,width2,height1);
        cateringBusiness.add(dietTotItemLabel);
//        显示总页数
        dietTotPage = numOfPage(dietTotalCount);
        JLabel dietTotPageLabel = new newJLabel().addJLabel("总页数："+dietTotPage,200,labelY,width2,height1);
        cateringBusiness.add(dietTotPageLabel);
//         显示当前页数
        JLabel dietDangQian = new newJLabel().addJLabel("当前页数：",800,labelY,width1,height1);
        cateringBusiness.add(dietDangQian);
        JLabel dietNowPage = new newJLabel().addJLabel(dietPage+1+"",920,labelY,width0,height1);
        cateringBusiness.add(dietNowPage);
//        查询按钮
        JButton queryResturant = new NewButtton().addButton("查询",1100,restY1,width0,height1);
        cateringBusiness.add(queryResturant);
//        上一页按钮
        JButton dietLastPage = new NewButtton().addButton("上一页",0,labelY,width0,height1);
        cateringBusiness.add(dietLastPage);
//        下一页按钮
        JButton dietNextPage = new NewButtton().addButton("下一页",1100,labelY,width0,height1);
        cateringBusiness.add(dietNextPage);
//        添加饮食按钮
        JButton addDiet = newButtton.addButton("添加餐饮",0,restY0,width1,height1);
        cateringBusiness.add(addDiet);
        JDialog showAddDiet = newJDialog.addJDialog();
        showAddDiet.setLayout(null);
//        添加按键监听
        addDiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJDialog.setJDialog(showAddDiet,"餐饮添加界面");
            }
        });
//        添加输入名称
        JLabel shuCaiMing = njl.addJLabel("请输入名称：",100,100,width2,height1);
        showAddDiet.add(shuCaiMing);
        JTextField inputDishName = ntf.addTextField(300,100,width1,height1);
        showAddDiet.add(inputDishName);
//        添加输入价格;
        JLabel shuRuCaiJia = njl.addJLabel("请输入价格：",100,200,width2,height1);
        showAddDiet.add(shuRuCaiJia);
        JTextField inputDishPrice = ntf.addTextField(300,200,width1,height1);
        showAddDiet.add(inputDishPrice);
//        添加确认按钮
        JButton confirmDietBtn = newButtton.addButton("确认",250,300,width0,height1);
        showAddDiet.add(confirmDietBtn);
//        添加取消按钮
        JButton cancelDietBtn = newButtton.addButton("取消",250,350,100,height1);
        showAddDiet.add(cancelDietBtn);
//        删除餐饮按钮
        JButton removeDiet = newButtton.addButton("删除餐饮",500,restY0,width1,height1);
        cateringBusiness.add(removeDiet);
//        修改餐饮价格按钮
        JButton modifyDietPrice = newButtton.addButton("修改价格",1050,restY0,width1,height1);
        cateringBusiness.add(modifyDietPrice);


        dietTable.setRowHeight(100);
        newJScrollPane(dietTable,cateringBusiness,0,100,width,550);
//          给表格添加按键监听
        dietTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = 0;
                resId = Integer.parseInt(addTables.addKeyMonitoring(dietTable,c));
            }
        });
//        添加查询按键监听
        queryResturant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietTrans1_1 = new DietTrans1_1(numberRegular,minPrice.getText(),maxPrice.getText());
                if (dietTrans1_1.isFlag()) {
                    receiveAllDiet = dietJudgeQueryConditionsDiet(dietPage*perPageCount,perPageCount,inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                    rt.refreshTable(receiveAllDiet,resturantHeader,dietTableModel,dietTable);
                    judgeQueryResults(receiveAllDiet);
                } else {
                    showJo("输入的价格格式不对请重新输入");
                }
                dietTotalCount = dietJudgeQueryCount(inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice());
                dietTotPage = numOfPage(dietTotalCount);
                totCounAndPage(dietTotItemLabel,dietTotPageLabel,dietTotalCount,dietTotPage);
                dietPage = 0;
                dietNowPage.setText(dietPage+1+"");
            }
        });
//        添加上一页按键监听
        dietLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietTrans1_1 = new DietTrans1_1(numberRegular,minPrice.getText(),maxPrice.getText());
                dietPage = lastPageMethod(dietPage);
                receiveAllDiet = dietJudgeQueryConditionsDiet(dietPage*perPageCount,perPageCount,inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                rt.refreshTable(receiveAllDiet,resturantHeader,dietTableModel,dietTable);
                dietNowPage.setText(dietPage+1+"");
            }
        });
//        添加下一页按键监听
        dietNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietTrans1_1 = new DietTrans1_1(numberRegular,minPrice.getText(),maxPrice.getText());
                dietTotalCount = dietJudgeQueryCount(inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice());
                dietTotPage = numOfPage(dietTotalCount);
                dietPage = nextPageMethod(dietPage,dietTotPage);
                receiveAllDiet = dietJudgeQueryConditionsDiet(dietPage*perPageCount,perPageCount,inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
                rt.refreshTable(receiveAllDiet,resturantHeader,dietTableModel,dietTable);
                dietNowPage.setText(dietPage+1+"");
            }
        });
//        添加确认按键监听
        confirmDietBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conAdDiet(inputDishName,inputDishPrice,resturantHeader,dietTotItemLabel,dietTotPageLabel,dietNowPage,showAddDiet);
            }
        });
        /*关闭窗口*/
        executeCancel(cancelDietBtn,showAddDiet);
//        确认删除按钮
        removeDiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleDiet(resturantHeader,dietTotItemLabel,dietTotPageLabel,dietNowPage,showAddDiet);
            }
        });
//        添加按键监听
        modifyDietPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modDiet(resturantHeader,dietTotItemLabel,dietTotPageLabel,dietNowPage,showAddDiet);
            }
        });
        return cateringBusiness;
    }
//    添加餐饮方法
    public void conAdDiet(JTextField inputDishName,JTextField inputDishPrice,String resturantHeader [],JLabel dietTotItemLabel,JLabel dietTotPageLabel,JLabel dietNowPage,JDialog showAddDiet) {
        dietTrans1_1 = new DietTrans1_1(numberRegular,minPrice.getText(),maxPrice.getText());
        if (inputDishName.getText().equals("") || inputDishPrice.getText().equals("")) {
            showJo("名称和价格均不能为空");
        } else {
            if (inputDishPrice.getText().matches(numberRegular)) {
                double idp = Double.parseDouble(inputDishPrice.getText());

                int allDietCount = dietJudgeQueryCount("",0,0);
                Object [][] allDiet0 = dietJudgeQueryConditionsDiet(0,allDietCount,"",
                        0,0,0);
                boolean repeatTimes0 = isRepeated(allDiet0,1,inputDishName.getText());
                if (repeatTimes0) {
                    showJo("此数据已有不能重复添加");
                } else {
                    int tot1 = dietBussiness.displayAllDietCount("",0,0,1);
                    Object allDiet1[][] = dietBussiness.displayDiets(0,tot1,"",0,
                            0);
                    boolean repeatTimes1 = isRepeated(allDiet1,1,inputDishName.getText());
                    if (repeatTimes1) {
                        int cD = dietBussiness.recoverData(idp,inputDishName.getText());
                        disExecuRes(cD,"餐饮添加");
                    } else {
                        int dietCount = dietBussiness.addDiet(inputDishName.getText(),idp);
                        disExecuRes(dietCount,"餐饮添加");
                    }
                }
                reDiet(resturantHeader,dietTotItemLabel,dietTotPageLabel);
                dietPage = 0;
                dietNowPage.setText(dietPage+1+"");
                showAddDiet.dispose();
                inputDishName.setText("");
                inputDishPrice.setText("");
            } else {
                showJo("菜的价格请输入数字");
            }
        }
    }
//    删除餐饮方法
    public void deleDiet (String resturantHeader [],JLabel dietTotItemLabel,JLabel dietTotPageLabel,JLabel dietNowPage,JDialog showAddDiet) {
        dietTrans1_1 = new DietTrans1_1(numberRegular,minPrice.getText(),maxPrice.getText());

        int yesOrNo = JOptionPane.showConfirmDialog(null,"是否删除该数据","操作提示",JOptionPane.YES_NO_OPTION);
        if (yesOrNo == 0) {
            int dietCount = dietBussiness.removeDiet(resId);
            disExecuRes(dietCount,"删除餐饮");
        } else {
            showAddDiet.dispose();
        }
        reDiet(resturantHeader,dietTotItemLabel,dietTotPageLabel);
        dietNowPage.setText(dietPage+1+"");
        resId = 0;
    }
//    修改餐饮方法
    public void modDiet (String resturantHeader [],JLabel dietTotItemLabel,JLabel dietTotPageLabel,JLabel dietNowPage,JDialog showAddDiet) {
        dietTrans1_1 = new DietTrans1_1(numberRegular, minPrice.getText(),maxPrice.getText());
        if (resId == 0) {
            showJo("请选择要修改的餐饮");
        }else {
            String dietPrice = JOptionPane.showInputDialog("请输入餐饮价格");
            if (dietPrice == null) {

            } else if (dietPrice.equals("")) {
                showJo("输入框不能为空");
            } else {

                if (dietPrice.matches(numberRegular)) {
                    int dietNewPrice = dietBussiness.alterDiet(Double.parseDouble(dietPrice),resId);
                    disExecuRes(dietNewPrice,"餐饮价格修改");
                } else {
                    showJo("只能输入数字");
                }
            }
        }
        reDiet(resturantHeader,dietTotItemLabel,dietTotPageLabel);
        showAddDiet.dispose();
    }
//    刷新餐饮界面
    public void reDiet(String resturantHeader [],JLabel dietTotItemLabel,JLabel dietTotPageLabel) {
        receiveAllDiet = dietJudgeQueryConditionsDiet(dietPage*perPageCount,perPageCount,
                inputResName.getText(),dietTrans1_1.getMinPrice(),dietTrans1_1.getMaxPrice(),0);
        rt.refreshTable(receiveAllDiet,resturantHeader,dietTableModel,dietTable);
        dietTotalCount = dietJudgeQueryCount(inputResName.getText(),dietTrans1_1.getMinPrice(),
                dietTrans1_1.getMaxPrice());
        dietTotPage = numOfPage(dietTotalCount);
        totCounAndPage(dietTotItemLabel,dietTotPageLabel,dietTotalCount,dietPage);
    }

//    消费记录模块
    public JPanel retConsump () {
        JPanel consumptionStatistics = new NewJPanel().newJPanel(width,height);
        //        表格模块
        receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,"",1);

        consumpTableModel = new DefaultTableModel(receConsump,consumpHeader);
        consumpTable = addTables.addTable(consumpTableModel);
        consumpTable.setRowHeight(100);
        newJScrollPane(consumpTable,consumptionStatistics,0,100,width,550);
//        显示总条数


        consumptionStatistics.add(consumpZongTiaoshu);
//      显示总页数


        consumptionStatistics.add(consumpZongYeShu);
//      显示当前页数
        JLabel consumpDangQianYeShu = njl.addJLabel("当前页数： ",800,labelY,width1,height1);
        consumptionStatistics.add(consumpDangQianYeShu);

        consumptionStatistics.add(consumpNowPage);
        /*根据手机号查询顾客消费*/
        JLabel guKeShouJiHao = njl.addJLabel("手机号： ",0,40,width1,height1);
        consumptionStatistics.add(guKeShouJiHao);
        JTextField enterCusPhone = ntf.addTextField(150,40,width1,height1);
        consumptionStatistics.add(enterCusPhone);
        /*添加查询按钮*/
        JButton queryConsumption = newButtton.addButton("查询",400,40,width0,height1);
        consumptionStatistics.add(queryConsumption);
//      上一页按钮
        JButton consumpLastPage = newButtton.addButton("上一页",0,labelY,width0,height1);
        consumptionStatistics.add(consumpLastPage);
//      下一页按钮
        JButton consumpNextPage = newButtton.addButton("下一页",1100,labelY,width0,height1);
        consumptionStatistics.add(consumpNextPage);
        /*添加按键监听*/
        queryConsumption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consumpPage = 0;
                if (enterCusPhone.getText().equals("")) {
                    receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,"",1);
                    rt.refreshTable(receConsump,consumpHeader,consumpTableModel,consumpTable);
                    consumpTotCount = listBussiness.findAll();
                    consumpTotPage = numOfPage(consumpTotCount);
                    totCounAndPage(consumpZongTiaoshu,consumpZongYeShu,consumpTotCount,consumpTotPage);
                } else {
                    receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,enterCusPhone.getText(),0);
                    rt.refreshTable(receConsump,consumpHeader,consumpTableModel,consumpTable);
                    totCounAndPage(consumpZongTiaoshu,consumpZongYeShu,1,1);
                }
                consumpNowPage.setText(consumpPage+1+"");
            }
        });
        /*添加上一页按键监听*/
        consumpLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consumpPage = lastPageMethod(consumpPage);
                receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,"",1);
                rt.refreshTable(receConsump,consumpHeader,consumpTableModel,consumpTable);
                consumpNowPage.setText(consumpPage+1+"");
            }
        });
        /*添加下一页按键监听*/
        consumpNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consumpPage = nextPageMethod(consumpPage,consumpTotPage);
                receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,"",1);
                rt.refreshTable(receConsump,consumpHeader,consumpTableModel,consumpTable);
                consumpNowPage.setText(consumpPage+1+"");
            }
        });
        /*给消费记录表添加按键监听*/
        consumpTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                listTable();
            }
        });
        return consumptionStatistics;
    }
//    给表格添加按键监听
    public void listTable() {
        /*显示顾客消费明细弹窗*/
        JDialog showCustomerCostDetail = newJDialog.addJDialog();
        showCustomerCostDetail.setLayout(null);
        /*显示顾客账号*/
        JLabel consumpCustomerDetial = njl.addJLabel("顾客账号： ",150,50,500,height1);
        showCustomerCostDetail.add(consumpCustomerDetial);

        cusPhone = addTables.addKeyMonitoring(consumpTable,0);
        consumpCustomerDetial.setText("顾客账号： "+cusPhone);
        /*订单表格*/
        String listHeader [] = {"订单号","餐饮名称","价格","订购日期"};
        receAllClist = listBussiness.displayAllList(cusPhone);
        listTableModel = new DefaultTableModel(receAllClist,listHeader);
        listTable = addTables.addTable(listTableModel);
        listTable.getTableHeader().setFont(new Font(Font.SERIF,Font.PLAIN,20));
        listTable.setFont(new Font("Menu.font", Font.PLAIN, 20));
        listTable.setRowHeight(50);
        listJs = new JScrollPane(listTable);
        listJs.setBounds(0,100,600,450);
        showCustomerCostDetail.add(listJs);
        /*顾客住房费用*/
        int cusLiveCost = listBussiness.liveCost(cusPhone);
        JLabel liveCostLabel = njl.addJLabel("住房费用： "+cusLiveCost+"￥",0,590,width300,20);
        showCustomerCostDetail.add(liveCostLabel);
        /*顾客消耗的所有费用*/
        int cusAllCost = 0;
        if (receAllClist.length > 0) {
            cusAllCost = cusLiveCost + listBussiness.oneDietCost(cusPhone);
        } else {
            cusAllCost = cusLiveCost;
        }
        JLabel allCostLabel = njl.addJLabel("所有费用： "+cusAllCost+"￥",300,590,width300,20);
        showCustomerCostDetail.add(allCostLabel);
        newJDialog.setJDialog(showCustomerCostDetail,"顾客消费明细");
    }


    //    顾客模块
    public JPanel forCus () {
        JPanel customerBusiness = new NewJPanel().newJPanel(width,height);
        //        顾客表标题

//        顾客表内容
        receiveAllCustomer = shopBusiness.displayAllCustomer(cusPage,perPageCount,"","",
                "",0);
        cusTotalCount = shopBusiness.displayAllCustomerCount("","","",0);
        System.out.println("总条数： "+cusTotalCount);
        cusTotalPage = numOfPage(cusTotalCount);
        shopTableModel = new DefaultTableModel(receiveAllCustomer,customerHeader);
        cusTable = addTables.addTable(shopTableModel);
        cusTable.setRowHeight(100);
        newJScrollPane(cusTable,customerBusiness,0,100,width,550);
//        表格添加监听
        cusTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                cusId = Integer.parseInt(addTables.addKeyMonitoring(cusTable,0));
                cusRoNum = addTables.addKeyMonitoring(cusTable,4);
            }
        });
//          输入顾客手机号
        JLabel shouJiHao = njl.addJLabel("手机号：",0,50,width0,height1);
        customerBusiness.add(shouJiHao);
        customerBusiness.add(cPhone);
//        输入顾客房间号
        JLabel fangJianHao = njl.addJLabel("房间号",350,50,width0,height1);
        customerBusiness.add(fangJianHao);
        customerBusiness.add(cRoomNum);
//        输入顾客姓名
        JLabel guKeXingMing = njl.addJLabel("姓名：",700,50,width0,height1);
        customerBusiness.add(guKeXingMing);

        customerBusiness.add(cName);
//        显示总条
        JLabel cusTotItemLabel = njl.addJLabel("总条数："+cusTotalCount,500,labelY,width2,height1);
        customerBusiness.add(cusTotItemLabel);
//        显示总页数
        JLabel cusTotPageLabel = njl.addJLabel("总页数："+cusTotalPage,200,labelY,width2,height1);
        customerBusiness.add(cusTotPageLabel);
//         显示当前页数
        JLabel cusDangQian = njl.addJLabel("当前页数：",800,labelY,width1,height1);
        customerBusiness.add(cusDangQian);
        JLabel cusNowPage = njl.addJLabel("",920,labelY,width0,height1);
        customerBusiness.add(cusNowPage);
        cusNowPage.setText(cusPage+1+"");
//        上一页
        JButton cusLastPage = newButtton.addButton("上一页",0,labelY,width0,height1);
        customerBusiness.add(cusLastPage);
//        下一页
        JButton cusNextPage = newButtton.addButton("下一页",1100,labelY,width0,height1);
        customerBusiness.add(cusNextPage);
//        添加顾客入住按钮
        JButton addCustomer = newButtton.addButton("顾客入住",0,0,width1,height1);
        customerBusiness.add(addCustomer);
//        新建顾客添加界面
        toAddCustomer.setLayout(null);
//      输入顾客姓名
        JLabel shuRuXingMing = njl.addJLabel("姓名：",cusLabelX,100,width1,height1);
        toAddCustomer.add(shuRuXingMing);
        JTextField inputCusName = ntf.addTextField(cusJtextX,100,width1,height1);
        toAddCustomer.add(inputCusName);
//        输入顾客手机号
        JLabel shuRuShouJiHao = njl.addJLabel("电话：",cusLabelX,150,width1,height1);
        toAddCustomer.add(shuRuShouJiHao);
        JTextField inputCusPhone = ntf.addTextField(cusJtextX,150,width1,height1);
        toAddCustomer.add(inputCusPhone);
//        输入顾客性别
        JLabel shuRuXingBie = njl.addJLabel("性别：",cusLabelX,200,width1,height1);
        toAddCustomer.add(shuRuXingBie);
        JComboBox choseGender = new JComboBox();
        choseGender.setBounds(cusJtextX,200,width1,height1);
        toAddCustomer.add(choseGender);
        choseGender.addItem("男");
        choseGender.addItem("女");
//        输入房间号
        JLabel shuRuRuZhuFJ = njl.addJLabel("房间号：",cusLabelX,250,width1,height1);
        toAddCustomer.add(shuRuRuZhuFJ);
        JTextField inputRoNum = ntf.addTextField(cusJtextX,250,width1,height1);
        toAddCustomer.add(inputRoNum);
//        输入入住时长
        JLabel shuRuRuShiChang = njl.addJLabel("时长：",cusLabelX,300,width1,height1);
        toAddCustomer.add(shuRuRuShiChang);
        JTextField inputLength = ntf.addTextField(cusJtextX,300,width1,height1);
        toAddCustomer.add(inputLength);
//        添加确认按钮
        JButton confirmAddCus = newButtton.addButton("确认",cusLabelX,400,width0,height1);
        toAddCustomer.add(confirmAddCus);
//          添加查询按钮
        JButton queryCustomer = newButtton.addButton("查询",1100,50,width0,height1);
        customerBusiness.add(queryCustomer);
//        添加取消按钮
        JButton cancelAddCus = newButtton.addButton("取消",300,400,width0,height1);
        toAddCustomer.add(cancelAddCus);
        /*执行取消*/
        executeCancel(cancelAddCus,toAddCustomer);
//      添加顾客删除按钮
        JButton removeCustomer = newButtton.addButton("顾客退房",200,0,width1,height1);
        customerBusiness.add(removeCustomer);
//        添加按键监听
        queryCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reCus(cusTotItemLabel,cusTotPageLabel,cusNowPage);
            }
        });
//        添加上一页按键监听
        cusLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusPage = lastPageMethod(cusPage);
                cusNowPage.setText(cusPage+1+"");
                receiveAllCustomer = shopBusiness.displayAllCustomer(cusPage*perPageCount,perPageCount,
                        cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
                rt.refreshTable(receiveAllCustomer,customerHeader,shopTableModel,cusTable);
            }
        });
//        添加下一页按键监听
        cusNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusPage = nextPageMethod(cusPage,cusTotalPage);
                receiveAllCustomer = shopBusiness.displayAllCustomer(cusPage*perPageCount,perPageCount,
                        cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
                rt.refreshTable(receiveAllCustomer,customerHeader,shopTableModel,cusTable);
                cusNowPage.setText(cusPage+1+"");
            }
        });
//        添加按键监听
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJDialog.setJDialog(toAddCustomer,"顾客信息登记");
            }
        });
//        添加确认添加监听
        confirmAddCus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusComIn(inputCusName,inputCusPhone,inputRoNum,inputLength,choseGender,cusTotItemLabel,cusTotPageLabel,cusNowPage);
            }
        });
//        添加确认删除按键监听
        removeCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusChOut(cusTotItemLabel,cusTotPageLabel,cusNowPage);
            }
        });
        return customerBusiness;
    }
//    顾客入住方法
    public void cusComIn (JTextField inputCusName,JTextField inputCusPhone,JTextField inputRoNum,JTextField inputLength,
                          JComboBox choseGender,JLabel cusTotItemLabel,JLabel cusTotPageLabel,JLabel cusNowPage) {
        if (inputCusName.getText().equals("") || inputCusPhone.getText().equals("") || inputRoNum.getText().equals("") || inputLength.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"所有输入框都不能为空",
                    "输入提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (inputRoNum.getText().matches(numberRegular) && inputLength.getText().matches(numberRegular) && inputCusPhone.getText().matches(numberRegular)) {
                /*查询所有入住顾客*/
                int allCheckInCustomersCount = shopBusiness.displayAllCustomerCount("","","",0);
                Object [][] allCheckInCustomers = shopBusiness.displayAllCustomer(0,allCheckInCustomersCount,
                        "","","",0);
                /*判断是否有输入的房间*/
                boolean flag = roomsBusiness.judgeHouse(inputRoNum.getText());
                if (flag) {
                    //                        入住时长
                    int lenOfDay = Integer.parseInt(inputLength.getText());
//                            入住人数
                    int cc = roomsBusiness.toKnowRoomNum(inputRoNum.getText());
                    cc = cc + 1;
                    /*判断该顾客是否已经入住*/
                    boolean repeatTimes0 = isRepeated(allCheckInCustomers,2,cPhone.getText());
                    if (repeatTimes0) {
                        JOptionPane.showMessageDialog(null,"该顾客已入住，不能重复选房间",
                                "操作提示",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int allCheckOutCustomerCount = shopBusiness.displayAllCustomerCount("",
                                "","",1);
                        Object allCheckOutCustomer [][] = shopBusiness.displayAllCustomer(0,
                                allCheckOutCustomerCount,"","","",1);
                        /*判断该顾客以前是否入住过该酒店*/
                        boolean repeateTimes1 = isRepeated(allCheckOutCustomer,2,inputCusPhone.getText());
                        if (repeateTimes1) {
                            int sqlCount = shopBusiness.sqlSever(inputRoNum.getText(),lenOfDay,inputCusName.getText(),inputCusPhone.getText());

                            if (sqlCount > 0) {
                                int c = roomsBusiness.modifyRoNum(inputRoNum.getText(),cc);
                                JOptionPane.showMessageDialog(null,"顾客入驻成功",
                                        "操作提示",JOptionPane.INFORMATION_MESSAGE);

                            } else {
                                JOptionPane.showMessageDialog(null,"顾客入驻失败",
                                        "操作提示",JOptionPane.INFORMATION_MESSAGE);
                            }
                            toAddCustomer.dispose();
                        } else {
                            int countAdd = shopBusiness.addCustomers(inputCusName.getText(),inputCusPhone.getText(),
                                    (String)choseGender.getSelectedItem(),inputRoNum.getText(),lenOfDay);
                            if (countAdd > 0) {
                                JOptionPane.showMessageDialog(null,"数据添加成功",
                                        "操作提示",JOptionPane.INFORMATION_MESSAGE);

                                consumpTotCount = listBussiness.findAll();
                                consumpZongTiaoshu.setText("总条数："+consumpTotCount);
                                int c = roomsBusiness.modifyRoNum(inputRoNum.getText(),cc);
                                disExecuRes(c,"顾客入住");
                                toAddCustomer.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null,"数据添加失败",
                                        "操作提示",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"没有此房间请重新输入",
                            "输入提示",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"房间号或者时长格式输入不正确,请重新输入",
                        "输入提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }
      receiveAllRoom = judgeQueryConditions(irn.getText(),(String)srt.getSelectedItem(),
                page*perPageCount,perPageCount);
        rt.refreshTable(receiveAllRoom,roomHeader,roomTableModel,roomsTable);
        reCus(cusTotItemLabel,cusTotPageLabel,cusNowPage);
    }
//    顾客退房方法
    public void cusChOut (JLabel cusTotItemLabel,JLabel cusTotPageLabel,JLabel cusNowPage) {
        if (cusId == 0) {
            JOptionPane.showMessageDialog(null,"请选择要退房的顾客",
                    "操作提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            int isCom = JOptionPane.showConfirmDialog(null,"该顾客是否确定退房",
                    "退房提醒", JOptionPane.YES_NO_OPTION);
            if (isCom == 0) {
                int tomCount = shopBusiness.tombstoning(cusId);
                if (tomCount > 0) {
                    cusTotalCount = shopBusiness.displayAllCustomerCount(cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
                    receiveAllCustomer = shopBusiness.displayAllCustomer(cusPage*perPageCount,perPageCount,
                            cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
                    rt.refreshTable(receiveAllCustomer,customerHeader,shopTableModel,cusTable);

                    JOptionPane.showMessageDialog(null,"退房成功",
                            "操作提示",JOptionPane.INFORMATION_MESSAGE);
                    int cNumber = 0;
                    cNumber = roomsBusiness.toKnowRoomNum(cusRoNum);
                    cNumber--;
                    int c = roomsBusiness.modifyRoNum(cusRoNum,cNumber);
                    disExecuRes(c,"顾客退房");

                    /*结账模块*/
                    int closeAccounts = listBussiness.removeClist(cusId);
                } else {
                    JOptionPane.showMessageDialog(null,"退房失败",
                            "操作提示",JOptionPane.INFORMATION_MESSAGE);
                }
                receiveAllRoom = judgeQueryConditions(irn.getText(),(String)srt.getSelectedItem(),
                        page*perPageCount,perPageCount);
                rt.refreshTable(receiveAllRoom,roomHeader,roomTableModel,roomsTable);
            } else {
                toAddCustomer.dispose();
            }
        }
    reCus(cusTotItemLabel,cusTotPageLabel,cusNowPage);
    }
//    刷新顾客页面
    public void reCus(JLabel cusTotItemLabel,JLabel cusTotPageLabel,JLabel cusNowPage) {
        //        刷新顾客页面
        receiveAllCustomer = shopBusiness.displayAllCustomer(cusPage*perPageCount,perPageCount,
                cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
        rt.refreshTable(receiveAllCustomer,customerHeader,shopTableModel,cusTable);
        cusTotalCount = shopBusiness.displayAllCustomerCount(cPhone.getText(),cRoomNum.getText(),cName.getText(),0);
        cusTotalPage = numOfPage(cusTotalCount);
        totCounAndPage(cusTotItemLabel,cusTotPageLabel,cusTotalCount,cusTotalPage);
        cusPage = 0;
        cusNowPage.setText(cusPage+1+"");

//        刷新餐饮页面
        consumpPage = 0;
        consumpNowPage.setText(consumpPage+1+"");
        consumpTotCount = listBussiness.findAll();
        consumpTotPage = numOfPage(consumpTotCount);
        totCounAndPage(consumpZongTiaoshu,consumpZongYeShu,consumpTotCount,consumpTotPage);
        receConsump = listBussiness.allCustomerCost(consumpPage*perPageCount,perPageCount,"",1);
        rt.refreshTable(receConsump,consumpHeader,consumpTableModel,consumpTable);
    }
//    房间模块表格刷新
    public void refreshRoom (String roNum,String rtype,int startIt,int pageSize) {
        receiveAllRoom = judgeQueryConditions(roNum,rtype,startIt,pageSize);
        rt.refreshTable(receiveAllRoom,roomHeader,roomTableModel,roomsTable);
    }


    //    判断查询结果
    public void judgeQueryResults (Object [][] arr) {
        if (arr.length == 0) {
            JOptionPane.showMessageDialog(null,"未找到符合条件的结果",
                    "操作提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Object[][] judgeQueryConditions (String riv,String rsv,int startItem,int pageSize) {
        Object [][] arr = null;
        arr = roomsBusiness.displayAllRooms(riv,rsv,startItem,pageSize,pleaseSelectRoomType,0);
        return arr;
    }

    //    餐饮业务
    public Object[][] dietJudgeQueryConditionsDiet (int startItem,int pageSize,String resName,int minPrice,int maxPrice,int isDelete) {
        Object [][] arr = null;
        arr = dietBussiness.diplayAllDiets(startItem,pageSize,resName,minPrice,maxPrice,isDelete);
        return arr;
    }

    //    判断总页数
    public int numOfPage (int tot) {
        int s = 0;
        if (tot % perPageCount == 0) {
            s = tot / perPageCount;
        }else {
            s = tot / perPageCount+1;
        }
        return s;
    }

    //    判断总条数
    public int judgeQueryCount (String riv,String rsv) {
        int count ;
        count = roomsBusiness.displayRoomCount(riv,rsv,pleaseSelectRoomType,0);
        return count;
    }

    public int dietJudgeQueryCount (String resName,int minPrice,int maxPrice) {
        int count;
        count = dietBussiness.displayAllDietCount(resName,minPrice,maxPrice,0);
        return count;
    }

    /*显示执行结果*/
    public void disExecuRes (int theResutl,String str) {
        if (theResutl > 0) {
            JOptionPane.showMessageDialog(null,str+"成功","操作提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,str+"失败","操作提示",JOptionPane.ERROR_MESSAGE);
        }
    }

    /*判断是否重复*/
    public boolean isRepeated (Object [][] arr,int i,String contrastItem) {
        boolean flag = false;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j][i].equals(contrastItem)) {
                flag = true;
            }
        }
        return flag;
    }

    /*上一页方法*/
    public int lastPageMethod (int thePage) {
        thePage--;
        if (thePage < 0) {
            thePage = 0;
            JOptionPane.showMessageDialog(null,"已经是第一页了","页数提示",JOptionPane.INFORMATION_MESSAGE);
        }
        return thePage;
    }

    /*下一页方法*/
    public int nextPageMethod (int thePage,int allPage) {
        thePage++;
        if (thePage + 1 > allPage) {
            thePage = allPage -1;
            JOptionPane.showMessageDialog(null,"已经是最后一页了","页数提示",JOptionPane.INFORMATION_MESSAGE);
        }
        return thePage;
    }

    /*添加取消按钮按键监听*/
    public void executeCancel (JButton button,JDialog dialog) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }

    /*显示总页数和总条数*/
    public void totCounAndPage (JLabel totCount,JLabel totPage,int totalCount,int totalPage) {
        totCount.setText("总条数："+totalCount);
        totPage.setText("总页数："+totalPage);
    }

    /*创建滚动条*/
    public void newJScrollPane (JTable table,JPanel panel,int x,int y,int width,int height) {
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(x,y,width,height);
        jScrollPane.setVisible(true);
        panel.add(jScrollPane);
    }

    public void showJo (String str) {
        JOptionPane.showMessageDialog(null,str,
                "输入提示",JOptionPane.INFORMATION_MESSAGE);
    }

//    main方法

}