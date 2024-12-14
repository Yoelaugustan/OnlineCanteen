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
		Scanner scan = new Scanner(System.in);
		
		System.out.printf("\nHello %s Welcome to Canteen!\n", customer.getCustomerName());
		System.out.println("1. Purchase Food");
		System.out.println("2. CheckOut");
		System.out.println("3. Update Item in Cart");
		System.out.println("4. Delete Item from Cart");
		System.out.println("5. Transaction History");
		System.out.println("6. Exit");
		
		int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.printf("Choice [1-6]: ");
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                if (choice >= 1 && choice <= 6) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please choose between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scan.next(); // Consume invalid input
            }
        }
        scan.nextLine(); // Consume newline
		
		switch (choice) {
		 	case 1:
		 		purchaseFood(canteen, carts);
                break;
		 	case 2:
		 		checkout(carts, customer);
		 	    break;
		 	case 3:
	            updateCartItem(carts);
	            break;

	        case 4:
	            deleteCartItem(carts);
	            break;
	        case 5:
	    		viewTransactionHistory(customer);
	        	break;
	        case 6:
	        	System.out.println("Thank you for visiting! Goodbye.");
	        	System.exit(0);
		 	default:
		 		System.out.println("Unexpected error occurred.");
		}
	}
	
	public static void purchaseFood(Canteen canteen, Carts carts) {
        Scanner scan = new Scanner(System.in);

        canteen.displayStalls();
        System.out.println("Please choose a stall you want to visit");
        System.out.printf("Enter stall name: ");

        Stalls selectedStall = null;
        while (selectedStall == null) {
            String stallName = scan.nextLine();
            selectedStall = canteen.getStallByName(stallName);
            if (selectedStall == null) {
                System.out.println("Stall not found. Please try again.");
                System.out.printf("Enter stall name: ");
            }
        }

        System.out.printf("\nDisplay %s Menu Items Options:\n", selectedStall.getStallName());
        System.out.println("1. Sort By Price");
        System.out.println("2. Sort By Rating");
        System.out.println("3. Normal");
        System.out.println("4. Search Menu Items");

        int displayChoice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.printf("Choice [1-4]: ");
            if (scan.hasNextInt()) {
                displayChoice = scan.nextInt();
                if (displayChoice >= 1 && displayChoice <= 4) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please choose between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scan.next(); // Consume invalid input
            }
        }
        scan.nextLine(); // Consume newline

        switch (displayChoice) {
            case 1:
                selectedStall.displayMenuSortedByPrice();
                break;
            case 2:
                selectedStall.displayMenuSortedByRating();
                break;
            case 3:
                selectedStall.displayMenuItems();
                break;
            case 4:
                searchAndAddMenuItemToCart(selectedStall, carts);
                return;
        }
        addMenuItemToCart(selectedStall, carts);
    }
	
	public static void addMenuItemToCart(Stalls stall, Carts carts) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Menu Item Name: ");

        MenuItems item = null;
        while (item == null) {
        	String itemName = scan.nextLine();
            item = stall.searchMenuItems(itemName);
            if (item == null) {
                System.out.println("Menu Item not found. Please try again.");
                System.out.printf("Enter Menu Item name: ");
            }
        }
        
        System.out.print("Enter Quantity: ");
        int quantity = scan.nextInt();
        scan.nextLine(); // Consume newline
        carts.addToCarts(item, quantity);
        System.out.println("Item added to cart.\n");
        carts.displayCart();
	}
	
	public static void searchAndAddMenuItemToCart(Stalls stall, Carts carts) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("\nEnter Menu Item name: ");
        MenuItems item = null;
        while (item == null) {
            String inputName = scan.nextLine();
            item = stall.searchMenuItems(inputName);
            if (item == null) {
                System.out.println("Menu Item not found. Please try again.");
                System.out.printf("Enter Menu Item name: ");
            }
        }

        System.out.printf("%-25s Rp.%-15.2f Rating: %-10.1f\n", item.getName(), item.getPrice(), item.getRating());
        addMenuItemToCart(stall, carts);
    }
	
	public static void checkout(Carts carts, Customers customer) {
		
		if (carts.getCartItems().isEmpty()) {
            System.out.println("\nUnable To Checkout, Cart Is Empty!");
            return;
        }
		
        System.out.println("\nHere's your current cart:");
        carts.displayCart();
        System.out.println("Checkout Successful!!");

     // Create Temporary Cart
        Carts cartTemp = new Carts();
        for (CartItems item : carts.getCartItems()) {
            cartTemp.addToCarts(item.getMenuitems(), item.getQuantity());
        }

        Transactions transaction = new Transactions(cartTemp);
        customer.addTransactions(transaction);
        carts.clearCart();
    }
	
	public static void updateCartItem(Carts carts) {
        Scanner scan = new Scanner(System.in);
        
        if (carts.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to update.");
            return;
        }
        
        System.out.println("\nUpdate Item in Cart:");
        carts.displayCart();
        System.out.print("Enter the name of the item to update: ");
        
        CartItems cartItem = null;
        String itemName = null;
        while (cartItem == null) {
        	itemName = scan.nextLine();
            cartItem = carts.getCartItemByName(itemName);
            if (cartItem == null) {
                System.out.println("Menu Item not found. Please try again.");
                System.out.printf("Enter the name of the item to update: ");
            }
        }

        int newQuantity = 0;
        boolean validQuantity = false;

        while (!validQuantity) {
            System.out.print("Enter the new quantity: ");
            if (scan.hasNextInt()) {
                newQuantity = scan.nextInt();
                if (newQuantity > 0) {
                    validQuantity = true;
                } else {
                    System.out.println("Quantity must be greater than zero.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive number.");
                scan.next(); // Consume invalid input
            }
        }

        carts.updateCartItem(itemName, newQuantity);
        System.out.println();
        carts.displayCart();
    }
	
	public static void deleteCartItem(Carts carts) {
        Scanner scan = new Scanner(System.in);
        
        if (carts.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to delete.");
            return;
        }
        
        System.out.println("\nDelete Item from Cart:");
        carts.displayCart();
        System.out.print("Enter the name of the item to delete: ");
        
        CartItems cartItem = null;
        String itemName = null;
        while (cartItem == null) {
        	itemName = scan.nextLine();
            cartItem = carts.getCartItemByName(itemName);
            if (cartItem == null) {
                System.out.println("Menu Item not found. Please try again.");
                System.out.printf("Enter the name of the item to delete: ");
            }
        }
        carts.deleteCartItem(itemName);
        System.out.println();
        carts.displayCart();
    }
	
	public static void viewTransactionHistory(Customers customer) {
        System.out.println("Your Transaction History:");
        customer.viewTransactionHistory();
    }
	
}
