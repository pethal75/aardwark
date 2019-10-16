package com.modeler.io;

import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

import com.google.common.base.CaseFormat;
import com.modeler.application.SmartApplication;
import com.modeler.application.SmartAttribute;
import com.modeler.application.SmartObject;
import com.modeler.application.SmartPackage;

public class ModelerCsvEntitiesReader {
	public SmartApplication parseCsvFile(SmartApplication application, String inputFile) throws IOException
	{
		ModelerReader reader = new ModelerReader();
		
		Iterable<CSVRecord> records = reader.readCsvFile(inputFile);

		String packageName = reader.getFileNameNoExt();
		SmartPackage smartPackage = application.findPackageByName(packageName);
		if ( smartPackage == null )
			smartPackage = new SmartPackage(packageName);
		
		SmartObject entity = null;
		
		for (CSVRecord record : records) {
	    	String node = record.get("Node");
	    	String field = record.get("Field");
	    	String type = record.get("Type");
	    	
	    	if ( !node.equals("") )
	    	{
	    		String name = node;
	    		
	    		if ( entity != null )
	    			application.addObject(entity);
	    		
	    		// Add package name in front of object name, in case it is not the same name
	    		String sqlName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name).replace("__", "_");

	    		if ( application.isAppendPackageName() ) 
	    			if ( packageName != null && !name.toLowerCase().startsWith(packageName.toLowerCase()) && !sqlName.toLowerCase().startsWith(packageName.toLowerCase()) )
	    				name = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, packageName ) + name;
	    		
	    		// Create new entity
	    		entity = new SmartObject(smartPackage, name);
	    		
	    		// Create first attribute
	    		entity.addAttribute(new SmartAttribute(field, type));
	    	}
	    	else if ( !field.equals("") )
	    	{
	    		if ( entity != null )
	    		{
	    			entity.addAttribute(new SmartAttribute(field, type));
	    		}
	    	}
		}
		
		if ( entity != null )
			application.addObject(entity);
		
		return application;
	}

}
