package shoppingcore;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        if (products.contains(product)){
            int index = products.indexOf(product);
            Product existingProduct = products.get(index);
            int qty = existingProduct.getNumberOfAvailableItems();
            int unitPrice = existingProduct.getPrice() / qty;
            qty++;
            int price = unitPrice * qty;
            existingProduct.setNumberOfAvailableItems(qty);
            existingProduct.setPrice(price);
        }
        else {
            products.add(product);
        }
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            totalCost += product.getPrice();
        }
        return totalCost;
    }
    
    public ArrayList<Product> getCartItems(){
        return products;
    }
}