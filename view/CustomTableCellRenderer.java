package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Integer ) {
            Integer amount = (Integer) value;
            if (amount.intValue() > 0) {
                cell.setBackground(Color.BLACK);
                // You can also customize the Font and Foreground this way
                // cell.setForeground();
                // cell.setFont();
            } else {
                cell.setBackground( Color.BLUE);
            }
        }
        
        return cell;
    }
}
