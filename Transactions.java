import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions {
	private Carts carts;
	private LocalDateTime timestamp;

	public Transactions(Carts carts) {
		super();
		this.carts = carts;
		this.timestamp = LocalDateTime.now();
	}
	
	public void displayTransactionDetails() {
		System.out.println(getFormattedTimestamp());
		carts.displayCart();
	}
	
	private String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timestamp.format(formatter);
    }
}
