package com.latte.tokens.expression;

import java.util.HashMap;

public abstract class LatteExpression {

	protected String expressionString;
	
	public static LatteExpression parseExpression(String expression)
	{
		LatteExpressionParser parser = new LatteExpressionParser();
		
		return parser.parseExpression(expression);
	}

	public LatteExpression(String expression) {
		this.expressionString = expression;
	}
	
	public abstract Object resolve(HashMap<String,Object> parameters);

	public abstract String render(HashMap<String,Object> parameters);

	@Override
	public String toString() {
		return this.expressionString;
	}
}