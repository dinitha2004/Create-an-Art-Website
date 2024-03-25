package shoppingcore;

public class Clothing extends Product {
    private int size;
    private String color;

    public Clothing(String productID, String productName, int numberOfAvailableItems, int price, int size, String  color) {
        super(productID, productName, numberOfAvailableItems, price);
        category = "Clothing";
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
