package CSE123;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Are you an Admin or User? (1 = Admin, 2 = User)");
        int role = sc.nextInt();
        sc.nextLine();

        if (role == 1) {
            if (!adminLogin(sc)) {
                System.out.println("Invalid login. Exiting...");
                return;
            }
            System.out.println("Welcome Admin! You have access to product management (not implemented).");
            return;
        }

        List<User> registeredUsers = new ArrayList<>();

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option (1 or 2): ");
        int loginChoice = sc.nextInt();
        sc.nextLine();

        String userName = "";
        boolean isLoggedIn = false;

        if (loginChoice == 1) {
            System.out.print("Enter a username (exactly 6 characters): ");
            userName = sc.nextLine();
            if (userName.length() != 6) {
                System.out.println("Username must be exactly 6 characters. Exiting...");
                return;
            }
            System.out.print("Enter a password: ");
            String password = sc.nextLine();
            registeredUsers.add(new User(userName, password));
            System.out.println("Registration successful! Please login now.");
        }

        System.out.print("Enter your username to login: ");
        String loginName = sc.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = sc.nextLine();

        for (User u : registeredUsers) {
            if (u.username.equals(loginName) && u.password.equals(loginPassword)) {
                userName = u.username;
                isLoggedIn = true;
                break;
            }
        }

        if (!isLoggedIn) {
            System.out.println("Login failed. Invalid username or password.");
            return;
        }

        System.out.println("Welcome, " + userName + "! Enjoy shopping with us!");

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", "Dell", "15-inch screen, i5 CPU", 800, 5));
        products.add(new Product("Phone", "Electronics", "Samsung", "Galaxy A52, 128GB", 500, 3));
        products.add(new Product("Headphones", "Accessories", "Sony", "Noise Cancelling", 100, 10));
        products.add(new Product("Keyboard", "Accessories", "Logitech", "Wireless, mechanical", 50, 8));
        products.add(new Product("Mouse", "Accessories", "HP", "Wireless optical mouse", 30, 15));
        products.add(new Product("Monitor", "Electronics", "LG", "24-inch FHD", 200, 4));
        products.add(new Product("Tablet", "Electronics", "Lenovo", "10-inch Android tablet", 300, 6));
        products.add(new Product("Charger", "Accessories", "Anker", "Fast Charging", 25, 20));
        products.add(new Product("Smartwatch", "Electronics", "Fitbit", "Fitness tracker", 150, 5));
        products.add(new Product("USB Cable", "Accessories", "Baseus", "1m Type-C", 10, 25));

        Cart cart = new Cart();

        int choice;
        do {
            System.out.println("\n======= MENU =======");
            System.out.println("1. View Products by Category");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Select Category:");
                    System.out.println("1. Electronics");
                    System.out.println("2. Accessories");
                    int catChoice = sc.nextInt();
                    sc.nextLine();
                    String cat = (catChoice == 1) ? "Electronics" : "Accessories";
                    System.out.println("\n" + cat + " Products:");
                    int count = 0;
                    for (int i = 0; i < products.size(); i++) {
                        Product p = products.get(i);
                        if (p.getCategory().equalsIgnoreCase(cat)) {
                            System.out.printf("%d. %s | Brand: %s | $%.2f | Stock: %d\n    Description: %s\n",
                                    i + 1, p.getName(), p.getBrand(), p.getPrice(), p.getStock(), p.getDescription());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("No products found in this category.");
                    }
                    break;

                case 2:
                    System.out.print("Enter product number to add: ");
                    int addIndex = sc.nextInt();
                    if (addIndex >= 1 && addIndex <= products.size()) {
                        cart.addProduct(products.get(addIndex - 1));
                    } else {
                        System.out.println("Invalid product number.");
                    }
                    break;

                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = sc.nextLine();
                    cart.removeProduct(removeName);
                    break;

                case 4:
                    cart.displayCart();
                    break;

                case 5:
                    cart.checkout(sc);
                    break;

                case 6:
                    System.out.println("Thank you for shopping, " + userName + ". Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close();
    }

    private static boolean adminLogin(Scanner sc) {
        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        return username.equals("Moinul") && password.equals("Moinul123");
    }
}
