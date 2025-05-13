package CSE123;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<Product> cartItems = new ArrayList<>();

    public void addProduct(Product product) {
        if (product.getStock() > 0) {
            cartItems.add(product);
            product.reduceStock();
            System.out.println(product.getName() + " added to cart.");
        } else {
            System.out.println("Out of stock.");
        }
    }

    public void removeProduct(String productName) {
        boolean found = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equalsIgnoreCase(productName)) {
                cartItems.get(i).increaseStock();
                cartItems.remove(i);
                System.out.println(productName + " removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found in cart.");
        }
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Items in Cart:");
        double total = 0;
        for (Product p : cartItems) {
            System.out.printf("- %s | $%.2f\n", p.getName(), p.getPrice());
            total += p.getPrice();
        }
        System.out.printf("Total: $%.2f\n", total);
    }

    public void checkout(Scanner sc) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }

        displayCart();
        System.out.print("Proceed to payment? (yes/no): ");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Checkout cancelled.");
            return;
        }

        // Payment options
        System.out.println("Select a payment method:");
        System.out.println("1. bKash");
        System.out.println("2. Nagad");
        System.out.println("3. Card");
        System.out.println("4. MasterCard");
        System.out.print("Enter your choice: ");
        int paymentOption = sc.nextInt();
        sc.nextLine(); // clear buffer

        String paymentMethod = "";
        switch (paymentOption) {
            case 1: paymentMethod = "bKash"; break;
            case 2: paymentMethod = "Nagad"; break;
            case 3: paymentMethod = "Card"; break;
            case 4: paymentMethod = "MasterCard"; break;
            default:
                System.out.println("Invalid payment method. Checkout cancelled.");
                return;
        }

        System.out.println("Processing payment via " + paymentMethod + "...");
        System.out.println("Payment successful! Thank you for your purchase.");
        cartItems.clear();
    }
}
