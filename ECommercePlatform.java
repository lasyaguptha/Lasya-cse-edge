import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int productId;
    private String productName;
    private double productPrice;

    public Product(int productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ShoppingCart {
    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItemToCart(CartItem item) {
        cartItems.add(item);
    }

    public void removeItemFromCart(CartItem item) {
        cartItems.remove(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getProductPrice() * item.getQuantity();
        }
        return total;
    }
}

public class ECommercePlatform {
    private static List<Product> productList = new ArrayList<>();
    private static ShoppingCart shoppingCart = new ShoppingCart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeProducts();

        while (true) {
            System.out.println("1. View Available Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            
            switch (option) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    removeFromCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void initializeProducts() {
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        // Add more products here
    }

    private static void browseProducts() {
        System.out.println("Available Products:");
        for (Product product : productList) {
            System.out.println(product.getProductId() + ". " + product.getProductName() + " - $" + product.getProductPrice());
        }
    }

    private static void addToCart() {
        System.out.print("Enter the ID of the product to add to your cart: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        Product selectedProduct = null;
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct != null) {
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            shoppingCart.addItemToCart(new CartItem(selectedProduct, quantity));
            System.out.println("The product has been added to your cart!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void viewCart() {
        List<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (CartItem item : cartItems) {
                System.out.println(item.getProduct().getProductName() + " - Quantity: " + item.getQuantity());
            }
            System.out.println("Total Price of your Cart: $" + shoppingCart.calculateTotalPrice());
        }
    }

    private static void removeFromCart() {
        List<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to remove.");
        } else {
            System.out.print("Enter the ID of the product to remove from your cart: ");
            int productId = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            Product productToRemove = null;
            for (CartItem item : cartItems) {
                if (item.getProduct().getProductId() == productId) {
                    productToRemove = item.getProduct();
                    shoppingCart.removeItemFromCart(item);
                    break;
                }
            }
            if (productToRemove != null) {
                System.out.println(productToRemove.getProductName() + " removed from your cart.");
            } else {
                System.out.println("Product not found in your cart.");
            }
        }
    }

    private static void checkout() {
        System.out.println("Checkout - Total Price: $" + shoppingCart.calculateTotalPrice());
        System.out.println("Thank you for shopping with us!");
        // Logic for completing the checkout process (e.g., payment, order creation, etc.) would go here
        // This is a simplified version without payment integration or order tracking
        shoppingCart = new ShoppingCart(); // Clear the cart after checkout
    }
}
