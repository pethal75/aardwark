package com.modeler.application;

import java.util.ArrayList;

public class SmartPackage {

	protected String packageName;
	
	protected ArrayList<SmartService> services = new ArrayList<SmartService>();

	protected ArrayList<SmartObject> objects = new ArrayList<SmartObject>();

	public SmartPackage(String packageName) {
		
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void addObject(SmartObject object)
	{
		this.objects.add(object);
	}
	
	public ArrayList<SmartObject> getObjects() {
		return this.objects;
	}

	public void addService(SmartService service)
	{
		this.services.add(service);
	}

	public ArrayList<SmartService> getServices() {
		return this.services;
	}
}
