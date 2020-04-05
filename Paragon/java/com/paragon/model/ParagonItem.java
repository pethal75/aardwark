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
	private boolean insurance;
	
	public ParagonItem(String name, BigDecimal price, boolean insurance) {
		this.name = name;
		this.price = price;
		this.insurance = insurance;

		this.recalculateTax();
	}
	
	public ParagonItem(String name, int price, boolean insurance) {
		this.name = name;
		this.price = BigDecimal.valueOf(price);
		this.insurance = insurance;

		this.recalculateTax();
	}

	public ParagonItem(String name) {
		this.name = name;
		this.price = null;
		this.insurance = false;

		this.recalculateTax();
	}

	private MathContext mc = new MathContext(2);
	
	/**
	 * Recalculates 12% of tax, except for insurance products
	 */
	private void recalculateTax() {
		
		if ( this.price == null )
			return;
		
		if ( this.insurance == false )
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

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
		
		this.recalculateTax();
	}
}
