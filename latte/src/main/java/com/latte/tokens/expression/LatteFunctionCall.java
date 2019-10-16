package com.latte.tokens.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class LatteFunctionCall extends LatteVariable {

	String functionName;
	
	public LatteFunctionCall(String variable, String functionName) {
		super(variable);
		
		this.functionName = functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * Invokes function call
	 * 
	 * @param parameters
	 * @return
	 */
	@Override
	public Object resolve(HashMap<String, Object> parameters)
	{
		// Get object
		Object obj = parameters.get(this.variableName);
		
		if ( obj != null )
		{
			// Call variable function
			try {
				Method method = obj.getClass().getMethod( this.functionName );
				
				Object result = method.invoke(obj);
				
				return result;
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@Override
	public String render(HashMap<String, Object> parameters) {
		
		// Invoke function call
		Object result = this.resolve(parameters);
		
		if ( result != null )
			return result.toString();
		
		return "";
	}
	
	@Override
	public String toString() {
		return "LatteFunctionCall " + this.variableName + " . " + this.functionName;
	}
}