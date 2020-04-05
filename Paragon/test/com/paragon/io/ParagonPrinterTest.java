package com.paragon.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paragon.controller.ParagonController;
import com.paragon.model.Paragon;

class ParagonPrinterTest {

	private Date createdAt = Date.valueOf(LocalDate.of(2020, 4, 5));
	
	@Test
	void testPrintParagon1() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon1.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.getItems().get(0).setPrice(BigDecimal.valueOf(10));
		p1.getItems().get(1).setPrice(BigDecimal.valueOf(20));
		p1.getItems().get(2).setPrice(BigDecimal.valueOf(30));
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"|SIM card                                10,00|\r\n" + 
				"|  Tax                                    1,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Phone case                              20,00|\r\n" + 
				"|  Tax                                    2,40|\r\n" + 
				"|                                             |\r\n" + 
				"|Wired earphones                         30,00|\r\n" + 
				"|  Tax                                    3,60|\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                       60,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                               7,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                           67,20|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}

	@Test
	void testPrintParagon2() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon2.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.getItems().get(0).setPrice(BigDecimal.valueOf(10));
		p1.getItems().get(1).setPrice(BigDecimal.valueOf(20));
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"|Phone insurance                         10,00|\r\n" + 
				"|  Tax                                    0,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Wireless earphones                      20,00|\r\n" + 
				"|  Tax                                    2,40|\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                       30,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                               2,40|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                           32,40|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}

	@Test
	void testPrintParagon3() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon3.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.getItems().get(0).setPrice(BigDecimal.valueOf(10));
		p1.getItems().get(1).setPrice(BigDecimal.valueOf(20));
		p1.getItems().get(2).setPrice(BigDecimal.valueOf(30));
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"|SIM card                                10,00|\r\n" + 
				"|  Tax                                    1,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Phone case                              20,00|\r\n" + 
				"|  Tax                                    2,40|\r\n" + 
				"|                                             |\r\n" + 
				"|Wired earphones                         30,00|\r\n" + 
				"|  Tax                                    3,60|\r\n" + 
				"|                                             |\r\n" + 
				"|Phone insurance                        120,00|\r\n" + 
				"|  Tax                                    0,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Wireless earphones                      50,00|\r\n" + 
				"|  Tax                                    6,00|\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                      230,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                              13,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                          243,20|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}

	@Test
	void testPrintParagonErr1() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon-err1.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.getItems().get(0).setPrice(BigDecimal.valueOf(10));
		p1.getItems().get(1).setPrice(BigDecimal.valueOf(20));
		p1.getItems().get(2).setPrice(BigDecimal.valueOf(30));
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"|Phone case                              10,00|\r\n" + 
				"|  Tax                                    1,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Wired earphones                         20,00|\r\n" + 
				"|  Tax                                    2,40|\r\n" + 
				"|                                             |\r\n" + 
				"|Wireless earphones                      30,00|\r\n" + 
				"|  Tax                                    3,60|\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                       60,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                               7,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                           67,20|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}

	@Test
	void testPrintParagonErr2() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon-err2.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                        0,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                               0,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                            0,00|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}

	@Test
	void testPrintParagonErr3() {
		ParagonController ctrl = new ParagonController();
		
		Paragon p1 = ctrl.loadParagonFromFile("data/paragon-err3.txt");
		p1.setCreatedAt(this.createdAt);
		p1.setId(1228440963);
		
		assertNotNull(p1);
		assertNotNull(p1.getItems());
		
		p1.getItems().get(0).setPrice(BigDecimal.valueOf(10));
		p1.getItems().get(1).setPrice(BigDecimal.valueOf(20));
		
		p1.recalculateTotals();
		
		String output = ParagonPrinter.printToString(p1);
		
		String outputReal = "-----------------------------------------------\r\n" + 
				"|Paragon ID:                        1228440963|\r\n" + 
				"|Date:                       5.4.2020 12:00:00|\r\n" + 
				"|                                             |\r\n" + 
				"|Items:                                       |\r\n" + 
				"|                                             |\r\n" + 
				"|Phone case                              10,00|\r\n" + 
				"|  Tax                                    1,20|\r\n" + 
				"|                                             |\r\n" + 
				"|Wired earphones                         20,00|\r\n" + 
				"|  Tax                                    2,40|\r\n" + 
				"|                                             |\r\n" + 
				"-----------------------------------------------\r\n" + 
				"|Total Net Amount:                       30,00|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Tax:                               3,60|\r\n" + 
				"|                                             |\r\n" + 
				"|Total Amount:                           33,60|\r\n" + 
				"-----------------------------------------------\r\n";
		
		assertEquals(output, outputReal);
	}
}
