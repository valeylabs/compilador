package io.valey.compilador.analise;

import java.util.ArrayList;
import java.util.List;


public class ErrorHandler {
	
	private static ErrorHandler instance = new ErrorHandler();
	
	private List<Error> erros = new ArrayList<Error>(); //criar classe de erro 
	
	private ErrorHandler(){}
	
	public static ErrorHandler getInstance(){
		return ErrorHandler.instance;
	}
}
