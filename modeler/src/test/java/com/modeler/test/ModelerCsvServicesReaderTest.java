package com.modeler.test;
import java.io.IOException;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;

import com.modeler.Modeler;
import com.modeler.io.ModelerFile.Type;

public class ModelerCsvServicesReaderTest {
    @Test 
    public void testReadCsvFile() throws IOException {

    	String inputFilePlayer = "application\\input\\services\\player.csv";
    	Modeler m = new Modeler("bwf", "ts", "application\\output");

		// read application services from CSV files
		m.addImportFile(Type.TYPE_CSV_SERVICES, inputFilePlayer);
		m.importFiles();
		
		// Check services were parsed
		assert( m.getApplication().getServices().size() > 0 );
		
		assert( m.getApplication().findServiceByName("ActivePlayerList") != null );
		
		assert( m.getApplication().findServiceByName("ActivePlayerList").getQueryParameters().size() == 2 );

		assert( m.getApplication().findServiceByName("HeadToHeadListDoubles") != null );
		
		assert( m.getApplication().findServiceByName("HeadToHeadListDoubles").getPathParameters().size() == 4 );
    }
}
