package OnlineCanteen;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Canteen canteen = new Canteen();
		Customers customer = new Customers("Pak Hendra");
		
		Carts carts = new Carts();
		
		Stalls Nara = new Stalls("Nara");
		Nara.addMenuItems(new MenuItems("Nasi Goreng", 15000, 5));
		Nara.addMenuItems(new MenuItems("Nasi Ayam Geprek", 20000, 4.5));
		Nara.addMenuItems(new MenuItems("Nasi Katsu", 25000, 4.7));
		Nara.addMenuItems(new MenuItems("Nasi FuYungHai", 18000, 4.9));
		Nara.addMenuItems(new MenuItems("Nasi Ayam Shihlin", 17000, 4.3));
		
		Stalls MKubik = new Stalls("MKubik");
		MKubik.addMenuItems(new MenuItems("Soto Betawi Daging", 20000, 4.5));
		MKubik.addMenuItems(new MenuItems("Soto Betawi Ayam", 17000, 4));
		MKubik.addMenuItems(new MenuItems("Soto Ayam", 15000, 4.3));
		MKubik.addMenuItems(new MenuItems("Tongseng", 25000, 4.8));
		MKubik.addMenuItems(new MenuItems("Gultik", 20000, 5));
		
		Stalls Koffielogik = new Stalls("Koffielogik");
		Koffielogik.addMenuItems(new MenuItems("Es Teh Tawar", 5000, 5));
		Koffielogik.addMenuItems(new MenuItems("Es Teh Manis", 5000, 4));
		Koffielogik.addMenuItems(new MenuItems("Es Kopi Logik", 12000, 4.5));
		Koffielogik.addMenuItems(new MenuItems("Es Cappucino", 17000, 4.8));
		Koffielogik.addMenuItems(new MenuItems("Es Coklat", 15000, 4.7));
		
		canteen.addFoodStall(Nara);
		canteen.addFoodStall(MKubik);
		canteen.addFoodStall(Koffielogik);
	
		while(true) {
			showMenu(canteen, customer, carts);
		}
	}
	
	public static void showMenu(Canteen canteen, Customers customer, Carts carts) {
		System.out.printf("\nHello %s Welcome to Canteen!\n", customer.getCustomerName());
		System.out.println("1. Purchase Food");
		System.out.println("2. CheckOut");
		System.out.println("3. View Transaction History");
		System.out.printf("Choice[1-3]: ");
		
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt(); 
		scan.nextLine(); //consume new line
		
		switch (choice) {
		 	case 1:
		 		System.out.println();
		 		canteen.displayStalls();
		 		System.out.println("Please choose a stall you want to visit");
		 		System.out.printf("Choice[Stall_Name]: ");
		 		String stallName = scan.nextLine();
                Stalls selectedStall = canteen.getStallByName(stallName);
                
                if(selectedStall != null) {
                	System.out.printf("\nDisplay %s Menu Items Options:\n", selectedStall.getStallName());
                	System.out.println("1. Sort By Price");
                	System.out.println("2. Sort By Rating");
                	System.out.println("3. Normal");
                	System.out.println("4. Search Menu Items");
                	System.out.printf("Choice[1-4]: ");
                	
                	int displayChoice = scan.nextInt();
                	scan.nextLine();//consume new line
                	
                	switch(displayChoice) {
                		case 1:
                			System.out.println();
                			selectedStall.displayMenuSortedByPrice();
                			addMenuItemToCart(selectedStall, carts);
                			break;
                		case 2:
                			System.out.println();
                			selectedStall.displayMenuSortedByRating();
                			addMenuItemToCart(selectedStall, carts);
                			break;
                		case 3:
                			System.out.println();
                			selectedStall.displayMenuItems();
                			addMenuItemToCart(selectedStall, carts);
                			break;
                		case 4:
                			System.out.printf("\nEnter menu item name: ");
                			String inputName = scan.nextLine();
                			MenuItems item = selectedStall.searchMenuItems(inputName);
                			if(item != null) {
                				System.out.printf("%-25s Rp.%-15.2f Rating: %-10.1f\n", item.getName(), item.getPrice(), item.getRating());
                				System.out.print("Enter Quantity: ");
                	            int quantity = scan.nextInt();
                	            scan.nextLine(); // Consume newline
                	            carts.addToCarts(item, quantity);
                	            System.out.println("Item added to cart.");
                			}
                			else {
                				System.out.println("Menu Item Not Found!");
                			}
                			break;
                	}
                	
                }
                else {
                	System.out.println("Stall Not Found!");
                }
                break;
		 	case 2:
		 		// Display Cart
		 	case 3:
		 		// Transaction History
		 	default:
		 		System.out.println("Please choose between 1 and 2");
		}
	}
	
	public static void addMenuItemToCart(Stalls stall, Carts carts) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Menu Item Name: ");
        String itemName = scan.nextLine();
        MenuItems item = stall.searchMenuItems(itemName);
        if (item != null) {
            System.out.print("Enter Quantity: ");
            int quantity = scan.nextInt();
            scan.nextLine(); // Consume newline
            carts.addToCarts(item, quantity);
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Menu item not found.");
        }
	}
}
