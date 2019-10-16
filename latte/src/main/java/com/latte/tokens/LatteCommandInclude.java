package com.latte.tokens;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.latte.LatteException;
import com.latte.LatteTemplate;

public class LatteCommandInclude extends LatteCommand {

	public static final String TOKEN = "include";
	
	protected String currentDir;
	
	protected String fileName;
	
	protected LatteTemplate template;
	
	public LatteCommandInclude(String currentDir) {
		super();
		
		this.command = TOKEN;
		
		this.currentDir = currentDir;
	}

	@Override
	public void parseParametrization(String expression) throws LatteException {
		
		// Store file name
		this.fileName = expression;
		
		// Read template
		try {
			
			this.template = new LatteTemplate(currentDir + File.separator + this.fileName);
			
		} catch (IOException e) {
			throw new LatteException(e.getMessage());
		}
	}

	@Override
	public String render(HashMap<String,Object> parameters)	{
		
		return this.template.render(parameters);
	}

	@Override
	public String toString() {
		return "LatteIncludeCommand (" + this.fileName + ")" + "\r\n";
	}
}