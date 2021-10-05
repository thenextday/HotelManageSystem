package view.Interface;

import javax.swing.*;
import java.awt.*;

public class newJLabel {
    public JLabel addJLabel (String text,int x,int y,int width,int height) {
        JLabel jl =  new JLabel();
        jl.setText(text);
        Font f = new Font(Font.SERIF,Font.PLAIN,24);
        jl.setForeground(Color.black);
        jl.setFont(f);
        jl.setBounds(x,y,width,height);
        return jl;
    }
}
