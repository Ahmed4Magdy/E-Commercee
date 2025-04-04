import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty!");
        }

        double subtotal = 0;
        double shippingFee = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double totalPrice = product.getPrice() * quantity;
            subtotal += totalPrice;
            System.out.println(quantity + "x " + product.getName() + " " + totalPrice);

            if (product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add((Shippable) product);
                }
                shippingFee += ((Shippable) product).getWeight() * 10;
            }

            product.reduceQuantity(quantity);
        }

        double total = subtotal + shippingFee;
        if (customer.getBalance() < total) {
            throw new IllegalArgumentException("Insufficient balance!");
        }

        customer.deductBalance(total);
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + total);
        System.out.println("Customer balance after payment: " + customer.getBalance());

        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        cart.clear();
    }
}