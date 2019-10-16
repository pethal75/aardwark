package com.modeler.application;

public class SmartServiceResponse {
	
	protected String httpCode;
	
	protected SmartAttribute responseType;
	
	public SmartServiceResponse() {
		this.httpCode = "200";
		this.responseType = new SmartAttribute("response", SmartAttribute.TYPE_TEXT);
	}

	public SmartServiceResponse(String httpCode, SmartAttribute type) {
		this.httpCode = httpCode;
		this.responseType = type;
	}

	public String getHttpCode() {
		return httpCode;
	}
	
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}
	
	public SmartAttribute getResponseType() {
		return responseType;
	}
	
	public void setResponseType(SmartAttribute responseTypeSimple) {
		this.responseType = responseTypeSimple;
	}
}
