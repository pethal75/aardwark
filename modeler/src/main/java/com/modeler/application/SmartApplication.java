package com.modeler.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.common.base.CaseFormat;

public class SmartApplication 
{
	protected String name, sqlSchema;
	
	protected ArrayList<SmartObject> objects = new ArrayList<SmartObject>();
	
	protected ArrayList<SmartService> services = new ArrayList<SmartService>();

	protected ArrayList<SmartInclude> includes = new ArrayList<SmartInclude>();
	
	protected HashMap<String,SmartPackage> packages = new HashMap<String,SmartPackage>();

	protected boolean appendPackageName = true;
	
	protected String outputDir;
	
	public SmartApplication() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOutputDir() {
		return outputDir;
	}
	
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
	public String getSqlSchema() {
		return sqlSchema;
	}
	
	public void setSqlSchema(String sqlSchema) {
		this.sqlSchema = sqlSchema;
	}
	
	public String getSqlName()
	{
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name).replace("__", "_");
	}
	
	public void addObject(SmartObject object)
	{
		this.objects.add(object);
		
		// add object package to list of packages
		SmartPackage pack = object.getPackage();
		
		if ( this.packages.get(pack.getPackageName()) == null )
			this.packages.put(pack.getPackageName(), pack);
	}
	
	public ArrayList<SmartObject> getObjects() {
		return objects;
	}

	public SmartObject findObjectByName(String name)
	{
		for(SmartObject object : this.objects)
			if ( object.getName().indexOf(name) > -1 )
				return object;
		
		return null;
	}
	
	public ArrayList<SmartService> getServices() {
		return services;
	}

	public SmartService findServiceByName(String name) {
		for(SmartService service : this.getServices() )
			if ( service.getName().equals(name))
				return service;
		
		return null;
	}

	public void addService(SmartService service) {
		this.services.add(service);

		// add service package to list of packages
		SmartPackage pack = service.getPackage();
		
		if ( this.packages.get(pack.getPackageName()) == null )
			this.packages.put(pack.getPackageName(), pack);
	}

	public Collection<SmartPackage> getPackages() {
		return this.packages.values();
	}
	
	public SmartPackage findPackageByName(String name) {
		for(SmartPackage pack : this.getPackages() )
			if ( pack.getPackageName().equals(name))
				return pack;
		
		return null;
	}

	public boolean isAppendPackageName() {
		return appendPackageName;
	}

	public void setAppendPackageName(boolean appendPackageName) {
		this.appendPackageName = appendPackageName;
	}

}
