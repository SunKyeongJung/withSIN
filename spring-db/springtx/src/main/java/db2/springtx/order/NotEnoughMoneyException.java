package db2.springtx.order;

public class NotEnoughMoneyException extends Exception {

	public NotEnoughMoneyException(String message) {
		super(message);
	}
}
