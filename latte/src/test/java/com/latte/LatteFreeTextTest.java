package com.latte;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.latte.LatteException;
import com.latte.LatteTemplate;

public class LatteFreeTextTest {

	@Test
	public void test() {
		
		try {
			String fileName = "data\\simple-freetext.latte";
			
			LatteTemplate tpl = new LatteTemplate(fileName);
			
			String result = tpl.render(null);
			
			String template = FileUtils.readFileToString(new File(fileName), "UTF-8");
			
			assert(result.equals(template));
			
		} catch (IOException | LatteException e) {
			fail("Fail exception " + e.getMessage());
		}
		
	}
}
