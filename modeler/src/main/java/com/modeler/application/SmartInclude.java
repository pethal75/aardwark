package com.modeler.application;

public class SmartInclude {

	public static final String TYPE_DATA_MODEL = "data-model";
	public static final String TYPE_INTERFACE = "interface";
	
	protected String fileName, type;
	
	public SmartInclude(String fileName, String type) {
		
		this.fileName = fileName;
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
