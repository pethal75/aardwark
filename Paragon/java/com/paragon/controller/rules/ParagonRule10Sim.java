package com.paragon.controller.rules;

import com.paragon.controller.ParagonException;
import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;
import com.paragon.model.ParagonItem.ItemType;

public class ParagonRule10Sim implements ParagonRule {

	@Override
	public void processParagon(Paragon paragon) throws ParagonException {
		
		int simCount = 0;
		
		for(ParagonItem item : paragon.getItems()) {
			if ( item.isType(ItemType.SIM) ) {
				simCount++;
			}
		}
		
		if ( simCount > 10 ) {
			throw new ParagonException("Too many SIM cards, maximum is 10!");
		}
	}

}
