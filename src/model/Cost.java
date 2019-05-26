package model;

import dto.Discount;
import dto.QuantifiedItemDTO;

public class Cost {
	private double cost6;
	private double cost12;
	private double cost25;
	private double discount;
	/**
	 * increases the cost and vat with values from an added item
	 * @param item - DTO that stores the quantity,cost and vat used
	 */
	public Cost() {
		cost6 = 0;
		cost12 = 0;
		cost25 = 0;
		discount = 0;
	}
	public void addCost(QuantifiedItemDTO item) {
		calculateNewCost(item);
	}
	private void calculateNewCost(QuantifiedItemDTO item) {
		switch(item.getVatRate()) {
		case 6:
			cost6+= item.getPrice()*item.getQuantity();
			break;
		case 12:
			cost12+= item.getPrice()*item.getQuantity();
			break;
		case 25:
			cost25+= item.getPrice()*item.getQuantity();
		}
	}
	/**
	 * 
	 * @param discount
	 */
	public void applyDiscount(Discount discount) {
		double costBefore = getCost();
		cost6-= discount.getDiscountVat6() + discount.getBigDiscount()*cost6/costBefore;
		cost12-= discount.getDiscountVat12()+ discount.getBigDiscount()*cost12/costBefore;
		cost25-= discount.getDiscountVat25() + discount.getBigDiscount()*cost25/costBefore;
		this.discount = discount.getDiscountVat6() + discount.getDiscountVat12() + discount.getDiscountVat25() + discount.getBigDiscount();
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
		return cost6*0.06 + cost12*0.12 + cost25*0.25;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}
}