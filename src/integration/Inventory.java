package integration;

import java.util.HashMap;
import dto.ItemDTO;
import exceptions.DatabaseFailureException;
import exceptions.ExceptionLogger;
import exceptions.FailedSearchException;

public class Inventory {
	private HashMap<Integer, ItemDTO> database;
	/**
	 * initializes inventory
	 */
	private Inventory() {
		database = new HashMap<Integer, ItemDTO>();
		init();
	}
	/**
	 * holder of static <Code>Inventory</Code>
	 *
	 */
	private static class InventoryHolder{
		private static Inventory inventory = new Inventory();
	}
	/**
	 * @return singleton instance of inventory
	 */
	public static Inventory getInventory() {
		return InventoryHolder.inventory;
	}
	/**
	 * returns matching itemDTO to matching itemID
	 * 
	 * @param itemID
	 *            integer representing the scanned code of the item
	 * @return ItemDTO DTO of the item matching the itemID
	 * @throws FailedSearchException
	 *             thrown when ItemID has no matching ItemDTO
	 * @throws DatabaseFailureException
	 *             thrown when database can't handle the itemID
	 */
	public ItemDTO findItem(int itemID) throws FailedSearchException, DatabaseFailureException {
		if (itemID == 6) {
			DatabaseFailureException e = new DatabaseFailureException("database failure in inventory");
			ExceptionLogger.getExceptionLogger().writeToLog("integration/Inventory", e);
			throw e;
		}
		ItemDTO item = database.get(itemID);
		if (item == null) {
			FailedSearchException e = new FailedSearchException("no such itemID exists in the database");
			throw e;
		}
		return item;
	}

	/**
	 * generates an existing database used to simulate the program
	 */
	private void init() {
		ItemDTO[] items = new ItemDTO[6];
		items[0] = new ItemDTO("hotdogs", 25, 12);
		items[1] = new ItemDTO("milk", 11, 12);
		items[2] = new ItemDTO("newspaper", 10, 6);
		items[3] = new ItemDTO("ciggaretes", 60, 25);
		items[4] = new ItemDTO("potatoes", 0.01, 12);
		items[5] = new ItemDTO("carrots", 0.02, 12);
		for (int i = 0; i < 6; i++) {
			database.put(i, items[i]);
		}
	}
}
