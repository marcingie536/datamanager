package com.zaju.datamanager.exceptions;

public class DataConvertException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final String INVALID_FILE_LINE_MESSAGE = "File line items count is different than input and output size";
	
	public DataConvertException(String message) {
		super(message);
	}
	
	public static DataConvertException getInvalidFileLineException() {
		return new DataConvertException(INVALID_FILE_LINE_MESSAGE);
	}
}
