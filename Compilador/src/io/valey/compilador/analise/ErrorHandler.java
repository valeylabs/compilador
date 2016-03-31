package io.valey.compilador.analise;

import java.util.ArrayList;
import java.util.List;


public class ErrorHandler {
	
	private static ErrorHandler instance = new ErrorHandler();
	
	private List<Error> errors = new ArrayList<Error>(); 
	
	private ErrorHandler(){}
	
	public static ErrorHandler getInstance(){
		return ErrorHandler.instance;
	}
	
	public static void addError(Error e){
		ErrorHandler.getInstance().errors.add(e);
	}

	
	public static void printErrors(){
		System.out.println("------------------------------------------------");
		System.out.println("|               ERROS ENCONTRADOS              |");
		System.out.println("------------------------------------------------");
		int i = 0;
		for (final Error e : getInstance().errors) {
			i++;
			e.printError();
		}
		if(i == 0)
			System.out.println("Não foi encontrado nenhum erro!");
	}
}
