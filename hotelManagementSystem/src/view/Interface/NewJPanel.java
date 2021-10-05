package view.Interface;

import javax.swing.*;

public class NewJPanel {

    public JPanel newJPanel (int width,int height) {
        JPanel panel = new JPanel();
        panel.setBounds(0,0,width,height);
        panel.setLayout(null);
        return panel;
    }

}
