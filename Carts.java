package OnlineCanteen;

import java.util.ArrayList;

public class Carts {
	private ArrayList<CartItems> cartItems;

	public Carts() {
		super();
		this.cartItems = new ArrayList<>();
	}
	
	public void addToCarts(MenuItems menuItems, int quantity) {
		cartItems.add(new CartItems(menuItems, quantity));
	}
	
	public void displayCart() {
	    if (cartItems.isEmpty()) {
	        System.out.println("Your cart is empty.");
	    } else {
	        System.out.println("Items in your cart:");
	        double total = 0;
	        for (CartItems item : cartItems) {
	            System.out.printf("%-25s x%-5d Rp.%-10.2f\n", 
	                item.getMenuitems().getName(), 
	                item.getQuantity(), 
	                item.getMenuitems().getPrice() * item.getQuantity());
	            total += item.getMenuitems().getPrice() * item.getQuantity();
	        }
	        System.out.printf("Total Price: Rp.%.2f\n", total);
	    }
	}
	public void updateCartItem(String itemName, int newQuantity) {
	    for (CartItems item : cartItems) {
	        if (item.getMenuitems().getName().equalsIgnoreCase(itemName)) {
	            if (newQuantity > 0) {
	                item.setQuantity(newQuantity);
	                System.out.println("Quantity updated successfully.");
	            } else {
	                System.out.println("Quantity must be greater than zero. Use delete if you want to remove the item.");
	            }
	            return;
	        }
	    }
	    System.out.println("Item not found in the cart.");
	}
	public void deleteCartItem(String itemName) {
	    for (int i = 0; i < cartItems.size(); i++) {
	        if (cartItems.get(i).getMenuitems().getName().equalsIgnoreCase(itemName)) {
	            cartItems.remove(i);
	            System.out.println("Item removed from the cart.");
	            return;
	        }
	    }
	    System.out.println("Item not found in the cart.");
	}
}
