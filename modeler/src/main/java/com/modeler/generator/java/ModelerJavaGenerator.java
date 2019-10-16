package com.modeler.generator.java;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.latte.LatteException;
import com.latte.LatteTemplate;
import com.modeler.application.SmartApplication;
import com.modeler.application.SmartObject;

/**
 * Generates JPA Java entities.
 * 
 * @author peter
 *
 */
public class ModelerJavaGenerator {

	public void generateJavaEntities(SmartApplication application, String outputPath) {
		
		String outputDir = outputPath + File.separator + "com" + File.separator + application.getName() + File.separator + application.getSqlSchema() + File.separator + "model";
		
		File f = new File(outputDir);
		
		if ( !f.exists() )
			f.mkdirs();

		try {
			// Generate schema file from template 
			LatteTemplate tplEntity = new LatteTemplate("templates\\java\\java-entity.latte");
			LatteTemplate tplRepository = new LatteTemplate("templates\\java\\java-entity-repository.latte");
			
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
				String result = tplEntity.render( parameters );
	
				// Save entity file
				FileUtils.writeStringToFile(new File(outputDir + File.separator + object.getPackageName() + File.separator + object.getJavaEntityName() + ".java"), result);

				// Render entity repository template
				String resultRepository = tplRepository.render( parameters );
	
				// Save entity file
				FileUtils.writeStringToFile(new File(outputDir + File.separator + object.getPackageName() + File.separator + object.getJavaEntityRepositoryName() + ".java"), resultRepository);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LatteException e) {
			e.printStackTrace();
		}

	}
}
