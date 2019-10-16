package com.latte.tokens;

import java.util.HashMap;

import com.latte.LatteException;

public class LatteCommandForeachClose extends LatteCommand {

	public static final String TOKEN = "/foreach";

	public LatteCommandForeachClose() {
		this.command = TOKEN;
		this.closure = true;
	}
	
	@Override
	public void parseParametrization(String expression) throws LatteException {
	}

	@Override
	public String render(HashMap<String, Object> parameters) {
		return "";
	}
}