package com.modeler.generator.sql;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.latte.LatteException;
import com.latte.LatteTemplate;
import com.modeler.application.SmartApplication;

/**
 * Generates SQL schema script for specified application. Script contains new schema
 * and tables generated from application objects.
 * 
 * @author peter
 *
 */
public class ModelerSQLGenerator {

	public void generateSQLSchema(SmartApplication application, String outputPath) {
		File f = new File(outputPath);
		
		if ( !f.exists() )
			f.mkdirs();

		try {
			// Generate schema file from template 
			LatteTemplate tpl = new LatteTemplate("templates\\sql\\sql-schema.latte");
			
			// Prepare parameters
			HashMap<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("application", application);
			
			// Render template
			String result = tpl.render( parameters );
	
			FileUtils.writeStringToFile(new File(outputPath + application.getSqlSchema() + ".sql"), result);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LatteException e) {
			e.printStackTrace();
		}

	}
}
