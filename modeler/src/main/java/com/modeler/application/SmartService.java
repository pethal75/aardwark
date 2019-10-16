package com.modeler.application;

import java.util.ArrayList;

import com.google.common.base.CaseFormat;

/**
 * Smart service represents API definition.
 * 
 * @author peter
 *
 */
public class SmartService {

	protected SmartPackage smartPackage;
	protected String name, path, description, method = "GET";
	protected ArrayList<SmartAttribute> queryParameters = new ArrayList<SmartAttribute>();
	protected ArrayList<SmartAttribute> pathParameters = new ArrayList<SmartAttribute>();
	protected SmartServiceResponse response = new SmartServiceResponse();
	
	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	
	public SmartService(String name)
	{
		this.name = name;
	}
	
	public SmartService(SmartPackage smartPackage, String name, String path, String description, String method)
	{
		this.smartPackage = smartPackage;
		this.name = name;
		this.path = path;
		this.description = description;
		this.method = method;
		
		this.smartPackage.addService(this);
	}

	public SmartPackage getPackage() {
		return this.smartPackage;
	}
	
	public String getPackageName() {
		return smartPackage.getPackageName();
	}
	
	public void setPackageName(String packageName) {
		this.smartPackage.setPackageName(packageName);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath()
	{
		return this.path;
	}
	
	public String getMethod()
	{
		return this.method.toLowerCase();
	}

	public boolean isPostMethod()
	{
		return this.method.equals(METHOD_POST);
	}
	
	public String getUpperCamelName()
	{
		String javaName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, this.name);

		if ( javaName.indexOf('_') >= 0 )
			javaName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, javaName);

		return javaName;
	}
	
	public String getLowerCamelName()
	{
		String javaName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, this.name);

		if ( javaName.indexOf('_') >= 0 )
			javaName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, javaName);

		return javaName;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean hasParameters()
	{
		return this.queryParameters.size() > 0 || this.pathParameters.size() > 0;
	}
	
	public ArrayList<SmartAttribute> getQueryParameters()
	{
		return this.queryParameters;
	}

	public void addQueryParameter(String name) {
		this.queryParameters.add(new SmartAttribute(name, SmartAttribute.TYPE_TEXT));
		
	}

	public ArrayList<SmartAttribute> getPathParameters()
	{
		return this.pathParameters;
	}

	public void addPathParameter(String name) {
		this.pathParameters.add(new SmartAttribute(name, SmartAttribute.TYPE_TEXT));
		
	}

	public SmartServiceResponse getResponse()
	{
		return this.response;
	}

	public void setResponse( SmartServiceResponse response ) {
		this.response = response;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append(this.getName() + " service " + System.getProperty("line.separator"));

		for(SmartAttribute attr : this.getPathParameters() )
			buf.append( "  Path: " + attr.getName() + System.getProperty("line.separator"));
		
		for(SmartAttribute attr : this.getQueryParameters() )
			buf.append( "  Query: " + attr.getName() + System.getProperty("line.separator"));

		buf.append("  Response: " + this.response.getResponseType().toString()  + System.getProperty("line.separator") );
		
		return buf.toString();
	}
}
