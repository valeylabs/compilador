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
import java.util.Map.Entry;

import io.valey.compilador.analise.Sintatico;
import io.valey.compilador.analise.ErrorHandler;
import io.valey.compilador.analise.SymbolTable;
import io.valey.compilador.analise.Token;

public class Compilador {

	public static void main(String[] args) throws IOException {
		Sintatico as = new Sintatico("c:/users/Gabriel/desktop/meuarquivo.txt");
		as.execute();

		System.out.println("");
		System.out.println("");

		SymbolTable.printTable();

		System.out.println("");
		System.out.println("");

		ErrorHandler.printErrors();
	}
}
