package dataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import shoppingcore.*;

public class DataStore {
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>(
            Arrays.asList(
                new User("sachi", "1001001"), 
                new User("leon", "Leon@123"),
                new User("claire", "Claire@123"),
                new User("batman", "2002002")
            )
        );
        return users;
    }
    
    public static String[] getCategories() {
        String[] categories = { "All", "Electronic", "Clothing" };
        return categories;
    }
    
    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>(
            Arrays.asList(
                new Clothing("PD001", "Polo T-Shirt", 10, 1490, 14, "Black"),
                new Clothing("PD002", "Moose Pants", 20, 1890, 29, "Gray"),
                new Clothing("PD003", "Midi Leather Skirt", 6, 3690, 30, "Blue"),
                new Clothing("PD004", "Selvedge Denim Skirt", 12, 7990, 32, "Indigo"),
                new Electronics("PE001", "Galaxy S22 Ultra", 5, 400000, "SAMSUNG", 12),
                new Electronics("PE002", "40LJ616D", 2, 150000, "LG Electronics", 36),
                new Electronics("PE003", "iPhone 15 Pro Max", 5, 400000, "Apple Inc.", 12),
                new Electronics("PE004", "Galaxy Watch 5", 15, 130000, "SAMSUNG", 12)
            )
        );
        return products;
    }
    
    public static String getCurrency() {
        return "LKR";
    }
    
    public static double getFirstPurchaseDiscount() {
        return 10;
    }
    
    public static double getThreeCategoryDiscount() {
        return 20;
    }
}
