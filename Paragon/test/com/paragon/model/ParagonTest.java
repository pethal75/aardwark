package com.paragon.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ParagonTest {

	@Test
	void testParagonCreation() {
		Paragon p = new Paragon();
		
		assertNotNull(p.getItems());
		assert(p.getItems().size() == 0);
		
		p.addItem(new ParagonItem("item",100,false));

		assertNotNull(p.getItems());
		assert(p.getItems().size() == 1);
		assertNotNull(p.getItems().get(0));
		assertNotNull(p.getItems().get(0).getName());
		assert(p.getItems().get(0).getName().equals("item"));
		assert(p.getItems().get(0).getPrice().equals(BigDecimal.valueOf(100)));
		assert(p.getItems().get(0).isInsurance() == false);

		p.addItem(new ParagonItem("item 2",200,true));

		assertNotNull(p.getItems());
		assert(p.getItems().size() == 2);
		assertNotNull(p.getItems().get(1));
		assertNotNull(p.getItems().get(1).getName());
		assert(p.getItems().get(1).getName().equals("item 2"));
		assert(p.getItems().get(1).getPrice().equals(BigDecimal.valueOf(200)));
		assert(p.getItems().get(1).isInsurance() == true);
		
		p.setTax(BigDecimal.valueOf(20));
		
		assert(p.getTax().equals(BigDecimal.valueOf(20)));
		
		assertNotNull(p.getCreatedAt());
	}

	@Test
	void testParagonCalculationsTest() {
		Paragon p = new Paragon();
		p.addItem(new ParagonItem("item",100,false));
		p.addItem(new ParagonItem("item 2",200,true));
		p.addItem(new ParagonItem("item 3",300,false));
		
		p.recalculateTotals();
		
		assertNotNull(p.getTax());
		assertNotNull(p.getTotalNetAmount());
		
		assert(p.getTax().equals(BigDecimal.valueOf(48)));
		assert(p.getTotalNetAmount().equals(BigDecimal.valueOf(600)));
	}
}