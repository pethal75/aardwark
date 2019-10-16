package com.latte.parser;

import java.util.ArrayList;

import com.latte.LatteException;
import com.latte.tokens.LatteCommand;
import com.latte.tokens.LatteCommandElse;
import com.latte.tokens.LatteCommandForeach;
import com.latte.tokens.LatteCommandForeachClose;
import com.latte.tokens.LatteCommandIf;
import com.latte.tokens.LatteCommandIfClose;
import com.latte.tokens.LatteCommandInclude;
import com.latte.tokens.LatteCommandVar;
import com.latte.tokens.LatteFreeText;
import com.latte.tokens.LatteToken;

/**
 * Latte parser parses latte template and stores list of LatteTokens. Tokens are used
 * for template rendering.
 * 
 * {@link LatteToken}
 * 
 * @author peter
 *
 */
public class LatteParser {

	public static final int STATE_START = 0;
	public static final int STATE_READING_FREE_TEXT = 1;
	public static final int STATE_READING_COMMAND = 2;
	public static final int STATE_READING_COMMAND_PARAMETRIZATION = 3;
	public static final int STATE_READING_COMMENT = 4;
	
	public static final int COMMAND_FREE_TEXT = 0;
	public static final int COMMAND_VAR = 1;
	public static final int COMMAND_FOREACH = 2;
	public static final int COMMAND_IF = 3;
	
	int index = 0, state = STATE_START, command = -1;
	
	String templateDir, content;
	
	String currentString = "";
	LatteToken currentToken = null;
	ArrayList<LatteToken> tokenStack = new ArrayList<LatteToken>();
	
	/**
	 * Parse latte template, and stores template in internal tokens array list.
	 * 
	 * @param inputContent
	 * @return
	 * @throws LatteException
	 */
	public ArrayList<LatteToken> parse(String templateDir, String inputContent) throws LatteException
	{
		this.templateDir = templateDir;
		
		this.content = inputContent;
		
		int length = this.content.length();

		LatteToken root = new LatteFreeText("ROOT");

		this.tokenStack.add(root);
		
		// Process template content
		while (this.index < length)
		{
			char currentChar = this.content.charAt(index);
			
			if ( state == STATE_START )
			{
				this.stateStart(currentChar);
			}
			else if ( state == STATE_READING_FREE_TEXT ) 
			{
				this.stateReadingFreeText(currentChar);
			}
			else if ( state == STATE_READING_COMMENT ) 
			{
				this.stateReadingComment(currentChar);
			}
			else if ( state == STATE_READING_COMMAND ) 
			{
				this.stateReadingCommand(currentChar);
			}
			else if ( state == STATE_READING_COMMAND_PARAMETRIZATION ) 
			{
				this.stateReadingCommandParametrization(currentChar);
			}
		}
		
		// Check if there is something left
		if ( currentToken == null )
		{
			if ( !currentString.equals("") )
			{
				currentToken = new LatteFreeText();
	
				((LatteFreeText)currentToken).setText(currentString);
					
				tokenStack.get(0).addChildren(currentToken);
			}
		}
		else
		{
			// TO DO exception unfinished command
		}
		
		/*System.out.println("Tokens");
		
		for(LatteToken token : tokenStack)
		{
			System.out.println("  " + token.toString() );
		}*/
		
		return tokenStack.get(0).getChildren();
	}
	
	/**
	 * Initial state when starting parse new command
	 * 
	 * @param currentChar
	 * @throws LatteException
	 */
	protected void stateStart(char currentChar) throws LatteException
	{
		// Read first character, check if it is command or free text
		if ( currentChar == '{' && index < content.length() && content.charAt(index+1) == '*') {
			state = STATE_READING_COMMENT;
			
			currentString = "";
			
			index++;
			index++;
		}
		else if ( currentChar == '{') {
			state = STATE_READING_COMMAND;
			
			currentString = "";
			
			index++;
		}
		else {
			state = STATE_READING_FREE_TEXT;
			
			// Reading free text
			currentString = "" + currentChar;
			
			index++;
		}
	}
	
