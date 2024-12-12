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
}
