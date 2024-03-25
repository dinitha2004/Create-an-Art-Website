package shoppingcore;

import java.util.Objects;

public abstract class Product implements Cloneable {
    private String productID;
    protected String productName;
    protected int numberOfAvailableItems;
    protected int price;
    protected String category; 

    public Product(String productID, String productName, int numberOfAvailableItems, int price) {
        this.productID = productID;
        this.productName = productName;
        this.numberOfAvailableItems = numberOfAvailableItems;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
    }

    public void setNumberOfAvailableItems(int numberOfAvailableItems) {
        this.numberOfAvailableItems = numberOfAvailableItems;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        Product product = (Product) obj;
        return productID.equalsIgnoreCase(product.productID);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.productID);
        return hash;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
