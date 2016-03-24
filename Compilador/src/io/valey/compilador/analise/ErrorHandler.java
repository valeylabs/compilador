package io.valey.compilador.analise;

import java.util.ArrayList;
import java.util.List;


public class ErrorHandler {
	
	private static ErrorHandler instance = new ErrorHandler();
	
	private List<Error> erros = new ArrayList<Error>(); 
	
	private ErrorHandler(){}
	
	public static ErrorHandler getInstance(){
		return ErrorHandler.instance;
	}
	
	public static void addError(Error e){
		ErrorHandler.getInstance().erros.add(e);
	}
}
