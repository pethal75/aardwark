package com.latte;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.latte.LatteException;
import com.latte.LatteTemplate;

public class LatteVarTest {

	@Test
	public void test() {
		
		try {
			String fileTemplate = "data\\simple-var.latte";
			String fileResult = "data\\test-results\\simple-var.txt";
			
			LatteTemplate tpl = new LatteTemplate(fileTemplate);
			
			HashMap<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("table_name", "Player");
			parameters.put("object", new TestObject());
			
			String result = tpl.render(parameters);
			
			String testResult = FileUtils.readFileToString(new File(fileResult), "UTF-8");
			
			assert(result.equals(testResult));
			
		} catch (IOException | LatteException e) {
			fail("Fail exception " + e.getMessage());
		}
		
	}

}
