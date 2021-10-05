package view.Interface;

//import model.Jdbc.CustomerLogin;
//import model.Jdbc.MangerLogin;

import controllor.*;
import view.CustomerInterface.ShopperInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class index {
    index a = this;
     int width = 1200;
     int height = 867;
//     输入框
     JTextField inputUserName = new JTextField();
//     密码框
     JPasswordField inputUserPassword = new JPasswordField();
//     下拉菜单
     JComboBox chID = new JComboBox();
//     按钮
     JButton loginButton = new JButton("登录");

    public String iun = null;
    String iup = null;
    ShopperInterface shopperInterface = new ShopperInterface();



    public void useFrame () {
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width -this.width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height -this.height)/2;
        JFrame frame = new JFrame();

//              添加一个面板
        JPanel conJpanel = new JPanel();


        conJpanel.setBounds(450,450,300,260);
        conJpanel.setBackground(new Color(0,0,0,0));

        conJpanel.setLayout(null);

//        添加文字
//        添加用户名
        JLabel username = new JLabel("用户名:");
        Font usernameFont = new Font(Font.SERIF, Font.PLAIN, 24);
        username.setForeground(Color.white);
        username.setFont(usernameFont);
        username.setBounds(0,0,100,50);
        conJpanel.add(username);

//      添加用户密码
        JLabel userPassword = new JLabel();
        String userPasswordS = "密码:";
        Font usrPasswordFont = new Font(Font.SERIF, Font.PLAIN, 24);
        userPassword.setText(userPasswordS);
        userPassword.setFont(usrPasswordFont);
        userPassword.setForeground(Color.white);
        userPassword.setBounds(0,50,120,50);
        conJpanel.add(userPassword);

//        添加用户身份选择
        JLabel chooseIdentity = new JLabel();
        String chooseIdentityS = "身份:";
        chooseIdentity.setText(chooseIdentityS);
        chooseIdentity.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        chooseIdentity.setForeground(Color.white);
        chooseIdentity.setBounds(0,100,120,50);
        conJpanel.add(chooseIdentity);

//        设置文本框
//        设置用户名输入框
        inputUserName.setEditable(true);
        inputUserName.setColumns(11);
        inputUserName.setBounds(120,13,150,30);
        inputUserName.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
        conJpanel.add(inputUserName);

//        设置密码输入框
        inputUserPassword.setEnabled(true);
        inputUserPassword.setColumns(11);
        inputUserPassword.setBounds(120,63,150,30);
        conJpanel.add(inputUserPassword);

//        设置下拉菜单
        chID.addItem("----请选择----");
        chID.addItem("顾客");
        chID.addItem("管理员");
        chID.setBounds(120,113,150,30);
        chID.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
        conJpanel.add(chID);

//        添加按钮
        loginButton.setBounds(100,170,100,25);
        loginButton.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
        conJpanel.add(loginButton);

//       添加按钮监听
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iun = inputUserName.getText();
                iup = new String(inputUserPassword.getPassword());
                if(iun.length() == 0 || iup.length() == 0) {
                    JOptionPane.showMessageDialog(null,"用户和密码不能为空","输入提示",
                    JOptionPane.INFORMATION_MESSAGE);
                }else{
                    String chooseId = (String)chID.getSelectedItem();
                    if(chooseId == "管理员"){
                        boolean flag = new UserLogin().administratorLogin(iun,iup);
                        if (flag) {
                            frame.dispose();
                            Administrator ad = new Administrator();
                            ad.buildBoundary();
                        }else{
                            JOptionPane.showMessageDialog(null,"用户名或密码错误登录失败",
                            "登录提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else if (chooseId == "顾客"){
                        boolean flag = new ShopBusiness().cusRes(iun,iup);
                        if (flag) {
                            shopperInterface.cusPhone = iun;
                            shopperInterface .buildSurface();
                            frame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(null,"用户名或密码错误登录失败",
                            "登录提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"请选择身份","身份提示",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        //        设置窗体

        frame.setTitle("酒店管理系统");
        frame.setBounds(x,y,width,height);

        //      设置背景图片

        frame.add(conJpanel);
        ImageIcon bg = new ImageIcon("hotelManagementSystem/src/img/hotel03.jpg");
        JLabel label = new JLabel(bg);
        label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
        frame.add(label);
        frame.setLayout(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);


    }

    public static void main (String[] args) {
        index in = new index();
        in.useFrame();
    }
}
