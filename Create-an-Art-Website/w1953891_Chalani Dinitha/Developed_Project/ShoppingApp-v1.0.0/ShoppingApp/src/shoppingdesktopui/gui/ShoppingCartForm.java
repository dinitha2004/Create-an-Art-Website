/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingdesktopui.gui;

import dataaccess.DataStore;
import java.awt.GridLayout;
import javax.swing.*;
import shoppingcore.Product;
import shoppingcore.ShoppingCart;
import shoppingdesktopui.ProductTableModel;

/**
 *
 * @author Sachintha Silva
 */
public class ShoppingCartForm extends JFrame{
    /* UI names */
    private final  String formName = "Shopping Cart";
    private final  String totalCostName = "Total";
    private final  String firstPurchaseDiscountName = "First Purchase Discount (%.1f%%)";
    private final  String threeCategoryDiscountName = "Three same Category Discount (%.1f%%)";
    private final  String FinalTotalCostName = "Final Total";
    
    private final ShoppingCart cart;
    private final String currency;
    private final double firstPurchaseDiscount;
    private final double threeCategoryDiscount;
    
    /* Component declarations */
    JTable productsTable = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    JPanel pnlSummaryGrid = new JPanel(new GridLayout(4, 2));
    JLabel lblTotalCost = new JLabel();
    JLabel lblTotalCostValue = new JLabel();
    JLabel lblFirstPurchase = new JLabel();
    JLabel lblFirstPurchaseValue = new JLabel();
    JLabel lblThreeCategory = new JLabel();
    JLabel lblThreeCategoryValue = new JLabel();
    JLabel lblFinalTotal = new JLabel();
    JLabel lblFinalTotalValue = new JLabel();

    public ShoppingCartForm(ShoppingCart cart) {
        this.cart = cart;
        this.currency = DataStore.getCurrency();
        this.firstPurchaseDiscount = DataStore.getFirstPurchaseDiscount();
        this.threeCategoryDiscount = DataStore.getThreeCategoryDiscount();
        initialize();
    }
    
    private void initialize() {
        setTitle(formName);
        setName(formName);
        setSize(540, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        
        /* Cost label */
        lblTotalCost.setText(totalCostName);
        lblTotalCost.setHorizontalAlignment(JLabel.RIGHT);
        lblTotalCostValue.setHorizontalAlignment(JLabel.RIGHT);
        
        /* First purchase discount label */
        lblFirstPurchase.setText(String.format(firstPurchaseDiscountName, firstPurchaseDiscount));
        lblFirstPurchase.setHorizontalAlignment(JLabel.RIGHT);
        lblFirstPurchaseValue.setHorizontalAlignment(JLabel.RIGHT);
        
        /* Three category discount label */
        lblThreeCategory.setText(String.format(threeCategoryDiscountName, threeCategoryDiscount));
        lblThreeCategory.setHorizontalAlignment(JLabel.RIGHT);
        lblThreeCategoryValue.setHorizontalAlignment(JLabel.RIGHT);
        
        /* Final total discount label */
        lblFinalTotal.setText(FinalTotalCostName);
        lblFinalTotal.setHorizontalAlignment(JLabel.RIGHT);
        lblFinalTotalValue.setHorizontalAlignment(JLabel.RIGHT);
        
        /* Product table */
        ProductTableModel productModel = new ProductTableModel(cart.getCartItems());
        productsTable.setModel(productModel);
        
        scrollPane.setViewportView(productsTable);
        scrollPane.setBounds(30, 10, 480, 120);
        
        /* Summary grid */
        pnlSummaryGrid.setBounds(30, 150, 480, 120);
        pnlSummaryGrid.add(lblTotalCost);
        pnlSummaryGrid.add(lblTotalCostValue);
        pnlSummaryGrid.add(lblFirstPurchase);
        pnlSummaryGrid.add(lblFirstPurchaseValue);
        pnlSummaryGrid.add(lblThreeCategory);
        pnlSummaryGrid.add(lblThreeCategoryValue);
        pnlSummaryGrid.add(lblFinalTotal);
        pnlSummaryGrid.add(lblFinalTotalValue);
        
        /* Add controls to the main form */
        add(scrollPane);
        getContentPane().add(pnlSummaryGrid);
        
        calculateCartSummary();
    }
    
    private void calculateCartSummary() {
        double total = cart.calculateTotalCost();
        double firstDiscount = total * firstPurchaseDiscount / 100;
        double threeCatDiscount = 0;
        double finalTotal;
        
        if (countCategory("Clothing") >= 3 || countCategory("Electronic") >= 3) {
            threeCatDiscount = total * threeCategoryDiscount / 100;
        }
        
        finalTotal = total - firstDiscount - threeCatDiscount;
        
        lblTotalCostValue.setText(String.format("%.2f %s", total, currency));
        lblFirstPurchaseValue.setText(String.format("%.2f %s", firstDiscount, currency));
        lblThreeCategoryValue.setText(String.format("%.2f %s", threeCatDiscount, currency));
        lblFinalTotalValue.setText(String.format("%.2f %s", finalTotal, currency));
    }
    
    private int countCategory(String category) {
        int count = 0;
        for (Product product : cart.getCartItems()) {
            if (product.getCategory().equals(category))
                count++;
        }
        return count;
    }
}
