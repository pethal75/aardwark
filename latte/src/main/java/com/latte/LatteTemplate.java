package com.latte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.latte.parser.LatteParser;
import com.latte.tokens.LatteCommand;
import com.latte.tokens.LatteToken;

/**
 * Latte template is a template file, that is rendered by filling input parameters and
 * executing internal latte commands.
 * 
 * {@link LatteCommand}
 * 
 * @author peter
 *
 */
public class LatteTemplate {

	protected String templateFile;
	
	protected String templateDir;
	
	protected String templateContent, renderedContent;
	
	protected ArrayList<LatteToken> tokens;
	
	public LatteTemplate() {
		
	}
	
	/**
	 * Opens new template
	 * 
	 * @param templateFile
	 * @throws IOException
	 */
	public LatteTemplate(String templateFile) throws IOException, LatteException {
		this.templateFile = templateFile;

		this.readTemplate(this.templateFile);
	}

	/**
	 * Reads and validates template file
	 * 
	 * @param fileName
	 * @return
	 */
	public void readTemplate(String fileName) throws IOException, LatteException
	{
		File tplFile = new File(this.templateFile);
		
		// Read file
		this.templateContent = FileUtils.readFileToString( tplFile, "UTF-8");
		
		this.templateDir = tplFile.getParentFile().getAbsolutePath();
		
		// Create parser
		LatteParser parser = new LatteParser();
		
		// Parse template
		this.tokens = parser.parse(this.templateDir, this.templateContent);
	}
	
	/**
	 * Renders template 
	 * @param parameters
	 * @return
	 */
	public String render(HashMap<String,Object> parameters) 
	{
		StringBuffer rendered = new StringBuffer();
		
		// Go through tokens, and resolve parameters and commands
		for(LatteToken token : this.tokens)
		{
			rendered.append( token.render(parameters) );
		}

		// Return rendered content
		this.renderedContent = rendered.toString();
		
		return this.renderedContent;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		for(LatteToken token : this.tokens)
		{
			buf.append(token.toString());
		}
		
		return buf.toString();
	}
}
