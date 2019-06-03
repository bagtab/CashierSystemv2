package controller;

import java.util.LinkedList;

import dto.FinalizedSalesLog;
import dto.ItemDTO;
import dto.Payment;
import dto.QuantifiedItemDTO;
import dto.SaleDTO;
import dto.UpdateDTO;
import exceptions.DatabaseFailureException;
import exceptions.FailedSearchException;
import integration.Inventory;
import model.Cost;
import model.Register;
import model.Sale;
import view.Observer;

/**
 * Controller is used for communication between the user interface and the
 * model.
 * 
 * @author mrjoh
 *
 */
public class Controller {
	private Inventory itemRegistry;
	private Register register;
	private Sale sale;
	private int quantity;
	private Cost cost;
	private LinkedList<Observer> updateForAddedItems;
	private LinkedList<Observer> updateAtSalesEnd;

	/**
	 * initializes controller with a new inventory and register.
	 */
	private Controller() {
		itemRegistry = Inventory.getInventory();
		register = new Register();
		quantity = 1;
		cost = new Cost();
		updateForAddedItems = new LinkedList<Observer>();
		updateAtSalesEnd = new LinkedList<Observer>();
	}

	private static class ControllerHolder {
		private static Controller instance = new Controller();
	}

	/**
	 * returns the singleton instance of controller
	 * 
	 * @return controller
	 */
	public static Controller getController() {
		return ControllerHolder.instance;
	}

	/**
	 * Initializes a new sale
	 */
	public void startNewSale() {
		sale = new Sale();
	}

	/**
	 * prepares to add multiple items
	 * 
	 * @param quantity
	 *            amount of scanned items
	 */
	public void scanManyItems(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param itemID
	 *            integer representation of an itemID
	 * @return UpdateDTO consisting of last added item and current price
	 * @throws DatabaseFailureException
	 *             thrown when ItemID has no matching ItemDTO in the inventory
	 * @throws FailedSearchException
	 *             thrown when database can't handle the itemID in the inventory
	 */
	public void scanItem(int itemID) throws DatabaseFailureException, FailedSearchException {
		QuantifiedItemDTO item = null;
		item = generateQuantifiedItem(itemID);
		sale.addItem(item);
		for (Observer observer : updateForAddedItems) {
			observer.update(cost.addCost(item));
		}
	}

	/**
	 * pays for the current sale and returns the change
	 * 
	 * @param payment
	 *            money that customer pays for sale
	 * @return Change to give to customer in a PaymentObject
	 */
	public void payAndEndSale(double payment) {
		Payment inPayment = new Payment(payment);
		FinalizedSalesLog finalSalesLog = new FinalizedSalesLog(inPayment, getSalesDTO());
		UpdateDTO update = new UpdateDTO(null, getCost(), inPayment.getAmount() - getCost(),
				register.endSale(finalSalesLog).getText());
		for (Observer observer : updateAtSalesEnd) {
			observer.update(update);
		}
		cost.reset();
	}

	/**
	 * creates and returns a salesDTO
	 * 
	 * @return SaleDTO
	 */
	private SaleDTO getSalesDTO() {
		return cost.getSalesDTO(sale.getItems());
	}

	/**
	 * generates and returns a QuantifiedItemDTO based upon itemID and saved
	 * quantity
	 * 
	 * @param itemID
	 *            integer identification for a specific item
	 * @return QuantifiedItem with quantity of item and ItemDTO
	 * @throws DatabaseFailureException
	 *             thrown when ItemID has no matching ItemDTO in the inventory
	 * @throws FailedSearchException
	 *             thrown when database can't handle the itemID in the inventory
	 */
	private QuantifiedItemDTO generateQuantifiedItem(int itemID)
			throws FailedSearchException, DatabaseFailureException {
		ItemDTO itemData = itemRegistry.findItem(itemID);
		QuantifiedItemDTO item = new QuantifiedItemDTO(itemData, quantity);
		quantity = 1;
		return item;
	}

	/**
	 * applies the discount and returns new cost
	 * 
	 * @param customerID
	 * @return cost after discounts
	 */
	public void applyDiscount(String customerID) {
		cost.applyDiscount(sale.getItems(), customerID);
		UpdateDTO update = new UpdateDTO(null, cost.getCost(), 0, null);
		for (Observer observer : updateForAddedItems) {
			observer.update(update);
		}
	}

	/**
	 * returns the current cost
	 * 
	 * @return cost of current sale
	 */
	private double getCost() {
		return cost.getCost();
	}

	public void addObserverForScans(Observer observer) {
		updateForAddedItems.add(observer);
	}

	public void addObserverForSalesEnd(Observer observer) {
		updateAtSalesEnd.add(observer);
	}
}
