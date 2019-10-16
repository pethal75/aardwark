package com.modeler.application;

import com.google.common.base.CaseFormat;

public class SmartAttribute {

	public static final String TYPE_DATETIME = "DateTime";
	public static final String TYPE_DATE = "Date";
	public static final String TYPE_TEXT = "Text";
	public static final String TYPE_LONG = "Long";
	public static final String TYPE_INTEGER = "Integer";
	public static final String TYPE_INTEGER_OBJECT = "IntegerObject";
	public static final String TYPE_OBJECT = "Object";
	public static final String TYPE_JSON = "Json";
	public static final String TYPE_BOOLEAN = "Boolean";
	public static final String TYPE_FLOAT = "Float";
	public static final String TYPE_XML_NODE = "Xml Node";
	
	protected String packageName, name, type, objectClass;
	
	public SmartAttribute(String name, String type) {
		
		this.name = name;

		int dot = name.indexOf('.');
		
		if ( dot >= 0 )
		{
			this.packageName = name.substring(0,dot);
			this.name = name.substring(dot+1);
		}
		
		this.type = type;
	}
	
	public SmartAttribute(String name, String type, String objectClass) {
		
		this.name = name;

		int dot = name.indexOf('.');
		
		if ( dot >= 0 )
		{
			this.packageName = name.substring(0,dot);
			this.name = name.substring(dot+1);
		}
		
		this.type = type;
		this.objectClass = objectClass;
	}
	
	public String fixName()
	{
		return this.name.replace("ID", "Id");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLowerCamelName()
	{
		String name = this.fixName();
		
		String javaName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);

		if ( javaName.indexOf('_') >= 0 ) {
			javaName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, javaName);
			javaName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, javaName);
		}
		
		return javaName;
	}
	
	public String getUpperCamelName()
	{
		String name = this.fixName();
		
		String javaName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);

		if ( javaName.indexOf('_') >= 0 ) {
			javaName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, javaName);
			javaName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, javaName);
		}
		
		return javaName;
	}
	
	public String getSQLName()
	{
		String name = this.fixName();
		
		String sqlName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name).replace("__", "_");
		
		if ( this.type != null && this.type.equals("Xml Node") )
		{
			if ( this.packageName != null )
				return this.packageName + "_id_" + sqlName + "_fk";
			else
				return "id_" + sqlName + "_fk";
		}
		
		return sqlName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isDate()
	{
		return this.type.equals(TYPE_DATETIME) || this.type.equals(TYPE_DATE);
	}
	
	public String getJavaType()
	{
		if ( this.type != null )
		{
			if ( this.type.toLowerCase().equals(TYPE_INTEGER.toLowerCase()) )
				return "int";
			if ( this.type.toLowerCase().equals(TYPE_INTEGER_OBJECT.toLowerCase()) )
				return "Integer";
			else if ( this.type.toLowerCase().equals(TYPE_LONG.toLowerCase()) )
				return "long";
			else if ( this.type.toLowerCase().equals(TYPE_DATETIME.toLowerCase()) )
				return "Date";
			else if ( this.type.toLowerCase().equals(TYPE_DATE.toLowerCase()) )
				return "Date";
			else if ( this.type.toLowerCase().equals(TYPE_TEXT.toLowerCase()) )
				return "String";
			else if ( this.type.toLowerCase().equals(TYPE_BOOLEAN.toLowerCase()))
				return "boolean";
			else if ( this.type.toLowerCase().equals(TYPE_FLOAT.toLowerCase()))
				return "float";
			else if ( this.type.toLowerCase().equals(TYPE_XML_NODE.toLowerCase()))
				return "long";
			else if ( this.type.toLowerCase().equals(TYPE_JSON.toLowerCase()))
				return "String";
		}
		
		return "UNKNOWN (" + this.type + ")";
	}

	public String getPostgresType()
	{
		if ( this.type != null )
		{
			if ( this.type.toLowerCase().equals(TYPE_INTEGER.toLowerCase()) )
				return "BIGINT";
			if ( this.type.toLowerCase().equals(TYPE_INTEGER_OBJECT.toLowerCase()) )
				return "BIGINT";
			else if ( this.type.toLowerCase().equals(TYPE_LONG.toLowerCase()) )
				return "BIGINT";
			else if ( this.type.toLowerCase().equals(TYPE_DATETIME.toLowerCase()) )
				return "TIMESTAMP WITH TIME ZONE";
			else if ( this.type.toLowerCase().equals(TYPE_DATE.toLowerCase()) )
				return "DATE";
			else if ( this.type.toLowerCase().equals(TYPE_TEXT.toLowerCase()) )
				return "TEXT";
			else if ( this.type.toLowerCase().equals(TYPE_BOOLEAN.toLowerCase()))
				return "BOOLEAN";
			else if ( this.type.toLowerCase().equals(TYPE_FLOAT.toLowerCase()))
				return "NUMERIC(8,2)";
			else if ( this.type.toLowerCase().equals(TYPE_XML_NODE.toLowerCase()))
				return "BIGINT";
			else if ( this.type.toLowerCase().equals(TYPE_JSON.toLowerCase()))
				return "JSON";
		}
		
		return "UNKNOWN (" + this.type + ")";
	}

	public String getObjectClass() {
		return objectClass;
	}
	
	@Override
	public String toString() {
		if ( this.type == TYPE_OBJECT )
			return this.name + " object " + this.objectClass;
		else
			return this.name + this.type;
	}
}
