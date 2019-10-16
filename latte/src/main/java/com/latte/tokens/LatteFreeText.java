package com.latte.tokens;

import java.util.HashMap;

public class LatteFreeText extends LatteToken {

	String text = "";
	
	public LatteFreeText() {
		super();
	}

	public LatteFreeText(String text) {
		super();
		
		this.setText(text);
	}

	public void setText(String text) {
		this.text = text;
	}

	public String render(HashMap<String,Object> parameters)	{
		return this.text;
	}

	@Override
	public String toString() {
		String children = super.toString();
		
		return "LatteFreeText \"" + this.text.replace("\r", " ").replace("\n", "EOL") + "\"" + "\r\n" + children;
	}
}