package view.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Refresh {
    public void refreshTable(Object [][] tableData, Object [] tableHeader, DefaultTableModel tableModel, JTable table) {
        tableModel = new DefaultTableModel(tableData,tableHeader);
        table.setModel(tableModel);
    }

    public void refreshJCombox (JComboBox comboBox,Object[][] data,String theFirst) {
        comboBox.removeAllItems();
        comboBox.addItem(theFirst);
        for (int i = 0; i < data.length; i++) {
            comboBox.addItem(data[i][1]);
        }
    }
}
