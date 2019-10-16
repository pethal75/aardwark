package com.latte.tokens;

import java.util.HashMap;
import java.util.Iterator;

import com.latte.LatteException;
import com.latte.tokens.expression.LatteExpression;

public class LatteCommandForeach extends LatteCommand {

	public static final String TOKEN = "foreach";
	
	protected LatteExpression expression;
	
	protected String iteratorValueName;
	
	public LatteCommandForeach() {
		super();
		
		this.command = TOKEN;
		
		this.compound = true;
	}

	@Override
	public void parseParametrization(String expression) throws LatteException {
		
		String tokens[] = expression.split(" ");
		
		if ( tokens.length != 3 )
			throw new LatteException("Invalid foreach structure");
		
		if ( !tokens[1].equals("as"))
			throw new LatteException("Missing or misplaced \"as\" clause");

		if ( !tokens[2].substring(0,1).equals("$") )
			throw new LatteException("Iterator must be variable");

		// Parse expression
		this.expression = LatteExpression.parseExpression(tokens[0]);

		// Store iterator variable name
		this.iteratorValueName = tokens[2].substring(1);
	}

	@Override
	public String render(HashMap<String,Object> parameters)	{
		
		StringBuffer result = new StringBuffer();
		
		// Get list
		Object list = this.expression.resolve(parameters);
		
		if ( list instanceof Iterable<?>)
		{
			Iterator<?> i = ((Iterable<?>)list).iterator();
			
			// Iterate through list
			while (i.hasNext()) 
			{
				// Get iterator variable value
				Object value = i.next();
				
				parameters.put(this.iteratorValueName, value);
				
				// Render all children
				for(LatteToken token : this.getChildren() )
				{
					String child = token.render(parameters);
					
					result.append(child);
				}
			}
		}
		
		return result.toString();
	}

	@Override
	public String toString() {
		String children = super.toString();
		
		return "LatteForeachCommand (" + this.expression.toString() + ")" + "\r\n" + children + "/LatteForeachCommand"  + "\r\n";
	}
}