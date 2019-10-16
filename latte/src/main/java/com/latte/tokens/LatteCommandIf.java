package com.latte.tokens;

import java.util.HashMap;

import com.latte.LatteException;
import com.latte.tokens.expression.LatteExpression;

public class LatteCommandIf extends LatteCommand {

	public static final String TOKEN = "if";
	
	protected LatteExpression expression;
	
	protected LatteCommandElse commandElse;
	
	public LatteCommandIf() {
		super();
		
		this.command = TOKEN;
		
		this.compound = true;
	}

	@Override
	public void addChildren(LatteToken child) {
		if ( child instanceof LatteCommandElse )
		{
			this.commandElse = (LatteCommandElse)child;
		}
		else if ( this.commandElse != null )
		{
			commandElse.addChildren(child);
		}
		else
		{
			super.addChildren(child);
		}
	}
	
	@Override
	public void parseParametrization(String expression) throws LatteException {
		
		// Parse expression
		this.expression = LatteExpression.parseExpression(expression);
	}

	@Override
	public String render(HashMap<String,Object> parameters)	{
		
		StringBuffer result = new StringBuffer();
		
		// Get list
		Object value = this.expression.resolve(parameters);
		
		if ( 
				(value instanceof Boolean && ((Boolean)value) == true) ||
				(value instanceof Integer && ((Integer)value) != 0 )
			)
		{
			// Render all children
			for(LatteToken token : this.getChildren() )
			{
				String child = token.render(parameters);
				
				result.append(child);
			}
		}
		else if ( this.commandElse != null )
		{
			result.append(this.commandElse.render(parameters));
		}
		
		return result.toString();
	}

	@Override
	public String toString() {
		String children = super.toString();
		
		return "LatteIfCommand (" + this.expression.toString() + ")" + "\r\n" + children + (this.commandElse != null ? this.commandElse.toString() : "") + "/LatteIfCommand"  + "\r\n";
	}
}