package model;

import dto.FinalizedSalesLog;
import dto.Payment;
import integration.Accounting;
import integration.StoreAndDate;

/**
 * Register finalizes the sale and stores the amount of money in the register.
 * 
 * @author mrjoh
 *
 */
public class Register {
	private Accounting accounting;
	private String shopName;
	private String address;
	private double presentMoney;
	public Register() {
		presentMoney = 0;
		accounting = Accounting.getAccounting();
		StoreAndDate store= StoreAndDate.getStoreAndDate();
		address = store.getAddress();
		shopName = store.getName();
	}
	/**
	 * logs relevant data, adds money to register and returns receipt
	 * 
	 * @param finalSalesLog
	 * @return
	 */
	public Receipt endSale(FinalizedSalesLog finalSalesLog) {
		addMoneyToRegistry(finalSalesLog.getPayment());
		Receipt receipt = new Receipt(finalSalesLog, shopName, address);
		accounting.recordSalesLog(finalSalesLog);
		return receipt;
	}

	private void addMoneyToRegistry(Payment payment) {
		presentMoney += payment.getAmount();
	}

	/**
	 * returns the money in the register
	 * 
	 * @return presentMoney
	 */
	public double getPresentMoney() {
		return presentMoney;
	}
}
