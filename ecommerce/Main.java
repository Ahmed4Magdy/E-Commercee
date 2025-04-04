import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ExpirableProduct("Cheese", 100, 5, new Date(System.currentTimeMillis() + 86400000));
        Product biscuits = new ExpirableProduct("Biscuits", 150, 3, new Date(System.currentTimeMillis() + 86400000));
        Product tv = new ShippableProduct("TV", 500, 2, 5);
        Product scratchCard = new NonExpirableProduct("Mobile Scratch Card", 50, 10);

        Customer customer = new Customer("Ahmed Magdy", 1000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 2);

        CheckoutService.checkout(customer, cart);
    }
}