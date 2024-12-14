import java.util.ArrayList;
import java.util.Comparator;

public class Stalls {
	private String name;
	private ArrayList<MenuItems> menuItems;
	public Stalls(String name) {
		super();
		this.name = name;
		this.menuItems = new ArrayList<>();
	}
	
	public void addMenuItems(MenuItems menuItem) {
		menuItems.add(menuItem);
	}
	
	public String getStallName() {
		return name;
	}
	
	public void displayMenuItems() {
		int i = 1;
		for (MenuItems menuItem : menuItems) {
			System.out.printf("%-1d. %-25s Rp.%-15.2f Rating: %-10.1f\n", i, menuItem.getName(), menuItem.getPrice(), menuItem.getRating());
			i++;
		}
	}
	
	public void displayMenuSortedByPrice() {
        menuItems.sort(Comparator.comparingDouble(MenuItems::getPrice));
		System.out.println();
        System.out.println("Menu sorted by Price for " + name + ":");
        displayMenuItems();
    }
	
	public void displayMenuSortedByRating() {
        menuItems.sort(Comparator.comparingDouble(MenuItems::getRating).reversed());
		System.out.println();
        System.out.println("Menu sorted by Price for " + name + ":");
        displayMenuItems();
    }
	
	public MenuItems searchMenuItems(String name) {
		for(MenuItems item : menuItems) {
			if(item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}
}
