package com.latte;

import java.util.ArrayList;

public class TestObject 
{
	protected ArrayList<String> params = new ArrayList<String>();
	
	protected String name = "TestObject";
	
	public TestObject()
	{
		params.add("param1");
		params.add("param2");
		params.add("param3");
	}
	
	public ArrayList<String> getParams() {
		return params;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isTitle()
	{
		return true;
	}
	
	public boolean isTitleFalse()
	{
		return false;
	}
	
	public String getTitle()
	{
		return "My test object";
	}
}
