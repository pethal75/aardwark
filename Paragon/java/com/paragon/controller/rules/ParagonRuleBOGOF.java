package com.paragon.controller.rules;

import java.math.BigDecimal;

import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;
import com.paragon.model.ParagonItem.ItemType;

public class ParagonRuleBOGOF implements ParagonRule {

	public void processParagon(Paragon paragon) {
		for(int index = 0; index < paragon.getItems().size(); index++) {
			ParagonItem item = paragon.getItems().get(index);
			
			if ( item.isType(ItemType.SIM) ) {
				ParagonItem sim = new ParagonItem(item);
				
				sim.setPrice(BigDecimal.valueOf(0));

				paragon.addItem(index + 1, sim);
				
				index++;
			}
		}
	}
}
