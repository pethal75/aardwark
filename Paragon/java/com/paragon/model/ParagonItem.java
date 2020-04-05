package com.paragon.model;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * ParagonItem contains data about specific paragon item - name, price, insurance attribute.
 * 
 * @author peter
 *
 */
public class ParagonItem {

	/** Name of paragon item */
	private String name;
	
	/** Price of paragon item */
	private BigDecimal price;
	
	/** Tax of paragon item */
	private BigDecimal tax;
	
	/** Attribute whether this item is insurance type */
	public enum ItemType {
		UNKNOWN, INSURANCE, SIM, EARPHONE;
	};

	private ItemType type;
	
	public ParagonItem(String name, BigDecimal price, ItemType type) {
		this.name = name;
		this.price = price;
		this.type = type;

		this.recalculateTax();
	}
	
	public ParagonItem(String name, int price, ItemType type) {
		this.name = name;
		this.price = BigDecimal.valueOf(price);
		this.type = type;
		
		this.recalculateTax();
	}

	public ParagonItem(String name) {
		this.name = name;
		this.price = null;
		this.type = ItemType.UNKNOWN;

		this.recalculateTax();
	}

	public ParagonItem(ParagonItem item) {
		this.name = item.getName();
		this.price = new BigDecimal(item.getPrice().toBigInteger());
		this.tax = new BigDecimal(item.getTax().toBigInteger());
		this.type = item.getType();
	}

	private static final MathContext mc = new MathContext(2);
	
	/**
	 * Recalculates 12% of tax, except for insurance products
	 */
	private void recalculateTax() {
		
		if ( this.price == null )
			return;
		
		if ( !this.isType(ItemType.INSURANCE) )
			this.tax = this.price.multiply(BigDecimal.valueOf(12)).divide(BigDecimal.valueOf(100)).round(mc);
		else
			this.tax = BigDecimal.valueOf(0);
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
		
		this.recalculateTax();
	}

	public BigDecimal getTax() {
		return tax;
	}

	public boolean isType(ItemType type) {
		return this.type == type;
	}

	public ItemType getType() {
		return this.type;
	}

	public void setType(ItemType type) {
		this.type = type;
		
		this.recalculateTax();
	}
}
