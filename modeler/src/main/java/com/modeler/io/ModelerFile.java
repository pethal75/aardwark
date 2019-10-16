package com.modeler.io;

public class ModelerFile 
{
	public enum Type {
		TYPE_CSV_ENTITIES,
		TYPE_CSV_SERVICES
	};
	
	protected Type type;
	
	protected String fileName;
	
	public ModelerFile(Type type, String fileName) {
		this.type = type;
		this.fileName = fileName;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getFileName() {
		return fileName;
	}
}
