/**
 * Alunos: 

 * Erik Zerbinatti      37816817851
 * Gabriel Malaquias    42143954840
 * Guilherme Ventura    43596980895
 * Luiz Fernando Santos 42547332833 
 * 
 * Curso: Ciência da Computação 7º Semestre
 */

package io.valey.compilador;

import java.io.IOException;
import io.valey.compilador.analise.Sintatico;
import io.valey.compilador.analise.ErrorHandler;
import io.valey.compilador.analise.SymbolTable;

public class Compilador {

	public static void main(String[] args) throws IOException {
		Sintatico as = new Sintatico("c:/users/gabriel.malaquias/desktop/meuarquivo.txt");
		as.execute();

		System.out.println("");
		System.out.println("");

		SymbolTable.printTable();

		System.out.println("");
		System.out.println("");

		ErrorHandler.printErrors();
	}
}
