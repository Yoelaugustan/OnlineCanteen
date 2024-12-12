package OnlineCanteen;

public class CartItems {
	private MenuItems menuitems;
	private int quantity;
	public CartItems(MenuItems menuitems, int quantity) {
		super();
		this.menuitems = menuitems;
		this.quantity = quantity;
	}
	public MenuItems getMenuitems() {
		return menuitems;
	}
	public int getQuantity() {
		return quantity;
	}
}
