package com.modeler.generator.swagger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.latte.LatteException;
import com.latte.LatteTemplate;
import com.modeler.application.SmartApplication;
import com.modeler.application.SmartPackage;

/**
 * Generates YAML files to be generated with swagger.
 * 
 * @author peter
 *
 */
public class ModelerSwaggerGenerator {

	public void generateSwaggerFiles(SmartApplication application, String outputPath) {
		String outputDir = outputPath + File.separator + "com" + File.separator + application.getName() + File.separator + application.getSqlSchema() + File.separator + "yaml";
		
		File f = new File(outputDir);
		
		if ( !f.exists() )
			f.mkdirs();

		try {
			// Generate schema file from template 
			LatteTemplate tplYaml = new LatteTemplate("templates\\swagger\\open-api-service.latte");
			
			LatteTemplate tplBat = new LatteTemplate("templates\\swagger\\open-api-generate.latte");

			// Prepare parameters
			HashMap<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("application", application);

			for(SmartPackage pack : application.getPackages() )
			{
				if ( pack.getServices() != null && pack.getServices().size() > 0 )
				{
					// Put actual service parameter
					parameters.put("package", pack);
					
					// Render YAML template
					String result = tplYaml.render( parameters );
					
					FileUtils.writeStringToFile(new File(outputDir + File.separator + pack.getPackageName() + ".yaml"), result);
	
					// Render YAML template
					result = tplBat.render( parameters );
					
					FileUtils.writeStringToFile(new File(outputDir + File.separator + pack.getPackageName() + "-generate.bat"), result);
				}
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LatteException e) {
			e.printStackTrace();
		}

	}
}
