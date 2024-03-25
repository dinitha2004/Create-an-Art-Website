package shoppingdesktopui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import shoppingcore.Product;

public class ProductTableModel extends AbstractTableModel{
    List<Product> data;
    String[] columnHeaders = { "Product ID", "Name", "Category", "Qty", "Price" };
    
    public ProductTableModel(List<Product> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnHeaders.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = data.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return product.getProductID(); 
            case 1: return product.getProductName(); 
            case 2: return product.getCategory(); 
            case 3: return product.getNumberOfAvailableItems(); 
            case 4: return product.getPrice(); 
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnHeaders[column];
    }
}
