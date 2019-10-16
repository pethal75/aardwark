package com.latte.tokens;

import com.latte.LatteException;

/**
 * Abstract latte command class, can be one of:
 * 
 * {@link LatteCommandVar}
 * {@link LatteCommandForeach}
 * {@link LatteCommandForeachClose}
 * 
 * @author peter
 *
 */
abstract public class LatteCommand extends LatteToken {

	protected boolean compound = false, closure = false;
	
	protected String command;
	
	public LatteCommand() {
		super();
	}

	public String getCommand()
	{
		return this.command;
	}
	
	public abstract void parseParametrization(String expression) throws LatteException;
	
	public boolean isCompound()
	{
		return this.compound;
	}

	public boolean isClosure()
	{
		return this.closure;
	}
}