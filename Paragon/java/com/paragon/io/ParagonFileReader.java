package com.paragon.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.paragon.controller.ParagonException;
import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;

/**
 * ParagonFileReader creates Paragon object from paragon type file. File contains list of paragon item names.
 *  
 * @author peter
 *
 */
public class ParagonFileReader {
	
	/**
	 * Reads paragon from paragon file. Format of the file is one line per item name.
	 *  
	 * @param fileNamePath path + name of the input file to be read
	 * @return Paragon object containing items from file.
	 * @throws ParagonException in case file read problem, exception is thrown.
	 */
	public static Paragon readFromFile(String fileNamePath) throws ParagonException {
		Paragon paragon = new Paragon();
		
		try {
			File paragonFile = new File(fileNamePath);
			FileReader fr = new FileReader(paragonFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			
			while( (line = br.readLine()) != null ) {
				// Create new item
				ParagonItem item = new ParagonItem(line);
				
				paragon.addItem(item);
			}

			br.close();
			fr.close();
		}
		catch (Exception exc) {
			throw new ParagonException(exc);
		}
		
		return paragon;
	}
}
