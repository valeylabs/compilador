/**
 * Alunos: 
 * Erik Zerbinatti      37816817851
 * Gabriel Malaquias    42143954840
 * Guilherme Ventura    43596980895
 * Luiz Fernando Santos 42547332833 
 * 
 * Curso: Ci�ncia da Computa��o 7� Semestre
 */

package io.valey.compilador.analise;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

	private static ErrorHandler instance = new ErrorHandler();

	private List<Error> errors = new ArrayList<Error>();

	private ErrorHandler() {
	}

	public static ErrorHandler getInstance() {
		return ErrorHandler.instance;
	}

	public static void addError(Error e) {
		ErrorHandler.getInstance().errors.add(e);
	}

	public static void printErrors() {
		System.out.println("------------------------------------------------");
		System.out.println("|               ERROS ENCONTRADOS              |");
		System.out.println("------------------------------------------------");
		int i = 0;
		for (final Error e : getInstance().errors) {
			i++;
			e.printError();
		}
		if (i == 0)
			System.out.println("N�o foi encontrado nenhum erro!");
	}
}
