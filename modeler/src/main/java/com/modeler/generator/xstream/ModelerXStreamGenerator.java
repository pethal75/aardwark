package com.modeler.generator.xstream;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.latte.LatteException;
import com.latte.LatteTemplate;
import com.modeler.application.SmartApplication;
import com.modeler.application.SmartObject;

/**
 * Generates XML mapping classes for XStream library.
 * 
 * @author peter
 *
 */
public class ModelerXStreamGenerator {

	public void generateXmlEntities(SmartApplication application, String outputPath) {
		String outputDir = outputPath + File.separator + "com" + File.separator + application.getName() + File.separator + application.getSqlSchema() + File.separator + "xml";
		
		File f = new File(outputDir);
		
		if ( !f.exists() )
			f.mkdirs();

		try {
			// Generate schema file from template 
			LatteTemplate tplXml = new LatteTemplate("templates\\xml\\java-xstream-entity.latte");
			
			for(SmartObject object : application.getObjects() )
			{
				// Create directory
				File dir = new File(outputDir + File.separator + object.getPackageName());
				dir.mkdirs();
				
				// Prepare parameters
				HashMap<String,Object> parameters = new HashMap<String,Object>();
				
				parameters.put("application", application);
				parameters.put("object", object);
				
				// Render entity template
				String result = tplXml.render( parameters );
	
				// Save entity file
				FileUtils.writeStringToFile(new File(outputDir + File.separator + object.getPackageName() + File.separator + object.getJavaXmlName() + ".java"), result);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LatteException e) {
			e.printStackTrace();
		}

	}
}
