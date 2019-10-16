package com.latte.tokens.expression;

import java.util.HashMap;

public class LatteOperator extends LatteExpression {

	public LatteOperator(String expression) {
		super(expression);
	}

	@Override
	public Object resolve(HashMap<String, Object> parameters) {
		return null;
	}
	
	@Override
	public String render(HashMap<String, Object> parameters) {
		return "";
	}
}