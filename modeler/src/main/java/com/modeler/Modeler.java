package com.modeler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import com.google.common.base.CaseFormat;
import com.modeler.application.SmartApplication;
import com.modeler.application.SmartAttribute;
import com.modeler.application.SmartObject;
import com.modeler.application.SmartPackage;
import com.modeler.application.SmartService;
import com.modeler.generator.java.ModelerJavaGenerator;
import com.modeler.generator.sql.ModelerSQLGenerator;
import com.modeler.generator.swagger.ModelerSwaggerGenerator;
import com.modeler.generator.xstream.ModelerXStreamGenerator;
import com.modeler.io.ModelerCsvEntitiesReader;
import com.modeler.io.ModelerCsvServicesReader;
import com.modeler.io.ModelerFile;
import com.modeler.io.ModelerFile.Type;

/**
 * Modeller class represents generic data model read from CSV file,
 * and performs code generation for different purposes.
 * 
 * @author peter
 *
 */
public class Modeler {

	protected Iterable<CSVRecord> records;
	
	protected SmartApplication application;
	
	protected ArrayList<ModelerFile> importFiles = new ArrayList<ModelerFile>();

	protected String outputDir;
	
	/**
	 * Creates empty application
	 * 
	 * @param applicationName
	 * @param sqlSchema
	 */
	public Modeler(String applicationName, String sqlSchema, String outputDir)
	{
		this.application = new SmartApplication();
		this.application.setName(applicationName);
		this.application.setOutputDir(outputDir);
		this.application.setSqlSchema(sqlSchema);
		
		this.outputDir = outputDir;
	}

	public SmartApplication getApplication() {
		return application;
	}
	
	public void addImportFile(Type type, String fileName)
	{
		this.importFiles.add(new ModelerFile(type, fileName));
	}

	public void importFiles() throws IOException
	{
		for(ModelerFile file : this.importFiles)
		{
			if ( file.getType() == Type.TYPE_CSV_ENTITIES )
				this.parseCsvEntityFile(file.getFileName());
			else if ( file.getType() == Type.TYPE_CSV_SERVICES )
				this.parseCsvServiceFile(file.getFileName());
		}
	}
	
	/**
	 * Read CSV file and parse entities with attributes and functions.
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	protected void parseCsvEntityFile(String inputFile) throws IOException
	{
		ModelerCsvEntitiesReader reader = new ModelerCsvEntitiesReader();
		
		reader.parseCsvFile(this.application, inputFile);
	}

	/**
	 * Reads CSV file and parser services with parameters
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	protected void parseCsvServiceFile(String inputFile) throws IOException
	{
		ModelerCsvServicesReader reader = new ModelerCsvServicesReader();
		
		reader.parseCsvFile(this.application, inputFile);
	}

	/**
	 * Adds id, time stamp attribute to each object
	 */
	public void prepareSQLSchema()
	{
		for(SmartObject obj : this.application.getObjects() )
		{
    		// Create ID attribute
    		obj.addAttribute( 0, new SmartAttribute("created_at", SmartAttribute.TYPE_DATETIME) );
    		obj.addAttribute( 0, new SmartAttribute("modified_at", SmartAttribute.TYPE_DATETIME) );
    		obj.addAttribute( 0, new SmartAttribute("id_" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, obj.getName() ), SmartAttribute.TYPE_INTEGER) );
		}
	}
	
	/**
	 * Generate SQL schema files for database creation.
	 * 
	 * @param outputPath
	 */
	public void generateSQLSchema()
	{
		ModelerSQLGenerator generator = new ModelerSQLGenerator();
		
		generator.generateSQLSchema(this.application, this.outputDir );
	}

	/**
	 * Generates Java JPA entity classes
	 * 
	 * @param outputPath
	 */
	public void generateJavaEntities()
	{
		// generate Java entities
		ModelerJavaGenerator generator = new ModelerJavaGenerator();
		
		generator.generateJavaEntities(this.application, this.outputDir);
	}
	
	/**
	 * Generates XML mapping classes
	 * 
	 * @param outputPath
	 */
	public void generateXmlEntities()
	{
		// generate Java entities
		ModelerXStreamGenerator generator = new ModelerXStreamGenerator();
		
		generator.generateXmlEntities(this.application, this.outputDir);
	}
	
	/**
	 * Generates YAML files
	 */
	public void generateYAMLFiles()
	{
		// generate YAML file for application
		ModelerSwaggerGenerator generator = new ModelerSwaggerGenerator();
		
		generator.generateSwaggerFiles(this.application, this.outputDir);
	}
	
	/**
	 * Run generated yaml files
	 */
	public void runYAML()
	{
		for(SmartPackage pack : this.application.getPackages() )
		{
			if ( pack.getServices() != null && pack.getServices().size() > 0 )
			{
				String s = null;
				
				try {
					
					String command = this.outputDir + File.separator + "com" + File.separator + this.application.getName() + File.separator + application.getSqlSchema() + File.separator + "yaml" + File.separator + pack.getPackageName() + "-generate.bat";
					
					Process p = Runtime.getRuntime().exec(command);
		            
		            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));
	
		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(p.getErrorStream()));
	
		            // read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            // read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println(s);
		            }
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		for(SmartObject entity : this.application.getObjects())
			buf.append(entity.toString() + System.getProperty("line.separator"));
		
		for(SmartService service : this.application.getServices())
			buf.append(service.toString() + System.getProperty("line.separator"));

		return buf.toString();
	}
}
