package com.paragon.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Main Paragon object, contains array of paragon items.
 * 
 * @author peter
 *
 */
public class Paragon {

	/** Id of the paragon */
	protected long id;
	
	/**
	 *  Array list of paragon items
	 */
	protected ArrayList<ParagonItem> items = new ArrayList<ParagonItem>();
	
	/** Paragon creation date */
	protected Date createdAt = new Date();
	
	/** Paragon final tax */
	protected BigDecimal tax;
	
	/** Paragon total amount */
	protected BigDecimal totalNetAmount;
	
	public Paragon() {
		this.createdAt = new Date();
		
		this.initId();
		
		this.tax = BigDecimal.valueOf(0);
		this.totalNetAmount = BigDecimal.valueOf(0);
	}
	
	public void initId() {
		
		if ( this.createdAt == null )
			this.createdAt = new Date();
		
		this.id = this.createdAt.hashCode();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Add item to current paragon
	 * 
	 * @param item ParagonItem object
	 */
	public void addItem(ParagonItem item) {
		
		if ( item != null )
			this.items.add(item);
	}

	/**
	 * Adds new item at specific position
	 * 
	 * @param item
	 * @param index
	 */
	public void addItem(int index, ParagonItem item) {

		if ( item != null )
			this.items.add(index, item);
	}

	public ArrayList<ParagonItem> getItems() {
		return this.items;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getTax() {
		return this.tax;
	}
	
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalNetAmount() {
		
		return totalNetAmount;
	}
	
	/**
	 * Recalculates total tax and total amount of this paragon
	 */
	public void recalculateTotals() {
		
		this.tax = BigDecimal.valueOf(0);
		
		for(ParagonItem item : this.items) {
			if ( item.getTax() != null )
				this.tax = this.tax.add(item.getTax());
		}

		this.totalNetAmount = BigDecimal.valueOf(0);
		
		for(ParagonItem item : this.items) {
			if ( item.getPrice() != null )
				this.totalNetAmount = this.totalNetAmount.add(item.getPrice());
		}
	}
}
