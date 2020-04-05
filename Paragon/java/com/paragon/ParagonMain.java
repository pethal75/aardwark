package com.paragon;

import com.paragon.controller.ParagonController;
import com.paragon.model.Paragon;

/**
 * Main class for Paragon application, provides command line interface.
 * 
 * @author peter
 *
 */
public class ParagonMain {

	public static void main(String[] args) {
		
		if ( args == null || args.length == 0 ) {
			System.out.println("Usage:");
			System.out.println("java -jar Paragon.jar ParagonMain <input-file.txt>");
			return;
		}
		
		// Create paragon controller
		ParagonController ctrl = new ParagonController();
		
		// Read paragon from file
		Paragon paragon = ctrl.loadParagonFromFile(args[0]);
		
		//  Write paragon to output
		String output = ctrl.printOutParagon(paragon);
		
		System.out.println("Paragon " + args[0] + " result:");
		System.out.println(output);
	}
}
