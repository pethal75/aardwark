package com.modeler.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Reads CSV file.
 * 
 * @author peter
 *
 */
public class ModelerReader {

	String fileName;
	
	Iterable<CSVRecord> records;
	
	public Iterable<CSVRecord> readCsvFile(String fileName) throws IOException
	{
		this.fileName = fileName;
		
		Reader in = new FileReader(fileName);
		
		this.records = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(in);

		return this.records;
	}
	
	public String getFileNameNoExt()
	{
		return fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.indexOf('.'));
	}
}
