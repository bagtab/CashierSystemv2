package integration;

import java.util.LinkedList;

import dto.FinalizedSalesLog;

public class Accounting {
	LinkedList<FinalizedSalesLog> recordedSalesLogs;

	private Accounting() {
		recordedSalesLogs = new LinkedList<FinalizedSalesLog>();
	}
	private static class AccountingHolder{
		private static Accounting accounting = new Accounting();
	}
	public static Accounting getAccounting() {
		return AccountingHolder.accounting;
	}
	/**
	 * logs the final saleslog
	 * @param finalSalesLog log that describes the sale
	 */
	public void recordSalesLog(FinalizedSalesLog finalSalesLog) {
		recordedSalesLogs.add(finalSalesLog);
	}
}
