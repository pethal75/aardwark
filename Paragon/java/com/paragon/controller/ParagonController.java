package com.paragon.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.paragon.controller.rules.ParagonRule;
import com.paragon.controller.rules.ParagonRuleBOGOF;
import com.paragon.io.ParagonFileReader;
import com.paragon.io.ParagonPrinter;
import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;
import com.paragon.model.ParagonItem.ItemType;

/**
 * Main paragon controller class, for loading, validating and other paragons manipulation
 * 
 * @author peter
 *
 */
public class ParagonController {

	/** Internal store of valid items */
	private HashMap<String,ParagonItem> itemsStore = new HashMap<String,ParagonItem>();

	private ArrayList<ParagonRule> rules = new ArrayList<ParagonRule>();
	
	public ParagonController() {
		this.initializeStore();
	}

	/**
	 * Initialize items store.
	 */
	public void initializeStore() {
		this.itemsStore.put("SIM card", new ParagonItem("SIM card", BigDecimal.valueOf(20), ItemType.SIM));
		this.itemsStore.put("Phone case", new ParagonItem("Phone case", BigDecimal.valueOf(10), ItemType.UNKNOWN));
		this.itemsStore.put("Phone insurance", new ParagonItem("Phone insurance", BigDecimal.valueOf(120), ItemType.INSURANCE));
		this.itemsStore.put("Wired earphones", new ParagonItem("Wired earphones", BigDecimal.valueOf(30), ItemType.EARPHONE));
		this.itemsStore.put("Wireless earphones", new ParagonItem("Wireless earphones", BigDecimal.valueOf(50), ItemType.EARPHONE));
		
		this.rules.add(new ParagonRuleBOGOF());
	}

	/**
	 * Loads Paragon from file, and makes validation to existing items in store.
	 * 
	 * @param fileNamePath
	 * @return
	 */
	public Paragon loadParagonFromFile(String fileNamePath) {
		
		Paragon paragon = null;
		
		try {
			paragon = ParagonFileReader.readFromFile(fileNamePath);
			
		} catch (ParagonException e) {
			return null;
		}

		// Setup insurance attributes for paragon items
		if ( paragon.getItems() != null ) {
			for(int index = 0; index < paragon.getItems().size(); index++ ) {
				ParagonItem item = paragon.getItems().get(index); 
				ParagonItem storeItem = this.searchStoreByItemName(item.getName());
				
				if ( storeItem != null ) {
					item.setPrice(storeItem.getPrice());
					item.setType(storeItem.getType());
				}
				else {
					// Remove item from paragon
					paragon.getItems().remove(index);
					
					index--;
				}
			}
		}

		// Run business rules to validate paragon
		try {
			this.runBusinessRules(paragon);
		} catch (ParagonException e) {
			return null;
		}
		
		paragon.recalculateTotals();
		
		return paragon;
	}

	/**
	 * Runs business rules over specified paragon
	 * 
	 * @param paragon
	 */
	private void runBusinessRules(Paragon paragon) throws ParagonException {
		for(ParagonRule rule : this.rules)
			rule.processParagon(paragon);
	}
	
	/**
	 * Prints specified paragon to the output console
	 * 
	 * @param paragon
	 */
	public String printOutParagon(Paragon paragon) {
		return ParagonPrinter.printToString(paragon);
	}
	
	/**
	 * Searches paragon store for existing item
	 * 
	 * @param name
	 * @return
	 */
	public ParagonItem searchStoreByItemName(String name) {
		return this.itemsStore.get(name);
	}
}
