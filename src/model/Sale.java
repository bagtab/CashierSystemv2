package model;

import java.util.LinkedList;

import dto.ItemListDTO;
import dto.QuantifiedItemDTO;

/**
 * tracks the current items and their quantity of the active sale.
 * 
 * @author Linus Johannisson
 *
 */
public class Sale {
	private LinkedList<QuantifiedItem> items;

	/**
	 * initializes a new sale
	 */
	public Sale() {
		items = new LinkedList<QuantifiedItem>();
	}

	/**
	 * adds a new QuantifiedItem to the list.
	 * 
	 * @param item
	 *            QuantifiedItemDTO to add to the list
	 */
	public void addItem(QuantifiedItemDTO item) {
		// TODO Auto-generated method stub
		addOrIncreaseQuantity(new QuantifiedItem(item));
	}

	/**
	 * either adds item as a new item or increases the quantity of the item
	 * 
	 * @param item
	 *            QuantifiedItem to add to the list
	 */
	private void addOrIncreaseQuantity(QuantifiedItem item) {
		if (items.contains(item)) {
			increaseQuantity(item);
		} else {
			addNewItem(item);
		}
	}

	/**
	 * increases the quantity of the item in the itemlist
	 * 
	 * @param item
	 *            QuantifiedItem to increase the quantity with
	 */
	private void increaseQuantity(QuantifiedItem item) {
		QuantifiedItem itemInList = items.get(items.indexOf(item));
		itemInList.increaseQuantity(item);
	}

	/**
	 * adds the item to the itemlist
	 * 
	 * @param item
	 *            QuantifiedItem to add to the itemlist
	 */
	private void addNewItem(QuantifiedItem item) {
		items.add(item);
	}

	/**
	 * returns a DTO of itemList
	 * 
	 * @return ItemListDTO of the current itemList
	 */
	public ItemListDTO getItems() {
		return generateListOfDTO();
	}

	/**
	 * creates and returns a DTO of the itemList
	 * 
	 * @return ItemListDTO of the current itemList
	 */
	private ItemListDTO generateListOfDTO() {
		LinkedList<QuantifiedItemDTO> itemsDTO = new LinkedList<QuantifiedItemDTO>();
		for (QuantifiedItem itemToConvert : items) {
			itemsDTO.add(new QuantifiedItemDTO(itemToConvert.getItemData(), itemToConvert.getQuantity()));

		}
		return new ItemListDTO(itemsDTO);
	}
}
