/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingconsoleui;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import shoppingcore.*;

public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> products = new ArrayList<>();
    boolean running = true;


    public void displayMenu() {
        while (running) {
            System.out.println("===================================");
            System.out.println("            MENU OPTIONS           ");
            System.out.println("===================================");
            System.out.println("\nChoose an option:");
            System.out.println("1. Add new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save products to a file");
            System.out.println("5. Load products from a file");
            System.out.println("6. Exit");
            System.out.println("===================================");
            System.out.print("Select an option: ");

            Scanner sc = new Scanner(System.in);
            int option;

            try {
                option = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input
                continue;      // Continue to the next iteration of the loop
            }

            switch (option) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    deleteAProduct();
                    break;
                case 3:
                    printTheListOfProduct();
                    break;
                case 4:
                    saveInFile();
                    break;
                case 5:
                    readBackAllInformation();
                    break;
                case 6:
                    System.out.println("You have Chosen to exit the program");
                    running = false;
                    break;
            }
        }
    }

    @Override
    public void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select (clothing or electronics)\n");
        System.out.print("Press C for clothes and E for electronics\n");
        System.out.print("Enter choice: ");

        int productLimit = 50;

        Random rand = new Random();

        char option = scanner.nextLine().charAt(0);

        if (option == 'c' || option == 'C') {
            System.out.print("\nYou have chosen Clothing\n");
            String productId = "C" + (rand.nextInt(9000) + 1000);
            System.out.println("Your product Id is -->" + productId);

            String productName = getValidProductName(scanner);
            int numberOfAvailableItems = getValidNumberOfItems(scanner);
            int price = getValidPrice(scanner);
            int size = getValidSize(scanner);
            String color = getValidColor(scanner);

            Clothing clothing = new Clothing(productId, productName, numberOfAvailableItems, price, size, color);
//            handleProductAddition(clothing);

            if (products.size() >= productLimit) {
                System.out.println("Product limit is " + productLimit);
                displayMenu();
            } 
            else {
                boolean isAdded = products.add(clothing);
                if (isAdded) {
                    System.out.println("Successfully added");
                } else {
                    System.out.println("Error, try again...");
                    addNewProduct();
                }
            }
        } 
        else if (option == 'e' || option == 'E') {
            System.out.println("\n You have chosen electronics\n");
            String productId = "E" + (rand.nextInt(9000) + 1000);
            System.out.println("Your product Id is -->" + productId);

            String productName = getValidProductName(scanner);
            int numberOfAvailableItems = getValidNumberOfItems(scanner);
            int price = getValidPrice(scanner);
            String brand = getValidBrand(scanner);
            int warrantyPeriod = getValidWarrantyPeriod(scanner);

            Electronics electronics = new Electronics(productId, productName, numberOfAvailableItems, price, brand, warrantyPeriod);
//            handleProductAddition(electronics);

            if (products.size() >= productLimit) {
                System.out.println("Product limit is " + productLimit);
                displayMenu();
            } else {
                boolean isAdded = products.add(electronics);
                if (isAdded) {
                    System.out.println("Successfully added");
                } 
                else {
                    System.out.println("Error, try again...");
                    addNewProduct();
                }
            }



        } else {
            System.out.println("Invalid option. Try again");
            addNewProduct();
        }
    }

    private String getValidProductName(Scanner scanner) {
        System.out.print("Enter product name: ");
        while (true) {
            String productName = scanner.next();
            if (isAlpha(productName)) {
                return productName;
            } 
            else {
                System.out.println("Invalid input. Please enter a valid product name.");
                System.out.print("Enter product name: ");
            }
        }
    }

    private int getValidNumberOfItems(Scanner scanner) {
        System.out.print("Enter number of available items: ");
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } 
            else {
                System.out.println("Invalid input. Please enter a valid number of available items.");
                System.out.print("Enter number of available items: ");
                scanner.next(); // Consume invalid input
            }
        }
    }

    private int getValidPrice(Scanner scanner) {
        System.out.print("Enter Price: ");
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } 
            else {
                System.out.println("Invalid input. Please enter a valid price.");
                System.out.print("Enter Price: ");
                scanner.next(); // Consume invalid input
            }
        }
    }

    private int getValidSize(Scanner scanner) {
        System.out.print("Enter size: ");
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } 
            else {
                System.out.println("Invalid input. Please enter a valid size.");
                System.out.print("Enter size: ");
                scanner.next(); // Consume invalid input
            }
        }
    }

    private String getValidColor(Scanner scanner) {
        System.out.print("Enter Color: ");
        while (true) {
            String color = scanner.next();
            if (isAlpha(color)) {
                return color;
            } 
            else {
                System.out.println("Invalid input. Please enter a valid color.");
                System.out.print("Enter Color: ");
            }
        }
    }

    private String getValidBrand(Scanner scanner) {
        System.out.print("Enter the brand: ");
        while (true) {
            String brand = scanner.next();
            if (isAlpha(brand)) {
                return brand;
            } 
            else {
                System.out.println("Invalid input. Please enter a valid brand.");
                System.out.print("Enter the brand: ");
            }
        }
    }

    private int getValidWarrantyPeriod(Scanner scanner) {
        System.out.print("Enter the warranty period example (In years like 1 or 2 years): ");
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid warranty period.");
                System.out.print("Enter the warranty period: ");
                scanner.next(); // Consume invalid input
            }
        }
    }

    private void handleProductAddition(Product product) {
        if (products.size() >= 50) {
            System.out.println("Products reached its limit.");
        } else if (products.add(product)) {
            System.out.println("Product successfully added.");
        } else {
            System.out.println("An error occurred. Try again.");
            addNewProduct();
        }
    }

    private boolean isAlpha(String str) {
        return str.chars().allMatch(Character::isLetter);
    }
    
    @Override
    public void deleteAProduct() {
        if (products.isEmpty()) {
            System.out.println("You didn't add any product to delete.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("List of all the product IDs --> ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getProductID());
        }

        System.out.print("Choose your id to delete: ");
        String delete = sc.next();
        boolean idFound = false;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(delete)) {
                products.remove(i);
                System.out.println("Product with ID " + delete + " removed successfully.");
                idFound = true;
                break; // Exit the loop once the product is found and removed.
            }
        }
        System.out.print("Remaining product IDs: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getProductID());
        }

        if (!idFound) {
            System.out.println("No product ID found: " + delete);
        }
    }

    @Override
    public void printTheListOfProduct() {

        for (Product product : products) {
            System.out.printf("\nProduct ID: %s%n", product.getProductID());
            System.out.println("===================================");
            System.out.printf("Product ID: %s%n", product.getProductID());
            System.out.printf("Product Name: %s%n", product.getProductName());
            System.out.printf("Number of Available Items: %d%n", product.getNumberOfAvailableItems());
            System.out.print("Product Price: " + product.getPrice());

            if (product instanceof Clothing) {
                Clothing clothing = (Clothing)product;
                System.out.println("\nType: Clothing");
                System.out.printf("Size: %s%n", clothing.getSize());
                System.out.printf("Color: %s%n", clothing.getColor());
            } 
            else if (product instanceof Electronics) {
                Electronics electronic = (Electronics)product;
                System.out.println("Type: Electronics");
                System.out.printf("Brand: %s%n", electronic.getBrand());
                System.out.printf("Warranty Period: %s years%n", electronic.getWarrantyPeriod());
            }
            System.out.println("===================================");
        }
    }


    @Override
    public void saveInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Products.txt"))) {
            for (int i = 0; i < products.size(); i++){
                Product product = products.get(i);
                System.out.println("\nProduct " + (i+1) + ":");
                System.out.println("============================================");
                writer.write("Product Id: " + product.getProductID());
                writer.newLine();
                writer.write("Product name: " + product.getProductName());
                writer.newLine();
                writer.write("Number of available items: " + product.getNumberOfAvailableItems());
                writer.newLine();
                writer.write("Product price: " + product.getPrice());
                writer.newLine();

                if (product instanceof Clothing){
                    Clothing clothing = (Clothing)product;
                    writer.write("Size: " + clothing.getSize());
                    writer.newLine();
                    writer.write("Color: "+ clothing.getColor());
                    writer.newLine();
                } 
                else if (product instanceof Electronics){
                    Electronics electronic = (Electronics)product;
                    writer.write("Brand: " + electronic.getBrand());
                    writer.newLine();
                    writer.write("Warranty period: " + electronic.getWarrantyPeriod());
                    writer.newLine();
                }

                writer.newLine(); // Add an empty line between products
            }
            System.out.println("============================================");
            System.out.println("Successfully saved to file");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readBackAllInformation() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Products.txt"))) {
            products = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line starts with "Product Id: ", if so, create a new product
                if (line.startsWith("Product Id: ")) {
                    String productId = line.substring("Product Id: ".length());
                    String productName = reader.readLine().substring("Product name: ".length());
                    int numberOfAvailableItems = Integer.parseInt(reader.readLine().substring("Number of available items: ".length()));
                    int price = Integer.parseInt(reader.readLine().substring("Product price: ".length()));

                    // Check if the next line is a clothing or electronics, if so, create a new product
                    String nextLine = reader.readLine();
                    if (nextLine.startsWith("Size: ")) {
                        int size = Integer.parseInt(nextLine.substring("Size: ".length()));
                        String color = reader.readLine().substring("Color: ".length());
                        Clothing clothing = new Clothing(productId, productName, numberOfAvailableItems, price, size, color);
                        products.add(clothing);
                    } 
                    else if (nextLine.startsWith("Brand: ")) {
                        String brand = nextLine.substring("Brand: ".length());
                        int warrantyPeriod = Integer.parseInt(reader.readLine().substring("Warranty period: ".length()));
                        Electronics electronics = new Electronics(productId, productName, numberOfAvailableItems, price, brand, warrantyPeriod);
                        products.add(electronics);
                    }
                }
            }
            System.out.println("File successfully loaded!");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String []arg){
        WestminsterShoppingManager c = new WestminsterShoppingManager();
        c.displayMenu();
    }
}
