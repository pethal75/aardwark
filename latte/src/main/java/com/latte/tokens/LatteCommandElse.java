package com.latte.tokens;

import java.util.HashMap;

import com.latte.LatteException;

public class LatteCommandElse extends LatteCommand {

	public static final String TOKEN = "else";
	
	public LatteCommandElse() {
		super();
		
		this.command = TOKEN;
		
		this.compound = true;
	}

	@Override
	public void parseParametrization(String expression) throws LatteException {
	}

	@Override
	public String render(HashMap<String,Object> parameters)	{
		
		StringBuffer result = new StringBuffer();
		
		// Render all children
		for(LatteToken token : this.getChildren() )
		{
			String child = token.render(parameters);
			
			result.append(child);
		}
		
		return result.toString();
	}

	@Override
	public String toString() {
		String children = super.toString();
		
		return "LatteElseCommand " + "\r\n" + children;
	}
}