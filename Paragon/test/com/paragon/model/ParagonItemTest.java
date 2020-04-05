package com.paragon.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.paragon.model.ParagonItem.ItemType;

class ParagonItemTest {

	@Test
	void testParagonItemCreation() {
		ParagonItem p = new ParagonItem("item1",100, ItemType.SIM);
		
		assertNotNull(p.getName());
		assert(p.getName().equals("item1"));
		assertNotNull(p.getPrice());
		assert(p.getPrice().equals(BigDecimal.valueOf(100)));
		assertNotNull(p.isType(ItemType.SIM));
	}
}
