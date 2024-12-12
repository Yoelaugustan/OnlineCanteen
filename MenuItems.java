package OnlineCanteen;

public class MenuItems {
	private String name;
	private double price;
	private double rating;
	public MenuItems(String name, double price, double rating) {
		super();
		this.name = name;
		this.price = price;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public double getRating() {
		return rating;
	}
}
