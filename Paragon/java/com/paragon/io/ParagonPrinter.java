package com.paragon.io;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.paragon.model.Paragon;
import com.paragon.model.ParagonItem;

/**
 * Prints paragon content to the console output
 * 
 * @author peter
 *
 */
public class ParagonPrinter {
	/** Date format for paragon date */
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy h:mm:ss");
	
	/** Decimal format for paragon item price */
	public static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
	
	public static String printToString(Paragon paragon) {
		
		if ( paragon == null ) {
			System.out.println("Invalid paragon");
			return "";
		}
		
		StringBuffer sb = new StringBuffer();

		sb.append(separatorline);
		
		// Print paragon ID
		printLine(sb, "Paragon ID:", "" + paragon.getId() );
		
		// Print paragon date
		printLine(sb, "Date:", dateFormat.format(paragon.getCreatedAt()) );

		// Print paragon items
		printLine(sb, "","");
		printLine(sb, "Items:","");
		printLine(sb, "","");

		if ( paragon.getItems() != null ) {
			for(int index = 0; index < paragon.getItems().size(); index++ ) {
				ParagonItem item = paragon.getItems().get(index);
				
				String name = item.getName();
				String price = item.getPrice() != null ? decimalFormat.format(item.getPrice()) : "0";
				String tax = item.getTax() != null ? decimalFormat.format(item.getTax()) : "0";
				
				printLine(sb,  name, price );
				printLine(sb,  "  Tax", tax );
				printLine(sb, "","");
			}
		}

		sb.append(separatorline);

		// Print total amount
		printLine(sb, "Total Net Amount:", decimalFormat.format(paragon.getTotalNetAmount()) );

		printLine(sb, "","");
		
		// Print tax
		printLine(sb, "Total Tax:", decimalFormat.format(paragon.getTax()) );
		
		printLine(sb, "","");
		
		// Print total amount
		printLine(sb, "Total Amount:", decimalFormat.format(paragon.getTotalNetAmount().add(paragon.getTax())) );
		
		sb.append(separatorline);
		
		return sb.toString();
	}
	
	public static final String separatorline = "-----------------------------------------------\r\n";
	public static final String separator = "\r\n";
	public static final String line = "                                                      ";

	/**
	 * Print one formatted line of paragon 
	 * 
	 * @param label of paragon line
	 * @param value of paragon line
	 */
	public static void printLine(StringBuffer sb, String label, String value) {
		sb.append( "|" + label + line.substring(0,45 - label.length() - value.length()) + value + "|" + separator);
	}
}
