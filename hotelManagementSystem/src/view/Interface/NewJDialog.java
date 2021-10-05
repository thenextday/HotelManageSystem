package view.Interface;

import javax.swing.*;
import java.awt.*;

public class NewJDialog {

    public JDialog addJDialog() {
        int width = 600;
        int height = 700;
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height)/2;
        JDialog dialog = new JDialog();
        dialog.setBounds(x,y,width,height);
        dialog.setLayout(new FlowLayout());
        return dialog;
    }

    public void setJDialog(JDialog jDialog,String tiltle) {
        jDialog.setModal(true);
        jDialog.setTitle(tiltle);
        jDialog.setVisible(true);
    }

}
