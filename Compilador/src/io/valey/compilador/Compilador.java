/**
 * Alunos: 
 * Erik Zerbinatti      37816817851
 * Gabriel Malaquias    42143954840
 * Guilherme Ventura    43596980895
 * Luiz Fernando Santos 42547332833 
 * 
 * Curso: Ci�ncia da Computa��o 7� Semestre
 */

package io.valey.compilador;

import java.io.IOException;
import java.util.Map.Entry;

import io.valey.compilador.analise.AnalisadorSintatico;
import io.valey.compilador.analise.ErrorHandler;
import io.valey.compilador.analise.SymbolTable;
import io.valey.compilador.analise.Token;

public class Compilador {

	public static void main(String[] args) throws IOException {
		AnalisadorSintatico as = new AnalisadorSintatico("c:/users/gabriel.malaquias/desktop/meuarquivo.txt");
		as.execute();

		System.out.println("");
		System.out.println("");

		SymbolTable.printTable();

		System.out.println("");
		System.out.println("");

		ErrorHandler.printErrors();
	}
}
