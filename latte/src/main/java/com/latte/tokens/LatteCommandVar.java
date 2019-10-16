package com.latte.tokens;

import java.util.HashMap;

import com.latte.tokens.expression.LatteExpression;

public class LatteCommandVar extends LatteCommand {

	public static final String TOKEN = "var";
	
	protected LatteExpression expression;
	
	public LatteCommandVar() {
		super();
		
		this.command = TOKEN;
	}

	@Override
	public void parseParametrization(String expression) {
		this.expression = LatteExpression.parseExpression(expression);
	}

	@Override
	public String render(HashMap<String,Object> parameters)	{
		return this.expression.render(parameters);
	}

	@Override
	public String toString() {
		return "LatteVarCommand (" + this.expression.toString() + ")"  + "\r\n";
	}
}