package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import dto.FinalizedSalesLog;
import dto.QuantifiedItemDTO;

public class Receipt {
	private String receiptText;
	public Receipt(FinalizedSalesLog finalSalesLog, String shopName, String address) {
		receiptText="";
		generateStartLine(shopName, address);
		generateReceipt(finalSalesLog);
	}
	public String getText() {
		return receiptText;
	}
	private void generateStartLine(String shopName, String address) {
		addText(shopName);
		addText(address);
		addText(generateDateAndTime());
	}

	private String generateDateAndTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
	}
	private void addText(String addedText) {
		receiptText+= addedText+ System.lineSeparator();
	}
	private void generateReceipt(FinalizedSalesLog finalSalesLog) {
		generateItemList(finalSalesLog);
		generateTotalCost(finalSalesLog);
		
	}
	/**
	 * @param finalSalesLog
	 */
	private void generateTotalCost(FinalizedSalesLog finalSalesLog) {
		addText(generatePlainTotal(finalSalesLog));
		addText(generateDiscount(finalSalesLog));
		addText(generateTotal(finalSalesLog));
		addText(generateVat(finalSalesLog));
	}
	/**
	 * @param finalSalesLog
	 * @return
	 */
	private String generateVat(FinalizedSalesLog finalSalesLog) {
		return "VAT" + addSpaces(22) + roundOf(finalSalesLog.getSalesLog().getVat());
	}
	/**
	 * @param finalSalesLog
	 * @return
	 */
	private String generateTotal(FinalizedSalesLog finalSalesLog) {
		return "total" + addSpaces(20) + finalSalesLog.getSalesLog().getCost();
	}
	/**
	 * @param finalSalesLog
	 * @return
	 */
	private String generateDiscount(FinalizedSalesLog finalSalesLog) {
		return "dicounts" + addSpaces(17) + finalSalesLog.getSalesLog().getDiscount();
	}
	/**
	 * @param finalSalesLog
	 * @return
	 */
	private String generatePlainTotal(FinalizedSalesLog finalSalesLog) {
		return "plain total" + addSpaces(14) + roundOf(finalSalesLog.getSalesLog().getCost() + finalSalesLog.getSalesLog().getDiscount());
	}
	private double roundOf(double val) {
		return Math.round(100*val)/100;
	}
	/**
	 * @param finalSalesLog
	 */
	private void generateItemList(FinalizedSalesLog finalSalesLog) {
		Iterator<QuantifiedItemDTO> itemIterator =finalSalesLog.getSalesLog().getItems().getIterator();
		while(itemIterator.hasNext()){
			QuantifiedItemDTO item  = itemIterator.next();
			generateItem(item);
			if(item.getQuantity() > 1) {
				generateQuantity(item);	
			}
		}
	}
	/**
	 * @param item
	 */
	private void generateQuantity(QuantifiedItemDTO item) {
		addText(item.getQuantity() + " st x " + item.getPrice());
	}
	/**
	 * @param item
	 */
	private void generateItem(QuantifiedItemDTO item) {
		addText(item.getDescription() + addSpaces(25 - item.getDescription().length()) +item.getPrice()*item.getQuantity());
	}
	private String addSpaces(int amountOfSpaces) {
		String spaces = "";
		while(amountOfSpaces > 0) {
			spaces+=' ';
			amountOfSpaces--;
		}
		return spaces;
	}
}
