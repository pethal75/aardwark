package com.latte.tokens;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generic latte template token, which means part of template that can be rendered. Can
 * be one of:
 * 
 * {@link LatteFreeText}
 * {@link LatteCommand}
 * 
 * @author peter
 *
 */
public class LatteToken {

	ArrayList<LatteToken> children = new ArrayList<LatteToken>();
	
	LatteToken parent = null;
	
	public LatteToken() {
	}
	
	public void addChildren(LatteToken child)
	{
		this.children.add(child);
		
		child.setParent(this);
	}
	
	public LatteToken getParent()
	{
		return this.parent;
	}
	
	public void setParent(LatteToken parent) {
		this.parent = parent;
	}
	
	public String render(HashMap<String,Object> parameters)
	{
		StringBuffer str = new StringBuffer();
		
		for(LatteToken token : children )
			str.append(token.render(parameters));
		
		return str.toString();
	}

	public ArrayList<LatteToken> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		for(LatteToken token : children )
			str.append(token.toString());
		
		return str.toString();
	}
}