package OnlineCanteen;

import java.util.ArrayList;

public class Customers {
	private String name;
	private ArrayList<Transactions> transactions;
	public Customers(String name) {
		super();
		this.name = name;
		this.transactions = new ArrayList<>();
	}

	public String getCustomerName() {
		return name;
	}
	
}
