package io.valey.compilador.analise;

public class ErrorHandler {
	
	private static ErrorHandler instance = new ErrorHandler();
	
	private ErrorHandler(){}
	
	public static ErrorHandler getInstance(){
		return ErrorHandler.instance;
	}
}
