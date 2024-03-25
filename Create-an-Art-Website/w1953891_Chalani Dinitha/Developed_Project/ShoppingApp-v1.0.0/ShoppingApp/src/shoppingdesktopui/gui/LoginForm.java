package shoppingdesktopui.gui;

import dataaccess.DataStore;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import shoppingcore.User;

public class LoginForm extends JFrame {
    /* UI names */
    private final String formName = "Login";
    private final String loginButtonName = "Login";
    private final String userNameLabelText = "Username: ";
    private final String passwordLabelText = "Password: ";
    
    /* Component declarations */
    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JLabel lblUsername = new JLabel(userNameLabelText);
    JLabel lblpassword = new JLabel(passwordLabelText);
    JButton btnLogin = new JButton(loginButtonName);
    
    public LoginForm() {
        initialize();
    }
    
    private void initialize() {
        /* Login form */
        setTitle(formName);
        setName(formName);
        setSize(320, 160);      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));
        setResizable(false);

        /* Login button */
        btnLogin.setBounds(30, 100, 100, 32);
        btnLogin.addActionListener((ActionEvent e) -> handleAuthentication(e));
        
        /* Add controls to the main form */
        add(lblUsername);
        add(txtUsername);
        add(lblpassword);
        add(txtPassword);
        add(new JLabel());
        add(btnLogin);
        add(new JLabel());
    }
    
    private void handleAuthentication(ActionEvent e) {
        
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        
        if (DataStore.getUsers().contains(new User(username, password))) {
            dispose();
            new ShoppingCenterForm("Wesminister Shopping Center - " + username).setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(
                this, 
                "Authentication failed!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            reset();
        }    
    }
    
    public void reset() {
        txtPassword.setText(null);
    }
}
