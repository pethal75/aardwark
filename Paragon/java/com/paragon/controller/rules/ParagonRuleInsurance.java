package com.paragon.controller.rules;

import java.math.BigDecimal;

import com.paragon.controller.ParagonException;
import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;
import com.paragon.model.ParagonItem.ItemType;

public class ParagonRuleInsurance implements ParagonRule {

	@Override
	public void processParagon(Paragon paragon) throws ParagonException {
		
		boolean earphone = false;
		
		for(ParagonItem item : paragon.getItems()) {
			if ( item.isType(ItemType.EARPHONE) ) {
				earphone = true;
				break;
			}
		}
		
		if ( earphone ) {
			for(ParagonItem item : paragon.getItems()) {
				if ( item.isType(ItemType.INSURANCE) ) {
					item.setPrice( item.getPrice().multiply(BigDecimal.valueOf(80).divide(BigDecimal.valueOf(100))));
					break;
				}
			}
		}
	}

}
