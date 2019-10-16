package com.modeler.io;

import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

import com.modeler.application.SmartApplication;
import com.modeler.application.SmartAttribute;
import com.modeler.application.SmartObject;
import com.modeler.application.SmartPackage;
import com.modeler.application.SmartService;
import com.modeler.application.SmartServiceResponse;

public class ModelerCsvServicesReader {
	public void parseCsvFile(SmartApplication application, String inputFile) throws IOException
	{
		ModelerReader reader = new ModelerReader();
		
		Iterable<CSVRecord> records = reader.readCsvFile(inputFile);
		
		String packageName = reader.getFileNameNoExt();
		SmartPackage smartPackage = application.findPackageByName(packageName);
		if ( smartPackage == null )
			smartPackage = new SmartPackage(packageName);

		
		for (CSVRecord record : records) 
		{
	    	String serviceName = record.get(0);
	    	String pathParameters = record.get(1);
	    	String queryParameters = record.get(2);
	    	String responseNode = record.get(3);
	    	String description = record.get(4);
	    	String method = record.size() > 5 ? record.get(5) : "GET";

	    	if ( !serviceName.equals("") )
	    	{
	    		serviceName = serviceName.replace(" ", "");

	    		String servicePath = serviceName;
	    		
    			serviceName = serviceName.replace("{", "");
	    		serviceName = serviceName.replace("}", "");
	    		serviceName = serviceName.replace("/", "");
	    		
	    		// Create new service
	    		SmartService service = new SmartService(smartPackage, serviceName, servicePath, description, method);
	    		
	    		// Parse path parameters
	    		String pparam[] = pathParameters.split("\n");
	    		
	    		for(String param : pparam)
	    		{
	    			if ( param != null && !param.equals(""))
	    				service.addPathParameter(param.split(" ")[0]);
	    		}
	    		
	    		// Parse query parameters
	    		String qparam[] = queryParameters.split("\n");
	    		
	    		for(String param : qparam)
	    		{
	    			if ( param != null && !param.equals(""))
	    				service.addQueryParameter(param.split(" ")[0]);
	    		}
	    		
	    		// Set response node
	    		SmartObject responseObject = application.findObjectByName(responseNode);
	    		
	    		if ( responseObject != null )
	    		{
	    			SmartAttribute responseType = new SmartAttribute("response", SmartAttribute.TYPE_OBJECT, responseObject.getName() );
	    			SmartServiceResponse response = new SmartServiceResponse("200", responseType);
	    			
	    			service.setResponse(response);
	    		}
	    		else if ( responseNode.equals(SmartAttribute.TYPE_TEXT) || responseNode.equals(SmartAttribute.TYPE_INTEGER) ) 
	    		{
	    			SmartAttribute responseType = new SmartAttribute("response", responseNode );
	    			SmartServiceResponse response = new SmartServiceResponse("200", responseType);
	    			
	    			service.setResponse(response);
	    		}
	    		else
	    		{
	    			System.out.println("Response type " + responseNode + " not found.");

	    			SmartAttribute responseType = new SmartAttribute("response", SmartAttribute.TYPE_TEXT );
	    			SmartServiceResponse response = new SmartServiceResponse("200", responseType);
	    			
	    			service.setResponse(response);
	    		}
	    		
	    		// Set description
	    		service.setDescription(description);
	    		
	    		// Add service to application
	    		application.addService(service);
	    	}
		}
	}
}
