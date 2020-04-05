package com.paragon.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import com.paragon.controller.ParagonException;
import com.paragon.model.Paragon;

class ParagonFileReaderTest {

	@Test
	void testReadFile1() {
		try {
			Paragon p1 = ParagonFileReader.readFromFile("data/paragon1.txt");
			
			assertNotNull(p1);
			assertNotNull(p1.getItems());
			assert(p1.getItems().size()==3);
			assertNotNull(p1.getItems().get(0));
			assertNotNull(p1.getItems().get(0).getName());
			assert(p1.getItems().get(0).getName().equals("SIM card"));
		} catch (ParagonException e) {
			e.printStackTrace();
		} 
	}

	@Test
	void testReadFileErr1() {
		try {
			Paragon p1 = ParagonFileReader.readFromFile("data/paragon-err1.txt");
			
			assertNotNull(p1);
			assertNotNull(p1.getItems());
			assert(p1.getItems().size()==5);
			assertNotNull(p1.getItems().get(0));
			assertNotNull(p1.getItems().get(0).getName());
			assert(p1.getItems().get(0).getName().equals("car"));
		} catch (ParagonException e) {
			e.printStackTrace();
		} 
	}

	@Test
	void testMissingFile() {
		try {
			ParagonFileReader.readFromFile("data/xyz.txt");

			fail();
			
		} catch (ParagonException e) {
			
		} 
	}
}
