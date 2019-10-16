package com.application;
import java.io.IOException;

import com.modeler.Modeler;
import com.modeler.io.ModelerFile.Type;

/*
 * Main application for Modeler functionality
 */
public class PlayersModeler {
	
    public static boolean generateBwfTSApplication() {
		System.out.println("Generate model");
		
		String entityFilePlayer = "application-players\\input\\data\\player.csv";

		String serviceFilePlayer = "application-players\\input\\services\\player.csv";

		String outputDir = "application-players\\output\\";
		
		try
		{
			Modeler m = new Modeler("aardwark", "players", outputDir);

			m.addImportFile(Type.TYPE_CSV_ENTITIES, entityFilePlayer);

			m.addImportFile(Type.TYPE_CSV_SERVICES, serviceFilePlayer);

			m.importFiles();
			
			// SQL schema
			System.out.println("Generate SQL model");
			
			m.prepareSQLSchema();
			m.generateSQLSchema();
			
			// Java JPA entities
			System.out.println("Generate Java entities");

			m.generateJavaEntities();

			// XML xstream classes
			System.out.println("Generate XML entities");

			m.generateXmlEntities();

			// YAML file
			System.out.println("Generate YAML file");

			m.generateYAMLFiles();
			m.runYAML();
			
			// System.out.println( m.toString() );
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
			
			return false;
		}
		
		return true;
    }
    
    public static final void main(String[] args)
    {
		System.out.println("BWF TS Generator");
		
		generateBwfTSApplication();
    }
}
