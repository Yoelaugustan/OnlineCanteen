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
	
	public void addTransactions(Transactions transaction) {
		transactions.add(transaction);
	}
	
	public void viewTransactionHistory() {
		if(transactions.isEmpty()) {
			System.out.println("No Trasaction Has Been Made");
			return;
		}
		
		System.out.println("Transaction history for " + name + ":");
		for(Transactions transaction : transactions) {
			transaction.displayTransactionDetails();
			System.out.println();
		}
	}
	
}
