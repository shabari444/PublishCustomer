package com.prokarma.publishCustomer.exception;

public class ApiValidationException extends ApiSubException {
	public ApiValidationException(String object, String field, Object rejectedValue, String message) {
		super();
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ApiValidationException [object=" + object + ", field=" + field + ", rejectedValue=" + rejectedValue
				+ ", message=" + message + "]";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationException(String object, String message) {
		this.object = object;
		this.message = message;
	}
}
