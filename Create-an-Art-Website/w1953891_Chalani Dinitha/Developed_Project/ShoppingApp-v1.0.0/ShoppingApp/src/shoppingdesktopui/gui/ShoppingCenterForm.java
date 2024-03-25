/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingdesktopui.gui;

import dataaccess.DataStore;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableRowSorter;
import shoppingcore.*;
import shoppingdesktopui.*;

public class ShoppingCenterForm extends JFrame {
    /* UI names */
    private String formName = "Shopping Center";
    private final  String categoriesName = "Categories";
    private final  String cartButtonName = "Shopping Cart";
    private final  String addToCartButtonName = "Add to Cart";
    
    private ArrayList<Product> products;
    private ShoppingCart cart = new ShoppingCart();
    private Product selectedProduct;
    private String currency;
    
    /* Component declarations */
    JComboBox<String> cmbCategories = new JComboBox<>();
    JLabel lblCategories = new JLabel(categoriesName);
    JButton btnViewCart = new JButton(cartButtonName);
    JButton btnAddToCart = new JButton(addToCartButtonName);
    ProductTableModel productModel;
    JTable productsTable = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>();
    JTextArea txtProductDescription = new JTextArea();  
    
    public ShoppingCenterForm() {
        initialize();
    }
    
    public ShoppingCenterForm(String formName) {
        this.formName = formName;
        initialize();
    }
    
    private void initialize() {
        products = DataStore.getProducts();
        currency = DataStore.getCurrency();
        
        /* Shopping Center form */
        setTitle(formName);
        setName(formName);
        setSize(720, 540);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new LoginForm().setVisible(true);
            }
        });
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        
        /* Categories label */
        lblCategories.setBounds(30, 30, 100, 32);
        
        /* Categories combobox */
        String[] categories = DataStore.getCategories();
        DefaultComboBoxModel<String> categoryOptionModel = new DefaultComboBoxModel<>(categories);
        cmbCategories.setModel(categoryOptionModel);
        cmbCategories.setBounds(120, 30, 120, 32);
        cmbCategories.addItemListener((ItemEvent e) -> handleCategoryChange(e));
        
        /* View Cart button */
        btnViewCart.setBounds(540, 30, 120, 32);
        btnViewCart.addActionListener((ActionEvent e) -> showCart(e));
        
        /* Add to Cart button */
        btnAddToCart.setBounds(30, 420, 120, 32);
        btnAddToCart.addActionListener((ActionEvent e) -> addToCart(e));
        
        /* Products table */
        productModel = new ProductTableModel(products);
        sorter.setModel(productModel);
        productsTable.setModel(productModel);
        productsTable.setRowSorter(sorter);
        productsTable.setDefaultRenderer(Object.class, new ShoppingTableRenderer());
        productsTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> handleProductSelection(e));
        
        /* Scroll pane */
        scrollPane.setViewportView(productsTable);
        scrollPane.setBounds(30, 100, 640, 150);
        
        /* Product description */
        txtProductDescription.setBounds(30, 260, 640, 150);
        txtProductDescription.setEditable(false);
        
        /* Add controls to the main form */
        add(lblCategories);
        add(cmbCategories);
        add(btnViewCart);
        add(scrollPane);
        add(txtProductDescription);
        add(btnAddToCart);
    }
    
    private void handleCategoryChange(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedCategory = cmbCategories.getSelectedItem().toString();
            RowFilter<Object, Object> rowFilter;
            int filterColumnIndex = 2;
            
            if (selectedCategory.equals("All")) {
                rowFilter = RowFilter.regexFilter(".*");
            } 
            else {
                rowFilter = RowFilter.regexFilter(selectedCategory, filterColumnIndex);
            }
            
            sorter.setRowFilter(rowFilter);
        }
        resetProductDescription();
    }
    
    private void handleProductSelection(ListSelectionEvent e) {
        resetProductDescription();
        
        if (!e.getValueIsAdjusting()) {
            int selectedRow = productsTable.getSelectedRow();
            int productIdColumnIndex = 0;
            
            if (selectedRow != -1) {
                String id = productsTable.getValueAt(selectedRow, productIdColumnIndex).toString();
                selectedProduct = products.stream().filter(p -> p.getProductID().equalsIgnoreCase(id)).findFirst().orElse(null);
                setProductDescription(selectedProduct);
            }
        }
    }
    
    private void resetProductDescription() {
        txtProductDescription.setText(null);
    }
    
    private void setProductDescription(Product product) {
        resetProductDescription();
        if (product != null) {
            StringBuilder productText = new StringBuilder();
            productText
                .append(String.format("Product ID: \t %s\n", product.getProductID()))
                .append(String.format("Product Name: \t %s\n", product.getProductName()))
                .append(String.format("Available Qty: \t %s\n", product.getNumberOfAvailableItems()))
                .append(String.format("Category: \t %s\n", product.getCategory()))
                .append(String.format("Price: \t %s %s\n", product.getPrice(), currency));
            
            if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                productText
                    .append(String.format("Size: \t %s\n", clothing.getSize()))
                    .append(String.format("Color: \t %s\n", clothing.getColor()));
            }
            
            if (product instanceof Electronics) {
                Electronics electronic = (Electronics) product;
                productText
                    .append(String.format("Brand: \t %s\n", electronic.getBrand()))
                    .append(String.format("Warranty: \t %s months\n", electronic.getWarrantyPeriod()));
            }
            
            txtProductDescription.setText(productText.toString());
        }
    }
    
    private void showCart(ActionEvent e) {
        new ShoppingCartForm(cart).setVisible(true);
    }
    
    private void addToCart(ActionEvent e) {
        try {
            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Select a product first!", 
                    "Product", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if (selectedProduct.getNumberOfAvailableItems() == 0) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Product is out of stock!", 
                    "Product", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            if (selectedProduct != null) {
                Product product = (Product)selectedProduct.clone();
                product.setNumberOfAvailableItems(1);
                cart.addProduct(product);
                selectedProduct.setNumberOfAvailableItems(selectedProduct.getNumberOfAvailableItems() - 1);
                JOptionPane.showMessageDialog(
                    this, 
                    "Product added to cart!", 
                    "Product", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                productModel.fireTableDataChanged();
            }
        }
        catch (CloneNotSupportedException ex){
            ex.getMessage();
        }
        finally {
            selectedProduct = null;
        }
    }
}