	/**
	 * State for reading free text
	 * 
	 * @param currentChar
	 * @throws LatteException
	 */
	protected void stateReadingFreeText(char currentChar) throws LatteException
	{
		if ( currentChar == '{' && this.index < this.content.length() && this.content.charAt(this.index+1) == '*' ) 
		{
			currentToken = new LatteFreeText();
			
			((LatteFreeText)currentToken).setText(currentString);
			
			currentString = "";
			
			this.addToken();
			
			state = STATE_READING_COMMENT;
			
			index++;
			index++;
		}
		else if ( currentChar == '{' && this.index < this.content.length() && !Character.isWhitespace(this.content.charAt(this.index+1)) && this.content.charAt(this.index+1) != '}' ) 
		{
			currentToken = new LatteFreeText();
			
			((LatteFreeText)currentToken).setText(currentString);
			
			currentString = "";
			
			this.addToken();
			
			state = STATE_READING_COMMAND;
			
			index++;
		}
		else
		{
			currentString += currentChar;
		
			index++;
		}
	}

	protected void stateReadingComment(char currentChar)
	{
		if ( currentChar == '*' && index < content.length() && content.charAt(index+1) == '}' )
		{
			state = STATE_START;
			
			index++;
		}
		
		index++;
	}
	
	/**
	 * State for reading command name
	 * 
	 * @param currentChar
	 * @throws LatteException
	 */
	protected void stateReadingCommand(char currentChar) throws LatteException
	{
		if ( currentChar == ' ' || currentChar == '}' )
		{
			// End of command
			if ( currentString.equals(LatteCommandVar.TOKEN) )
			{
				currentToken = new LatteCommandVar();
			}
			else if ( currentString.equals(LatteCommandForeach.TOKEN) )
			{
				currentToken = new LatteCommandForeach();
			}
			else if ( currentString.equals(LatteCommandForeachClose.TOKEN) )
			{
				currentToken = new LatteCommandForeachClose();
			}
			else if ( currentString.equals(LatteCommandIf.TOKEN) )
			{
				currentToken = new LatteCommandIf();
			}
			else if ( currentString.equals(LatteCommandElse.TOKEN) )
			{
				currentToken = new LatteCommandElse();
			}
			else if ( currentString.equals(LatteCommandIfClose.TOKEN) )
			{
				currentToken = new LatteCommandIfClose();
			}
			else if ( currentString.equals(LatteCommandInclude.TOKEN) )
			{
				currentToken = new LatteCommandInclude(this.templateDir);
			}
			else
			{
			}
			
			currentString = "";
		}
		
		if ( currentChar == ' ' )
		{
			// Start reading expression
			state = STATE_READING_COMMAND_PARAMETRIZATION;
			
			index++;
		}
		else if ( currentChar == '}')
		{
			boolean compount = false;
			
			if ( currentToken instanceof LatteCommand )
				compount = ((LatteCommand)currentToken).isCompound() || ((LatteCommand)currentToken).isClosure();
			
			// Add current token
			this.addToken();
			
			index++;
			
			if ( compount )
				this.skipEOL();
		}
		else 
		{
			currentString += currentChar;
		
			index++;
		}
	}
	
	/**
	 * State for reading command parameters
	 * 
	 * @param currentChar
	 * @throws LatteException
	 */
	protected void stateReadingCommandParametrization(char currentChar) throws LatteException
	{
		if ( currentChar == '}')
		{
			boolean compound = ((LatteCommand)currentToken).isCompound() || ((LatteCommand)currentToken).isClosure();
			
			// Setup command expression
			((LatteCommand)currentToken).parseParametrization(currentString);
			
			currentString = "";
			
			// Add current token
			this.addToken();
			
			index++;
			
			if ( compound) 
				this.skipEOL();
		}	
		else 
		{
			currentString += currentChar;
		
			index++;
		}
	}
	
	/**
	 * Add token to last parent or create new parent
	 */
	protected void addToken()
	{
		if ( currentToken instanceof LatteCommand && ((LatteCommand) currentToken).isClosure() )
		{
			// TO DO check whether closure matches starting token
			
			// Remove token from stack
			tokenStack.remove(tokenStack.size()-1);
		}
		else 
		{
			// Add token to last parent token
			tokenStack.get(tokenStack.size()-1).addChildren(currentToken);

			if ( currentToken instanceof LatteCommand && ((LatteCommand) currentToken).isCompound() )
			{
				tokenStack.add(currentToken);
			}
		}
			
		currentToken = null;
	
		state = STATE_START;
	}
	
	/**
	 * Skip one EOL character after command end
	 */
	protected void skipEOL()
	{
		if ( index < this.content.length() && this.content.charAt(index) == '\r')
			index++;
		
		if ( index < this.content.length() && this.content.charAt(index) == '\n')
			index++;
	}
}
