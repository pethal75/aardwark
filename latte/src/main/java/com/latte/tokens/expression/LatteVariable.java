package com.latte.tokens.expression;

import java.util.HashMap;

public class LatteVariable extends LatteExpression {

	String variableName;
	
	public LatteVariable(String variableName) {
		super(variableName);
		
		this.variableName = variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	@Override
	public Object resolve(HashMap<String, Object> parameters) {
		return parameters.get(this.variableName);
	}

	@Override
	public String render(HashMap<String, Object> parameters) {
		
		// Get variable value
		return parameters.get(this.variableName).toString();
	}
	
	@Override
	public String toString() {
		return "LatteVariable " + this.variableName;
	}
}