package view.Interface;

import javax.swing.*;
import java.awt.*;

public class NewTextField {

    public JTextField addTextField (int x,int y,int width,int height) {
        JTextField inputSomething = new JTextField();
        inputSomething.setEditable(true);
        inputSomething.setColumns(11);
        inputSomething.setBounds(x,y,width,height);
        inputSomething.setFont(new Font(Font.SERIF,Font.PLAIN,22));
        return inputSomething;

    }

}
