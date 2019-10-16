package com.latte.tokens.expression;

/**
 * Expression parser
 * 
 * @author peter
 *
 * TO DO Operators empty ! > < <= >= in Expression
 * TO DO Implement multiple function call $object.getAttribute().getName()
 */
public class LatteExpressionParser {

	public static final int STATE_START = 0;
	public static final int STATE_VARIABLE_NAME = 1;
	
	int index = 0;
	int state = STATE_START;
	String currentString = "";
	String expression;
	LatteExpression result = null;
	
	public LatteExpression parseExpression(String exp)
	{
		expression = exp.trim().replace(" ", "");
		
		while ( index < expression.length() )
		{
			if ( state == STATE_START )
				parseStart();
			else if ( state == STATE_VARIABLE_NAME )
				parseVariableName();
		}

		return result;
	}
	
	protected void parseStart()
	{
		if ( expression.charAt(index) == '$')
		{
			state = STATE_VARIABLE_NAME;
			
			index++;
		}
	}

	protected void parseVariableName()
	{
		if ( expression.charAt(index) == '(' || expression.charAt(index) == ')' )
		{
			index++;
		}
		else if ( Character.isJavaIdentifierPart(expression.charAt(index)) || expression.charAt(index) == '.')
		{
			currentString += expression.charAt(index);
			
			index++;
		}
		
		if ( index == expression.length() )
		{
			if ( currentString.indexOf(".") > 0 )
			{
				String variable = currentString.split("\\.")[0];
				String function = currentString.split("\\.")[1];
				
				// Function call
				result = new LatteFunctionCall(variable,function);
			}
			else
			{
				// Variable
				result = new LatteVariable(currentString);
			}
		}
	}
}