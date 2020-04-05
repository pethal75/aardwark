package com.paragon.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import com.paragon.io.ParagonFileReader;
import com.paragon.io.ParagonPrinter;
import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;

/**
 * Main paragon controller class, for loading, validating and other paragons manipulation
 * 
 * @author peter
 *
 */
public class ParagonController {

	/** Internal store of valid items */
	private HashMap<String,ParagonItem> itemsStore = new HashMap<String,ParagonItem>();
	
	public ParagonController() {
		this.initializeStore();
	}

	/**
	 * Initialize items store.
	 */
	public void initializeStore() {
		this.itemsStore.put("SIM card", new ParagonItem("SIM card", BigDecimal.valueOf(20), false));
		this.itemsStore.put("Phone case", new ParagonItem("Phone case", BigDecimal.valueOf(10), false));
		this.itemsStore.put("Phone insurance", new ParagonItem("Phone insurance", BigDecimal.valueOf(120), true));
		this.itemsStore.put("Wired earphones", new ParagonItem("Wired earphones", BigDecimal.valueOf(30), false));
		this.itemsStore.put("Wireless earphones", new ParagonItem("Wireless earphones", BigDecimal.valueOf(50), false));
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
					item.setInsurance(storeItem.isInsurance());
				}
				else {
					// Remove item from paragon
					paragon.getItems().remove(index);
					
					index--;
				}
			}
		}
		
		return paragon;
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
