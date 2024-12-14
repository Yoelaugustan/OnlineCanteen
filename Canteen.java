package OnlineCanteen;

import java.util.ArrayList;

public class Canteen {
	private ArrayList<Stalls> stalls;

	public Canteen() {
		super();
		this.stalls = new ArrayList<>();
	}
	
	public void addFoodStall(Stalls stall) {
		stalls.add(stall);
	}
	
	public void displayStalls() {
		int i = 1;
		System.out.println();
		for(Stalls stall : stalls) {
			System.out.printf("%d. %s\n", i, stall.getStallName());
			i++;
		}
	}
	
	public Stalls getStallByName(String name) {
		for(Stalls stall : stalls) {
			if(stall.getStallName().equalsIgnoreCase(name)) {
				return stall;
			}
		}
		return null;
	}
}
