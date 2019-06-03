package model;

import dto.Discount;
import dto.ItemListDTO;
import dto.QuantifiedItemDTO;
import dto.SaleDTO;
import dto.UpdateDTO;
import integration.DiscountHandler;

public class Cost {
	private double cost6;
	private double cost12;
	private double cost25;
	private double discount;
	private DiscountHandler discounter;

	/**
	 * increases the cost and vat with values from an added item
	 * 
	 * @param item
	 *            - DTO that stores the quantity,cost and vat used
	 */
	public Cost() {
		cost6 = 0;
		cost12 = 0;
		cost25 = 0;
		discount = 0;
		discounter = new DiscountHandler();
	}

	public UpdateDTO addCost(QuantifiedItemDTO item) {
		if(item != null) {
			calculateNewCost(item);
		}
		return new UpdateDTO(item, getCost(), getVat(), null);
	}

	private void calculateNewCost(QuantifiedItemDTO item) {
		switch (item.getVatRate()) {
		case 6:
			cost6 += item.getPrice() * item.getQuantity();
			break;
		case 12:
			cost12 += item.getPrice() * item.getQuantity();
			break;
		case 25:
			cost25 += item.getPrice() * item.getQuantity();
		}
	}

	/**
	 * generates a discount based upon customerID and items, and then aplies the
	 * discount to the cost
	 * 
	 * @param items
	 * @param customerID
	 */
	public void applyDiscount(ItemListDTO items, String customerID) {
		applyDiscount(discounter.generateDiscount(items, customerID));
	}

	/**
	 * applies discount to the cost
	 * 
	 * @param discount
	 */
	public void applyDiscount(Discount discount) {
		double costBefore = getCost();
		cost6 -= discount.getDiscountVat6() + discount.getBigDiscount() * cost6 / costBefore;
		cost12 -= discount.getDiscountVat12() + discount.getBigDiscount() * cost12 / costBefore;
		cost25 -= discount.getDiscountVat25() + discount.getBigDiscount() * cost25 / costBefore;
		this.discount = discount.getDiscountVat6() + discount.getDiscountVat12() + discount.getDiscountVat25()
				+ discount.getBigDiscount();
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost6 + cost12 + cost25;
	}

	/**
	 * @return the vat
	 */
	public double getVat() {
		return ((double)Math.round(100*(cost6 * (1.0-1.0/1.06) + cost12 * (1.0-1.0/1.12) + cost25 * 0.20)))/100;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * generates a SaleDTO for current sale
	 * 
	 * @param itemsDTO
	 * @return
	 */
	public SaleDTO getSalesDTO(ItemListDTO itemsDTO) {
		return new SaleDTO(getCost(), getVat(), getDiscount(), itemsDTO);
	}
	public void reset() {
		cost6 = 0;
		cost12 = 0;
		cost25 = 0;
		discount = 0;
	}
}