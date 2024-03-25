package shoppingdesktopui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ShoppingTableRenderer extends DefaultTableCellRenderer {
    private final int MIN_QTY_LIMIT = 3;
    private final Color QTY_LIMITING_COLOR = Color.RED;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        int qtyIndex = 3;
        int qty = (int) table.getModel().getValueAt(row, qtyIndex);
        
        if (qty < MIN_QTY_LIMIT) {
            component.setBackground(QTY_LIMITING_COLOR);
        } else {
            component.setBackground(table.getBackground());
        }

        return component;
    }
}
