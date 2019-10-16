package com.modeler.application;

import java.util.ArrayList;

import com.google.common.base.CaseFormat;

/**
 * Smart object represents universal object to be used for applications model. Smart object
 * can contain list of attributes and functions. Based on SmartObject target applications
 * are generated.
 * 
 * @author peter
 *
 */
public class SmartObject {

	protected SmartPackage smartPackage;
	
	protected String name;
	protected ArrayList<SmartAttribute> attributes = new ArrayList<SmartAttribute>();
	protected ArrayList<SmartFunction> functions= new ArrayList<SmartFunction>();
	
	public SmartObject(SmartPackage smartPackage, String name)
	{
		this.smartPackage = smartPackage;
		this.name = name;
		
		this.smartPackage.addObject(this);
	}
	
	public SmartPackage getPackage() {
		return smartPackage;
	}
	
	public String getPackageName() {
		return smartPackage.getPackageName().toLowerCase();
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

	public String getJavaEntityName()
	{
		return this.name + "Entity";
	}
	
	public String getJavaEntityRepositoryName()
	{
		return this.name + "EntityRepository";
	}
	
	public String getJavaXmlName()
	{
		return this.name + "Xml";
	}
	
	public String getJavaXmlCollectionName()
	{
		return this.name + "Xml";
	}
	
	public String getSQLName()
	{
		String name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name).replace("__", "_");
		
		return name;
	}
	
	public ArrayList<SmartAttribute> getAttributes()
	{
		return this.attributes;
	}

	public void addAttribute(SmartAttribute attribute)
	{
		this.attributes.add(attribute);
	}

	public void addAttribute(int index, SmartAttribute attribute)
	{
		this.attributes.add(index, attribute);
	}

	/**
	 * Get primary key attribute
	 * 
	 * @return
	 */
	public String getKeyAttributeSQLName()
	{
		for(SmartAttribute attr : this.attributes )
			if ( attr.getName().startsWith("id") )
				return attr.getSQLName();
		
		return "";
	}
	
	public ArrayList<SmartFunction> getFunctions()
	{
		return this.functions;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append(this.getName() + System.getProperty("line.separator"));
		
		for(SmartAttribute attr: this.getAttributes())
			buf.append("  " + attr.getName() + System.getProperty("line.separator"));
		
		return buf.toString();
	}
}
