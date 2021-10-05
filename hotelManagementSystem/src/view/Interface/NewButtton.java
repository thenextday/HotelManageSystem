package view.Interface;

import javax.swing.*;
import java.awt.*;

public class NewButtton {

    public JButton addButton(String text,int x,int y ,int width,int height) {
        JButton button = new JButton();
        button.setText(text);
        button.setBounds(x,y,width,height);
        button.setFont(new Font(Font.SERIF,Font.PLAIN,22));
        return button;
    }

}
