package view.Interface;

import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class AddTable {

    public JTable addTable (DefaultTableModel tableModel) {
        JTable table = new JTable(tableModel){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(1, 50));//设置表格的大小
        table.setRowHeight (30);//设置每行的高度为20
        table.setRowHeight (0, 20);//设置第1行的高度为15
        table.setRowMargin (5);//设置相邻两行单元格的距离
        table.setRowSelectionAllowed (true);//设置可否被选择.默认为false
        table.setSelectionBackground (Color.white);//设置所选择行的背景色
        table.setSelectionForeground (Color.red);//设置所选择行的前景色
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor (Color.black);//设置网格线的颜色
        table.clearSelection ();//取消选择
        table.setDragEnabled (false);//关闭拖动处理
        table.setShowGrid (false);//是否显示网格线
        table.setShowHorizontalLines (true);//是否a显示水平的网格线
        table.setShowVerticalLines (false);//是否显示垂直的网格线
        table.doLayout ();
        table.setBackground (Color.lightGray);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,cr);
        table.getTableHeader().setFont(new Font(Font.SERIF,Font.PLAIN,25));
        table.setFont(new Font("Menu.font", Font.PLAIN, 25));
        return table;
    }

    public String addKeyMonitoring (JTable table ,int column) {
        int r = table.getSelectedRow();
        Object values = table.getValueAt(r,column);
        String string = values.toString();
        return string;
    }

    public DefaultTableModel returntableModel (Object [][] arr, String [] tableHeaer, JPanel panel) {
        DefaultTableModel tableModel = new DefaultTableModel(arr,tableHeaer);
        JTable table = addTable(tableModel);
        JScrollPane js = new JScrollPane(table);
        js.setBounds(0,100,1225,550);
        panel.add(js);
        return tableModel;
    }
}
